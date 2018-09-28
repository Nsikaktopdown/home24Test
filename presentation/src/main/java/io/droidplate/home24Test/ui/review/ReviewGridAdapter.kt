package io.droidplate.home24Test.ui.review

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.droidplate.home24Test.inflate
import io.droidplate.home24Test.model.ArticleItem
import io.droidplate.home24test.R
import kotlinx.android.synthetic.main.item_review_grid.view.*

class ReviewGridAdapter constructor() : RecyclerView.Adapter<ReviewGridAdapter.ViewHolder>() {

    private var items = ArrayList<ArticleItem>()
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder = ViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_review_grid)) {

        fun bind(items: ArticleItem) {
            Glide.with(itemView.review_article_image_grid.context).load(items.image_url).into(itemView.review_article_image_grid)
            var context = itemView.review_article_like_grid.context

            when (items.action) {
                true -> itemView.review_article_like_grid.setColorFilter(context.resources.getColor(R.color.colorPrimary))
                false -> itemView.review_article_like_grid.setColorFilter(context.resources.getColor(R.color.textDark))
            }
        }
    }

    fun addItems(item: List<ArticleItem>) {
        this.items.clear()
        this.items.addAll(item)
        notifyDataSetChanged()
    }
}