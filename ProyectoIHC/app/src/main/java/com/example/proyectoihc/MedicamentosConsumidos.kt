package com.example.proyectoihc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoihc.databinding.ActivityMedicamentosConsumidosBinding
import com.example.proyectoihc.databinding.ActivityPerfilesAjenosBinding

class MedicamentosConsumidos : AppCompatActivity() {

    private lateinit var binding: ActivityMedicamentosConsumidosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicamentosConsumidosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val usuarioGeneral = bundle?.getString("user")
        binding.nombre.text=usuarioGeneral

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, PantallaPrincipal::class.java)
            intent.putExtra("user",usuarioGeneral)

            startActivity(intent)
            overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out)
        }
    }
}