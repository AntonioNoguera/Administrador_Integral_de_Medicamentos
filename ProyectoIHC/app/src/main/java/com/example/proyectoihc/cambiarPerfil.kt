package com.example.proyectoihc

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.proyectoihc.databinding.ActivityCambiarPerfilBinding

class cambiarPerfil : AppCompatActivity() {
    private lateinit var binding: ActivityCambiarPerfilBinding
    lateinit var medicamentosDBHelper:miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambiarPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")

        binding.btnContinuar.isEnabled=false

        medicamentosDBHelper=miSQLiteHelper(this)
        val db: SQLiteDatabase =medicamentosDBHelper.readableDatabase
        val cursor=db.rawQuery("SELECT * FROM perfiles",null)
        var items = mutableListOf<String>()

        if(cursor.moveToFirst()) {
            do {
                if(cursor.getString(1).toString()!=usuarioGeneral.toString()){
                    items.add(cursor.getString(1).toString())
                }
            }while (cursor.moveToNext())
            cursor.close()
        }

        val adapter = ArrayAdapter(this, R.layout.list_item,items)
        binding.dropdownField.setAdapter(adapter)

        binding.dropdownField.setOnItemClickListener{adapterView, view,i,l->
            val vias = view as TextView
            binding.btnContinuar.isEnabled=true
            binding.trucacho.setText(view.text)
        }

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, PerfilesAjenos::class.java)
            intent.putExtra("user",usuarioGeneral)

            startActivity(intent)
            overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out)
        }

        binding.btnContinuar.setOnClickListener{
            medicamentosDBHelper=miSQLiteHelper(this)
            val db: SQLiteDatabase =medicamentosDBHelper.readableDatabase

            medicamentosDBHelper.cambiar(binding.trucacho.text.toString(),"1")
            medicamentosDBHelper.cambiar(usuarioGeneral.toString(),"0")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

    }
}