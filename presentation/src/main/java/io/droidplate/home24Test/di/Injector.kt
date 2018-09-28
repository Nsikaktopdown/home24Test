package io.droidplate.home24Test.di

import dagger.Component
import io.droidplate.home24Test.ui.articlelist.ArticleListActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, NetworkModule::class, RepositoryModule::class])
interface Injector {

    fun inject(activity: ArticleListActivity)

}