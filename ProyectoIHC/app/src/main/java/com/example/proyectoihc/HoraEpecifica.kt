package com.example.proyectoihc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.proyectoihc.databinding.ActivityHoraEpecificaBinding
import com.example.proyectoihc.databinding.ActivityMainBinding

class HoraEpecifica : AppCompatActivity() {

    private lateinit var binding: ActivityHoraEpecificaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoraEpecificaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinuar.isEnabled=false

        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")
        val nMed = bundle?.getString("nMed")
        var nomMed = bundle?.getString("nomMed")
        val hora = bundle?.getString("hora")
        if(hora!=null){
            binding.nEspecifico.setText(hora)
            binding.btnContinuar.isEnabled=true
        }

        val dosis = bundle?.getString("dosis")
        val viaAdm = bundle?.getString("viaAdm")
        val horaDor = bundle?.getString("horaDor")
        val horaDes = bundle?.getString("horaDes")
        val flagAg = bundle?.getString("flagAg")


        binding.textView2.setText(usuarioGeneral+",\n el medicamento:\n"+nomMed)

        binding.nEspecifico.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnContinuar.isEnabled = binding.nEspecifico.text.toString() != ""
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        //Botones Desplazamiento
        binding.btnContinuar.setOnClickListener{
            val intent =Intent(this, CantidadDeDosis::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",binding.nEspecifico.text.toString())
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("flagAg",flagAg)

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this,HorasPorDosis::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",binding.nEspecifico.text.toString())
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("flagAg",flagAg)

            startActivity(intent)
            overridePendingTransition(R.anim.from_up_in, R.anim.from_down_out)
        }
    }
}