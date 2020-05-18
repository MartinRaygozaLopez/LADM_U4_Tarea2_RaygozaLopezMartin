package com.example.ladm_u4_tarea2_raygozalopezmartin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import kotlinx.android.synthetic.main.activity_main3.*
import java.util.*
import kotlin.collections.ArrayList

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        btn_Crear.setOnClickListener {
            var nombre = txt_Nombre.text.toString()
            var hora = txt_Hora.text.toString().toInt()
            var minutos = txt_Minutos.text.toString().toInt()
            var dias = ArrayList<Int>()

            if(chk_Lunes.isChecked) dias.add(Calendar.MONDAY)
            if(chk_Martes.isChecked) dias.add(Calendar.TUESDAY)
            if(chk_Miercoles.isChecked) dias.add(Calendar.WEDNESDAY)
            if(chk_Jueves.isChecked) dias.add(Calendar.THURSDAY)
            if(chk_Viernes.isChecked) dias.add(Calendar.FRIDAY)
            if(chk_Sabado.isChecked) dias.add(Calendar.SATURDAY)
            if(chk_Domingo.isChecked) dias.add(Calendar.SUNDAY)

            var vibrar = switch1.isChecked

            crearAlarma(nombre, hora, minutos, dias, vibrar)

            txt_Nombre.setText("")
            txt_Hora.setText("")
            txt_Minutos.setText("")
            dias.clear()
            chk_Lunes.isChecked = false
            chk_Martes.isChecked = false
            chk_Miercoles.isChecked = false
            chk_Jueves.isChecked = false
            chk_Viernes.isChecked = false
            chk_Sabado.isChecked = false
            chk_Domingo.isChecked = false
            switch1.isChecked = false

        }
    }

    private fun crearAlarma(nombre : String, hora : Int, min : Int, dias : ArrayList<Int>, vibrar : Boolean) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM)

        intent.putExtra(AlarmClock.EXTRA_MESSAGE, nombre)
        intent.putExtra(AlarmClock.EXTRA_HOUR, hora)
        intent.putExtra(AlarmClock.EXTRA_MINUTES, min)
        intent.putExtra(AlarmClock.EXTRA_DAYS, dias)
        intent.putExtra(AlarmClock.EXTRA_VIBRATE, vibrar)

        startActivity(intent)
    }
}
