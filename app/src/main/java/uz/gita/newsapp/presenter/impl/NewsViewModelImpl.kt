package uz.gita.newsapp.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsapp.data.remote.model.Article
import uz.gita.newsapp.domain.useCase.LatestNewsUseCase
import uz.gita.newsapp.domain.useCase.TopHeadlinesUseCase
import uz.gita.newsapp.presenter.NewsViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModelImpl @Inject constructor(
    private val latestNewsUseCase: LatestNewsUseCase,
    private val topHeadlinesUseCase: TopHeadlinesUseCase
) :
    NewsViewModel, ViewModel() {

    override val latestNewsList = MutableSharedFlow<PagingData<Article>>()

    override val topHeadlinesList = MutableSharedFlow<List<Article>>()

    override fun topHeadlines(category: String) {
        viewModelScope.launch {
            topHeadlinesUseCase.topHeadlines(category).collectLatest {
                topHeadlinesList.emit(it)
            }
        }
    }

    init {
        viewModelScope.launch(IO) {
            latestNewsUseCase.latestNews().collectLatest {
                latestNewsList.emit(it)
            }
        }
//        viewModelScope.launch(IO) {
//            topHeadlinesUseCase.topHeadlines("business").collectLatest {
//                topHeadlinesList.emit(it)
//            }
//        }


    }
}
