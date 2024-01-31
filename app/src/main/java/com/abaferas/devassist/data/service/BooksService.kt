package com.abaferas.devassist.data.service

import com.abaferas.devassist.data.model.books.BookDetailsDto
import com.abaferas.devassist.data.model.books.BookSearchDto
import com.abaferas.devassist.data.model.books.NewBookDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksService {
    @GET("new")
    suspend fun getNewBooks(): Response<NewBookDto>
    @GET("search/{query}")
    suspend fun searchBooksByQuery(@Path("query") query: String): Response<BookSearchDto>
    @GET("books/{isbn13}")
    suspend fun getBookDetailsByIsbn(@Path("isbn13") id: String): Response<BookDetailsDto>
}