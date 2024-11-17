package com.example.awaq1.data.formularios

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OfflineFormulariosRepository(
    private val formularioUnoDAO: FormularioUnoDAO,
    private val formularioDosDAO: FormularioDosDAO,
    private val formularioTresDAO: FormularioTresDAO,
    private val formularioCuatroDAO: FormularioCuatroDAO
): FormulariosRepository {
    override fun getAllFormularioUnosStream(): Flow<List<FormularioUnoEntity>> = formularioUnoDAO.getAllFormularioUnoEntities()
    override fun getFormularioUnoStream(id: Long): Flow<FormularioUnoEntity?> = formularioUnoDAO.getFormularioUnoEntity(id)
    override suspend fun insertFormularioUno(item: FormularioUnoEntity) {
        formularioUnoDAO.insert(item)
    }
    override suspend fun deleteFormularioUno(item: FormularioUnoEntity) = formularioUnoDAO.delete(item)
    override suspend fun updateFormularioUno(item: FormularioUnoEntity) = formularioUnoDAO.update(item)

    
    override fun getAllFormularioDosesStream(): Flow<List<FormularioDosEntity>> = formularioDosDAO.getAllFormularioDosEntities()
    override fun getFormularioDosStream(id: Long): Flow<FormularioDosEntity?> = formularioDosDAO.getFormularioDosEntity(id)
    override suspend fun insertFormularioDos(item: FormularioDosEntity) {
        formularioDosDAO.insert(item)
    }
    override suspend fun deleteFormularioDos(item: FormularioDosEntity) = formularioDosDAO.delete(item)
    override suspend fun updateFormularioDos(item: FormularioDosEntity) = formularioDosDAO.update(item)
    
    override fun getAllFormularioTresStream(): Flow<List<FormularioTresEntity>> = formularioTresDAO.getAllFormularioTresEntities()
    override fun getFormularioTresStream(id: Long): Flow<FormularioTresEntity?> = formularioTresDAO.getFormularioTresEntity(id)
    override suspend fun insertFormularioTres(item: FormularioTresEntity) {
        formularioTresDAO.insert(item)
    }
    override suspend fun deleteFormularioTres(item: FormularioTresEntity) = formularioTresDAO.delete(item)
    override suspend fun updateFormularioTres(item: FormularioTresEntity) = formularioTresDAO.update(item)
    
    override fun getAllFormularioCuatroStream(): Flow<List<FormularioCuatroEntity>> = formularioCuatroDAO.getAllFormularioCuatroEntities()
    override fun getFormularioCuatroStream(id: Long): Flow<FormularioCuatroEntity?> = formularioCuatroDAO.getFormularioCuatroEntity(id)
    override suspend fun insertFormularioCuatro(item: FormularioCuatroEntity) {
        formularioCuatroDAO.insert(item)
    }
    override suspend fun deleteFormularioCuatro(item: FormularioCuatroEntity) = formularioCuatroDAO.delete(item)
    override suspend fun updateFormularioCuatro(item: FormularioCuatroEntity) = formularioCuatroDAO.update(item)

    override fun getFormularioUnoCount(): Flow<Int> = flow {
        emit(formularioUnoDAO.getFormularioUnoCount())
    }
    override fun getFormularioDosCount(): Flow<Int> = flow {
        emit(formularioDosDAO.getFormularioDosCount())
    }
    override fun getFormularioTresCount(): Flow<Int> = flow {
        emit(formularioTresDAO.getFormularioTresCount())
    }
    override fun getFormularioCuatroCount(): Flow<Int> = flow {
        emit(formularioCuatroDAO.getFormularioCuatroCount())
    }
}
