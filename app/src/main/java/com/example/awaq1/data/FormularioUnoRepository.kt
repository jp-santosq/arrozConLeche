package com.example.awaq1.data

import kotlinx.coroutines.flow.Flow

interface FormularioUnoRepository {

    fun getAllFormularioUnosStream(): Flow<List<FormularioUnoEntity>>
    fun getFormularioUnoStream(id: Int): Flow<FormularioUnoEntity?>

    suspend fun insertFormularioUno(item: FormularioUnoEntity)
    suspend fun deleteFormularioUno(item: FormularioUnoEntity)
    suspend fun updateFormularioUno(item: FormularioUnoEntity)
}