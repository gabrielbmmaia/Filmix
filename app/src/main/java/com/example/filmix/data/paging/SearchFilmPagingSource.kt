package com.example.filmix.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.filmix.data.model.films.FilmDto
import com.example.filmix.data.remote.TMDBService

class SearchFilmPagingSource(
    private val searchService: TMDBService,
    private val query: String
) : PagingSource<Int, FilmDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmDto> {
        val currentPage = params.key ?: 1

        return try {
            val response = searchService.searchFilms(page = currentPage, query = query)

            LoadResult.Page(
                data = response.films,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.films.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FilmDto>): Int? {
        return state.anchorPosition
    }
}