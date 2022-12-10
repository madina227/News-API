package uz.gita.newsapp.presenter

import uz.gita.newsapp.data.local.room.NewsEntity

interface ArticleViewModel {
//    val checkFlow: NewsEntity?

    suspend fun check(title: String):Boolean

    fun bookmarkArticle(news: NewsEntity)

    fun unBookmarkArticle(news: NewsEntity)
}