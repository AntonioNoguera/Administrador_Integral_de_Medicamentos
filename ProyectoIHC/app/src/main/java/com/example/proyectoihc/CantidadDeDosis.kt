package com.example.proyectoihc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.proyectoihc.databinding.ActivityCantidadDeDosisBinding
import com.example.proyectoihc.databinding.ActivityMainBinding

class CantidadDeDosis : AppCompatActivity() {

    private lateinit var binding: ActivityCantidadDeDosisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCantidadDeDosisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinuar.isEnabled=false

        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")
        val nMed = bundle?.getString("nMed")
        var nomMed = bundle?.getString("nomMed")
        val hora = bundle?.getString("hora")
        val dosis = bundle?.getString("dosis")
        if(dosis!=null){
            binding.txtDosis.setText(dosis)
            binding.btnContinuar.isEnabled=true
        }
        val viaAdm = bundle?.getString("viaAdm")
        val horaDor = bundle?.getString("horaDor")
        val horaDes = bundle?.getString("horaDes")

        var flagAg = bundle?.getString("flagAg")

        var itera = bundle?.getString("itera")


        binding.textView2.setText(usuarioGeneral+", del medicamento:\n"+nomMed)

        binding.txtDosis.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnContinuar.isEnabled = binding.txtDosis.text.toString() != ""

                binding.Caracters.text="Caractéres Disponibles: " + (15-binding.txtDosis.text.toString().length)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        //Movilidad
        binding.btnContinuar.setOnClickListener{
            val intent = Intent(this, ViaAdministracion::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",hora)
            intent.putExtra("dosis",binding.txtDosis.text.toString())
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("flagAg",flagAg)

            intent.putExtra("itera",itera)

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnVolver.setOnClickListener{
            val intent =Intent(this, HorasPorDosis::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",hora)
            intent.putExtra("dosis",binding.txtDosis.text.toString())
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