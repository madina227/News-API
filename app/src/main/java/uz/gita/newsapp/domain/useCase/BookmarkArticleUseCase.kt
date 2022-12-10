package uz.gita.newsapp.domain.useCase

import uz.gita.newsapp.data.local.room.NewsEntity

interface BookmarkArticleUseCase {
    suspend fun bookmarkArticle(news: NewsEntity)
}