package com.lgtm.qr_reader.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lgtm.qr_reader.model.QrData
import kotlinx.coroutines.CoroutineScope

@Database(entities = [QrData::class], version = 1)
abstract class QrRoomDatabase : RoomDatabase() {

    abstract fun qrDataDao(): QrDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE : QrRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): QrRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    QrRoomDatabase::class.java,
                    "qr_database"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}