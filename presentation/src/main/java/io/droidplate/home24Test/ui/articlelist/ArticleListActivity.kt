package io.droidplate.home24Test.ui.articlelist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.altalabs.androidbase.Data
import io.altalabs.androidbase.DataState
import io.droidplate.home24Test.*
import io.droidplate.home24Test.model.ArticleItem
import io.droidplate.home24Test.ui.review.ReviewActivity
import io.droidplate.home24test.R
import kotlinx.android.synthetic.main.activity_article_list.*
import timber.log.Timber
import javax.inject.Inject

class ArticleListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    var articleList = mutableListOf<ArticleItem>()
    var articleAdapter = ArticleListAdapter()
    var position = 0
    var listSize = 0
    var counter = 0
    lateinit var viewModel: ArticleActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)
        getAppInjector().inject(this)

        supportActionBar?.title = "Articles"

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent))

        viewModel = withViewModel<ArticleActivityViewModel>(viewModelFactory) {
            swipeRefreshLayout.setOnRefreshListener { getArticles(true) }
            getArticles(true)
            observe(articles, ::updateArticles)
        }

        initView()
    }

    /**
     * View setup
     */
    private fun initView() {

        with(articleRecyclerViewHome) {
            adapter = articleAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ArticleListActivity, LinearLayoutManager.HORIZONTAL, false)

            /**
             * Update action in cache
             */
            articleAdapter.onLikeClick = {
                viewModel.setActionCounter(counter++)
                viewModel.updateUserAction(it.id, true)
                navigateToNextArticle()
                toast("Liked Article", this@ArticleListActivity)
            }

            articleAdapter.onDisLikeClick = {
                viewModel.updateUserAction(it.id, false)
                navigateToNextArticle()
                toast("DisLiked Article", this@ArticleListActivity)
            }
        }
        /**
         * observe for action counter change
         */
        viewModel.getActionCounter().observe(this, Observer { action: Int? ->
            action_counter.text = " $action / $listSize"
        })


        review_button.setOnClickListener { startActivity(Intent(this@ArticleListActivity, ReviewActivity::class.java)) }
    }

    /**
     * Change Article item on action button click
     */
    private fun navigateToNextArticle() {
        position++
        review_button.visibility = View.VISIBLE
        if (position > articleList.size - 1) {
            empty_state_layout.visibility = View.VISIBLE
            articleRecyclerViewHome.visibility = View.GONE
        } else {
            articleAdapter.addItem(articleList[position])
            Timber.e(position.toString())
        }

    }

    /**
     * adapter data-binding
     * @param data [shopItem list from the data layer]
     */
    private fun updateArticles(data: Data<List<ArticleItem>>?) {
        data?.let {
            when (it.dataState) {
                DataState.LOADING -> swipeRefreshLayout.startRefreshing()
                DataState.SUCCESS -> swipeRefreshLayout.stopRefreshing()
                DataState.ERROR -> swipeRefreshLayout.stopRefreshing()
            }
            it.data?.let { setData(it) }
            it.message?.let { toast(it, this@ArticleListActivity) }
        }
    }


    private fun setData(list: List<ArticleItem>) {

        this.listSize = list.size
        action_counter.text = "$counter  /  $listSize"
        this.articleList.addAll(list)
        if (articleList.size > 0) {
            articleAdapter.addItem(articleList[0])
        }
    }

}
