package uz.gita.newsapp.domain.useCase.impl

import uz.gita.newsapp.data.local.room.NewsEntity
import uz.gita.newsapp.data.repository.NewsRepository
import uz.gita.newsapp.domain.useCase.BookmarkArticleUseCase
import javax.inject.Inject

class BookmarkArticleUseCaseImpl @Inject constructor(private val repository: NewsRepository) :
    BookmarkArticleUseCase {
    override suspend fun bookmarkArticle(news: NewsEntity) = repository.bookmarkArticle(news)
}