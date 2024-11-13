package com.example.awaq1.data.formularioUno

import kotlinx.coroutines.flow.Flow

interface FormularioUnoRepository {

    // FormularioUno
    fun getAllFormularioUnosStream(): Flow<List<FormularioUnoEntity>>
    fun getFormularioUnoStream(id: Int): Flow<FormularioUnoEntity?>

    suspend fun insertFormularioUno(item: FormularioUnoEntity)
    suspend fun deleteFormularioUno(item: FormularioUnoEntity)
    suspend fun updateFormularioUno(item: FormularioUnoEntity)

    // FormularioDos
    fun getAllFormularioDosesStream(): Flow<List<FormularioDosEntity>>
    fun getFormularioDosStream(id: Int): Flow<FormularioDosEntity?>

    suspend fun insertFormularioDos(item: FormularioDosEntity)
    suspend fun deleteFormularioDos(item: FormularioDosEntity)
    suspend fun updateFormularioDos(item: FormularioDosEntity)

}
