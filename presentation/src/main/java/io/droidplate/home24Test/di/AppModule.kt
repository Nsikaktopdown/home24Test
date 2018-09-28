package io.droidplate.home24Test.di

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import io.droidplate.data.cache.ArticleCache
import io.droidplate.data.cache.ArticleCacheImpl
import io.droidplate.home24Test.App
import io.droidplate.data.db.AppDatabase
import io.droidplate.data.repository.ArticleRepositoryImpl
import javax.inject.Singleton


@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun provideApp(): App = app

    /**
     * Room Database instance
     * @param app application context
     */
    @Provides
    @Singleton
    fun provideAppDatabase(app: App): AppDatabase {
        return Room.databaseBuilder(app.applicationContext, AppDatabase::class.java, "home_24.db").build()
    }

    /**
     * AppCache instance
     * @param appCacheImpl
     */
    @Provides
    @Singleton
    fun provideAppCache(articleCacheImpl: ArticleCacheImpl): ArticleCache = articleCacheImpl


    /*@Provides
    @Singleton
    fun providePreferenceHelper(ipreference: IPreferenceHelperImpl): IPreferenceHelper = ipreference*/

}