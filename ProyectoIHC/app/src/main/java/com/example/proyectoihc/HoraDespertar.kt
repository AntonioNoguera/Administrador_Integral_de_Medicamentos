package com.example.proyectoihc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.proyectoihc.databinding.ActivityHoraDespertarBinding

class HoraDespertar : AppCompatActivity() {
    private lateinit var binding: ActivityHoraDespertarBinding
    lateinit var medicamentosDBHelper:miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityHoraDespertarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
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
        val itera = bundle?.getString("itera")

        if(horaDes!=null){
            if(horaDes.toString().toInt()<=12){
                binding.editTextTextPersonName.setText(horaDes.toString())
                binding.ampm.setText("AM")
            }else{
                var neoHora = horaDes.toString().toInt()-12
                binding.editTextTextPersonName.setText(neoHora.toString())
                binding.ampm.setText("PM")
            }
        }

        binding.titulo.text=usuarioGeneral

        binding.btnMas.setOnClickListener(){
            var floatInput = binding.editTextTextPersonName.text.toString().toInt()
            floatInput += 1

            if(floatInput==12){
                if(binding.ampm.text.toString()=="PM"){
                    binding.ampm.text = "AM"
                }else{
                    binding.ampm.text = "PM"
                }
            }

            if (floatInput==13){
                binding.editTextTextPersonName.setText("1")
            }else{
                var NeoString = floatInput.toString()
                binding.editTextTextPersonName.setText(NeoString)
            }
        }

        binding.btnMenos.setOnClickListener(){
            var floatInput = binding.editTextTextPersonName.text.toString().toInt()
            floatInput -= 1

            if(floatInput<=0){
                if(binding.ampm.text.toString()=="PM"){
                    binding.ampm.text = "AM"
                }else{
                    binding.ampm.text = "PM"
                }
            }

            if (floatInput<=0){
                binding.editTextTextPersonName.setText("12")
            }else{
                var NeoString = floatInput.toString()
                binding.editTextTextPersonName.setText(NeoString)
            }


        }


        //Movilidad
        binding.btnContinuar.setOnClickListener{
            val intent = Intent(this, PantallaPrincipal::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",hora)
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)


            var horaDes =  binding.editTextTextPersonName.text.toString().toInt()
            if(binding.ampm.text.toString()==="PM"){
                horaDes = binding.editTextTextPersonName.text.toString().toInt()+12
            }


            intent.putExtra("horaDes",horaDes.toString())


            medicamentosDBHelper=miSQLiteHelper(this)
            medicamentosDBHelper.nuevoPerfil(usuarioGeneral.toString(),horaDor.toString(),horaDes.toString(),"1")
            Toast.makeText(this,"Perfil Almacenado Exitosamente",Toast.LENGTH_LONG).show()

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, HoraDormir::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",hora)
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)


            var horaDes =  binding.editTextTextPersonName.text.toString().toInt()
            if( binding.ampm.text.toString()==="PM"){
                horaDes = binding.editTextTextPersonName.text.toString().toInt()+12
            }

            intent.putExtra("horaDes",horaDes.toString())

            startActivity(intent)
            overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out)
        }
    }
}