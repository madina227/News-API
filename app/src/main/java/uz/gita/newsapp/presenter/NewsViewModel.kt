package uz.gita.newsapp.presenter

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.local.room.NewsEntity
import uz.gita.newsapp.data.remote.model.Article

interface NewsViewModel {

    val latestNewsList: Flow<PagingData<Article>>
    val topHeadlinesList: Flow<List<Article>>

//    fun latestNews()
//
    fun topHeadlines(category: String)
}