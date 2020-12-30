package com.lucascabral.allcomponents2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener
, SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()

        spinner_static.onItemSelectedListener = this
        seekBar.setOnSeekBarChangeListener(this)

        switch_main.setOnCheckedChangeListener(this)

        loadSpinner()
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.button_toast -> {
                val toast = Toast.makeText(this, "Toast teste", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP, 0,400)

                //val textView = toast.view?.findViewById<TextView>(android.R.id.message)
                //textView?.setTextColor(Color.BLUE)

                val layout = layoutInflater.inflate(R.layout.toast_layout, null)
                toast.view = layout

                toast.show()
            }

            R.id.button_snackBar -> {
                val snack = Snackbar.make(linear_root, "Snack", Snackbar.LENGTH_LONG)

                snack.setAction("Desfazer", View.OnClickListener {
                    toast("Desfeito")
                })

                snack.setActionTextColor(Color.WHITE)
                snack.setTextColor(Color.WHITE)
                snack.setBackgroundTint(Color.DKGRAY)

                snack.show()
            }

            R.id.button_get_spinner -> {
                val selectedItem = spinner_static.selectedItem
                val selectedItemId = spinner_static.selectedItemId
                val selectedItemPosition = spinner_static.selectedItemPosition
                toast("Item: $selectedItemId: $selectedItem" )
            }
            R.id.button_set_spinner -> {
                spinner_static.setSelection(2)
            }

            R.id.button_get_seekBar -> {
                toast("SeekBar: ${seekBar.progress}")
            }
            R.id.button_set_seekBar -> {
                seekBar.progress = 20
            }


        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when(buttonView?.id) {
            R.id.switch_main -> {
                toast("Switch: ${if (isChecked) "true" else "false"}")
                //switch_main.isChecked = true
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        seekBar_value.text = "Valor seekBar: $progress"
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        toast("Track Started")
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        toast("Track Stoped")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id) {
            R.id.spinner_static -> {
                toast(parent.getItemAtPosition(position).toString())
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        toast("Nothing")
    }

    fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    private fun loadSpinner() {
        val mList = listOf("Km", "M", "Cm", "Mm")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mList)
        spinner_dynamic.adapter = adapter
    }

    private fun setListeners() {
        button_toast.setOnClickListener(this)
        button_snackBar.setOnClickListener(this)

        button_get_spinner.setOnClickListener(this)
        button_set_spinner.setOnClickListener(this)

        button_get_seekBar.setOnClickListener(this)
        button_set_seekBar.setOnClickListener(this)
    }
}