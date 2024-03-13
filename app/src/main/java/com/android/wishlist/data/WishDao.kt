package com.android.wishlist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// Data Access Object - DAO
@Dao
abstract class WishDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(wish: Wish)

    // Loads all wishes from the wish table
    @Query("SELECT * FROM `wish-table`")
    abstract fun getAllWishes() : Flow<List<Wish>>

    @Update
    abstract suspend fun updateAWish(wishEntry : Wish)

    @Delete
    abstract suspend fun deleteAWish(wishEntry: Wish)

    @Query("SELECT * FROM `wish-table` WHERE id=:id")
    abstract fun getAWishById(id : Long) : Flow<Wish>
}