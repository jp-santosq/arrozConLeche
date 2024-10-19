package com.example.awaq1.data

import androidx.room.Database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FormularioUnoEntity::class], version = 1, exportSchema = false)
abstract class FormulariosDatabase : RoomDatabase() {
    abstract fun formulario1Dao(): FormularioUnoDAO

    companion object {
        @Volatile
        private var Instance: FormulariosDatabase? = null

        fun getDatabase(context: Context): FormulariosDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FormulariosDatabase::class.java, "formularios_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}