package com.lgtm.qr_reader.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "QrData")
data class QrData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val text: String? = null,
    val type: QrType = QrType.TEXT,
    val timeStamp: Long? = null
) : Parcelable