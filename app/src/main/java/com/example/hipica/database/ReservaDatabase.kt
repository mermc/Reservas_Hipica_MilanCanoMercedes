package com.example.hipica.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hipica.model.Reserva

// La clase ReservaDatabase es la base de datos de Room que contiene la entidad Reserva e interactúa con el DAO ReservaDao
@Database(entities = [Reserva::class], version = 1)
abstract class ReservaDatabase: RoomDatabase() {

    abstract fun getReservaDao(): ReservaDao

    companion object{
        @Volatile
        private var instance: ReservaDatabase? = null
        private val LOCK = Any()
        // El nombre de la base de datos
        private const val DATABASE_NAME = "reservasDB"

        // El método invoke se utiliza para obtener una instancia de la base de datos
        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?:
            createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ReservaDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}