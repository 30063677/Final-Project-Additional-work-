package com.umdaa.nurseasst.Database

import android.content.Context
import androidx.room.*
import androidx.room.Database
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.umdaa.nurseasst.Objects.Patient
import com.umdaa.nurseasst.Objects.Vitals

@Database(entities = [Patient::class, Vitals::class], version = 1)
abstract class NurseDatabase : RoomDatabase() {

    abstract fun commonDao(): CommonDao

    companion object {


        @Volatile
        private var databaseInstance: NurseDatabase? = null

        internal fun getDatabase(context: Context): NurseDatabase? {
            if (databaseInstance == null) {
                synchronized(Database::class.java) {
                    if (databaseInstance == null) {
                        databaseInstance = Room.databaseBuilder<NurseDatabase>(
                            context.applicationContext,
                            NurseDatabase::class.java!!, "umdaa-nurse-database"
                        ).build()
                    }
                }

            }
            return databaseInstance
        }
    }
}
