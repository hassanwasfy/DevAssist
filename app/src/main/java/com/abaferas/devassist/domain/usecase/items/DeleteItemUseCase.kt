package com.abaferas.devassist.domain.usecase.items

import com.abaferas.devassist.data.repository.LearningItemsRepository
import com.abaferas.devassist.domain.models.LearningItem
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(
    private val learningItemsRepository: LearningItemsRepository
) {
    suspend operator fun invoke(learningItem: LearningItem): Task<QuerySnapshot?> {
        return learningItemsRepository.deleteLearningItem(learningItem)
    }
}