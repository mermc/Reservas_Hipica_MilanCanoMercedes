package com.example.hipica.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hipica.model.Reserva

@Dao
//en la Data Access Object definimos los distintos métodos para acceder a la base de datos
interface ReservaDao {
    @Query("SELECT * FROM RESERVAS ORDER BY id DESC")
    fun getAllReservas(): LiveData<List<Reserva>>

    @Query("SELECT * FROM RESERVAS WHERE fechaReserva = :fecha AND horaReserva = :hora")
    fun searchReserva(fecha: String, hora: String): LiveData<List<Reserva>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReserva(reserva: Reserva)

    @Update
    suspend fun updateReserva(reserva: Reserva)

    @Delete
    suspend fun deleteReserva(reserva: Reserva)
}