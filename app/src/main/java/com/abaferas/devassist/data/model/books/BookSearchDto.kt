package com.abaferas.devassist.data.model.books


import com.google.gson.annotations.SerializedName

data class BookSearchDto(
    @SerializedName("total")
    val total: String? = "",
    @SerializedName("page")
    val page: String? = "",
    @SerializedName("books")
    val bookDtos: List<BookDto>? = emptyList()
)