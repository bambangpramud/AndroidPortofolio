package com.bcaf.training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    lateinit var username:EditText
//    lateinit var password :EditText
//    lateinit var btnLogin:Button
    lateinit var container:LinearLayout
    var counter=0;


    fun init(){
//        username = findViewById(R.id.txt_username)
//        password = findViewById(R.id.txt_password)
//        btnLogin = findViewById((R.id.btn_login))
//        container = findViewById(R.id.containerDummy)


        btn_login.setOnClickListener(View.OnClickListener {
            checkPassword(it)
//            var button = Button(applicationContext)
//            button.text="Button" + counter++
//            containerDummy.addView(button)
////            var nama = readLine();
////            print(nama!!.length)

        })


    }

    val defaultPassword = "12345"
    val defaultUsername="user"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    init()
    }

    fun checkPassword(v:View){
        if (txt_username.text.contentEquals(defaultUsername)&& txt_password.text.contentEquals(defaultPassword)){
//            Toast.makeText(applicationContext, "Selamat anda berhasil Login!",Toast.LENGTH_LONG).show()
            val intent = Intent(this,Portofolio::class.java)
            intent.putExtra("username",txt_username.text.toString())
            intent.putExtra("password",txt_password.text.toString())
            startActivity(intent)


        }else{
            Toast.makeText(applicationContext, "Username tidak ditemukan",Toast.LENGTH_LONG).show()
        }
    }

    fun forgotPassword(v:View){
        Toast.makeText(applicationContext, "Forgot password ",Toast.LENGTH_LONG).show()
    }

//    override fun onStart() {
//        super.onStart()
//        Log.d("Lifecycle Activity","start action");
//    }
//
//    override fun onResume() {
//        super.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//    }
//
//    override fun onStop() {
//        super.onStop()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//    }
}