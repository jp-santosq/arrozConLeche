package com.example.awaq1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.awaq1.data.formularioUno.FormularioUnoDAO
import com.example.awaq1.data.formularioUno.FormularioUnoEntity

@Database(entities = [FormularioUnoEntity::class], version = 1, exportSchema = false)
abstract class FormularioUnoDatabase : RoomDatabase() {

    abstract fun formularioUnoDao(): FormularioUnoDAO
}
