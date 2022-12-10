package uz.gita.newsapp.domain.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.remote.model.Article

interface TopHeadlinesUseCase {

    suspend fun topHeadlines(category: String): Flow<List<Article>>
}