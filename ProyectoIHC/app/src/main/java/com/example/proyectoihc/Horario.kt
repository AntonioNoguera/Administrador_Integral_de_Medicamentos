package com.example.proyectoihc

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectoihc.databinding.ActivityHorarioBinding

class Horario : AppCompatActivity() {
    private lateinit var binding: ActivityHorarioBinding
    lateinit var medicamentosDBHelper:miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val usuarioGeneral = bundle?.getString("user")

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, PantallaPrincipal::class.java)
            intent.putExtra("user",usuarioGeneral)
            startActivity(intent)
            overridePendingTransition(R.anim.from_up_in, R.anim.from_down_out)
        }

        binding.textView5.text=""

        medicamentosDBHelper=miSQLiteHelper(this)
        val db:SQLiteDatabase=medicamentosDBHelper.readableDatabase

        val cursor=db.rawQuery("SELECT * FROM medicamentos",null)

        if(cursor.moveToFirst()) {
            do {
                binding.textView5.append(cursor.getString(2).toString() + ", ")
                binding.textView5.append(cursor.getString(3).toString() + " H, ")
                binding.textView5.append(cursor.getString(4).toString() + ", ")
                binding.textView5.append(cursor.getString(5).toString() + ".\n")
            } while (cursor.moveToNext())
        }



    }
}