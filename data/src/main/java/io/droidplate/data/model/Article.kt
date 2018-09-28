package io.droidplate.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json



data class ArticleResponse(@Json(name = "_embedded") var embedded: ArticleList)
data class ArticleList(@Json(name = "articles") var articles: List<ArticleData>)

data class ArticleData(@Json(name = "sku") var id: String,
                       @Json(name = "title") var title: String,
                       @Json(name = "media") var media: List<Media>)


data class Media(@Json(name = "uri") var uri: String)


@Entity(tableName = "article")
data class ArticleEntity(@PrimaryKey() var id: String,
                         var title: String,
                         var image_url: String,
                         var action: Boolean)