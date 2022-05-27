package com.ecommerce_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_items")
data class ProductsItem(
    val category: String ?=null,
    var description: String,
    val id: Int ?=null,
    var image: String,
    val price: Double ?=null,
    val rating: Rating ?=null,
    val title: String ?=null
)  {
    @PrimaryKey(autoGenerate = true)
    var roomId: Int = 0
}