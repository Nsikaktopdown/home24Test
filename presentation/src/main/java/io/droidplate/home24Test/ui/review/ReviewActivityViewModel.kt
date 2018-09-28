package io.droidplate.home24Test.ui.review

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.altalabs.androidbase.Data
import io.altalabs.androidbase.DataState
import io.droidplate.domain.usecase.ArticleFetchUserCase
import io.droidplate.domain.usecase.ReviewArticleUseCase
import io.droidplate.domain.usecase.UserArticleActionUseCase
import io.droidplate.home24Test.model.ArticleItem
import io.droidplate.home24Test.model.ArticleItemMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReviewActivityViewModel @Inject constructor(var useCase: ReviewArticleUseCase,
                                                   var mapper: ArticleItemMapper) : ViewModel() {


    val articles = MutableLiveData<Data<List<ArticleItem>>>()
   private val compositeDisposable = CompositeDisposable()


    init {
        getArticles()
    }

    /**
     * Fetch articles
     */
    fun getArticles() = compositeDisposable.add(useCase.getReviewArticles()
            .doOnSubscribe { articles.postValue(Data(dataState = DataState.LOADING, data = articles.value?.data, message = null)) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { mapper.mapToPresentation(it) }
            .subscribe({
                articles.postValue(Data(dataState = DataState.SUCCESS, data = it, message = null))
            }, { articles.postValue(Data(dataState = DataState.ERROR, data = articles.value?.data, message = it.message)) }))

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}