package com.example.hipica.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hipica.databinding.ReservaLayoutBinding
import com.example.hipica.fragments.HomeFragmentDirections
import com.example.hipica.model.Reserva

// Adapter para mostrar las reservas en la lista del HomeFragment
class ReservaAdapter : RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>() {
    // ViewHolder para cada elemento de la lista
    class ReservaViewHolder(val itemBinding: ReservaLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    // Diferenciador para comparar los elementos de la lista
    private val differCallback = object : DiffUtil.ItemCallback<Reserva>(){
        override fun areItemsTheSame(oldItem: Reserva, newItem: Reserva): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.nombreJinete == newItem.nombreJinete &&
                    oldItem.telefono == newItem.telefono &&
                    oldItem.nombreCaballo == newItem.nombreCaballo &&
                    oldItem.fechaReserva == newItem.fechaReserva &&
                    oldItem.horaReserva == newItem.horaReserva &&
                    oldItem.comentario == newItem.comentario

        }

        override fun areContentsTheSame(oldItem: Reserva, newItem:Reserva): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    // Inflamos el layout de cada elemento de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        return ReservaViewHolder(
            ReservaLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // Enlazamos los datos del modelo a la vista mostrando los detalles de la reserva según el ViewHolder
    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        val currentReserva = differ.currentList[position]

        holder.itemBinding.nombreJinete.text = "Nombre: ${currentReserva.nombreJinete}"
        holder.itemBinding.telefono.text = "Teléfono: ${currentReserva.telefono}"
        holder.itemBinding.nombreCaballo.text = "Caballo: ${currentReserva.nombreCaballo}"
        holder.itemBinding.fechaReserva.text = "Fecha: ${currentReserva.fechaReserva.toString()}"
        holder.itemBinding.horaReserva.text = "Hora: ${currentReserva.horaReserva.toString()}"
        holder.itemBinding.comentario.text = "Comentarios: ${currentReserva.comentario}"

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditReservaFragment(currentReserva)
            it.findNavController().navigate(direction)
        }
    }
}