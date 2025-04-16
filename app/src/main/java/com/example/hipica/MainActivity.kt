package com.example.hipica

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.hipica.database.ReservaDatabase
import com.example.hipica.databinding.ActivityMainBinding
import com.example.hipica.repository.ReservaRepository
import com.example.hipica.viewmodel.ReservaViewModel
import com.example.hipica.viewmodel.ReservaViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var reservaViewModel: ReservaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
    }

    private fun setupViewModel(){
        // Inicializamos el ViewModel y el repositorio
        val reservaRepository = ReservaRepository(ReservaDatabase(this))
        val viewModelProviderFactory = ReservaViewModelFactory(application, reservaRepository)
        reservaViewModel = ViewModelProvider(this, viewModelProviderFactory)[ReservaViewModel::class.java]
    }
}