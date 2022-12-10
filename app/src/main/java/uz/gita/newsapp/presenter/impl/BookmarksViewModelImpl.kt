package uz.gita.newsapp.presenter.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsapp.data.local.room.NewsEntity
import uz.gita.newsapp.domain.useCase.BookmarksUseCase
import uz.gita.newsapp.domain.useCase.CheckUseCase
import uz.gita.newsapp.domain.useCase.UnBookmarkArticleUseCase
import uz.gita.newsapp.presenter.BookmarksViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModelImpl @Inject constructor(
    private val bookmarksUseCase: BookmarksUseCase,
    private val unBookmarkArticleUseCase: UnBookmarkArticleUseCase,
    private val checkUseCase: CheckUseCase
) : BookmarksViewModel, ViewModel() {

    override val bookmarksList = MutableSharedFlow<List<NewsEntity>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val searchBookmarksList = MutableSharedFlow<List<NewsEntity>>()

//    override fun searchBookmarks(search: String) {
//
//    }
//
//    override suspend fun check(title: String) {
//
//    }

    init {
        viewModelScope.launch(IO) {
            bookmarksUseCase.bookmarks().collectLatest {
                bookmarksList.emit(it)
                Log.d("LOGGI", "${it}")
            }
        }
    }

}