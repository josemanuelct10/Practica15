package com.example.listacompra.BBDD

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Dao
interface ListaDAO {
    @Query("SELECT * FROM lista_compra")
    fun getAllElements(): MutableList<Listacompra>

    @Insert
    fun addElemento(elemento: Listacompra): Long

    @Query("SELECT * FROM lista_compra WHERE id like :id")
    fun getElementoId(id: Long): Listacompra

    @Update
    fun updateLista(taslEntity: Listacompra):Int

    @Delete
    fun deleteLista(elemento: Listacompra)
}