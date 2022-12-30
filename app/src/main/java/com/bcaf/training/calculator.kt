package com.bcaf.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_calculator.*
import org.mariuszgromada.math.mxparser.Expression

class calculator : AppCompatActivity() {
    var isComa = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
    }

    fun onDigitPress(view: View){
        if (txtInputNumber.text.toString().equals("0")){
            txtInputNumber.setText((view as Button).text)
        }else{

        txtInputNumber.append((view as Button).text)
        }
    }

    fun onClean(view: View){

        txtInputNumber.setText("0")
    }
    fun onOperatorPress(view: View){
//        if((view as Button).equals(".")&& isComa==false){
//
//        }else{
//
//        }
        if (txtInputNumber.text.toString().substring(txtInputNumber.text.toString().length-1).equals(null)){

            txtInputNumber.append((view as Button).text)


        }else{
        if (txtInputNumber.text.toString().substring(txtInputNumber.text.toString().length-1).equals(".")){
            txtInputNumber.setText(txtInputNumber.text.toString().substring(0,txtInputNumber.text.toString().length-1))
            isComa=false;
        }else{
            txtInputNumber.append((view as Button).text)}
        }



    }
    fun onResult(view: View){
        if (txtInputNumber.text.toString().substring(txtInputNumber.text.toString().length-1).equals(".")){
            txtInputNumber.setText(txtInputNumber.text.toString().substring(0,txtInputNumber.text.toString().length-1))
        }else{
            var e = Expression(txtInputNumber.text.toString())

            txtInputNumber.setText(e.calculate().toString())
        }

    }


}