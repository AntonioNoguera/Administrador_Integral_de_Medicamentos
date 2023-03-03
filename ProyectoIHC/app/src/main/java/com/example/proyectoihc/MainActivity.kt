package com.example.proyectoihc

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast

import com.example.proyectoihc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var medicamentosDBHelper:miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(300)
        setTheme(R.style.Theme_ProyectoIHC)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        medicamentosDBHelper=miSQLiteHelper(this)
        val db: SQLiteDatabase =medicamentosDBHelper.readableDatabase
        val cursor=db.rawQuery("SELECT * FROM perfiles",null)

        //Verificar si hay usuarios existentes
        if(cursor.moveToFirst()) {
         //SE DETECTO UN USUARIO

            do {
                if(cursor.getString(4).toString()=="1"){
                    Toast.makeText(this,"Se ha detectado un Perfil!",Toast.LENGTH_LONG).show()
                    val intent = Intent(this, PantallaPrincipal::class.java)
                    intent.putExtra("user",cursor.getString(1).toString())
                    startActivity(intent)
                    overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
                }

            } while (cursor.moveToNext())


        }


        binding.btnContinuar.isEnabled=false

        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")
        val nMed = bundle?.getString("nMed")
        var nomMed = bundle?.getString("nomMed")
        val hora = bundle?.getString("hora")
        val dosis = bundle?.getString("dosis")
        val viaAdm = bundle?.getString("viaAdm")
        val horaDor = bundle?.getString("horaDor")
        val horaDes = bundle?.getString("horaDes")
        val itera= bundle?.getString("itera")

        if(usuarioGeneral!=null){
            binding.TxtUser.setText(usuarioGeneral)
            binding.btnContinuar.isEnabled=true
            binding.Caracters.text="Caractéres Disponibles: " + (15-binding.TxtUser.text.toString().length)
        }




        binding.TxtUser.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnContinuar.isEnabled = binding.TxtUser.text.toString() != ""

                binding.Caracters.text="Caractéres Disponibles: " + (15-binding.TxtUser.text.toString().length)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        //Funciones de Movilidad
        binding.btnContinuar.setOnClickListener{
            val intent = Intent(this, NumeroDeMedicamentos::class.java)
            val usuario = binding.TxtUser.text.toString()
            intent.putExtra("user",usuario)
            intent.putExtra("nMed",nMed)
            intent.putExtra("nomMed",nomMed)
            intent.putExtra("hora",hora)
            intent.putExtra("dosis",dosis)
            intent.putExtra("viaAdm",viaAdm)
            intent.putExtra("horaDor",horaDor)
            intent.putExtra("horaDes",horaDes)
            intent.putExtra("itera",itera)

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }
    }
}