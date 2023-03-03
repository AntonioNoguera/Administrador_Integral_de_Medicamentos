package com.example.proyectoihc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.proyectoihc.databinding.ActivityHoraEpecificaBinding
import com.example.proyectoihc.databinding.ActivityPantallaPrincipalBinding

class PantallaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")
        binding.textView7.text=usuarioGeneral

        binding.btnHorario.setOnClickListener{
            var intent = Intent(this, Horario::class.java)
            intent.putExtra("user",usuarioGeneral)
            startActivity(intent)
            overridePendingTransition(R.anim.from_down_in, R.anim.from_up_out)
        }

        binding.btnMiPerfil.setOnClickListener{
            var intent = Intent(this,Perfil::class.java)
            intent.putExtra("user",usuarioGeneral)
            startActivity(intent)
            overridePendingTransition(R.anim.from_down_in, R.anim.from_up_out)
        }

        binding.btnAjenos.setOnClickListener{
            var intent = Intent(this,PerfilesAjenos::class.java)
            intent.putExtra("user",usuarioGeneral)
            startActivity(intent)
            overridePendingTransition(R.anim.from_down_in, R.anim.from_up_out)
        }

        binding.btnConsumidos.setOnClickListener{
            var intent = Intent(this,MedicamentosConsumidos::class.java)
            intent.putExtra("user",usuarioGeneral)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnPendientes.setOnClickListener{
            var intent = Intent(this,MedicamentosPendientes::class.java)
            intent.putExtra("user",usuarioGeneral)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

    }
}