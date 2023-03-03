package com.example.proyectoihc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoihc.databinding.ActivityProgresoBinding

class Progreso : AppCompatActivity() {
    private lateinit var binding: ActivityProgresoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgresoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")


        binding.textVisew2.text="Historial de Consumo de:\n"+usuarioGeneral

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, Perfil::class.java)

            intent.putExtra("user",usuarioGeneral)

            startActivity(intent)
            overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out)
        }
    }
}