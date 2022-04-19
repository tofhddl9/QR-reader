package com.lgtm.qr_reader

import android.app.Application
import com.lgtm.qr_reader.data.QrHistoryRepositoryImpl
import com.lgtm.qr_reader.data.QrRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class QrReaderApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { QrRoomDatabase.getDatabase(this, applicationScope) }

    val repository by lazy { QrHistoryRepositoryImpl(database.qrDataDao()) }

}
