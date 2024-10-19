package com.example.awaq1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Formulario1")
data class FormularioUnoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val transecto: String,
    val tipoAnimal: String,
    val nombreComun: String,
    val nombreCientifico: String,
    val numeroIndividuos: String,
    val tipoObservacion: String,
    val observaciones: String,
)
