package com.abaferas.devassist.data.mappers

import com.abaferas.devassist.data.model.books.BookDto
import com.abaferas.devassist.data.model.books.NewBookDto
import com.abaferas.devassist.domain.models.Book
import com.abaferas.devassist.domain.models.NewBook

fun NewBookDto.toDomain(): NewBook{
    return NewBook(
        total = this.total ?: "0",
        books = this.books?.map {
            it.toDomain()
        } ?: emptyList()
    )
}

fun BookDto.toDomain(): Book {
    return Book(
        title = this.title ?: "",
        subtitle = this.subtitle ?: "",
        isbn13 = this.isbn13 ?: "",
        price = this.price ?: "",
        image = this.image ?: "",
        url = this.url ?: "",
    )
}