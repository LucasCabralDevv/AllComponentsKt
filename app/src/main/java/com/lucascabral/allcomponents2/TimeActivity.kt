package com.lucascabral.allcomponents2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_time.*
import java.text.SimpleDateFormat
import java.util.*

class TimeActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
        TimePicker.OnTimeChangedListener {

    private val mSimpleDate = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        button_date.setOnClickListener(this)
        button_get_time.setOnClickListener(this)
        button_set_time.setOnClickListener(this)

        time_picker.setOnTimeChangedListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_date -> {

                val calendar = Calendar.getInstance()
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH)
                val year = calendar.get(Calendar.YEAR)

                DatePickerDialog(this, this, year, month, day).show()
            }
            R.id.button_get_time -> {
                if (Build.VERSION.SDK_INT >= 23) {
                    val hour = time_picker.hour
                    val minute = time_picker.minute
                    toast("$hour:$minute")
                } else {
                    val hour = time_picker.currentHour
                    val minute = time_picker.currentMinute
                    toast("$hour:$minute")
                }
            }
            R.id.button_set_time -> {
                if (Build.VERSION.SDK_INT >= 23) {
                    time_picker.hour = 18
                    time_picker.minute = 15
                } else {
                    time_picker.currentHour = 18
                    time_picker.currentMinute = 15
                }
            }
        }
    }

    override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
        toast("$hourOfDay:$minute")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val date = Calendar.getInstance()
        date.set(year, month, dayOfMonth)
        button_date.text = mSimpleDate.format(date.time)
    }

    fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}