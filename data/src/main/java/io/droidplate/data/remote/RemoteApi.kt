package io.droidplate.data.remote

import io.droidplate.data.model.ArticleData
import io.droidplate.data.model.ArticleResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * All Api endpoint for this projects should be places here
 */

interface RemoteApi {

    @GET("articles?")
    @Headers("Accept: application/json",
            "Content-type:application/json")
    fun getArticles(@Query("appDomain") appDomain: Int,
                    @Query("locale") locale: String,
                    @Query("limit") limit: Int): Single<ArticleResponse>

}
