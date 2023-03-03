package com.example.proyectoihc

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectoihc.databinding.ActivityPantallaPrincipalBinding
import com.example.proyectoihc.databinding.ActivityPerfilesAjenosBinding

class PerfilesAjenos : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilesAjenosBinding
    lateinit var medicamentosDBHelper:miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilesAjenosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, PantallaPrincipal::class.java)

            intent.putExtra("user",usuarioGeneral)
            startActivity(intent)
            overridePendingTransition(R.anim.from_up_in, R.anim.from_down_out)

        }

        binding.btnCambiar.setOnClickListener{
            val intent = Intent(this, cambiarPerfil::class.java)

            intent.putExtra("user",usuarioGeneral)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnEliminarPerfil.setOnClickListener{
            val intent = Intent(this, EliminarPerfil::class.java)

            intent.putExtra("user",usuarioGeneral)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnAgregar.setOnClickListener{
            medicamentosDBHelper=miSQLiteHelper(this)
            val db: SQLiteDatabase =medicamentosDBHelper.readableDatabase
            //Linea de "Cierre"
            medicamentosDBHelper.cambiar(usuarioGeneral.toString(),"0")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }
    }
}