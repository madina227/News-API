package uz.gita.newsapp.domain.useCase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.remote.model.Article

interface LatestNewsUseCase {
    suspend fun latestNews(): Flow<PagingData<Article>>
}