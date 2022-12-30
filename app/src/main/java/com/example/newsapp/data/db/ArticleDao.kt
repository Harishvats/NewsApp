package com.example.newsapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(article: Article)

    @Query("SELECT * FROM saved_news")
    fun getSavedArticle(): Flow<List<Article>>

    @Delete
    suspend fun deleteSavedArticle(article: Article)
}