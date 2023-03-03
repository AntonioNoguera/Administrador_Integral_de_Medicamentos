package com.example.proyectoihc

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.proyectoihc.databinding.ActivityEditarMedicamentoBinding

class editarMedicamento : AppCompatActivity() {
    private lateinit var binding: ActivityEditarMedicamentoBinding
    lateinit var medicamentosDBHelper:miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarMedicamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinuar.isEnabled=false
        binding.VIAS.isVisible=false
        binding.Dosis.isVisible=false
        binding.nombreMedicamento.isVisible=false
        binding.horas.isVisible=false

        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")

        //Llenar Array Med
        medicamentosDBHelper=miSQLiteHelper(this)
        val db: SQLiteDatabase =medicamentosDBHelper.readableDatabase
        val cursor=db.rawQuery("SELECT * FROM medicamentos",null)


        var items = mutableListOf<String>()

        if(cursor.moveToFirst()) {
            do {
                items.add(cursor.getString(2).toString())
            }while (cursor.moveToNext())
            cursor.close()
        }

        val items2 = listOf("Oral (Boca)","Nasal (Nariz)","Ocular (Ojos)","Cutánea (Piel)","Ótica (Oídos)","Rectal","Subcutánea","Intramuscular","Intravenosa","Intrasecal")
        val adapter2 = ArrayAdapter(this, R.layout.list_item,items2)
        binding.dropdownField2.setAdapter(adapter2)

        val adapter = ArrayAdapter(this, R.layout.list_item,items)
        binding.dropdownField.setAdapter(adapter)

        binding.dropdownField.setOnItemClickListener{adapterView, view,i,l->
            val vias = view as TextView
            binding.btnContinuar.isEnabled=true
            binding.VIAS.isVisible=true
            binding.Dosis.isVisible=true
            binding.nombreMedicamento.isVisible=true
            binding.horas.isVisible=true
            binding.trucacho.setText(view.text)

            binding.labelA.isVisible=true
            binding.labelB.isVisible=true
            binding.labelC.isVisible=true
            binding.labelD.isVisible=true

            medicamentosDBHelper=miSQLiteHelper(this)
            val db:SQLiteDatabase=medicamentosDBHelper.readableDatabase
            val cursor=db.rawQuery("SELECT * FROM medicamentos",null)

            if(cursor.moveToFirst()) {
                do {
                    if(cursor.getString(2).toString()==binding.trucacho.text.toString()){
                        binding.dropdownField2.hint="Anteriormente: "+cursor.getString(5).toString()
                        binding.trucacho2.text=cursor.getString(5).toString()
                        binding.Dosis.setText(cursor.getString(4).toString())
                        binding.nombreMedicamento.setText(cursor.getString(2).toString())
                        binding.horas.setText(cursor.getString(3).toString())
                        break
                    }
                } while (cursor.moveToNext())
            }
        }

        binding.dropdownField2.setOnItemClickListener{adapterView2, view,i,l->
            val vias = view as TextView
            binding.trucacho2.setText(view.text)

        }

        binding.btnContinuar.setOnClickListener{
            val intent = Intent(this, Perfil::class.java)
            intent.putExtra("user",usuarioGeneral)

            medicamentosDBHelper=miSQLiteHelper(this)
            val db: SQLiteDatabase =medicamentosDBHelper.readableDatabase

            medicamentosDBHelper.ActMed(binding.trucacho.text.toString(),binding.nombreMedicamento.text.toString() ,binding.Dosis.text.toString(),binding.horas.text.toString(),binding.trucacho2.text.toString())


            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, Perfil::class.java)
            intent.putExtra("user",usuarioGeneral)

            startActivity(intent)
            overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out)
        }
    }
}