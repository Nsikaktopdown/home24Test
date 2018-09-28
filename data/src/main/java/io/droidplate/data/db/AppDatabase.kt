package io.droidplate.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.droidplate.data.model.ArticleEntity


@Database(entities = arrayOf(ArticleEntity::class), version = 1)
public abstract class AppDatabase: RoomDatabase() {

   public abstract fun articleDao(): ArticleDao

}