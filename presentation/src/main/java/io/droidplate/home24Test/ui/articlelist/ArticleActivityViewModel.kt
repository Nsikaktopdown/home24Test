package io.droidplate.home24Test.ui.articlelist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.bumptech.glide.Glide.init
import io.altalabs.androidbase.Data
import io.altalabs.androidbase.DataState
import io.droidplate.domain.usecase.ArticleFetchUserCase
import io.droidplate.domain.usecase.UserArticleActionUseCase
import io.droidplate.home24Test.model.ArticleItem
import io.droidplate.home24Test.model.ArticleItemMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ArticleActivityViewModel @Inject constructor(var useCase: ArticleFetchUserCase,
                                                   var mapper: ArticleItemMapper,
                                                   var userActionUseCase: UserArticleActionUseCase) : ViewModel() {


    val articles = MutableLiveData<Data<List<ArticleItem>>>()
    val compositeDisposable = CompositeDisposable()
    var actionCounter =   MutableLiveData<Int>()


    init {
        getArticles(false)
    }

    /**
     * Fetch articles
     */
    fun getArticles(refresh: Boolean = false) = compositeDisposable.add(useCase.get(refresh)
            .doOnSubscribe { articles.postValue(Data(dataState = DataState.LOADING, data = articles.value?.data, message = null)) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { mapper.mapToPresentation(it) }
            .subscribe({
                articles.postValue(Data(dataState = DataState.SUCCESS, data = it, message = null))
            }, { articles.postValue(Data(dataState = DataState.ERROR, data = articles.value?.data, message = it.message)) }))


    /**
     * Update user actions on like button click
     */
    fun updateUserAction(article_id: String, action: Boolean) = userActionUseCase.updateAction(article_id, action)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                object : DisposableObserver<Int>() {
                    override fun onComplete() {
                        Timber.d("action updated")
                    }

                    override fun onNext(t: Int) {
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onError(e: Throwable) {

                        Timber.d("action update failed: ${e.message}")
                    }
                }

            }


    fun setActionCounter(counter: Int) {
        this.actionCounter.value = counter
    }

    fun getActionCounter(): LiveData<Int>{
        return actionCounter
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}