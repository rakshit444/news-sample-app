package com.rakshitjain.presentation.entities

data class NewsPublisher(
        var id: Int,
        var name: String? = null,
        var description: String? = null,
        var url: String? = null,
        var category: String? = null)