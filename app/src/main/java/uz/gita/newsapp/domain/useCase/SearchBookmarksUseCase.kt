package uz.gita.newsapp.domain.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.local.room.NewsEntity

interface SearchBookmarksUseCase {
    suspend fun searchBookmarks(search: String): Flow<List<NewsEntity>>
}