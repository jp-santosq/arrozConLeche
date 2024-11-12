package com.example.awaq1.data.formularioUno

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Formulario1")
data class FormularioUnoEntity(
    val transecto: String,
    val tipoAnimal: String,
    val nombreComun: String,
    val nombreCientifico: String,
    val numeroIndividuos: String,
    val tipoObservacion: String,
    val observaciones: String,
) {
    // Excluye id de las funciones autogeneradas (equals, copy, hashCode...)
    // https://kotlinlang.org/docs/data-classes.html#properties-declared-in-the-class-body
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
