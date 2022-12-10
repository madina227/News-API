package uz.gita.newsapp.domain.useCase.impl

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.remote.model.Article
import uz.gita.newsapp.data.repository.NewsRepository
import uz.gita.newsapp.domain.useCase.SearchUseCase
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(private val repository: NewsRepository): SearchUseCase {

    override suspend fun search(search: String): Flow<PagingData<Article>> = repository.search(search)
}