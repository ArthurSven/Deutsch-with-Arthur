package com.devapps.deutschmitarthur.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.devapps.deutschmitarthur.data.model.Remainder
import kotlinx.coroutines.flow.Flow

@Dao
interface RemainderDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(remainder: Remainder)

    @Update
    suspend fun update(remainder: Remainder)
    @Delete
    suspend fun delete(remainder: Remainder)

    @Query("SELECT * from remainders WHERE creator = :creator")
    suspend fun getRemainder(creator: String) : Flow<List<Remainder>>

    @Query("SELECT * FROM remainders ORDER BY id DESC")
    suspend fun getMyRemainders() : Flow<List<Remainder>>
}