package io.droidplate.domain.repository

import io.droidplate.domain.model.Article
import io.reactivex.Completable
import io.reactivex.Single

interface ArticleRepository{
    fun saveArticlesToDB(): Single<List<Article>>
    fun getArticles(refresh: Boolean): Single<List<Article>>
    fun updateUserAction(id: String, action: Boolean): Completable
    fun getReviewArticles(): Single<List<Article>>
}