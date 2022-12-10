package uz.gita.newsapp.domain.useCase

import uz.gita.newsapp.data.local.room.NewsEntity

interface CheckUseCase {
    suspend fun check(title: String): NewsEntity?
}