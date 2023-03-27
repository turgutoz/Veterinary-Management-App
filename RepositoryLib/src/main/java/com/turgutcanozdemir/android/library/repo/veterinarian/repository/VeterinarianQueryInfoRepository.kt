package com.turgutcanozdemir.android.library.repo.veterinarian.repository

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.turgutcanozdemir.android.library.repo.veterinarian.entity.VeterinarianQueryInfo
import javax.inject.Inject
import com.karandev.util.datetime.DateTimeConvertUtil
import java.util.*

private const val DIPLOMA_NO = "diploma_no"
private const val QUERY_COUNT = "query_count"
private const val QUERY_DATE_TIME = "query_date_time"
private const val SAVE_DATE_TIME = "save_date_time"
private const val SOURCE_SERVICE = "source_service"

private const val TABLE_NAME = "veterinarian_info"

class VeterinarianQueryInfoRepository @Inject constructor() : IVeterinarianQueryInfoRepository {
    @Inject
    lateinit var db : SQLiteDatabase

    private fun createVeterinarianQueryInfo(cursor: Cursor) : VeterinarianQueryInfo
    {
        val diplomaNo = cursor.getLong(0)
        val queryCount = cursor.getLong(1)
        val queryDateTime = cursor.getLong(2)
        val saveDateTime = cursor.getLong(3)
        val sourceService = cursor.getString(4)

        return VeterinarianQueryInfo(diplomaNo, queryCount, DateTimeConvertUtil.toLocalDateTime(queryDateTime),
        DateTimeConvertUtil.toLocalDateTime(saveDateTime), sourceService)
    }

    override fun count(): Long
    {
        db.rawQuery("select count(*) as count from $TABLE_NAME", arrayOf("count")).use {
            it.moveToFirst()
            return it.getLong(0)
        }
    }

    //not sure this method works properly
    override fun existsById(id: Long?) = findByDiplomaNo(id!!) != null

    override fun findByDiplomaNo(diplomaNo: Long): VeterinarianQueryInfo?
    {
        val projection = arrayOf(DIPLOMA_NO, QUERY_COUNT, QUERY_DATE_TIME, SAVE_DATE_TIME, SOURCE_SERVICE)
        var cursor: Cursor? = null
        var veterinarianQueryInfo: VeterinarianQueryInfo? = null

        try {
            cursor = db.query(TABLE_NAME, projection, "diploma_no = $diplomaNo", null, null, null, null)
            if (cursor != null && cursor.moveToFirst())
                veterinarianQueryInfo = createVeterinarianQueryInfo(cursor)
        }
        finally {
            cursor?.close()
        }

        return veterinarianQueryInfo
    }

    override fun <S : VeterinarianQueryInfo?> save(veterinarianQueryInfo: S): S
    {
        val cv = ContentValues()

        cv.put(DIPLOMA_NO, veterinarianQueryInfo?.diplomaNo)
        cv.put(QUERY_COUNT, 1)
        cv.put(QUERY_DATE_TIME, DateTimeConvertUtil.toMilliseconds(veterinarianQueryInfo?.queryDateTime))
        cv.put(SAVE_DATE_TIME, DateTimeConvertUtil.toMilliseconds(veterinarianQueryInfo?.saveDateTime))
        cv.put(SOURCE_SERVICE, veterinarianQueryInfo?.sourceService)

        db.insertOrThrow(TABLE_NAME, null, cv)

        return veterinarianQueryInfo
    }


    //Not implemented methods
    override fun delete(entity: VeterinarianQueryInfo?) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<VeterinarianQueryInfo>?) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Long>?) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long?) {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableIterable<VeterinarianQueryInfo> {
        TODO("Not yet implemented")
    }

    override fun findAllById(id: MutableIterable<Long>?): MutableIterable<VeterinarianQueryInfo> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long?): Optional<VeterinarianQueryInfo> {
        TODO("Not yet implemented")
    }

    override fun <S : VeterinarianQueryInfo?> saveAll(entities: MutableIterable<S>?): MutableIterable<S> {
        TODO("Not yet implemented")
    }
}