package com.devapps.deutschmitarthur.data.repository

import com.devapps.deutschmitarthur.data.RemainderDao
import com.devapps.deutschmitarthur.data.model.Remainder
import kotlinx.coroutines.flow.Flow

interface OfflineRemainderRepository : RemainderRepository {

    val remainderDao: RemainderDao
    override suspend fun getMyRemainders(): Flow<List<Remainder>> = remainderDao.getMyRemainders()

    override suspend fun deleteRemainder(remainder: Remainder) = remainderDao.delete(remainder)

    override suspend fun updateRemainder(remainder: Remainder) = remainderDao.update(remainder)

    override suspend fun insertRemainder(remainder: Remainder) = remainderDao.insert(remainder)
}
