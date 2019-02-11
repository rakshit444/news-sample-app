package com.rakshitjain.domain.entities

data class NewsPublisherEntity(
        var id: Int,
        var name: String? = null,
        var description: String? = null,
        var url: String? = null,
        var category: String? = null)