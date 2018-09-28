package io.droidplate.data.remote

import io.droidplate.data.model.ArticleData
import io.droidplate.domain.model.Article
import io.reactivex.Single


interface AppRemote {

    fun fetchArticles(): Single<List<ArticleData>>

}