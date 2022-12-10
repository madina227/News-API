package uz.gita.newsapp.domain.useCase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.local.room.NewsEntity
import uz.gita.newsapp.data.repository.NewsRepository
import uz.gita.newsapp.domain.useCase.BookmarksUseCase
import javax.inject.Inject

class BookmarksUseCaseImpl @Inject constructor(private val repository: NewsRepository) :
    BookmarksUseCase {
    override suspend fun bookmarks(): Flow<List<NewsEntity>> = repository.bookmarks()
}