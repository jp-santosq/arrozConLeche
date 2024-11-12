package com.example.awaq1

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.awaq1.data.FormulariosDatabase
import com.example.awaq1.data.formularioUno.FormularioUnoDAO
import org.junit.After
import org.junit.Assert.assertNotNull
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
}