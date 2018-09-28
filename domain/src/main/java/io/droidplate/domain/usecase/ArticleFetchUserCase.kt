package io.droidplate.domain.usecase

import io.droidplate.domain.model.Article
import io.droidplate.domain.repository.ArticleRepository
import io.reactivex.Single
import javax.inject.Inject

class ArticleFetchUserCase @Inject constructor(var repository: ArticleRepository){

    fun get(refresh: Boolean) : Single<List<Article>> = repository.getArticles(refresh)
}