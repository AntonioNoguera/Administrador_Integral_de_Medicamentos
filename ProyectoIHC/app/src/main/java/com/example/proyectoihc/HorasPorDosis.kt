package com.example.proyectoihc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectoihc.databinding.ActivityHorasPorDosisBinding

class HorasPorDosis : AppCompatActivity() {

    private lateinit var binding: ActivityHorasPorDosisBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorasPorDosisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")
        val nMed = bundle?.getString("nMed")
        var nomMed = bundle?.getString("nomMed")
        val hora = bundle?.getString("hora")
        val dosis = bundle?.getString("dosis")
        val viaAdm = bundle?.getString("viaAdm")
        val horaDor = bundle?.getString("horaDor")
        val horaDes = bundle?.getString("horaDes")

        var flagAg = bundle?.getString("flagAg")

        var itera = bundle?.getString("itera")

        binding.textView2.setText(usuarioGeneral+",\n el medicamento:\n"+nomMed)

        binding.Horas4.setOnClickListener{
            val intent = Intent(this, CantidadDeDosis::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora","4")
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)

            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            intent.putExtra("flagAg",flagAg)

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.Horas6.setOnClickListener{
            val intent = Intent(this, CantidadDeDosis::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora","6")
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            intent.putExtra("flagAg",flagAg)

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.Horas8.setOnClickListener{
            val intent = Intent(this, CantidadDeDosis::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora","8")
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            intent.putExtra("flagAg",flagAg)

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.Horas12.setOnClickListener{
            val intent = Intent(this, CantidadDeDosis::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora","12")
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            intent.putExtra("flagAg",flagAg)

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.Horas24.setOnClickListener{
            val intent =Intent(this, CantidadDeDosis::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora","24")
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            intent.putExtra("flagAg",flagAg)

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        //Movilidad
        binding.OtrasHoras.setOnClickListener{
            val intent = Intent(this, HoraEpecifica::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",hora)
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            intent.putExtra("flagAg",flagAg)

            startActivity(intent)
            overridePendingTransition(R.anim.from_down_in, R.anim.from_up_out)
        }

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, NombreMedicamento::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",hora)
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            intent.putExtra("flagAg",flagAg)

            startActivity(intent)
            overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out)
        }
    }
}