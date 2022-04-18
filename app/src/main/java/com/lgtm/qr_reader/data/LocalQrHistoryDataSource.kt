package com.lgtm.qr_reader.data

import androidx.annotation.WorkerThread
import com.lgtm.qr_reader.model.QrData
import kotlinx.coroutines.flow.Flow

class LocalQrHistoryDataSource(
    private val dao: QrDatabaseDao
) : QrHistoryRepository {

    override val allQrHistories: Flow<List<QrData>> = dao.getAll()

    @WorkerThread
    override suspend fun insert(qrData: QrData) {
        dao.insert(qrData)
    }

    @WorkerThread
    override suspend fun delete(qrData: QrData) {
        dao.delete(qrData)
    }

    @WorkerThread
    override suspend fun clear() {
        dao.clear()
    }
}