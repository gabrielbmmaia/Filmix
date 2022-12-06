package com.example.filmix.features.trending.data.model

/**
 * A Feature de Trending só está sendo feita para pegar
 * o ID do item, e depois o repositório faz um request
 * dos detalhes desse ID
 * */

data class TrendingItemDto(
    val id: Int
)
