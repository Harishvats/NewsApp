package com.example.newsapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.db.ArticleDB
import com.example.newsapp.data.db.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    @Singleton
    fun provideDatabaseInstance(app: Application): ArticleDB {
        return Room.databaseBuilder(app, ArticleDB::class.java, "news_db")
            .fallbackToDestructiveMigration().build()
    }


    @Provides
    @Singleton
    fun provideArticleDao(articleDB: ArticleDB): ArticleDao {
        return articleDB.getArticleDao()
    }
}