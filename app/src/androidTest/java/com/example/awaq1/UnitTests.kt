package com.example.awaq1

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.awaq1.data.FormulariosDatabase
import com.example.awaq1.data.formularioUno.FormularioUnoDAO
import com.example.awaq1.data.formularioUno.FormularioUnoEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UnitTests {

    private lateinit var db: FormulariosDatabase
    private lateinit var dao: FormularioUnoDAO

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, FormulariosDatabase::class.java).build()
        dao = db.formulario1Dao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun appDatabase_DaoIsInitialized_DaoIsAccessible() {
        assertNotNull("DAO should be initialized and accessible when the application starts", dao)
    }

    @Test
    fun write_Formulario1_andReadBack() {
        val form1 = FormularioUnoEntity(
            transecto = "transecto",
            tipoAnimal = "tipoAnimal",
            nombreComun = "nombreComun",
            nombreCientifico = "nombreCientifico",
            numeroIndividuos = "numeroIndividuos",
            tipoObservacion = "tipoObservacion",
            observaciones = "observaciones",
        )
        var expected : FormularioUnoEntity
        runBlocking { dao.insert(form1) }
        // Este test solo funciona si no se ha hecho otro insert
        // TODO: Generar funcion de select por algun otro dato en FormularioUnoDAO
        runBlocking {expected = dao.getAllFormularioUnoEntitys().first()[0]}
        assertEquals(form1, expected)
    }
}