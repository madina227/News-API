package uz.gita.newsapp.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import uz.gita.newsapp.data.local.room.NewsEntity
import uz.gita.newsapp.domain.useCase.BookmarkArticleUseCase
import uz.gita.newsapp.domain.useCase.CheckUseCase
import uz.gita.newsapp.domain.useCase.UnBookmarkArticleUseCase
import uz.gita.newsapp.presenter.ArticleViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModelImpl @Inject constructor(
    private val bookmarkArticleUseCase: BookmarkArticleUseCase,
    private val unBookmarkArticleUseCase: UnBookmarkArticleUseCase,
    private val checkUseCase: CheckUseCase
) : ArticleViewModel, ViewModel() {
    override suspend fun check(title: String): Boolean {
        return checkUseCase.check(title) != null
    }

//    override val checkFlow: NewsEntity?
//
//
//    override suspend fun check(title: String) {
//
//    }

    override fun bookmarkArticle(news: NewsEntity) {
        viewModelScope.launch(IO) {
            val exBookmark = checkUseCase.check(news.article!!.title!!)
            if (exBookmark == null) {
                bookmarkArticleUseCase.bookmarkArticle(news)
            } else {
                unBookmarkArticleUseCase.unBookmarkArticle(exBookmark)
            }


        }
    }

    override fun unBookmarkArticle(news: NewsEntity) {
        viewModelScope.launch(IO) {
            unBookmarkArticleUseCase.unBookmarkArticle(news)
        }
    }
}