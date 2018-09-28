package io.droidplate.home24Test.di

import dagger.Binds
import dagger.Module
import io.droidplate.data.repository.ArticleRepositoryImpl
import io.droidplate.domain.repository.ArticleRepository

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindArticleRepository(repository: ArticleRepositoryImpl):  ArticleRepository

}