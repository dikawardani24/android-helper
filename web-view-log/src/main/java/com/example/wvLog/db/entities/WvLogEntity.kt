package com.example.wvLog.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wvLog.model.WebViewLog
import java.util.Date

@Entity(tableName = "wv_log")
class WvLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "message") val message: String?,
    @ColumnInfo (name = "line_number") val lineNumber: Int,
    @ColumnInfo (name = "source_id") val sourceId: String?,
    @ColumnInfo val level: String,
    @ColumnInfo val date: Date
) {
    companion object {
        fun from(wvLog: WebViewLog) = WvLogEntity(
            id = wvLog.id,
            message = wvLog.message,
            lineNumber = wvLog.lineNumber,
            sourceId = wvLog.sourceId,
            level = wvLog.level.name,
            date = wvLog.date
        )
    }
}