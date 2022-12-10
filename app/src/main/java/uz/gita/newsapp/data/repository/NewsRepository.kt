package uz.gita.newsapp.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.local.room.NewsEntity
import uz.gita.newsapp.data.remote.model.Article

interface NewsRepository {

    fun latestNews(): Flow<PagingData<Article>>

    suspend fun topHeadlines(category: String): Flow<List<Article>>

    fun search(search: String): Flow<PagingData<Article>>

    fun bookmarks(): Flow<List<NewsEntity>>

    fun searchBookmarks(search: String): Flow<List<NewsEntity>>

    suspend fun bookmarkArticle(news: NewsEntity)

    suspend fun unBookmarkArticle(news: NewsEntity)

    suspend fun check(title: String): NewsEntity?
}