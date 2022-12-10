package uz.gita.newsapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import uz.gita.newsapp.data.remote.model.Article
import java.io.IOException

class EverythingDataSource(private val search: String, private val newsApi: NewsApi) :
    PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val position = params.key ?: 1
            val response = newsApi.everything(search = search)

            if (response.isSuccessful){
                Log.d("SSS","response succesful")
            } else{
                Log.d("SSS","response not succesful")
            }

            LoadResult.Page(
                data = response.body()!!.articles,
                prevKey = if (position > 1) position - 1 else null,
                nextKey = if (position < response.body()!!.totalResults / 15) position + 1 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}