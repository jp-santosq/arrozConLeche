package com.example.awaq1.data.usuario

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.awaq1.data.formularios.FormularioUnoEntity
import kotlinx.coroutines.flow.Flow


// DAO for UsuarioEntity
@Dao
interface UsuarioDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuario: UsuarioEntity): Long

    @Update
    suspend fun update(usuario: UsuarioEntity)

    @Delete
    suspend fun delete(usuario: UsuarioEntity)

    @Query("SELECT * from Usuarios WHERE id = :id")
    fun getUsuarioById(id: Int): Flow<UsuarioEntity?>

    @Query("SELECT * from Usuarios ORDER BY id ASC")
    fun getAllUsuarios(): Flow<List<UsuarioEntity>>

    @Query("SELECT id FROM Usuarios WHERE username = :username")
    suspend fun getUserIdByUsername(username: String): Int?

    @Query("UPDATE Usuarios SET lastAccess = :lastAccess WHERE id = :userId")
    suspend fun updateLastAccess(userId: Int, lastAccess: String)

    @Query("UPDATE Usuarios SET lastLogin = :lastLogin WHERE id = :userId")
    suspend fun updateLastLogin(userId: Int, lastLogin: String)
}

// DAO for UsuarioFormulario1Entity
@Dao
interface UsuarioFormulario1DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioFormulario: UsuarioFormulario1Entity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFromParts(usuario: UsuarioEntity, formulario: FormularioUnoEntity)

    @Update
    suspend fun update(usuarioFormulario: UsuarioFormulario1Entity)

    @Delete
    suspend fun delete(usuarioFormulario: UsuarioFormulario1Entity)

    @Query("SELECT * from UsuarioFormulario1 WHERE usuarioId = :usuarioId")
    fun getFormulariosForUsuario(usuarioId: Int): Flow<List<UsuarioFormulario1Entity>>

    @Query("SELECT * from UsuarioFormulario1 WHERE formId = :formId")
    fun getUsuariosForFormulario(formId: Int): Flow<List<UsuarioFormulario1Entity>>
}

// DAO for UsuarioFormulario2Entity
@Dao
interface UsuarioFormulario2DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioFormulario: UsuarioFormulario2Entity)

    @Update
    suspend fun update(usuarioFormulario: UsuarioFormulario2Entity)

    @Delete
    suspend fun delete(usuarioFormulario: UsuarioFormulario2Entity)

    @Query("SELECT * from UsuarioFormulario2 WHERE usuarioId = :usuarioId")
    fun getFormulariosForUsuario(usuarioId: Int): Flow<List<UsuarioFormulario2Entity>>

    @Query("SELECT * from UsuarioFormulario2 WHERE formId = :formId")
    fun getUsuariosForFormulario(formId: Int): Flow<List<UsuarioFormulario2Entity>>
}

// DAO for UsuarioFormulario3Entity
@Dao
interface UsuarioFormulario3DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioFormulario: UsuarioFormulario3Entity)

    @Update
    suspend fun update(usuarioFormulario: UsuarioFormulario3Entity)

    @Delete
    suspend fun delete(usuarioFormulario: UsuarioFormulario3Entity)

    @Query("SELECT * from UsuarioFormulario3 WHERE usuarioId = :usuarioId")
    fun getFormulariosForUsuario(usuarioId: Int): Flow<List<UsuarioFormulario3Entity>>

    @Query("SELECT * from UsuarioFormulario3 WHERE formId = :formId")
    fun getUsuariosForFormulario(formId: Int): Flow<List<UsuarioFormulario3Entity>>
}

// DAO for UsuarioFormulario4Entity
@Dao
interface UsuarioFormulario4DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(usuarioFormulario: UsuarioFormulario4Entity)

    @Update
    suspend fun update(usuarioFormulario: UsuarioFormulario4Entity)

    @Delete
    suspend fun delete(usuarioFormulario: UsuarioFormulario4Entity)

    @Query("SELECT * from UsuarioFormulario4 WHERE usuarioId = :usuarioId")
    fun getFormulariosForUsuario(usuarioId: Int): Flow<List<UsuarioFormulario4Entity>>

    @Query("SELECT * from UsuarioFormulario4 WHERE formId = :formId")
    fun getUsuariosForFormulario(formId: Int): Flow<List<UsuarioFormulario4Entity>>
}
