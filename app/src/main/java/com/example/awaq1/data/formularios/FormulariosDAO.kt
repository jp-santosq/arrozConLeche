package com.example.awaq1.data.formularios

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
    suspend fun insert(item: FormularioUnoEntity): Long

    @Update
    suspend fun update(item: FormularioUnoEntity)

    @Delete
    suspend fun delete(item: FormularioUnoEntity)

    @Query("SELECT * from Formulario1 WHERE id = :id")
    fun getFormularioUnoEntity(id: Long): Flow<FormularioUnoEntity?>

    @Query("SELECT * from Formulario1 ORDER BY id ASC")
    fun getAllFormularioUnoEntities(): Flow<List<FormularioUnoEntity>>

    @Query("SELECT COUNT(*) FROM Formulario1")
    suspend fun getFormularioUnoCount(): Int
}

@Dao
interface FormularioDosDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FormularioDosEntity): Long

    @Update
    suspend fun update(item: FormularioDosEntity)

    @Delete
    suspend fun delete(item: FormularioDosEntity)

    @Query("SELECT * from Formulario2 WHERE id = :id")
    fun getFormularioDosEntity(id: Long): Flow<FormularioDosEntity?>

    @Query("SELECT * from Formulario2 ORDER BY id ASC")
    fun getAllFormularioDosEntities(): Flow<List<FormularioDosEntity>>

    @Query("SELECT COUNT(*) FROM Formulario2")
    suspend fun getFormularioDosCount(): Int
}

@Dao
interface FormularioTresDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FormularioTresEntity): Long

    @Update
    suspend fun update(item: FormularioTresEntity)

    @Delete
    suspend fun delete(item: FormularioTresEntity)

    @Query("SELECT * from Formulario3 WHERE id = :id")
    fun getFormularioTresEntity(id: Long): Flow<FormularioTresEntity?>

    @Query("SELECT * from Formulario3 ORDER BY id ASC")
    fun getAllFormularioTresEntities(): Flow<List<FormularioTresEntity>>

    @Query("SELECT COUNT(*) FROM Formulario3")
    suspend fun getFormularioTresCount(): Int
}

@Dao
interface FormularioCuatroDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: FormularioCuatroEntity): Long

    @Update
    suspend fun update(item: FormularioCuatroEntity)

    @Delete
    suspend fun delete(item: FormularioCuatroEntity)

    @Query("SELECT * from Formulario4 WHERE id = :id")
    fun getFormularioCuatroEntity(id: Long): Flow<FormularioCuatroEntity?>

    @Query("SELECT * from Formulario4 ORDER BY id ASC")
    fun getAllFormularioCuatroEntities(): Flow<List<FormularioCuatroEntity>>

    @Query("SELECT COUNT(*) FROM Formulario4")
    suspend fun getFormularioCuatroCount(): Int
}
