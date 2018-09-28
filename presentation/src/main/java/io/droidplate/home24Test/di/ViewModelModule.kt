package io.droidplate.home24Test.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.altalabs.androidbase.ViewModelFactory
import io.altalabs.androidbase.ViewModelKey
import io.droidplate.home24Test.ui.articlelist.ArticleActivityViewModel
import io.droidplate.home24Test.ui.review.ReviewActivityViewModel


@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ArticleActivityViewModel::class)
    internal abstract fun articleViewModel(viewModel: ArticleActivityViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ReviewActivityViewModel::class)
    internal abstract fun reviewViewModel(viewModel: ReviewActivityViewModel): ViewModel
}