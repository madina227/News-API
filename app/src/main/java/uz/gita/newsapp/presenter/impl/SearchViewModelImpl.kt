package uz.gita.newsapp.presenter.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsapp.data.remote.model.Article
import uz.gita.newsapp.domain.useCase.SearchUseCase
import uz.gita.newsapp.presenter.SearchViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModelImpl @Inject constructor(private val searchUseCase: SearchUseCase) :
    SearchViewModel, ViewModel() {
    override val searchListFlow = MutableSharedFlow<PagingData<Article>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun search(search: String) {
        viewModelScope.launch(IO) {
            searchUseCase.search(search).collectLatest {
                Log.d("SSS", "$it vmodel")
                searchListFlow.emit(it)
            }
        }
    }
}