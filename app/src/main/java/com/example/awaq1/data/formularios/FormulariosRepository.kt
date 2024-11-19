package com.example.awaq1.data.formularios

import kotlinx.coroutines.flow.Flow

interface FormulariosRepository {

    // FormularioUno
    fun getAllFormularioUnosStream(): Flow<List<FormularioUnoEntity>>
    fun getFormularioUnoStream(id: Long): Flow<FormularioUnoEntity?>

    suspend fun insertFormularioUno(item: FormularioUnoEntity)
    suspend fun deleteFormularioUno(item: FormularioUnoEntity)
    suspend fun updateFormularioUno(item: FormularioUnoEntity)

    // FormularioDos
    fun getAllFormularioDosesStream(): Flow<List<FormularioDosEntity>>
    fun getFormularioDosStream(id: Long): Flow<FormularioDosEntity?>

    suspend fun insertFormularioDos(item: FormularioDosEntity)
    suspend fun deleteFormularioDos(item: FormularioDosEntity)
    suspend fun updateFormularioDos(item: FormularioDosEntity)

    // FormularioTres
    fun getAllFormularioTresStream(): Flow<List<FormularioTresEntity>>
    fun getFormularioTresStream(id: Long): Flow<FormularioTresEntity?>

    suspend fun insertFormularioTres(item: FormularioTresEntity)
    suspend fun deleteFormularioTres(item: FormularioTresEntity)
    suspend fun updateFormularioTres(item: FormularioTresEntity)

    // FormularioCuatro
    fun getAllFormularioCuatroStream(): Flow<List<FormularioCuatroEntity>>
    fun getFormularioCuatroStream(id: Long): Flow<FormularioCuatroEntity?>

    suspend fun insertFormularioCuatro(item: FormularioCuatroEntity)
    suspend fun deleteFormularioCuatro(item: FormularioCuatroEntity)
    suspend fun updateFormularioCuatro(item: FormularioCuatroEntity)

    // General Formularios

    fun getFormularioUnoCount(): Flow<Int>
    fun getFormularioDosCount(): Flow<Int>
    fun getFormularioTresCount(): Flow<Int>
    fun getFormularioCuatroCount(): Flow<Int>

    fun getAllFormulariosCount(): Flow<Int>
}
