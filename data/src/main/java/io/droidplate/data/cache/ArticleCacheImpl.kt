package io.droidplate.data.cache

import io.droidplate.data.db.AppDatabase
import io.droidplate.data.model.ArticleEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

/**
 *  This class implements the  database Cache logic
 *  @param database Room database instance
 */
class ArticleCacheImpl @Inject constructor(private val database: AppDatabase) : ArticleCache {


    override fun updateAction(id: String, action: Boolean): Completable {
        return Completable.fromAction { database.articleDao().updateAction(id, action) }
    }

    override fun getAllArticle(): Single<List<ArticleEntity>> {
        return database.articleDao().getllArticle()
    }

    override fun insertArticle(entity: List<ArticleEntity>): Completable {
        return Completable.fromAction {
            kotlin.run {
                database.articleDao().insertArticle(entity)
                getAllArticle()
            }

        }
    }


}