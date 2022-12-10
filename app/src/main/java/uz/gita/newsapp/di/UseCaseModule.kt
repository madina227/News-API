package uz.gita.newsapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsapp.domain.useCase.*
import uz.gita.newsapp.domain.useCase.impl.*

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindBookmarkArticleUseCase(impl: BookmarkArticleUseCaseImpl): BookmarkArticleUseCase

    @Binds
    fun bindBookmarkUseCase(impl: BookmarksUseCaseImpl): BookmarksUseCase

    @Binds
    fun bindLatestNewsUseCase(impl: LatestNewsUseCaseImpl): LatestNewsUseCase

    @Binds
    fun bindSearchBookmarkUseCase(impl: SearchBookmarksUseCaseImpl): SearchBookmarksUseCase

    @Binds
    fun bindSearchUseCase(impl: SearchUseCaseImpl): SearchUseCase

    @Binds
    fun bindTopHeadlinesUseCase(impl: TopHeadlinesUseCaseImpl): TopHeadlinesUseCase

    @Binds
    fun bindUnBookmarkArticleUseCase(impl: UnBookmarkArticleUseCaseImpl): UnBookmarkArticleUseCase

    @Binds
    fun checkUseCase(impl: CheckUseCaseImpl): CheckUseCase

}