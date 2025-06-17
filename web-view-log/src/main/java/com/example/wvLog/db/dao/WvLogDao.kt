package com.example.wvLog.db.dao

import androidx.room.Dao
import com.example.wvLog.db.entities.WvLogEntity
import dika.helper.database.CrudDao

@Dao
interface WvLogDao: CrudDao<WvLogEntity>