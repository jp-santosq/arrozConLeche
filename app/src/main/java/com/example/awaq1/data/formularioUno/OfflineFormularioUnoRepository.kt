package com.example.awaq1.data.formularioUno

import kotlinx.coroutines.flow.Flow

class OfflineFormularioUnoRepository(private val formularioUnoDAO: FormularioUnoDAO):
    FormularioUnoRepository {
    override fun getAllFormularioUnosStream(): Flow<List<FormularioUnoEntity>> = formularioUnoDAO.getAllFormularioUnoEntitys()
    override fun getFormularioUnoStream(id: Int): Flow<FormularioUnoEntity?> = formularioUnoDAO.getFormularioUnoEntity(id)
    override suspend fun insertFormularioUno(item: FormularioUnoEntity) = formularioUnoDAO.insert(item)
    override suspend fun deleteFormularioUno(item: FormularioUnoEntity) = formularioUnoDAO.delete(item)
    override suspend fun updateFormularioUno(item: FormularioUnoEntity) = formularioUnoDAO.update(item)
    override fun getTotalCount(): Flow<Int> = formularioUnoDAO.getTotalCount()
    override fun getCountSent(): Flow<Int> = formularioUnoDAO.getCountSent()
    override fun getCountSaved(): Flow<Int> = formularioUnoDAO.getCountSaved()


}
