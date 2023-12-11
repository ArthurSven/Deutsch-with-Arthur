package com.devapps.deutschmitarthur.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remainders")
data class Remainder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remainderTitle: String,
    val time: String,
    val creator: String

)
