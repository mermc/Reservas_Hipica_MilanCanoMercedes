package com.example.hipica.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hipica.repository.ReservaRepository

// la clase ReservaViewModelFactory implementa la interfaz ViewModelProvider.Factory para crear instancias de ReservaViewModel
class ReservaViewModelFactory(val app: Application, private val reservaRepository: ReservaRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReservaViewModel(app, reservaRepository) as T
    }
}