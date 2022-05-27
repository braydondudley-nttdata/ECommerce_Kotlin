package com.ecommerce_app.room

import androidx.room.TypeConverter
import com.ecommerce_app.model.Rating

class Converters {

    @TypeConverter
    fun fromRating(rating: Rating): String {
        return rating.rate.toString()
    }

    @TypeConverter
    fun toRating(rating: String): Rating {
        return Rating(0,rating.toDouble())
    }
}