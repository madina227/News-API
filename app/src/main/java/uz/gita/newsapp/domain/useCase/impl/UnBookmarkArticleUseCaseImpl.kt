package uz.gita.newsapp.domain.useCase.impl

import uz.gita.newsapp.data.local.room.NewsEntity
import uz.gita.newsapp.data.repository.NewsRepository
import uz.gita.newsapp.domain.useCase.UnBookmarkArticleUseCase
import javax.inject.Inject

class UnBookmarkArticleUseCaseImpl @Inject constructor(private val repository: NewsRepository): UnBookmarkArticleUseCase {
    override suspend fun unBookmarkArticle(news: NewsEntity) = repository.unBookmarkArticle(news)
}