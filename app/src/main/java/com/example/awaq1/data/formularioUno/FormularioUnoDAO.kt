package com.example.awaq1.data.formularioUno

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FormularioUnoDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FormularioUnoEntity)

    @Update
    suspend fun update(item: FormularioUnoEntity)

    @Delete
    suspend fun delete(item: FormularioUnoEntity)

    @Query("SELECT * from Formulario1 WHERE id = :id")
    fun getFormularioUnoEntity(id: Int): Flow<FormularioUnoEntity>

    @Query("SELECT * from Formulario1 ORDER BY id ASC")
    fun getAllFormularioUnoEntitys(): Flow<List<FormularioUnoEntity>>
}
