package com.abaferas.devassist.domain.models


data class BookSearch(
    val total: String,
    val page: String,
    val books: List<Book>
)
