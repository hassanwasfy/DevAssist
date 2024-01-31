package com.abaferas.devassist.data.repository

import com.abaferas.devassist.data.model.books.BookDetailsDto
import com.abaferas.devassist.data.model.books.BookSearchDto
import com.abaferas.devassist.data.model.books.NewBookDto
import com.abaferas.devassist.data.service.BooksService
import com.abaferas.devassist.data.utils.wrapRequest
import com.abaferas.devassist.data.utils.wrapResponse
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val booksService: BooksService
) : BooksRepository {
    override suspend fun getNewBooks(): NewBookDto {
        return wrapResponse { booksService.getNewBooks() }
    }

    override suspend fun searchBooksByQuery(query: String): BookSearchDto {
        return wrapResponse { booksService.searchBooksByQuery(query) }
    }

    override suspend fun getBookDetailsByIsbn(id: String): BookDetailsDto {
        return wrapResponse { booksService.getBookDetailsByIsbn(id) }
    }
}