package io.droidplate.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.droidplate.data.model.ArticleEntity
import io.reactivex.Single


@Dao
interface ArticleDao {

    /**
     * Data access to get all saved articles
     */
    @Query("select * from article")
    fun getllArticle(): Single<List<ArticleEntity>>


    /**
     * Insert article using this DAO
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(articles: List<ArticleEntity>)


    /**
     * Update article action
     */
    @Query("UPDATE article SET `action` = :action WHERE id = :id")
    fun updateAction(id: String, action: Boolean)
}