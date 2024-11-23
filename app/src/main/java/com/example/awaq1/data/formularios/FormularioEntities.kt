package com.example.awaq1.data.formularios

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = Date()
    return dateFormat.format(date)
}


// Fauna en Transectos
@Entity(tableName = "Formulario1")
data class FormularioUnoEntity(
    var transecto: String,
    var clima: String,
    var temporada: String,
    var tipoAnimal: String,
    var nombreComun: String,
    var nombreCientifico: String,
    var numeroIndividuos: String,
    var tipoObservacion: String,
    var observaciones: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val fecha: String = getCurrentDate()
) {
    // Excluye id de las funciones autogeneradas (equals, copy, hashCode...)
    // https://kotlinlang.org/docs/data-classes.html#properties-declared-in-the-class-body
    // Tiene que ser var, o necesitara accesors (get, set)
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun withID(id: Long): FormularioUnoEntity {
        val newForm = this.copy()
        newForm.id = id
        return newForm
    }
}


// Fauna en Punto de Conteo
@Entity(tableName = "Formulario2")
data class FormularioDosEntity(
    var zona: String,
    var clima: String,
    var temporada: String,
    var tipoAnimal: String,
    var nombreComun: String,
    var nombreCientifico: String,
    var numeroIndividuos: String,
    var tipoObservacion: String,
    var alturaObservacion: String,
    var observaciones: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val fecha: String = getCurrentDate()

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun withID(id: Long): FormularioDosEntity {
        val newForm = this.copy()
        newForm.id = id
        return newForm
    }
}

// Validacion de cobertura
@Entity(tableName = "Formulario3")
data class FormularioTresEntity(
    val codigo: String,
    var clima: String,
    var temporada: String,
    val seguimiento: Boolean,
    val cambio: Boolean,
    val cobertura: String,
    val tipoCultivo: String,
    val disturbio: String,
    val observaciones: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val fecha: String = getCurrentDate()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun withID(id: Long): FormularioTresEntity {
        val newForm = this.copy()
        newForm.id = id
        return newForm
    }
}

// Parcela de vegetacion
@Entity(tableName = "Formulario4")
data class FormularioCuatroEntity(
    val codigo: String,
    var clima: String,
    var temporada: String,
    val quad_a: String,
    val quad_b: String,
    val sub_quad: String,
    val habitoDeCrecimiento: String,
    val nombreComun: String,
    val nombreCientifico: String,
    val placa: String,
    val circunferencia: String,
    val distancia: String,
    // Que es esto?? Dice "Estatura Biomonitor en mt" en Figma
    val estatura: String,
    val altura: String,
    val observaciones: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val fecha: String = getCurrentDate()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun withID(id: Long): FormularioCuatroEntity {
        val newForm = this.copy()
        newForm.id = id
        return newForm
    }
}

//Fauna Busqueda Libre
@Entity(tableName = "Formulario5")
data class FormularioCincoEntity(
    var zona: String,
    var clima: String,
    var temporada: String,
    var tipoAnimal: String,
    var nombreComun: String,
    var nombreCientifico: String,
    var numeroIndividuos: String,
    var tipoObservacion: String,
    var alturaObservacion: String,
    var observaciones: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val fecha: String = getCurrentDate()


) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun withID(id: Long): FormularioCincoEntity {
        val newForm = this.copy()
        newForm.id = id
        return newForm
    }
}

//Camaras Trampa
@Entity(tableName = "Formulario6")
data class FormularioSeisEntity(
    val codigo: String,
    var clima: String,
    var temporada: String,
    val zona: String,
    val nombreCamara: String,
    val placaCamara: String,
    val placaGuaya: String,
    val anchoCamino: String,
    val fechaInstalacion: String,
    val distanciaObjetivo: String,
    val alturaLente: String,
    val checklist: String,
    var observaciones: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val fecha: String = getCurrentDate()

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun withID(id: Long): FormularioSeisEntity {
        val newForm = this.copy()
        newForm.id = id
        return newForm
    }
}

//Variables Climaticas
@Entity(tableName = "Formulario7")
data class FormularioSieteEntity(
    var clima: String,
    var temporada: String,
    val zona: String,
    val pluviosidad: String,
    val temperaturaMaxima: String,
    val humedadMaxima: String,
    val temperaturaMinima: String,
    val nivelQuebrada: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val fecha: String = getCurrentDate()

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun withID(id: Long): FormularioSieteEntity {
        val newForm = this.copy()
        newForm.id = id
        return newForm
    }
}