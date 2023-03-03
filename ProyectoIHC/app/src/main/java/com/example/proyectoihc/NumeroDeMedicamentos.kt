package com.example.proyectoihc

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.proyectoihc.databinding.ActivityNumeroDeMedicamentosBinding

class NumeroDeMedicamentos : AppCompatActivity() {

    private lateinit var binding: ActivityNumeroDeMedicamentosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumeroDeMedicamentosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMenos.isEnabled=false

        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")
        val nMed = bundle?.getString("nMed")
        if(!nMed.isNullOrEmpty()){
            binding.editTextTextPersonName.setText(nMed)
            if(nMed.toString().toInt()>1){
                binding.btnMenos.isEnabled=true
            }
        }

        var nomMed = bundle?.getString("nomMed")
        var hora = bundle?.getString("hora")
        var dosis = bundle?.getString("dosis")
        var viaAdm = bundle?.getString("viaAdm")
        var horaDor = bundle?.getString("horaDor")
        var horaDes = bundle?.getString("horaDes")
        var itera = bundle?.getString("itera")

        var flagAg = bundle?.getString("flagAg")

        if(flagAg!=null){
            binding.btnVolver.isVisible=false
            binding.btnCancelar.isVisible=true
            binding.textView.text="¿Cuántos medicamentos deseas agregar?"
        }

        binding.titulo.text="Hola,\n"+usuarioGeneral

        binding.btnMas.setOnClickListener(){
            var floatInput = binding.editTextTextPersonName.text.toString().toInt()

                floatInput += 1
                var NeoString = floatInput.toString()
                binding.btnMenos.isEnabled=true

                binding.editTextTextPersonName.setText(NeoString)

            if(floatInput==10){
                binding.btnMas.isEnabled=false
            }
        }

        binding.btnMenos.setOnClickListener(){
            var floatInput = binding.editTextTextPersonName.text.toString().toInt()

            floatInput -= 1
            var NeoString = floatInput.toString()
            binding.btnMas.isEnabled=true
            binding.editTextTextPersonName.setText(NeoString)

            if(floatInput==1){
                binding.btnMenos.isEnabled=false
            }
        }

        //BotonesMovilidad
        binding.btnContinuar.setOnClickListener{
            val intent = Intent(this, NombreMedicamento::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",binding.editTextTextPersonName.text.toString())
            intent.putExtra("nomMed",nomMed)
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
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",binding.editTextTextPersonName.text.toString())
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

        binding.btnCancelar.setOnClickListener{
            val intent = Intent(this, Perfil::class.java)
            intent.putExtra("user",usuarioGeneral)

            startActivity(intent)
            overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out)
        }
    }
}