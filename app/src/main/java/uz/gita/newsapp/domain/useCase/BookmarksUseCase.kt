package uz.gita.newsapp.domain.useCase

import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.local.room.NewsEntity

interface BookmarksUseCase {

    suspend fun bookmarks(): Flow<List<NewsEntity>>

}