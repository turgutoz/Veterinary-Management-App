package com.turgutcanozdemir.android.library.repo.veterinarian.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.karandev.util.data.repository.exception.RepositoryException
import dagger.hilt.android.qualifiers.ApplicationContext
import java.sql.SQLException
import javax.inject.Inject

private const val DATABASE_NAME = "veterinarianappdb"
private const val DATABASE_VERSION = 1

/*
data class VeterinarianInfo(
                        var diplomaNo: Long,
                        var citizenId: String?,
                        var firstName: String?,
                        var middleName: String?,
                        var lastName: String?,
                        var registerDate: String?
) :Serializable {
    override fun toString() = when {
        middleName != null -> "$firstName $middleName ${lastName?.uppercase()}"
        else -> "$firstName ${lastName?.uppercase()}"
    }
}
 */

private const val CREATE_VETERINARIAN_INFO = """
    CREATE TABLE veterinarian_info (
        diploma_no INTEGER,
        query_count INTEGER default(1) not null,
        query_date_time INTEGER not null,
        save_date_time INTEGER not null,
        source_service TEXT not null,
        CONSTRAINT veterinarian_info_PK PRIMARY KEY(diploma_no)
    );
"""
/*
data class VeterinarianSave(
                        var diplomaNo: Long = 0,
                        var citizenId: String? = null,
                        var firstName: String? = null,
                        var middleName: String? = null,
                        var lastName: String? = null,
                        var birthDate: String? = null,
                        var registerDate: String? =  DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now())
)

 */

private const val CREATE_VETERINARIANS = """
    CREATE TABLE veterinarians (
        veterinarian_id INTEGER primary key AUTOINCREMENT,
        diploma_no INTEGER not null,
        citizen_id TEXT not null,
        first_name TEXT not null,
        middle_name TEXT,
        last_name TEXT,
        birth_date INTEGER not null,
        register_date INTEGER not null,
        CONSTRAINT veterinarians_FK FOREIGN KEY (diploma_no) REFERENCES veterinarian_info(diploma_no)
    );
"""

private const val DROP_VETERINARIAN_INFO = "DROP TABLE veterinarian_info"
private const val DROP_VETERINARIANS = "DROP TABLE veterinarians"

class DatabaseHelper @Inject constructor(@ApplicationContext var context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase)
    {
        try {
            db.execSQL(CREATE_VETERINARIAN_INFO)
            db.execSQL(CREATE_VETERINARIANS)
        }
        catch (ex: SQLException) {
            throw RepositoryException("DatabaseHelper.onCreate")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {
        try {
            db.execSQL(DROP_VETERINARIANS)
            db.execSQL(DROP_VETERINARIAN_INFO)
            onCreate(db)
        }
        catch (ex: SQLException) {
            throw RepositoryException("DatabaseHelper.onCreate")
        }
    }
}