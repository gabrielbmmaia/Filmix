package com.example.filmix.features.filmList.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.filmix.core.Constants.PAGINGSOURCE_TAG
import com.example.filmix.features.shared.data.model.MediaDto
import com.example.filmix.features.filmList.data.remote.FilmService

/**
 * Paginação da lista de filmes "Em Breve"
 * */
class SoonFilmPagingSource(
    private val filmService: FilmService
) : PagingSource<Int, MediaDto>() {

    override fun getRefreshKey(state: PagingState<Int, MediaDto>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaDto> {
        val currentPage = params.key ?: 1
        return try {
            val response = filmService.getSoonFilms(page = currentPage)
            LoadResult.Page(
                data = response.medias,
                prevKey = if (currentPage == 1) null else currentPage -1,
                nextKey = if (response.medias.isEmpty()) null else currentPage +1
            )
        } catch (e: Exception) {
            Log.e(PAGINGSOURCE_TAG, e.message.toString())
            LoadResult.Error(e)
        }
    }
}