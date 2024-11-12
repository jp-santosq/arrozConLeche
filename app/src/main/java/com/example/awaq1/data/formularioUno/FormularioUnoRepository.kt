package com.example.awaq1.data.formularioUno

import kotlinx.coroutines.flow.Flow

interface FormularioUnoRepository {

    fun getAllFormularioUnosStream(): Flow<List<FormularioUnoEntity>>
    fun getFormularioUnoStream(id: Int): Flow<FormularioUnoEntity?>
    fun getTotalCount(): Flow<Int>
    fun getCountSent(): Flow<Int>
    fun getCountSaved(): Flow<Int>

    suspend fun insertFormularioUno(item: FormularioUnoEntity)
    suspend fun deleteFormularioUno(item: FormularioUnoEntity)
    suspend fun updateFormularioUno(item: FormularioUnoEntity)

}