package com.example.proyectoihc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext

import com.example.proyectoihc.databinding.ActivityViaAdministracionBinding

class ViaAdministracion : AppCompatActivity() {

    private lateinit var binding: ActivityViaAdministracionBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViaAdministracionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinuar.isEnabled=false

        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")
        val nMed = bundle?.getString("nMed")
        var nomMed = bundle?.getString("nomMed")
        val hora = bundle?.getString("hora")
        val dosis = bundle?.getString("dosis")
        val viaAdm = bundle?.getString("viaAdm")

        var flagAg = bundle?.getString("flagAg")

        var itera = bundle?.getString("itera")

        if(viaAdm!=null){
            binding.dropdownField.setText(viaAdm)
            binding.viaa.setText(viaAdm)
            binding.btnContinuar.isEnabled=true
        }
        val horaDor = bundle?.getString("horaDor")
        val horaDes = bundle?.getString("horaDes")



        binding.textView2.setText(usuarioGeneral+",\ndel medicamento:\n"+nomMed)

        val items = listOf("Oral (Boca)","Nasal (Nariz)","Ocular (Ojos)","Cutánea (Piel)","Ótica (Oídos)","Rectal","Subcutánea","Intramuscular","Intravenosa","Intrasecal")

        val adapter = ArrayAdapter(this, R.layout.list_item,items)

        binding.dropdownField.setAdapter(adapter)

        binding.dropdownField.setOnItemClickListener{adapterView, view,i,l->
            val vias = view as TextView
            binding.btnContinuar.isEnabled=true
            binding.viaa.setText(view.text)
        }

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, CantidadDeDosis::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",hora)
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",binding.viaa.text.toString())
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            intent.putExtra("flagAg",flagAg)

            startActivity(intent)
            overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out)
        }

        binding.btnContinuar.setOnClickListener{
            val intent = Intent(this, confirmarMed::class.java)


            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",hora)
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",binding.viaa.text.toString())
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            intent.putExtra("flagAg",flagAg)

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }
    }
}