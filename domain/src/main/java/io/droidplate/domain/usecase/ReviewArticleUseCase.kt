package io.droidplate.domain.usecase

import io.droidplate.domain.model.Article
import io.droidplate.domain.repository.ArticleRepository
import io.reactivex.Single
import javax.inject.Inject

class  ReviewArticleUseCase @Inject constructor(private  var repository: ArticleRepository){

    fun getReviewArticles(): Single<List<Article>> = repository.getReviewArticles()
}