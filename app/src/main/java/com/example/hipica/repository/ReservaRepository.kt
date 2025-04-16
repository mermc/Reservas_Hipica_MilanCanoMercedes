package com.example.hipica.repository

import com.example.hipica.database.ReservaDatabase
import com.example.hipica.model.Reserva

// la clase ReservaRepository es la que se encarga de interactuar con la base de datos y el DAO ReservaDao para realizar las operaciones CRUD
class ReservaRepository(private val db: ReservaDatabase) {

    fun getAllReservas() = db.getReservaDao().getAllReservas()
    fun searchReserva(fecha: String, hora: String) = db.getReservaDao().searchReserva(fecha, hora)

    suspend fun insertReserva(reserva: Reserva) = db.getReservaDao().insertReserva(reserva)
    suspend fun deleteReserva(reserva: Reserva) = db.getReservaDao().deleteReserva(reserva)
    suspend fun updateReserva(reserva: Reserva) = db.getReservaDao().updateReserva(reserva)

}