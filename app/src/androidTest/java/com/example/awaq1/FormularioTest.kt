package com.example.awaq1.data.formularioUno

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.awaq1.data.FormularioUnoDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class FormularioUnoDaoTest {

    @get:Rule

    private lateinit var database: FormularioUnoDatabase
    private lateinit var formularioUnoDao: FormularioUnoDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FormularioUnoDatabase::class.java
        ).allowMainThreadQueries().build()

        formularioUnoDao = database.formularioUnoDao()
    }

    @After
    fun teardown() {
        database.close()
    }
}
