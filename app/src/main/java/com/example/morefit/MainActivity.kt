package com.example.morefit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        setContentView(R.layout.activity_main)

        findViewById<ImageButton>(R.id.cal_btn).setOnClickListener(this)
        findViewById<ImageButton>(R.id.setting_btn).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.cal_btn -> bottomCal()
            R.id.setting_btn -> bottomSetting()
        }
    }

    private fun bottomSetting() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.setting_option, null)
        dialog.setContentView(view)
        dialog.setCancelable(true)
        dialog.show()
    }

    private fun bottomCal() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.cal_option, null)
        dialog.setContentView(view)
        dialog.setCancelable(true)
        dialog.show()
    }


}