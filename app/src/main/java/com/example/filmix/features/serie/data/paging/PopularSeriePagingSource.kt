package com.example.filmix.features.serie.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.filmix.core.Constants.PAGINGSOURCE_TAG
import com.example.filmix.features.serie.data.model.SerieDto
import com.example.filmix.features.serie.data.remote.SerieService

class PopularSeriePagingSource(
    private val serieService: SerieService
) : PagingSource<Int, SerieDto>() {

    override fun getRefreshKey(state: PagingState<Int, SerieDto>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SerieDto> {
        val currentPage = params.key ?: 1
        return try {
            val response = serieService.getPopularSeries(page = currentPage)
            val endOfPaginationReached = response.series.isEmpty()

            if (response.series.isNotEmpty()) {
                LoadResult.Page(
                    data = response.series,
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
            Log.e(PAGINGSOURCE_TAG, e.stackTraceToString())
            LoadResult.Error(e)
        }
    }
}