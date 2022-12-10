package uz.gita.newsapp.domain.useCase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.local.room.NewsEntity
import uz.gita.newsapp.data.repository.NewsRepository
import uz.gita.newsapp.domain.useCase.SearchBookmarksUseCase
import javax.inject.Inject

class SearchBookmarksUseCaseImpl @Inject constructor(private val repository: NewsRepository): SearchBookmarksUseCase {
    override suspend fun searchBookmarks(search: String): Flow<List<NewsEntity>> =
        repository.searchBookmarks(search)
}