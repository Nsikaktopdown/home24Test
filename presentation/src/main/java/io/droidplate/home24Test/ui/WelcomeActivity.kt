package io.droidplate.home24Test.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.droidplate.home24Test.ui.articlelist.ArticleListActivity
import io.droidplate.home24test.R
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)


        startButton.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, ArticleListActivity::class.java ))
        }
    }
}
