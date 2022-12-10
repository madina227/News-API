package uz.gita.newsapp.presenter

import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.local.room.NewsEntity

interface BookmarksViewModel {

    val bookmarksList: Flow<List<NewsEntity>>
    val searchBookmarksList: Flow<List<NewsEntity>>

//    fun searchBookmarks(search: String)

//    suspend fun check(title: String)
}