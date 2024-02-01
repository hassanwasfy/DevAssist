package com.abaferas.devassist.data.repository

import com.abaferas.devassist.data.mappers.toDomain
import com.abaferas.devassist.data.service.BooksService
import com.abaferas.devassist.data.utils.wrapResponse
import com.abaferas.devassist.domain.models.BookDetails
import com.abaferas.devassist.domain.models.BookSearch
import com.abaferas.devassist.domain.models.NewBook
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val booksService: BooksService
) : BooksRepository {
    override suspend fun getNewBooks(): NewBook {
        return wrapResponse { booksService.getNewBooks() }.toDomain()
    }

    override suspend fun searchBooksByQuery(query: String): BookSearch {
        return wrapResponse { booksService.searchBooksByQuery(query) }.toDomain()
    }

    override suspend fun getBookDetailsByIsbn(id: String): BookDetails {
        return wrapResponse { booksService.getBookDetailsByIsbn(id) }.toDomain()
    }
}