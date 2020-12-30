package com.lucascabral.allcomponents2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_toast.setOnClickListener(this)
        button_snackBar.setOnClickListener(this)
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
        }
    }

    fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }
}