package com.example.filmix.features.shared.presentation

import androidx.paging.PagingData
import com.example.filmix.features.shared.domain.model.Media

sealed class MediaState {
    class Success(val data: PagingData<Media>): MediaState()
    class Error(val message: String): MediaState()
    object Loading: MediaState()
    object Empty: MediaState()
}