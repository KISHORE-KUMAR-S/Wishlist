package com.android.wishlist.data

import android.content.Context
import androidx.room.Room

object Graph {
    private lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(
            context = context,
            klass = WishDatabase::class.java,
            name = "wishlist.db"
        ).build()
    }
}