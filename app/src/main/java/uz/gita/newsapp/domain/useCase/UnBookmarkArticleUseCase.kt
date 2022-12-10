package uz.gita.newsapp.domain.useCase

import uz.gita.newsapp.data.local.room.NewsEntity

interface UnBookmarkArticleUseCase {
    suspend fun unBookmarkArticle(news: NewsEntity)
}