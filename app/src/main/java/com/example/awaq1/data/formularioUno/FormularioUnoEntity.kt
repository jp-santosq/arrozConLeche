package com.example.awaq1.data.formularioUno

import androidx.room.Entity
import androidx.room.PrimaryKey

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
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

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
    var id: Int = 0
}
