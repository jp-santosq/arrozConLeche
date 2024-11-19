package com.example.awaq1.data.formularios

import androidx.room.Entity
import androidx.room.PrimaryKey

// Fauna en Transectos
@Entity(tableName = "Formulario1")
data class FormularioUnoEntity(
    var transecto: String,
    var tipoAnimal: String,
    var nombreComun: String,
    var nombreCientifico: String,
    var numeroIndividuos: String,
    var tipoObservacion: String,
    var observaciones: String,
) {
    // Excluye id de las funciones autogeneradas (equals, copy, hashCode...)
    // https://kotlinlang.org/docs/data-classes.html#properties-declared-in-the-class-body
    // Tiene que ser var, o necesitara accesors (get, set)
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    fun withID(id: Long): FormularioUnoEntity {
        val newForm = this.copy()
        newForm.id = id
        newForm.transecto = "huhuhu"
        return newForm
    }
}


// Fauna en Punto de Conteo
@Entity(tableName = "Formulario2")
data class FormularioDosEntity(
    var zona: String,
    var tipoAnimal: String,
    var nombreComun: String,
    var nombreCientifico: String,
    var numeroIndividuos: String,
    var tipoObservacion: String,
    var alturaObservacion: String,
    var observaciones: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

// Validacion de cobertura
@Entity(tableName = "Formulario3")
data class FormularioTresEntity(
    val codigo: String,
    val seguimiento: Boolean,
    val cambio: Boolean,
    val cobertura: String,
    val tipoCultivo: String,
    val disturbio: String,
    val observaciones: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

// Parcela de vegetacion
@Entity(tableName = "Formulario4")
data class FormularioCuatroEntity(
    val codigo: String,
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
    val observaciones: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

