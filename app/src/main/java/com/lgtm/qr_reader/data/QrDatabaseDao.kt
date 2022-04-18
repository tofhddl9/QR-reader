package com.lgtm.qr_reader.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lgtm.qr_reader.model.QrData
import kotlinx.coroutines.flow.Flow

@Dao
interface QrDatabaseDao {

    @Query("SELECT * FROM QrData ORDER BY timeStamp")
    fun getAll(): Flow<List<QrData>>

    @Insert
    suspend fun insert(qrData: QrData)

    @Query("DELETE FROM QrData")
    suspend fun clear()

    @Delete
    suspend fun delete(qrData: QrData)
}