package com.example.hipica.dialogs

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import java.util.Calendar

//creación de esta clase Dialog para customizar el datePicker y poder hacer que solo deje fines de semana, desde la fecha actual
class datePickerDialogCustom(
    context: Context,
    private val onDateSetListener: DatePickerDialog.OnDateSetListener
) : DatePickerDialog(context, onDateSetListener,
    Calendar.getInstance().get(Calendar.YEAR),
    Calendar.getInstance().get(Calendar.MONTH),
    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {

    override fun onDateChanged(view: DatePicker, year: Int, month: Int, day: Int) {
        super.onDateChanged(view, year, month, day)
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
            // Si no es sábado o domingo lanzar un toast
            Toast.makeText(context, "Solo se pueden reservar fechas en sábado o domingo", Toast.LENGTH_SHORT).show()
        }
    }

    init {
        // Configurar el primer día de la semana como lunes
        datePicker.firstDayOfWeek = Calendar.MONDAY

        // Establecer fecha actual como mínima para que no se pueda reservar en fechas anteriores
        val today = Calendar.getInstance()
        datePicker.minDate = today.timeInMillis
    }
}
