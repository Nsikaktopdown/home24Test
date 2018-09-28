package io.droidplate.data.repository

import io.droidplate.data.cache.ArticleCache
import io.droidplate.data.mapper.ArticleMapper
import io.droidplate.data.remote.AppRemote
import io.droidplate.domain.model.Article
import io.droidplate.domain.repository.ArticleRepository
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

/**
 * Article Repository implementation
 * @param cache -> local storage
 * @param remote -> remote data source
 * @param mapper -> entity mappers
 */
class ArticleRepositoryImpl @Inject constructor(var cache: ArticleCache,
                                                var remote: AppRemote,
                                                var mapper: ArticleMapper) : ArticleRepository {


    override fun getReviewArticles(): Single<List<Article>> {
        return cache.getAllArticle().map { mapper.mapEntityToDomain(it) }
    }

    override fun updateUserAction(id: String, action: Boolean) : Completable{
        return  cache.updateAction(id, action).doOnComplete { Timber.d("action updated") }
    }

    override fun saveArticlesToDB(): Single<List<Article>> {
        return remote.fetchArticles().doAfterSuccess {
            cache.insertArticle(mapper.mapToEntity(it)).subscribe()
        }.map { mapper.mapToDomain(it) }
    }


    override fun getArticles(refresh: Boolean): Single<List<Article>> = when (refresh) {
        true -> saveArticlesToDB().doOnSuccess { cache.getAllArticle().map { mapper.mapEntityToDomain(it) } }
        false -> cache.getAllArticle().map { mapper.mapEntityToDomain(it) }.doOnError { getArticles(true) }
    }


}