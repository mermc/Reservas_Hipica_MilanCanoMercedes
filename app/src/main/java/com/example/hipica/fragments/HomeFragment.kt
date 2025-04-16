package com.example.hipica.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hipica.MainActivity
import com.example.hipica.R
import com.example.hipica.adapter.ReservaAdapter
import com.example.hipica.databinding.FragmentHomeBinding
import com.example.hipica.viewmodel.ReservaViewModel
import com.example.hipica.model.Reserva

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener,
    MenuProvider {

    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    private lateinit var reservasViewModel : ReservaViewModel
    private lateinit var reservaAdapter: ReservaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        reservasViewModel = (activity as MainActivity).reservaViewModel

        setupHomeRecyclerView()

        binding.addReservaFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addReservaFragment)

        }
    }

    private fun updateUI(reserva: List<Reserva>?){
        if (reserva != null){
            if (reserva.isNotEmpty()){
                binding.emptyReservasImage.visibility = View.GONE
                binding.homeRecyclerView.visibility = View.VISIBLE
            } else {
                binding.emptyReservasImage.visibility = View.VISIBLE
                binding.homeRecyclerView.visibility = View.GONE
            }
        }
    }

    private fun setupHomeRecyclerView(){
        reservaAdapter = ReservaAdapter()
        binding.homeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = reservaAdapter
        }

        activity?.let {
            reservasViewModel.getAllReservas().observe(viewLifecycleOwner){ reserva ->
                reservaAdapter.differ.submitList(reserva)
                updateUI(reserva)
            }
        }
    }
//buscamos la reserva en la base de datos con los datos de fecha y hora ya filtrados en el método onQueryTextChange
    private fun searchReserva(fecha: String, hora: String){
        val searchQuery = fecha
        val searchQuery2 = hora

        reservasViewModel.searchReserva(searchQuery, searchQuery2).observe(this) {list ->
            reservaAdapter.differ.submitList(list)
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }
//filtramos la busqueda para que solo acepte el formato de fecha y hora generado por la app
    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null){
            val regex = Regex("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")
            if (regex.matches(newText)) {
                val parts = newText.split(" ")
                val fecha = parts[0].trim()
                val hora = parts[1].trim()
                searchReserva(fecha, hora)
            } else {
                return false
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        homeBinding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)

        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = false
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }
}
