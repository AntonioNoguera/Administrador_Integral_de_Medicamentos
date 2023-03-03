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
import com.example.proyectoihc.databinding.ActivityEliminarPerfilBinding

class EliminarPerfil : AppCompatActivity() {
    private lateinit var binding: ActivityEliminarPerfilBinding
    lateinit var medicamentosDBHelper:miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEliminarPerfilBinding.inflate(layoutInflater)
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
            val dialogBinding=layoutInflater.inflate(R.layout.custom_alert_eliminar_perfil,null)
            val myDialog = Dialog(this)

            myDialog.setContentView(dialogBinding)

            myDialog.setCancelable(false)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            myDialog.show()

            val BtnClose = dialogBinding.findViewById<Button>(R.id.btnCerrarz)
            BtnClose.setOnClickListener{
                myDialog.dismiss()
            }

            val Seguir = dialogBinding.findViewById<Button>(R.id.btnSeguirz)

            Seguir.setOnClickListener{
                //Eliminar
                medicamentosDBHelper=miSQLiteHelper(this)
                val db: SQLiteDatabase =medicamentosDBHelper.readableDatabase

                medicamentosDBHelper.eliminarUser(binding.trucacho.text.toString())
                val intent = Intent(this, PerfilesAjenos::class.java)
                intent.putExtra("user",usuarioGeneral)
                Toast.makeText(this,"El perfil ha sido eleminado con exito",Toast.LENGTH_SHORT).show()
                startActivity(intent)
                overridePendingTransition(R.anim.from_left_in, R.anim.from_right_out)

            }
        }

    }
}