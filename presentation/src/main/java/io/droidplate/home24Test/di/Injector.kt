package io.droidplate.home24Test.di

import android.support.v7.widget.RecyclerView
import dagger.Component
import io.droidplate.home24Test.ui.articlelist.ArticleListActivity
import io.droidplate.home24Test.ui.review.ReviewActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, NetworkModule::class, RepositoryModule::class])
interface Injector {

    fun inject(activity: ArticleListActivity)
    fun inject(activity: ReviewActivity)

}