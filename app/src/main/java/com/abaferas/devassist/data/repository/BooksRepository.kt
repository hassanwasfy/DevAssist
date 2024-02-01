package com.abaferas.devassist.data.repository

import com.abaferas.devassist.domain.models.BookDetails
import com.abaferas.devassist.domain.models.BookSearch
import com.abaferas.devassist.domain.models.NewBook

interface BooksRepository {
    suspend fun getNewBooks(): NewBook
    suspend fun searchBooksByQuery(query: String): BookSearch
    suspend fun getBookDetailsByIsbn(id: String): BookDetails
}