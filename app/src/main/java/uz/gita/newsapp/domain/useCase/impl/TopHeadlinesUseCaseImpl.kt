package uz.gita.newsapp.domain.useCase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.remote.model.Article
import uz.gita.newsapp.data.repository.NewsRepository
import uz.gita.newsapp.domain.useCase.TopHeadlinesUseCase
import javax.inject.Inject

class TopHeadlinesUseCaseImpl @Inject constructor(private val repository: NewsRepository) :
    TopHeadlinesUseCase {
    override suspend fun topHeadlines(category: String): Flow<List<Article>> =
        repository.topHeadlines(category)
}