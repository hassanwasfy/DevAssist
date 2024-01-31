package com.abaferas.devassist.data.repository

import com.abaferas.devassist.data.model.books.BookDetailsDto
import com.abaferas.devassist.data.model.books.BookSearchDto
import com.abaferas.devassist.data.model.books.NewBookDto

interface BooksRepository {
    suspend fun getNewBooks(): NewBookDto
    suspend fun searchBooksByQuery(query: String): BookSearchDto
    suspend fun getBookDetailsByIsbn(id: String): BookDetailsDto
}