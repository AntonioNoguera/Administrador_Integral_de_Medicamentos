package com.example.proyectoihc

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.proyectoihc.databinding.ActivityHoraDormirBinding

class HoraDormir : AppCompatActivity() {

    private lateinit var binding: ActivityHoraDormirBinding
    lateinit var medicamentosDBHelper:miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHoraDormirBinding.inflate(layoutInflater)
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

        if(horaDor!=null){
            if(horaDor.toString().toInt()<=12){
                binding.editTextTextPersonName.setText(horaDor.toString())
                binding.ampm.setText("AM")
            }else{
                var neoHora = horaDor.toString().toInt()-12
                binding.editTextTextPersonName.setText(neoHora.toString())
                binding.ampm.setText("PM")
            }
            binding.btnContinuar.isVisible=true
            binding.btnOmitir.isVisible=false
        }

        binding.titulo.text=usuarioGeneral


        binding.btnMas.setOnClickListener(){
            var floatInput = binding.editTextTextPersonName.text.toString().toInt()
            floatInput += 1

            if(floatInput==12){
               if(binding.ampm.text.toString()=="PM"){
                    binding.ampm.setText("AM")
               }else{
                   binding.ampm.setText("PM")
               }
            }

            if (floatInput==13){
                binding.editTextTextPersonName.setText("1")
            }else{
                var NeoString = floatInput.toString()
                binding.editTextTextPersonName.setText(NeoString)
            }

            binding.btnContinuar.isVisible=true
            binding.btnOmitir.isVisible=false
        }

        binding.btnMenos.setOnClickListener(){
            var floatInput = binding.editTextTextPersonName.text.toString().toInt()
            floatInput -= 1

            if(floatInput<=0){
                if(binding.ampm.text.toString()=="PM"){
                    binding.ampm.setText("AM")
                }else{
                    binding.ampm.setText("PM")
                }
            }

            if (floatInput<=0){
                binding.editTextTextPersonName.setText("12")
            }else{
                var NeoString = floatInput.toString()
                binding.editTextTextPersonName.setText(NeoString)
            }

            binding.btnContinuar.isVisible=true
            binding.btnOmitir.isVisible=false

        }

        binding.btnOmitir.setOnClickListener{
            val dialogBinding=layoutInflater.inflate(R.layout.custom_alert,null)
            val myDialog = Dialog(this)

            myDialog.setContentView(dialogBinding)

            myDialog.setCancelable(false)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            myDialog.show()


            val BtnClose = dialogBinding.findViewById<Button>(R.id.btnCerrar)
            BtnClose.setOnClickListener{
                myDialog.dismiss()
            }

            val Seguir = dialogBinding.findViewById<Button>(R.id.btnSeguir)

            Seguir.setOnClickListener{
                val intent = Intent(this, PantallaPrincipal::class.java)

                intent.putExtra("user",usuarioGeneral)
                intent.putExtra("nMed",nMed)
                intent.putExtra("nomMed",nomMed)
                intent.putExtra("hora",hora)
                intent.putExtra("dosis",dosis)
                intent.putExtra("viaAdm",viaAdm)
                intent.putExtra("horaDes",horaDes)
                intent.putExtra("itera",itera)

                var horaDo =  binding.editTextTextPersonName.text.toString().toInt()
                if(binding.ampm.text.toString()=="0"){
                    horaDo=20
                }else{
                    if(binding.ampm.text.toString()==="PM"){
                        horaDo = binding.editTextTextPersonName.text.toString().toInt()+12
                    }
                }

                intent.putExtra("horaDor",horaDo.toString())

                medicamentosDBHelper=miSQLiteHelper(this)
                medicamentosDBHelper.nuevoPerfil(usuarioGeneral.toString(),"20","6","1")
                Toast.makeText(this,"Perfil Almacenado Exitosamente",Toast.LENGTH_LONG).show()

                startActivity(intent)
                overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
            }
        }

        binding.btnContinuar.setOnClickListener{
            val intent = Intent(this, HoraDespertar::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",hora)
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            var horaDo =  binding.editTextTextPersonName.text.toString().toInt()
            if(binding.ampm.text.toString()=="0"){
                horaDo=20
            }else{
                if(binding.ampm.text.toString()==="PM"){
                    horaDo = binding.editTextTextPersonName.text.toString().toInt()+12
                }
            }

            intent.putExtra("horaDor",horaDo.toString())

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, confirmarMed::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",hora)
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            var horaDo =  binding.editTextTextPersonName.text.toString().toInt()
            if(horaDo!=0){
                if(binding.ampm.text.toString()==="PM"){
                    horaDo = binding.editTextTextPersonName.text.toString().toInt()+12
                }
                intent.putExtra("horaDor",horaDo.toString())
            }

            startActivity(intent)
            overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out)
        }
    }
}