package com.example.listacompra

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listacompra.BBDD.Listacompra
import com.example.listacompra.BBDD.MiListaApp
import com.example.listacompra.adapter.ListaAdapter
import com.example.listacompra.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var elementos: MutableList<Listacompra>
    lateinit var adapter : ListaAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        elementos = ArrayList()

        getElementos()

        binding.anadir.setOnClickListener{
            addElemento(Listacompra(nombre = binding.texto.text.toString()))
        }

    }

    fun addElemento(elemento:Listacompra){
        CoroutineScope(Dispatchers.IO).launch {
            val id = MiListaApp.database.listaDao().addElemento(elemento)

            val recoveryElemento = MiListaApp.database.listaDao().getElementoId(id)
            runOnUiThread{
                elementos.add(recoveryElemento)

                adapter.notifyItemInserted(elementos.size)

                clearFocus()

                hideKeyboard()
            }
        }
    }

    fun updateLista(elemento: Listacompra){
        CoroutineScope(Dispatchers.IO).launch {
            elemento.activo = !elemento.activo
            MiListaApp.database.listaDao().updateLista(elemento)
        }
    }

    fun deleteLista(elemento: Listacompra){
        CoroutineScope(Dispatchers.IO).launch {
            val position = elementos.indexOf(elemento)
            MiListaApp.database.listaDao().deleteLista(elemento)
            elementos.remove(elemento)
            runOnUiThread{
                adapter.notifyItemRemoved(position)
            }
        }
    }

    //Al pulsar sobre el boton añadir, se limpia
    fun clearFocus(){
        binding.texto.setText("")
    }

    //Oculta el teclado cuando terminamos de escribir en el cuadro de texto
    fun Context.hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.texto.windowToken, 0)
    }

    //Obtenemos todas las filas de la tabla y se las pasamos al adapter del recyclerView, para que las muestre
    fun getElementos() {
        CoroutineScope(Dispatchers.IO).launch  {
            elementos = MiListaApp.database.listaDao().getAllElements()

            runOnUiThread  {
                adapter = ListaAdapter(elementos, {updateLista(it)}, {deleteLista(it)})
                recyclerView= binding.recycler
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = adapter

            }
        }
    }


}