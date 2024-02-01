package com.abaferas.devassist.domain.usecase.books

import com.abaferas.devassist.data.repository.BooksRepository
import com.abaferas.devassist.data.repository.LearningItemsRepository
import com.abaferas.devassist.domain.models.Book
import com.abaferas.devassist.domain.models.LearningItem
import com.abaferas.devassist.domain.models.NewBook
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class GetNewBooksUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {
    suspend operator fun invoke(): List<Book> {
        return booksRepository.getNewBooks().books
    }
}