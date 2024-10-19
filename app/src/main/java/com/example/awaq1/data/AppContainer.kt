package com.example.awaq1.data

import android.content.Context

// Esto luego lo extendemos con los otros repositorios
interface AppContainer {
    val formularioUnoRepository: FormularioUnoRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val formularioUnoRepository: FormularioUnoRepository by lazy {
        OfflineFormularioUnoRepository(FormulariosDatabase.getDatabase(context).formulario1Dao())
    }
}

