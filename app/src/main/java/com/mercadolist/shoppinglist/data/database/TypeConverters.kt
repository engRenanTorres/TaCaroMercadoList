package com.mercadolist.shoppinglist.data.database

import androidx.room.TypeConverter
import java.util.Date

/**
 * Type converters for Room database.
 * Converts between Java types and SQLite types.
 */
class TypeConverters {
    /**
     * Convert Long to Date.
     * @param value The timestamp as Long
     * @return The Date object
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    /**
     * Convert Date to Long.
     * @param date The Date object
     * @return The timestamp as Long
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
