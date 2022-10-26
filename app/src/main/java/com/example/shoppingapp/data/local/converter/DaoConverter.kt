package com.example.shoppingapp.data.local.converter

import androidx.room.TypeConverter

class DaoConverter {
    @TypeConverter
    fun toListofStrings(stringValue:String):List<String>?{

        return stringValue.split(",").map { it }
    }
    @TypeConverter
    fun fromListofStrings(listofString:List<String>?):String{
        return listofString?.joinToString(separator = ",") ?:""
    }
}