package com.nubari.groceries.data.local.typeconverters

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.*

class ApplicationTypeConverter {
    @TypeConverter
    fun bigDecimalToString(input: BigDecimal): String {
        return input.toPlainString()
    }

    @TypeConverter
    fun stringToBigDecimal(input: String): BigDecimal {
        return input.toBigDecimalOrNull() ?: BigDecimal.valueOf(0.0)
    }

    @TypeConverter
    fun dateFromTypeStamp(value: Long?): Date {
        return value?.let { Date(it) } ?: Date()
    }

    @TypeConverter
    fun dateToTimeStamp(date: Date?): Long {
        return date?.time?.toLong() ?: -1L
    }
}