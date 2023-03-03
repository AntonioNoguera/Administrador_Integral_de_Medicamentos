package com.example.proyectoihc

import android.app.Dialog
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.proyectoihc.databinding.ActivityPerfilBinding

class Perfil : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding
    lateinit var medicamentosDBHelper:miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val usuarioGeneral = bundle?.getString("user")
        binding.textView7.text=usuarioGeneral

        binding.btnVolver.setOnClickListener{
            val intent = Intent(this, PantallaPrincipal::class.java)
            intent.putExtra("user",usuarioGeneral)
            startActivity(intent)
            overridePendingTransition(R.anim.from_up_in, R.anim.from_down_out)
        }

        binding.btnHistorial.setOnClickListener{
            val intent = Intent(this, Progreso::class.java)

            intent.putExtra("user",usuarioGeneral)


            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnEditar.setOnClickListener{
            val intent = Intent(this, editarMedicamento::class.java)

            intent.putExtra("user",usuarioGeneral)


            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnAgregar.setOnClickListener{
            val intent = Intent(this, NumeroDeMedicamentos::class.java)

            intent.putExtra("user",usuarioGeneral)
            intent.putExtra("flagAg","xxx")

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnEliminarMedicamentos.setOnClickListener{
            val intent = Intent(this, eliminar_medicamento::class.java)

            intent.putExtra("user",usuarioGeneral)

            startActivity(intent)
            overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
        }

        binding.btnEliminarPeril.setOnClickListener(){

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
                medicamentosDBHelper.eliminarUser(usuarioGeneral.toString())

                val cursor=db.rawQuery("SELECT * FROM perfiles",null)

                if(cursor.moveToFirst()) {
                    do {
                        if(cursor.getString(1).toString()!=usuarioGeneral.toString()){
                            medicamentosDBHelper.cambiar(cursor.getString(1).toString(),"1")
                        }
                    } while (cursor.moveToNext())
                }

                //Toast.makeText(this,"Tu perfil ha sido eleminado con éxito!",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out)
            }
        }
    }
}