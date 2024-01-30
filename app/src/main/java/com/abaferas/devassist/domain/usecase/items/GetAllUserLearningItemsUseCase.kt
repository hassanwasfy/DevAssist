package com.abaferas.devassist.domain.usecase.items

import com.abaferas.devassist.data.repository.LearningItemsRepository
import com.abaferas.devassist.domain.models.LearningItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUserLearningItemsUseCase @Inject constructor(
    private val learningItemsRepository: LearningItemsRepository
) {
    suspend operator fun invoke(userId: String): Flow<List<LearningItem>> {
        return learningItemsRepository.getAllLearningItems(userId)
    }
}