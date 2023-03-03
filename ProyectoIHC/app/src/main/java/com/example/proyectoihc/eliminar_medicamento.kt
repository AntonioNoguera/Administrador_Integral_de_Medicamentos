package com.example.proyectoihc

import android.app.Dialog
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.proyectoihc.databinding.ActivityEliminarMedicamentoBinding

class eliminar_medicamento : AppCompatActivity() {
    private lateinit var binding:ActivityEliminarMedicamentoBinding
    lateinit var medicamentosDBHelper:miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEliminarMedicamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinuar.isEnabled=false
        medicamentosDBHelper=miSQLiteHelper(this)

        val bundle = intent.extras
        val usuarioGeneral = bundle?.getString("user")

        val db: SQLiteDatabase =medicamentosDBHelper.readableDatabase
        val cursor=db.rawQuery("SELECT * FROM medicamentos",null)
        var items = mutableListOf<String>()

        if(cursor.moveToFirst()) {
            do {
                items.add(cursor.getString(2).toString())
            }while (cursor.moveToNext())
            cursor.close()
        }

        val adapter = ArrayAdapter(this, R.layout.list_item,items)
        binding.dropdownField.setAdapter(adapter)

        binding.dropdownField.setOnItemClickListener{adapterView, view,i,l->
            val vias = view as TextView
            Toast.makeText(this,"Busqueda", Toast.LENGTH_SHORT).show()
            binding.btnContinuar.isEnabled=true
            binding.trucacho.setText(view.text)
        }

        binding.btnContinuar.setOnClickListener{
            val dialogBinding=layoutInflater.inflate(R.layout.custom_alert_eliminar_med,null)
            val myDialog = Dialog(this)

            myDialog.setContentView(dialogBinding)

            myDialog.setCancelable(false)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            myDialog.show()

            val BtnClose = dialogBinding.findViewById<Button>(R.id.btnCerraraa)
            BtnClose.setOnClickListener{
                myDialog.dismiss()
            }

            val Seguir = dialogBinding.findViewById<Button>(R.id.btnSeguiraa)

            Seguir.setOnClickListener{
                //Eliminar
                medicamentosDBHelper=miSQLiteHelper(this)
                val db: SQLiteDatabase =medicamentosDBHelper.readableDatabase

                medicamentosDBHelper.eliminarMed(binding.trucacho.text.toString())
                Thread.sleep(400)
                val intent = Intent(this, Perfil::class.java)
                intent.putExtra("user",usuarioGeneral)

                startActivity(intent)
                overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
            }


        }
        binding.btnVor.setOnClickListener{
            val intent = Intent(this, Perfil::class.java)
            intent.putExtra("user",usuarioGeneral)
            startActivity(intent)
            overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out)
        }
    }
}