package com.devapps.deutschmitarthur.data

import android.content.Context
import com.devapps.deutschmitarthur.data.db.RemainderDatabase
import com.devapps.deutschmitarthur.data.repository.OfflineRemainderRepository
import com.devapps.deutschmitarthur.data.repository.RemainderRepository


interface AppContainer {
    val remainderRepository : RemainderRepository
}

class AppDataContainer(private val context: Context,
                       override val remainderRepository: RemainderRepository
) : AppContainer {

     val reminderRepository : RemainderRepository by lazy {
        OfflineRemainderRepository(RemainderDatabase.getDatabase(context).remainderDao())
    }
}
