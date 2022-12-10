package uz.gita.newsapp.domain.useCase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.newsapp.data.remote.model.Article

interface SearchUseCase {

    suspend fun search(search: String): Flow<PagingData<Article>>
}