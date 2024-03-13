package com.android.wishlist

import android.app.Application
import com.android.wishlist.data.Graph

class WishListApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Graph.provide(this)
    }
}