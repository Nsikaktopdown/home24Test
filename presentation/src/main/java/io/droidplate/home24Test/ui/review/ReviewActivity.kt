package io.droidplate.home24Test.ui.review

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.altalabs.androidbase.Data
import io.altalabs.androidbase.DataState
import io.droidplate.home24Test.*
import io.droidplate.home24Test.model.ArticleItem
import io.droidplate.home24test.R
import kotlinx.android.synthetic.main.activity_review.*
import javax.inject.Inject

class ReviewActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    var reviewArticleAdapter = ReviewListAdapter()
    var reviewGridAdapter = ReviewGridAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        getAppInjector().inject(this)

        withViewModel<ReviewActivityViewModel>(viewModelFactory) {
            swipeRefreshLayoutReview.setOnRefreshListener { getArticles() }
            observe(articles, ::updateArticles)
        }

        initView()
    }

    /**
     * View setup
     */
    private fun initView() {
        showRecyclerView(reviewGridAdapter)
        supportActionBar?.title = "Article Review"

        show_grid.setOnClickListener {
            show_list.setColorFilter(resources.getColor(R.color.textDark))
            show_grid.setColorFilter(resources.getColor(R.color.colorPrimary))
            showRecyclerView(reviewGridAdapter)
        }

        show_list.setOnClickListener {
            show_grid.setColorFilter(resources.getColor(R.color.textDark))
            show_list.setColorFilter(resources.getColor(R.color.colorPrimary))
            showRecyclerView(reviewArticleAdapter)
        }
    }


    /**
     *  RecyclerView Setup
     *  @param mAdapter -> generic adapter
     *
     */
    private fun showRecyclerView(mAdapter: RecyclerView.Adapter<*>){
        with(articleRecyclerViewReview) {
            adapter = null
            adapter = mAdapter
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@ReviewActivity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(this@ReviewActivity, LinearLayoutManager.VERTICAL, false)

        }

    }
    /**
     * adapter data-binding
     * @param data [shopItem list from the data layer]
     */
    private fun updateArticles(data: Data<List<ArticleItem>>?) {
        data?.let {
            when (it.dataState) {
                DataState.LOADING -> swipeRefreshLayoutReview.startRefreshing()
                DataState.SUCCESS -> swipeRefreshLayoutReview.stopRefreshing()
                DataState.ERROR -> swipeRefreshLayoutReview.stopRefreshing()
            }
            it.data?.let { setData(it) }
            it.message?.let { toast(it, this@ReviewActivity) }
        }
    }

    /**
     * add items to adapters
     */
    private fun setData(list: List<ArticleItem>){
        reviewArticleAdapter.addItems(list)
        reviewGridAdapter.addItems(list)
    }

}
