package com.example.awaq1.data

import androidx.room.Database
import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.awaq1.data.formularioUno.FormularioDosEntity
import com.example.awaq1.data.formularioUno.FormularioUnoDAO
import com.example.awaq1.data.formularioUno.FormularioUnoEntity


@Database(entities = [FormularioUnoEntity::class, FormularioDosEntity::class], version = 1, exportSchema = true)
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
