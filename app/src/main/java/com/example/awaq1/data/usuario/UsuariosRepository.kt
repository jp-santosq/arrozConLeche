package com.example.awaq1.data.usuario

import androidx.room.Transaction
import com.example.awaq1.data.formularios.FormularioUnoDAO
import com.example.awaq1.data.formularios.FormularioUnoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

// Repository for handling user-related operations, including associations with formularios
class UsuariosRepository(
    private val usuarioDAO: UsuarioDAO,
    private val usuarioFormulario1DAO: UsuarioFormulario1DAO,
    private val usuarioFormulario2DAO: UsuarioFormulario2DAO,
    private val usuarioFormulario3DAO: UsuarioFormulario3DAO,
    private val usuarioFormulario4DAO: UsuarioFormulario4DAO
) {

    // User operations
    suspend fun insertUser(usuario: UsuarioEntity): Long = usuarioDAO.insert(usuario)

    suspend fun updateUser(usuario: UsuarioEntity) = usuarioDAO.update(usuario)

    suspend fun deleteUser(usuario: UsuarioEntity) = usuarioDAO.delete(usuario)

    fun getUserById(id: Int): Flow<UsuarioEntity?> = usuarioDAO.getUsuarioById(id)

    fun getAllUsers(): Flow<List<UsuarioEntity>> = usuarioDAO.getAllUsuarios()

    suspend fun getUserIdByUsername(username: String): Int? = usuarioDAO.getUserIdByUsername(username)

    suspend fun updateLastAccess(userId: Int, lastAccess: String) = usuarioDAO.updateLastAccess(userId, lastAccess)

    suspend fun updateLastLogin(userId: Int, lastLogin: String) = usuarioDAO.updateLastLogin(userId, lastLogin)

    
    // UsuarioFormulario1 operations (associations)
    @Transaction
    suspend fun insertUserWithFormularioUno(usuario: UsuarioEntity, formulario: UsuarioFormulario1Entity) {
        val userId = insertUser(usuario)
        if (userId == -1L) throw Exception("Failed to insert user")

        val usuarioFormulario = formulario.copy(usuarioId = userId.toInt())
        usuarioFormulario1DAO.insert(usuarioFormulario)
    }

    fun getFormulariosForUsuarioUno(usuarioId: Int): Flow<List<UsuarioFormulario1Entity>> = usuarioFormulario1DAO.getFormulariosForUsuario(usuarioId)

    fun getUsuariosForFormularioUno(formId: Int): Flow<List<UsuarioFormulario1Entity>> = usuarioFormulario1DAO.getUsuariosForFormulario(formId)

    // UsuarioFormulario2 operations
    @Transaction
    suspend fun insertUserWithFormularioDos(usuario: UsuarioEntity, formulario: UsuarioFormulario2Entity) {
        val userId = insertUser(usuario)
        if (userId == -1L) throw Exception("Failed to insert user")

        val usuarioFormulario = formulario.copy(usuarioId = userId.toInt())
        usuarioFormulario2DAO.insert(usuarioFormulario)
    }

    fun getFormulariosForUsuarioDos(usuarioId: Int): Flow<List<UsuarioFormulario2Entity>> = usuarioFormulario2DAO.getFormulariosForUsuario(usuarioId)

    fun getUsuariosForFormularioDos(formId: Int): Flow<List<UsuarioFormulario2Entity>> = usuarioFormulario2DAO.getUsuariosForFormulario(formId)

    // UsuarioFormulario3 operations
    @Transaction
    suspend fun insertUserWithFormularioTres(usuario: UsuarioEntity, formulario: UsuarioFormulario3Entity) {
        val userId = insertUser(usuario)
        if (userId == -1L) throw Exception("Failed to insert user")

        val usuarioFormulario = formulario.copy(usuarioId = userId.toInt())
        usuarioFormulario3DAO.insert(usuarioFormulario)
    }

    fun getFormulariosForUsuarioTres(usuarioId: Int): Flow<List<UsuarioFormulario3Entity>> = usuarioFormulario3DAO.getFormulariosForUsuario(usuarioId)

    fun getUsuariosForFormularioTres(formId: Int): Flow<List<UsuarioFormulario3Entity>> = usuarioFormulario3DAO.getUsuariosForFormulario(formId)

    // UsuarioFormulario4 operations
    @Transaction
    suspend fun insertUserWithFormularioCuatro(usuario: UsuarioEntity, formulario: UsuarioFormulario4Entity) {
        val userId = insertUser(usuario)
        if (userId == -1L) throw Exception("Failed to insert user")

        val usuarioFormulario = formulario.copy(usuarioId = userId.toInt())
        usuarioFormulario4DAO.insert(usuarioFormulario)
    }

    fun getFormulariosForUsuarioCuatro(usuarioId: Int): Flow<List<UsuarioFormulario4Entity>> = usuarioFormulario4DAO.getFormulariosForUsuario(usuarioId)

    fun getUsuariosForFormularioCuatro(formId: Int): Flow<List<UsuarioFormulario4Entity>> = usuarioFormulario4DAO.getUsuariosForFormulario(formId)
}

