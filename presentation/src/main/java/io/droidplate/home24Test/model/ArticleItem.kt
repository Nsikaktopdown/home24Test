package io.droidplate.home24Test.model

import io.droidplate.domain.model.Article
import javax.inject.Inject


data class ArticleItem(var id: String,
                       var title: String,
                       var image_url: String,
                       var action: Boolean)





class ArticleItemMapper @Inject constructor(){


    fun mapToPresentation(article: Article): ArticleItem = ArticleItem(article.id, article.title, article.image_url, article.action)

    fun mapToPresentation(list: List<Article>): List<ArticleItem> = list.map { mapToPresentation(it) }

}