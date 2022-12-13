package com.example.listacompra.BBDD

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(Listacompra::class),
    version = 1
)
abstract class DBLista : RoomDatabase(){
    abstract fun listaDao(): ListaDAO
}