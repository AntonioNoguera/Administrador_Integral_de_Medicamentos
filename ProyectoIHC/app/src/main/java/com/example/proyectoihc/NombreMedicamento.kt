package com.example.proyectoihc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.proyectoihc.databinding.ActivityNombreMedicamentoBinding

class NombreMedicamento : AppCompatActivity() {

    private lateinit var binding: ActivityNombreMedicamentoBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = ActivityNombreMedicamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.nombreMedicamento.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnContinuar.isEnabled = binding.nombreMedicamento.text.toString() != ""

                binding.Caracters.text="Caractéres Disponibles: " + (15-binding.nombreMedicamento.text.toString().length)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.btnContinuar.isEnabled=false
        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")
        val nMed = bundle?.getString("nMed")
        var nomMed = bundle?.getString("nomMed")
        if(!nomMed.isNullOrEmpty()){
            Toast.makeText(this,"xd?", Toast.LENGTH_SHORT).show()
            binding.nombreMedicamento.setText(nomMed)
            binding.btnContinuar.isEnabled=true
            binding.Caracters.text="Caractéres Disponibles: " + (15-binding.nombreMedicamento.text.toString().length)
        }
        val hora = bundle?.getString("hora")
        val dosis = bundle?.getString("dosis")
        val viaAdm = bundle?.getString("viaAdm")
        val horaDor = bundle?.getString("horaDor")
        val horaDes = bundle?.getString("horaDes")

        var flagAg = bundle?.getString("flagAg")

        var itera = bundle?.getString("itera")

        if (itera==null){
            itera="0"
        }

        var numeros=listOf("primer","segundo","tercer","cuarto","quinto","sexto","séptimo","octavo","noveno","décimo")
        var indice=itera.toString().toInt()
        binding.textView2.setText(usuarioGeneral+",\nel "+numeros[indice]+" medicamento")

        //Funciones de Movilidad
        binding.btnContinuar.setOnClickListener{
            val intent = Intent(this, HorasPorDosis::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",binding.nombreMedicamento.text.toString())
            intent.putExtra("hora",hora)
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            intent.putExtra("flagAg",flagAg)

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, NumeroDeMedicamentos::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",binding.nombreMedicamento.text.toString())
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