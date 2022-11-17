package com.example.filmix.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.filmix.data.local.FilmDatabase
import com.example.filmix.data.model.FilmDto
import com.example.filmix.data.model.FilmRemoteKeys
import com.example.filmix.data.remote.FilmService
import javax.inject.Inject

/*
  O RemoteMediator auxilia a paginar os dados
  e coloca-los em cache no banco de dados local.
  Ele vai ser notificado para pedir mais páginas
  de dados quando os dados do Database forem
  consumidos pelo usuário.
  */
@ExperimentalPagingApi
class RemoteMediator @Inject constructor(
    private val filmService: FilmService,
    private val filmDatabase: FilmDatabase
) : RemoteMediator<Int, FilmDto>() {

    private val filmDao = filmDatabase.filmDao()
    private val filmRemoteKeysDao = filmDatabase.filmRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FilmDto>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {

                /*  O Refresh será acionado no primeiro request da Api.
                    Como o nextPage na tabela do FilmRemoteKeys será nulo,
                    o valor inicial do currentPage será 1, caso contrário,
                    o valor do currentPage será o nextPage menos 1. */
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                /*  O Prepend carrega no INÍCIO do PagingData, que no caso,
                    seria o primeiro item da Pagina de dados enviados pela Api. */
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                /*  O Append carrega no FINAL do PagingData, que no caso,
                    seria o último item da Pagina de dados enviados pela Api. */
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            /*
            * Quando o LoadType.Refresh é ativo nós temos acesso ao currentPage,
            * então, conseguimos fazer nossa requisição à API que nos devolve um
            * FilmResponse que, por sua vez, temos acesso a lista de filmes.
            * */
            val response = filmService.getPopularFilms(page = currentPage)
            val films = response.films

            /*
            * Quando for feito uma chamada à Api e ela nos devolver uma página
            * vazia significa que não existe mais dados a serem carregados. Em
            * outras palavras, o final da paginação foi alcançado.
            * */
            val endOfPaginationReached = films.isEmpty()

            /*
            * Aqui estamos calculando os valores da página anterior e posterior em
            * relação da página atual.
            * Caso a página atual seja a inicial, a página anterior a ela será nula,
            * já que não podemos ter a página 0 ou -1. Caso contrário, será o valor
            * da página atual menos 1.
            * Caso o final da paginação for alcançado, a próxima página será nula,
            * já que não teremos mais dados para serem carregados da API. Caso contrário,
            * será a página atual mais 1.
            * */
            val prevPage = if (currentPage == 1) null else currentPage.minus(1)
            val nextPage = if (endOfPaginationReached) null else currentPage.plus(1)

            /*
            * Quando o RemoteMediator é iniciado nós precisamos invalidar os dados
            * existentes nas tabelas de films e chaves para não misturar dados antigos
            * com os novos dados e somente depois disso que podemos salvar os novos dados
            * em nosso banco de dados.
            * */
            filmDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    filmDao.deleteAllFilms()
                    filmRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = films.map { filmDto ->
                    FilmRemoteKeys(
                        id = filmDto.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                filmRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                filmDao.addFilms(films = films)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    // Função para pegar a chave atual
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, FilmDto>
    ): FilmRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                filmRemoteKeysDao.getRemoteKey(id = id)
            }
        }
    }

    // Função para pegar a chave do primeiro item da página
    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, FilmDto>
    ): FilmRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { filmDto ->
                filmRemoteKeysDao.getRemoteKey(id = filmDto.id)
            }
    }

    // Função para pegar a chave do último item da página
    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, FilmDto>
    ): FilmRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { filmDto ->
                filmRemoteKeysDao.getRemoteKey(id = filmDto.id)
            }
    }
}
