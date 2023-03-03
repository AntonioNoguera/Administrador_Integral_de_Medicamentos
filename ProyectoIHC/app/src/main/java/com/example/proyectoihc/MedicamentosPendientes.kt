package com.example.proyectoihc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoihc.databinding.ActivityMedicamentosConsumidosBinding
import com.example.proyectoihc.databinding.ActivityMedicamentosPendientesBinding

class MedicamentosPendientes : AppCompatActivity() {

    private lateinit var binding: ActivityMedicamentosPendientesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicamentosPendientesBinding.inflate(layoutInflater)
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