package com.example.awaq1.data

import android.content.Context
import com.example.awaq1.data.formularios.FormulariosRepository
import com.example.awaq1.data.formularios.OfflineFormulariosRepository
import com.example.awaq1.data.usuario.UsuariosRepository

// Esto luego lo extendemos con los otros repositorios
interface AppContainer {
    val formulariosRepository: FormulariosRepository
    val usuariosRepository: UsuariosRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val formulariosRepository: FormulariosRepository by lazy {
        OfflineFormulariosRepository(
            formularioUnoDAO = FormulariosDatabase.getDatabase(context).formulario1Dao(),
            formularioDosDAO = FormulariosDatabase.getDatabase(context).formulario2Dao(),
            formularioTresDAO = FormulariosDatabase.getDatabase(context).formulario3Dao(),
            formularioCuatroDAO = FormulariosDatabase.getDatabase(context).formulario4Dao()
        )
    }

    override val usuariosRepository: UsuariosRepository by lazy {
        UsuariosRepository(
            usuarioDAO = FormulariosDatabase.getDatabase(context).usuarioDAO(),
            formularioUnoDAO = FormulariosDatabase.getDatabase(context).formulario1Dao(),
            usuarioFormulario1DAO = FormulariosDatabase.getDatabase(context).usuarioFormulario1DAO()
        )
    }
}

