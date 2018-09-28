package io.droidplate.data.cache

import io.droidplate.data.model.ArticleEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface ArticleCache{
    fun insertArticle(entity: List<ArticleEntity>): Completable
    fun getAllArticle(): Single<List<ArticleEntity>>
    fun updateAction(id: String, action: Boolean): Completable
}