package uz.gita.newsapp.presenter

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.remote.model.Article

interface SearchViewModel {

    val searchListFlow: Flow<PagingData<Article>>

    fun search(search: String)
}