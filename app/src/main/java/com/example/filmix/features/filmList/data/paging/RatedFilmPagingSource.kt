package com.example.filmix.features.filmList.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.filmix.core.Constants
import com.example.filmix.features.filmList.data.model.FilmDto
import com.example.filmix.features.filmList.data.remote.FilmService

class RatedFilmPagingSource(
    private val filmService: FilmService
) : PagingSource<Int, FilmDto>() {

    override fun getRefreshKey(state: PagingState<Int, FilmDto>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmDto> {
        val currentPage = params.key ?: 1
        return try {
            val response = filmService.getTopRatedFilms(page = currentPage)
            val endOfPaginationReached = response.films.isEmpty()
            if (response.films.isNotEmpty()) {
                LoadResult.Page(
                    data = response.films,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            Log.e(Constants.PAGINGSOURCE_TAG, e.message.toString())
            LoadResult.Error(e)
        }
    }
}