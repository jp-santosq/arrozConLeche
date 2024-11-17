package com.example.awaq1.data.usuario

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.awaq1.data.formularios.FormularioCuatroEntity
import com.example.awaq1.data.formularios.FormularioDosEntity
import com.example.awaq1.data.formularios.FormularioTresEntity
import com.example.awaq1.data.formularios.FormularioUnoEntity
import kotlinx.coroutines.flow.Flow

// User Entity
@Entity(tableName = "Usuarios")
data class UsuarioEntity(
    val username: String,
    val lastAccess: String,  // Assuming datetime as String for simplicity
    val lastLogin: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

// Usuario Formulario 1 Association Entity
@Entity(
    tableName = "UsuarioFormulario1",
    foreignKeys = [
        ForeignKey(entity = UsuarioEntity::class, parentColumns = ["id"], childColumns = ["usuarioId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = FormularioUnoEntity::class, parentColumns = ["id"], childColumns = ["formId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class UsuarioFormulario1Entity(
    val usuarioId: Long,
    val formId: Long,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

// Usuario Formulario 2 Association Entity
@Entity(
    tableName = "UsuarioFormulario2",
    foreignKeys = [
        ForeignKey(entity = UsuarioEntity::class, parentColumns = ["id"], childColumns = ["usuarioId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = FormularioDosEntity::class, parentColumns = ["id"], childColumns = ["formId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class UsuarioFormulario2Entity(
    val usuarioId: Long,
    val formId: Long,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

// Usuario Formulario 3 Association Entity
@Entity(
    tableName = "UsuarioFormulario3",
    foreignKeys = [
        ForeignKey(entity = UsuarioEntity::class, parentColumns = ["id"], childColumns = ["usuarioId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = FormularioTresEntity::class, parentColumns = ["id"], childColumns = ["formId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class UsuarioFormulario3Entity(
    val usuarioId: Long,
    val formId: Long,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

// Usuario Formulario 4 Association Entity
@Entity(
    tableName = "UsuarioFormulario4",
    foreignKeys = [
        ForeignKey(entity = UsuarioEntity::class, parentColumns = ["id"], childColumns = ["usuarioId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = FormularioCuatroEntity::class, parentColumns = ["id"], childColumns = ["formId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class UsuarioFormulario4Entity(
    val usuarioId: Long,
    val formId: Long,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
