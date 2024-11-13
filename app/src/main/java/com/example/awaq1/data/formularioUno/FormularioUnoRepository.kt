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

    // FormularioTres
    fun getAllFormularioTresStream(): Flow<List<FormularioTresEntity>>
    fun getFormularioTresStream(id: Int): Flow<FormularioTresEntity?>

    suspend fun insertFormularioTres(item: FormularioTresEntity)
    suspend fun deleteFormularioTres(item: FormularioTresEntity)
    suspend fun updateFormularioTres(item: FormularioTresEntity)

    // FormularioCuatro
    fun getAllFormularioCuatroStream(): Flow<List<FormularioCuatroEntity>>
    fun getFormularioCuatroStream(id: Int): Flow<FormularioCuatroEntity?>

    suspend fun insertFormularioCuatro(item: FormularioCuatroEntity)
    suspend fun deleteFormularioCuatro(item: FormularioCuatroEntity)
    suspend fun updateFormularioCuatro(item: FormularioCuatroEntity)

}
