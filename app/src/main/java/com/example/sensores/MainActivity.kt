package com.example.sensores

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var tvValores: TextView
    private lateinit var tvEstado: TextView
    private lateinit var sensorManager: SensorManager

    private lateinit var layout: LinearLayout
    private lateinit var layoutMostrar: LinearLayout

    //Crear objeto para crear sensor de luz
    private var sensorLuz: Sensor? = null
    //private lateinit var sensorEventListener: SensorEventListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        tvValores = findViewById(R.id.tvValores)
        layoutMostrar = findViewById(R.id.layoutMostrar)
        tvEstado = findViewById(R.id.TvEstado)
        //permitir el uso de los servicios de hardware del dispositivo
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        //seleccioonar el sensor de luz
        sensorLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        //VALIDAR SI EL SENSOR EXISTE
        if (sensorLuz == null) {
            tvEstado.text = "No hay sensor de luz :("
        } else {
            tvEstado.text = "El dispositivo cuenta con sensor de luz"
        }


    }

    //Metodos obligatorios para el uso de sensores
    //Metodo para los cambios en los valores del sensor
    override fun onSensorChanged(event: SensorEvent?) {
        //Verificar el uso correcto del sensor
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            //variable wque guarde la informacion de un sensor
            val luz = event.values[0]
            tvValores.text = "luz: $luz lx"

            if (luz < 50) {

                tvEstado.text = "Se activa el modo oscuro"
                // --- MODO OSCURO ---
                layoutMostrar.setBackgroundColor(Color.BLACK)
                tvValores.setTextColor(Color.WHITE)
                tvEstado.setTextColor(Color.WHITE)


            } else if (luz > 500) {
                tvEstado.text = "Modo claro "
                layoutMostrar.setBackgroundColor(Color.WHITE)
                tvValores.setTextColor(Color.BLACK)
                tvEstado.setTextColor(Color.BLACK)
            } else {
                tvEstado.text = "Demasiada luz *Inserte meme del xocas"
            }
        }
    }

    //metodo para medir el valor matematico del sensor
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
//Metdodo para detener el uso del sensosr
override fun onResume() {
    super.onResume()



        sensorLuz?.let{
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
    }
    //layaut,setBackgraoudColor(color.black)
//setbackgraudncolor(color <color



