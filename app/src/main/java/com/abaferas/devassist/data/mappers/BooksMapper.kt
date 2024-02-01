package com.abaferas.devassist.data.mappers

import com.abaferas.devassist.data.model.books.BookDetailsDto
import com.abaferas.devassist.data.model.books.BookDto
import com.abaferas.devassist.data.model.books.BookSearchDto
import com.abaferas.devassist.data.model.books.NewBookDto
import com.abaferas.devassist.data.model.books.Pdf
import com.abaferas.devassist.domain.models.Book
import com.abaferas.devassist.domain.models.BookDetails
import com.abaferas.devassist.domain.models.BookSearch
import com.abaferas.devassist.domain.models.NewBook
import com.google.gson.annotations.SerializedName

fun NewBookDto.toDomain(): NewBook {
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

fun BookSearchDto.toDomain(): BookSearch {
    return BookSearch(
        total = this.total ?: "",
        page = this.page ?: "",
        books = this.bookDtos?.map {
            it.toDomain()
        } ?: emptyList()
    )
}

fun BookDetailsDto.toDomain(): BookDetails {
    return BookDetails(
        error = this.error ?: "",
        title = this.title ?: "",
        subtitle = this.subtitle ?: "",
        authors = this.authors ?: "",
        publisher = this.publisher ?: "",
        isbn10 = this.isbn10 ?: "",
        isbn13 = this.isbn13 ?: "",
        pages = this.pages ?: "",
        year = this.year ?: "",
        rating = this.rating ?: "",
        desc = this.desc ?: "",
        price = this.price ?: "",
        image = this.image ?: "",
        url = this.url ?: "",
        pdf = Pdf()
    )
}