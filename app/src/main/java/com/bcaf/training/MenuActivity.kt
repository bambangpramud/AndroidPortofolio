package com.bcaf.training

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_menu.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Month
import java.time.Period
import java.time.Year
import java.util.*

class MenuActivity : AppCompatActivity() {
    var username:String=""
    var password:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val datas :Bundle?= intent.extras
        username = datas?.getString("username","").toString()
        password = datas?.getString("password","").toString()
        txtHello.text =  "${txtHello.text} $username"
//        animateText()
    btnPickDate.setOnClickListener(View.OnClickListener { pickDate() })


        btnDial.setOnClickListener(){
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:08188899911")
            }
            startActivity(intent)
        }
    }

    fun animateText(){
        val anim = AlphaAnimation(0.0f,1f)
        anim.duration=50;
        anim.startOffset-20
        anim.repeatMode= Animation.REVERSE
        anim.repeatCount=Animation.INFINITE
        txtHello.startAnimation(anim)
    }

    fun pickDate(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dateSetListener = object : DatePickerDialog.OnDateSetListener{
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDateSet(
                view: DatePicker , year:Int, monthOfYear: Int, dayOfMonth: Int
            ){
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
                txtTanggalLahir.setText(sdf.format(c.getTime()))
//                val dateNow = Date()

                var diff = Period.between(LocalDate.of(year,month,dayOfMonth-1),LocalDate.now())

                txtUmur.setText("${diff.years} tahun ${diff.months} bulan ")

            }
        }

        DatePickerDialog(this,dateSetListener,
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
        c.get(Calendar.DAY_OF_MONTH)).show()
    }


}