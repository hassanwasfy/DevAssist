package com.abaferas.devassist.data.model.books


import com.google.gson.annotations.SerializedName

data class NewBookDto(
    @SerializedName("total")
    val total: String? = "",
    @SerializedName("books")
    val books: List<BookDto>? = emptyList()
)