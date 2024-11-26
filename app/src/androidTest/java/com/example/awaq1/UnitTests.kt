package com.example.awaq1

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.awaq1.data.AppContainer
import com.example.awaq1.data.FormulariosDatabase
import com.example.awaq1.data.formularios.FormularioUnoDAO
import com.example.awaq1.data.formularios.FormularioUnoEntity
import com.example.awaq1.data.formularios.FormulariosRepository
import com.example.awaq1.data.formularios.OfflineFormulariosRepository
import com.example.awaq1.data.usuario.UsuarioEntity
import com.example.awaq1.data.usuario.UsuarioFormulario1Entity
import com.example.awaq1.data.usuario.UsuariosRepository
import junit.framework.TestCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime



@RunWith(AndroidJUnit4::class)
class UnitTests {

    private lateinit var db: FormulariosDatabase
    private lateinit var appContainer: AppContainer

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, FormulariosDatabase::class.java)
            .fallbackToDestructiveMigration().build()
            //.setTransactionExecutor(Executors.newSingleThreadExecutor())
        // Modifica el executor de las pruebas. Puede ayudar a eliminar data races creo
        //appContainer = mockAppDataContainer(db) // Mock AppDataContainer to get the repositories
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun appDatabase_DaoIsInitialized_DaoIsAccessible() {
        assertNotNull(
            "DAO should be initialized and accessible when the application starts",
            db.formulario1Dao()
        )
    }

    //@Test
    //fun write_Formulario1_andReadBack() {
      //  val form1 = FormularioUnoEntity(
        //    transecto = "transecto",
          //  tipoAnimal = "tipoAnimal",
          //  nombreComun = "nombreComun",
           // nombreCientifico = "nombreCientifico",
           // numeroIndividuos = "numeroIndividuos",
            //tipoObservacion = "tipoObservacion",
            //observaciones = "observaciones",
        //)
       // var expected: FormularioUnoEntity?
       // var insertId: Long = 0
        //runBlocking { insertId = db.formulario1Dao().insert(form1) }
        // NOTA: SIEMPRE usar first() para tomar de un flow. Single suena mejor pero se traba,
        // es alguna operacion diferente que seguira esperando para siempre.
        //runBlocking {
          //  expected = db.formulario1Dao().getFormularioUnoEntity(insertId).first()
        //}
        //assertEquals(form1, expected)
    //}

    @Test
    fun write_Usuario_andReadBack() {
        assertNotNull("Usuarios DAO should be up and running", db.usuarioDAO())
        val newUser = UsuarioEntity(
            username = "user@example.com",
            lastAccess = LocalDateTime.now().toString(),
            lastLogin = LocalDateTime.now().toString()
        )

        Log.d("TEST", "NUEVO USUARIO: $newUser")
        val newUserId: Long = runBlocking { db.usuarioDAO().insert(newUser) }
        val readUser: UsuarioEntity? =
            runBlocking { db.usuarioDAO().getUsuarioById(newUserId).first() }

        assertEquals(newUser, readUser)
    }

    @Test
    fun write_Formulario1_with_user_LINKED() {
        assertNotNull(
            "DAO should be initialized and accessible when the application starts",
            db.formulario1Dao()
        )
        assertNotNull("Usuarios DAO should be up and running", db.usuarioDAO())

        val newUser = UsuarioEntity(
            username = "another_user@example.com",
            lastAccess = LocalDateTime.now().toString(),
            lastLogin = LocalDateTime.now().toString()
        )

        val newUser_id = runBlocking {
            appContainer.usuariosRepository.insertUser(newUser)
        }
        Log.d("TEST_Formilario1_with_user_LINKED", "NUEVO USUARIO: $newUser #${newUser_id}")

       // val newForm = FormularioUnoEntity(
         //   transecto = "transecto",
           // tipoAnimal = "tipoAnimal",
            //nombreComun = "nombreComun",
            //nombreCientifico = "nombreCientifico",
            //numeroIndividuos = "numeroIndividuos",
            //tipoObservacion = "tipoObservacion",
            //observaciones = "observaciones"
       // )

       // runBlocking {
        //    appContainer.usuariosRepository.insertUserWithFormularioUno(newUser_id, newForm)
       // }

        val form_read: FormularioUnoEntity? =
            runBlocking {
                val forms =
                    appContainer.usuariosRepository.getFormulariosForUsuarioUno(newUser_id).first()
                Log.d("TEST_Formilario1_with_user_LINKED", "$forms")
                val usuarioFormulario =forms[0]
                val form_id = usuarioFormulario.formId
                Log.d(
                    "TEST_Formilario1_with_user_LINKED",
                    "usuarioFormulario=${usuarioFormulario};form_id=${form_id}"
                )
                appContainer.formulariosRepository.getFormularioUnoStream(form_id).first()
            }

        assertNotNull(form_read)
        //assertEquals(newForm, form_read)

        Log.d("TEST_Formilario1_with_user_LINKED", "RECIBIDO FormularioUnoEntity: $form_read")
    }

    // TODO: Test de el "aggregate" de los formularios por usuario, en forma de FormInfo's
}

// Gets the database from the constructor, so it can be given the in-memory one.
// Still implements AppContainer so it follows the same behaviour.
//class mockAppDataContainer (private val database: FormulariosDatabase): AppContainer {
   // override val formulariosRepository: FormulariosRepository by lazy {
     //   OfflineFormulariosRepository(
       //     formularioUnoDAO = database.formulario1Dao(),
         //   formularioDosDAO = database.formulario2Dao(),
           // formularioTresDAO = database.formulario3Dao(),
           // formularioCuatroDAO = database.formulario4Dao()
        //)
  //  }

    //override val usuariosRepository: UsuariosRepository by lazy {
      //  UsuariosRepository(
        //    usuarioDAO = database.usuarioDAO(),
          //  usuarioFormulario1DAO = database.usuarioFormulario1DAO(),
           // usuarioFormulario2DAO = database.usuarioFormulario2DAO(),
           // usuarioFormulario3DAO = database.usuarioFormulario3DAO(),
           // usuarioFormulario4DAO = database.usuarioFormulario4DAO(),
           // formularioUnoDAO = database.formulario1Dao(),
           // formularioDosDAO = database.formulario2Dao(),
           // formularioTresDAO = database.formulario3Dao(),
           // formularioCuatroDAO = database.formulario4Dao(),
       // )
   // }
//}
