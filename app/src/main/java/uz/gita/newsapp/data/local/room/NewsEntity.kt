package uz.gita.newsapp.data.local.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.newsapp.data.remote.model.Article

@Entity
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val news_id: Int = 0,
    @Embedded val article: Article?
)
