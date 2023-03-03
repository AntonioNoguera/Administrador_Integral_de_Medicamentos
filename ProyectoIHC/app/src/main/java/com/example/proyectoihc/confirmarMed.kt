package com.example.proyectoihc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.proyectoihc.databinding.ActivityConfirmarMedBinding

class confirmarMed : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmarMedBinding
    lateinit var medicamentosDBHelper:miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityConfirmarMedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val usuarioGeneral = bundle?.getString("user")
        val nMed = bundle?.getString("nMed")
        var nomMed = bundle?.getString("nomMed")
        val hora = bundle?.getString("hora")
        val dosis = bundle?.getString("dosis")
        val viaAdm = bundle?.getString("viaAdm")

        val horaDor = bundle?.getString("horaDor")
        var flagAg = bundle?.getString("flagAg")

        val horaDes = bundle?.getString("horaDes")

        var itera = bundle?.getString("itera")

        binding.titulo.text = usuarioGeneral+",\ndel medicamento:\n"+nomMed
        binding.txtHoras.text= "1/C  "+hora+" H"
        binding.txtDosis.text = dosis
        binding.txtAdministraciN.text = viaAdm

        //Moviliadd
        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, ViaAdministracion::class.java)

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

        if(itera.toString().toInt()<(nMed.toString().toInt()-1)){
            binding.btnContinuar.isVisible=true
            binding.btnTerminar.isVisible=false
        }else{
            binding.btnContinuar.isVisible=false
            binding.btnTerminar.isVisible=true
        }

        if(flagAg != null){
            binding.btnTerminar.isVisible=false
            binding.btnContinuar.isVisible=false
            binding.btnAgregarEnd.isVisible=true
        }

        var neoItera=(itera.toString().toInt())+1


        binding.btnContinuar.setOnClickListener{

            medicamentosDBHelper=miSQLiteHelper(this)
            medicamentosDBHelper.nuevoMed(usuarioGeneral.toString(),nomMed.toString(),hora.toString(),dosis.toString(),viaAdm.toString())
            Toast.makeText(this,"Medicamento Almacenado Exitosamente",Toast.LENGTH_LONG).show()

            val intent = Intent(this, NombreMedicamento::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("nMed",nMed)
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("flagAg",flagAg)

            intent.putExtra("itera",neoItera.toString())

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnAgregarEnd.setOnClickListener{
            val intent = Intent(this, Perfil::class.java)
            intent.putExtra("user",usuarioGeneral)
            medicamentosDBHelper=miSQLiteHelper(this)
            medicamentosDBHelper.nuevoMed(usuarioGeneral.toString(),nomMed.toString(),hora.toString(),dosis.toString(),viaAdm.toString())
            Toast.makeText(this,"Medicamento Almacenado Exitosamente",Toast.LENGTH_LONG).show()
            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnTerminar.setOnClickListener{
            val intent = Intent(this, HoraDormir::class.java)
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

            medicamentosDBHelper=miSQLiteHelper(this)
            medicamentosDBHelper.nuevoMed(usuarioGeneral.toString(),nomMed.toString(),hora.toString(),dosis.toString(),viaAdm.toString())
            Toast.makeText(this,"Medicamento Almacenado Exitosamente",Toast.LENGTH_LONG).show()

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

    }
}