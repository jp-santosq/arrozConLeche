package com.example.awaq1.data.formularioUno

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OfflineFormularioUnoRepository(private val formularioUnoDAO: FormularioUnoDAO): FormularioUnoRepository {
    override fun getAllFormularioUnosStream(): Flow<List<FormularioUnoEntity>> = formularioUnoDAO.getAllFormularioUnoEntitys()
    override fun getFormularioUnoStream(id: Int): Flow<FormularioUnoEntity?> = formularioUnoDAO.getFormularioUnoEntity(id)
    override suspend fun insertFormularioUno(item: FormularioUnoEntity) = formularioUnoDAO.insert(item)
    override suspend fun deleteFormularioUno(item: FormularioUnoEntity) = formularioUnoDAO.delete(item)
    override suspend fun updateFormularioUno(item: FormularioUnoEntity) = formularioUnoDAO.update(item)

    
    override fun getAllFormularioDosesStream(): Flow<List<FormularioDosEntity>> = formularioUnoDAO.getAllFormularioDosEntitys()
    override fun getFormularioDosStream(id: Int): Flow<FormularioDosEntity?> = formularioUnoDAO.getFormularioDosEntity(id)
    override suspend fun insertFormularioDos(item: FormularioDosEntity) = formularioUnoDAO.insert(item)
    override suspend fun deleteFormularioDos(item: FormularioDosEntity) = formularioUnoDAO.delete(item)
    override suspend fun updateFormularioDos(item: FormularioDosEntity) = formularioUnoDAO.update(item)
    
    override fun getAllFormularioTresStream(): Flow<List<FormularioTresEntity>> = formularioUnoDAO.getAllFormularioTresEntitys()
    override fun getFormularioTresStream(id: Int): Flow<FormularioTresEntity?> = formularioUnoDAO.getFormularioTresEntity(id)
    override suspend fun insertFormularioTres(item: FormularioTresEntity) = formularioUnoDAO.insert(item)
    override suspend fun deleteFormularioTres(item: FormularioTresEntity) = formularioUnoDAO.delete(item)
    override suspend fun updateFormularioTres(item: FormularioTresEntity) = formularioUnoDAO.update(item)
    
    override fun getAllFormularioCuatroStream(): Flow<List<FormularioCuatroEntity>> = formularioUnoDAO.getAllFormularioCuatroEntitys()
    override fun getFormularioCuatroStream(id: Int): Flow<FormularioCuatroEntity?> = formularioUnoDAO.getFormularioCuatroEntity(id)
    override suspend fun insertFormularioCuatro(item: FormularioCuatroEntity) = formularioUnoDAO.insert(item)
    override suspend fun deleteFormularioCuatro(item: FormularioCuatroEntity) = formularioUnoDAO.delete(item)
    override suspend fun updateFormularioCuatro(item: FormularioCuatroEntity) = formularioUnoDAO.update(item)

    override fun getFormularioUnoCount(): Flow<Int> = flow {
        emit(formularioUnoDAO.getFormularioUnoCount())
    }
    override fun getFormularioDosCount(): Flow<Int> = flow {
        emit(formularioUnoDAO.getFormularioDosCount())
    }
    override fun getFormularioTresCount(): Flow<Int> = flow {
        emit(formularioUnoDAO.getFormularioTresCount())
    }
    override fun getFormularioCuatroCount(): Flow<Int> = flow {
        emit(formularioUnoDAO.getFormularioCuatroCount())
    }
}
