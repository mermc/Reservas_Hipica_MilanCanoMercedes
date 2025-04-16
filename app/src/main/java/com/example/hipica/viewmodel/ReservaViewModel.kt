package com.example.hipica.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hipica.model.Reserva
import com.example.hipica.repository.ReservaRepository
import kotlinx.coroutines.launch

// la clase ReservaViewModel extiende de AndroidViewModel para poder usar el contexto de la aplicacion
class ReservaViewModel(app: Application, private val reservaRepository: ReservaRepository): AndroidViewModel(app) {

    fun getAllReservas() = reservaRepository.getAllReservas()

    fun searchReserva(fecha: String, hora: String) =
        reservaRepository.searchReserva(fecha, hora)

    fun addReserva(reserva: Reserva) =
        viewModelScope.launch {
            reservaRepository.insertReserva(reserva)
        }

    fun deleteReserva(reserva: Reserva)=
        viewModelScope.launch {
            reservaRepository.deleteReserva(reserva)
        }

    fun updateReserva(reserva: Reserva)=
        viewModelScope.launch {
            reservaRepository.updateReserva(reserva)
        }
}