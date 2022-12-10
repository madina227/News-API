package uz.gita.newsapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsapp.data.repository.NewsRepository
import uz.gita.newsapp.data.repository.NewsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun newsRepository(impl: NewsRepositoryImpl): NewsRepository
}