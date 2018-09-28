package io.droidplate.data.mapper


import io.droidplate.data.model.ArticleData
import io.droidplate.data.model.ArticleEntity
import io.droidplate.data.model.ArticleList
import io.droidplate.data.model.ArticleResponse
import io.droidplate.domain.model.Article
import javax.inject.Inject


class ArticleMapper @Inject constructor() {

    fun mapToDomain(article: ArticleData): Article = Article(article.id, article.title, article.media[0].uri, false)

    fun mapToDomain(list: List<ArticleData>): List<Article> = list.map { mapToDomain(it) }

    fun mapResponse(response: ArticleResponse): List<ArticleData> = response.embedded.articles

    fun mapEntityToDomain(entity: ArticleEntity): Article = Article(entity.id, entity.title, entity.image_url, entity.action)

    fun mapEntityToDomain(list: List<ArticleEntity>): List<Article> = list.map { mapEntityToDomain(it) }


    fun mapToEntity(article: ArticleData): ArticleEntity = ArticleEntity(article.id, article.title, article.media[0].uri, false)

    fun mapToEntity(list: List<ArticleData>): List<ArticleEntity> = list.map { mapToEntity(it) }

}