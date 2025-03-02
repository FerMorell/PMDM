package com.example.jugandoalosdados

import android.content.DialogInterface
import android.media.MediaPlayer
import androidx.appcompat.app.AlertDialog
import com.example.jugandoalosdados.R
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.jugandoalosdados.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var botonPresionado = false;
    var botonPresionado2 = false;
    var saldoIncial = 0
    lateinit var sonidoTirarDados: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        saldoIncial = binding.numero.text.toString().toIntOrNull() ?: 0

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        binding.b1.setOnClickListener {
            botonPresionado = !botonPresionado
            botonPresionado2 = false
            actualizarEstadoBotones()
        }

        binding.b2.setOnClickListener {
            botonPresionado2 = !botonPresionado2
            botonPresionado = false
            actualizarEstadoBotones()
        }

    }

    fun actualizarEstadoBotones() {
        if (botonPresionado) {
            val opciones = arrayOf("Par", "Impar")
            val adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)
            binding.spinner.adapter = adapter
        } else if (botonPresionado2) {
            val opciones = arrayOf("Mayor o igual que 7", "Menor que 7")
            val adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones)
            binding.spinner.adapter = adapter
        } else {
            binding.spinner.adapter = null
        }
    }


    fun lanzar(v: View) {
        binding.imageView2?.setImageResource(0)
        Glide.with(this).asGif().load("").into(binding.ganaroperder)
        binding.tirada!!.text = ""
        binding.tirada2!!.text = ""
        val apuestaString = binding.editTextNumber.text.toString().trim();
        val apuestaInt = apuestaString.toIntOrNull()

        if (!botonPresionado && !botonPresionado2) {

            lanzarSnackBar("1")
        } else if (apuestaString.isEmpty()) {
            lanzarSnackBar("2")
        } else if (apuestaInt == null) {
            lanzarSnackBar("3")
        } else if (apuestaInt != null && !validarNumero(apuestaInt)) {
            lanzarSnackBar("4")
        } else if (!validarQueNoSeaCero(apuestaInt)) {
            lanzarSnackBar("5")
        } else {
            Glide.with(this).asGif().load(R.drawable.dado_imagen_animada_0092)
                .into(binding.imageView)
            sonidoTirarDados = MediaPlayer.create(this, R.raw.sonidodados)
            sonidoTirarDados.start()
            CoroutineScope(Dispatchers.Main).launch {
                girar()

                var num = generarNumeroAleatorio()
                var num2 = generarNumeroAleatorio()
                binding.tirada!!.text = num.toString()
                binding.tirada2!!.text = num2.toString()
                var eleccion = binding.spinner.selectedItem.toString()
                var resulado = jugar(num, num2, eleccion)
                if (resulado) {
                    saldoIncial = (binding.numero.text.toString().toIntOrNull() ?: 0) + apuestaInt
                    binding.numero.text = saldoIncial.toString()
                    binding.imageView2?.setImageResource(R.drawable.ganar_dados)
                    gifPerderGanar("1");



                    CoroutineScope(Dispatchers.Main).launch {
                        delay(1000)
                        preguntarSiContinuarJugando()
                    }
                } else {
                    saldoIncial = (binding.numero.text.toString().toIntOrNull() ?: 0) - apuestaInt
                    binding.numero.text = saldoIncial.toString()
                    if (saldoIncial <= 0) {
                        saldoIncial = 0
                        binding.numero.text = "0"
                        bancarota()
                    } else {
                        binding.imageView2?.setImageResource(R.drawable.perder_dados)
                        gifPerderGanar("2");
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(1000)
                            preguntarSiContinuarJugando()
                        }
                    }

                }

            }

        }
    }

    fun gifPerderGanar(opcion: String) {
        if (opcion == "1") {
            binding.ganaroperder?.let {
                Glide.with(this).asGif().load(R.drawable._10588aeff0e5da3432bc076be8652ea)
                    .into(it)
            }
        } else if (opcion == "2") {
            binding.ganaroperder?.let {
                Glide.with(this).asGif().load(R.drawable._45ad73edc419bc4b13f5ae670d98a0d)
                    .into(it)
            }
        }
    }

    suspend fun girar() {
        delay(3000)
        Glide.with(this).asGif().load("").into(binding.imageView)


    }

    fun validarQueNoSeaCero(num: Int): Boolean {
        return num > 0
    };
    fun jugar(num: Int, num2: Int, eleccion: String): Boolean {
        var sumaDados = num + num2
        return when (eleccion) {
            "Par" -> esPar(sumaDados)
            "Impar" -> !esPar(sumaDados)
            "Mayor o igual que 7" -> sumaDados >= 7
            "Menor que 7" -> sumaDados < 7
            else -> false
        }
    }

    fun esPar(num: Int): Boolean {
        return num % 2 == 0
    }

    fun generarNumeroAleatorio(): Int = (Math.random() * 6).toInt() + 1

    fun validarNumero(num: Int): Boolean {
        return saldoIncial >= num
    }

    fun lanzarSnackBar(opcion: String) {

        val sn: Snackbar = when (opcion) {
            "1" -> {
                Snackbar.make(
                    binding.coordinatorLayout,
                    "Debe elegir una opción de juego",
                    Snackbar.LENGTH_INDEFINITE
                )
            }

            "2" -> {
                Snackbar.make(
                    binding.coordinatorLayout,
                    "Debe introducir una apuesta",
                    Snackbar.LENGTH_INDEFINITE
                )
            }

            "3" -> {
                Snackbar.make(
                    binding.coordinatorLayout,
                    "La apuesta debe ser un número válido",
                    Snackbar.LENGTH_INDEFINITE
                )
            }

            "4" -> {
                Snackbar.make(
                    binding.coordinatorLayout,
                    "La apuesta debe ser menor al saldo disponible",
                    Snackbar.LENGTH_INDEFINITE
                )
            }

            "5" -> {
                Snackbar.make(
                    binding.coordinatorLayout,
                    "La apuesta debe ser mayor a 0",
                    Snackbar.LENGTH_INDEFINITE
                )
            }

            else -> {
                return
            }
        }

        sn.show()
        val snQuitar = sn.view

        snQuitar.setOnTouchListener(object : View.OnTouchListener {
            private var posicioninicial = 0f
            private var posicionManito = 0f
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (v == null || event == null) return false

                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        posicioninicial = event.rawX
                        posicionManito = v.x - posicioninicial
                    }

                    MotionEvent.ACTION_MOVE -> {
                        v.x = event.rawX + posicionManito
                    }

                    MotionEvent.ACTION_UP -> {
                        if (Math.abs(v.x - posicioninicial) > 200) {
                            sn.dismiss()
                        } else {
                            v.animate().x(posicioninicial).setDuration(200).start()
                        }
                    }
                }
                return true
            }

        })
    }

    fun preguntarSiContinuarJugando() {
        val alerta = AlertDialog.Builder(this)
        alerta.setMessage("Jugando a los dados")
            .setCancelable(false)
            .setPositiveButton("Seguir jugando") { dialog, id -> }
            .setNegativeButton("Salir del juego") { dialog, id ->
                finish()
            }
        alerta.create().show()

    }

    fun bancarota() {
        binding.imageView2?.setImageResource(R.drawable.bancarrota)

        val alerta = AlertDialog.Builder(this)
        alerta.setMessage("Jugando a los dados")
            .setCancelable(false)
            .setNegativeButton("Estas arruinado. Debes dejar el juego.") { dialog, id ->
                finish()
            }
        alerta.create().show()
    }
}
