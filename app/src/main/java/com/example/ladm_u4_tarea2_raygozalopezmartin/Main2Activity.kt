package com.example.ladm_u4_tarea2_raygozalopezmartin

import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*
import kotlin.collections.ArrayList

class Main2Activity : AppCompatActivity() {

    var siPermisoLLamadas = 10

    var tipoDeRegistro = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CALL_LOG), siPermisoLLamadas)
        }

        btn_Mostrar.setOnClickListener {
            if(rbtn_Entrantes.isChecked) {
                tipoDeRegistro = 1
            } else
                if(rbtn_Salientes.isChecked) {
                    tipoDeRegistro = 2
                } else
                    if(rbtn_Perdidas.isChecked) {
                        tipoDeRegistro = 3
                    }

            leerRegistroLLamadas()
        }

    }

    private fun leerRegistroLLamadas() {
        var llamadas : Uri = Uri.parse("content://call_log/calls")

        /*
        Tipo 1= LLamadas Entrantes
        Tipo 2= LLamadas Salientes
        Tipo 3 = LLamadas Perdidas
         */

        var projection = arrayOf(CallLog.Calls.TYPE, CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION)

        var cursorLLamadas = contentResolver.query(llamadas, projection, "TYPE=?", arrayOf(tipoDeRegistro.toString()), null)

        var llamadasRegistro = ArrayList<String>()

        if(cursorLLamadas!!.moveToFirst()) {
            do {
                llamadasRegistro.add("Número teléfonico: " + cursorLLamadas.getString(1) +
                        "\nFecha: " + Date(cursorLLamadas.getLong(2)))
            } while (cursorLLamadas!!.moveToNext())
        } else {
            llamadasRegistro.add("NO HAY LLAMADAS REGISTRADAS")
        }

        lista.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, llamadasRegistro)
        cursorLLamadas!!.close()
    }
}
