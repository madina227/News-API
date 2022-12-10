package uz.gita.newsapp.domain.useCase.impl

import uz.gita.newsapp.data.local.room.NewsEntity
import uz.gita.newsapp.data.repository.NewsRepository
import uz.gita.newsapp.domain.useCase.CheckUseCase
import javax.inject.Inject

class CheckUseCaseImpl @Inject constructor(private val repository: NewsRepository) : CheckUseCase {
    override suspend fun check(title: String): NewsEntity? = repository.check(title)
}