package com.turgutcanozdemir.android.library.repo.veterinarian.repository

import android.content.ContentValues
import android.database.Cursor
import com.karandev.util.datetime.DateTimeConvertUtil
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import com.turgutcanozdemir.android.library.repo.veterinarian.entity.Veterinarian
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/*
  veterinarian_id INTEGER primary key AUTOINCREMENT,
        diploma_no INTEGER not null,
        citizen_id TEXT not null,
        first_name TEXT not null,
        middle_name TEXT,
        last_name TEXT,
        birth_date INTEGER not null,
        register_date INTEGER not null,
 */
private const val DIPLOMA_NO = "diploma_no"
private const val CITIZEN_ID = "citizen_id"
private const val FIRST_NAME = "first_name"
private const val MIDDLE_NAME = "middle_name"
private const val LAST_NAME = "last_name"
private const val BIRTH_DATE = "birth_date"
private const val REGISTER_DATE = "register_date"

private const val TABLE_NAME = "veterinarians"

class VeterinarianRepository @Inject constructor() : IVeterinarianRepository {
    @Inject
    lateinit var db: SQLiteDatabase

    private fun createVeterinarian(cursor: Cursor) : Veterinarian
    {
        return Veterinarian().apply {
            diplomaNo = cursor.getLong(0)
            citizenId = cursor.getString(1)
            firstName = cursor.getString(2)
            middleName = cursor.getString(3)
            lastName = cursor.getString(4)
            birthDate = DateTimeConvertUtil.toLocalDateTime(cursor.getLong(5))
            registerDate = DateTimeConvertUtil.toLocalDateTime(cursor.getLong(6))
        }
    }

    private fun findByDiplomaNoQueryCallback(cursor: Cursor) : MutableIterable<Veterinarian>
    {
        val veterinarians = ArrayList<Veterinarian>()

        if (!cursor.moveToFirst())
            return veterinarians

        do
            veterinarians.add(createVeterinarian(cursor))
        while (cursor.moveToFirst())

        return veterinarians
    }

    override fun findByDiplomaNo(diplomaNo: Long): MutableIterable<Veterinarian> {
        val projection = arrayOf(DIPLOMA_NO, CITIZEN_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, BIRTH_DATE, REGISTER_DATE)

        db.rawQuery("select * from $TABLE_NAME where diplomaNo = $diplomaNo", projection)
            .use { return findByDiplomaNoQueryCallback(it) }
    }

    override fun <S : Veterinarian?> save(veterinarian: S): S {
        val cv = ContentValues()

        cv.put(DIPLOMA_NO, veterinarian?.diplomaNo)
        cv.put(CITIZEN_ID, veterinarian?.citizenId)
        cv.put(FIRST_NAME, veterinarian?.firstName)
        cv.put(MIDDLE_NAME, veterinarian?.middleName)
        cv.put(LAST_NAME, veterinarian?.lastName)
        cv.put(BIRTH_DATE, DateTimeConvertUtil.toMilliseconds(veterinarian?.birthDate))
        cv.put(REGISTER_DATE, DateTimeConvertUtil.toMilliseconds(veterinarian?.registerDate))

        val veterinarianId = db.insert(TABLE_NAME, null, cv)

        if (veterinarianId == -1L)
            throw SQLiteException("VeterinarianRepository.save")

        return veterinarian.also { it?.diplomaNo = veterinarianId }
    }

    //Not implemented methods

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Veterinarian?) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<Veterinarian>?) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Long>?) {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long?) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Long?): Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableIterable<Veterinarian> {
        TODO("Not yet implemented")
    }

    override fun findAllById(id: MutableIterable<Long>?): MutableIterable<Veterinarian> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long?): Optional<Veterinarian> {
        TODO("Not yet implemented")
    }

    override fun <S : Veterinarian?> saveAll(entities: MutableIterable<S>?): MutableIterable<S> {
        TODO("Not yet implemented")
    }
}



