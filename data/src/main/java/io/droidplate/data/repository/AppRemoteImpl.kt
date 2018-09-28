package io.droidplate.data.repository


import io.droidplate.data.mapper.ArticleMapper
import io.droidplate.data.model.ArticleData
import io.droidplate.data.remote.RemoteApi
import io.droidplate.data.remote.AppRemote
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

/**
 * Here implements all the api interface
 * Binding this it to the domain
 * @param api retrofit interface
 * @param mapper data passer
 */
class AppRemoteImpl @Inject constructor(private val api: RemoteApi, private val mapper: ArticleMapper): AppRemote {

    override fun fetchArticles(): Single<List<ArticleData>> {
        return api.getArticles(1, "de_DE", 10).map { mapper.mapResponse(it) }

    }

}