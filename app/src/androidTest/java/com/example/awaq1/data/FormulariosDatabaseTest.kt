package com.example.awaq1.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.awaq1.data.formularios.FormularioUnoDAO
import com.example.awaq1.data.formularios.FormularioUnoEntity
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FormulariosDatabaseTest : TestCase() {
    private lateinit var db: FormulariosDatabase
    private lateinit var dao: FormularioUnoDAO

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, FormulariosDatabase::class.java).build()
        dao = db.formulario1Dao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun appDataBase_AddData_InsertedData() = runBlocking {
        val formulario = FormularioUnoEntity(
            "1", "12", "Mamifero", "Perro", "Can", "3", "Visto", "4", "4", 11.1, 12.11, "11", "11"
        )
        dao.insert(formulario)
        val forms = dao.getAllFormularioUnoEntities().first()
        assertTrue(forms.contains(formulario))
    }
}
