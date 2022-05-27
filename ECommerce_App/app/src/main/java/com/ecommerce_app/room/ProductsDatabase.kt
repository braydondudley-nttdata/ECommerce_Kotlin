package com.ecommerce_app.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ecommerce_app.model.ProductsItem

@Database(entities = [ProductsItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductsDatabase: RoomDatabase() {
    abstract fun productsDao(): ProductsDao

    companion object {
        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        fun getInstance(context: Context): ProductsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(ProductsDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "products_database"
                )
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}