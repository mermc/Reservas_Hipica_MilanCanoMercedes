package com.example.hipica.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "reservas")
@Parcelize
data class Reserva(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val nombreJinete: String,
    val telefono: String,
    val nombreCaballo: String,
    val fechaReserva: String,
    val horaReserva: String,
    val comentario: String
): Parcelable
//parcelable is used to pass data between fragments or to the internet
