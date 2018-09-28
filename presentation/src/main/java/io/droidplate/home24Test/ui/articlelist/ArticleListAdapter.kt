package io.droidplate.home24Test.ui.articlelist

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.droidplate.domain.model.Article
import io.droidplate.home24Test.inflate
import io.droidplate.home24Test.model.ArticleItem
import io.droidplate.home24test.R
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleListAdapter constructor(): RecyclerView.Adapter< ArticleListAdapter.ViewHolder>() {

    private var items = ArrayList<ArticleItem>()
    var onLikeClick: ((ArticleItem) -> Unit)? = null
    var onDisLikeClick: ((ArticleItem) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder = ViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_article)) {


        fun bind(items: ArticleItem) {

            //itemView.article_title.text = items.title
            Glide.with(itemView.article_image.context).load(items.image_url).into(itemView.article_image)
            itemView.like_article.setOnClickListener { onLikeClick?.invoke(items) }
            itemView.dislike_article.setOnClickListener { onDisLikeClick?.invoke(items) }
        }
    }

    fun addItem(item: ArticleItem) {
        this.items.clear()
        this.items.add(item)
        notifyDataSetChanged()
    }
}