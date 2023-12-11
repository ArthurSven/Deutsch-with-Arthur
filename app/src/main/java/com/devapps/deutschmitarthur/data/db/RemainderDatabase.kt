package com.devapps.deutschmitarthur.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devapps.deutschmitarthur.data.RemainderDao
import com.devapps.deutschmitarthur.data.model.Remainder
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [Remainder::class], version = 1, exportSchema = false)
abstract class RemainderDatabase : RoomDatabase() {

    abstract fun remainderDao() : RemainderDao

    companion object {

        @Volatile
        private var Instance: RemainderDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context) : RemainderDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, RemainderDatabase::class.java, "remainder_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}