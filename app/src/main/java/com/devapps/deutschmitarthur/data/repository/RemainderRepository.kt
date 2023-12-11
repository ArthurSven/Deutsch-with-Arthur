package com.devapps.deutschmitarthur.data.repository

import com.devapps.deutschmitarthur.data.model.Remainder
import kotlinx.coroutines.flow.Flow

interface RemainderRepository {

    suspend fun getMyRemainders() : Flow<List<Remainder>>

    suspend fun getRemainder(creator: String) : Flow<Remainder>

    suspend fun insertRemainder(remainder: Remainder)

    suspend fun updateRemainder(remainder: Remainder)

    suspend fun deleteRemainder(remainder: Remainder)
}