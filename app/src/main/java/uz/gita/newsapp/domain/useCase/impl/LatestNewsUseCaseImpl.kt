package uz.gita.newsapp.domain.useCase.impl

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.remote.model.Article
import uz.gita.newsapp.data.repository.NewsRepository
import uz.gita.newsapp.domain.useCase.LatestNewsUseCase
import javax.inject.Inject

class LatestNewsUseCaseImpl @Inject constructor(private val repository: NewsRepository) :
    LatestNewsUseCase {
    override suspend fun latestNews(): Flow<PagingData<Article>> = repository.latestNews()
}