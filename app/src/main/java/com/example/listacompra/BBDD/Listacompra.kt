package com.example.listacompra.BBDD

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="Lista_Compra")
class Listacompra (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nombre: String = "",
    var activo: Boolean = false

        )