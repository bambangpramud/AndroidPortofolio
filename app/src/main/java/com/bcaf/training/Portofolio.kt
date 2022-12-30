package com.bcaf.training

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_portofolio.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class Portofolio : AppCompatActivity() {

    companion object{
        private val REQUEST_CODE_PERMISSION = 999
        private val REQUEST_CAMERA_PERMISSION = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portofolio)


        imgDial.setOnClickListener(){
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:08188899911")
            }
            startActivity(intent)
        }

        imgEmail.setOnClickListener(){
            val intent=Intent(Intent.ACTION_SEND).apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("bambangpramudhitab@gmail.com")) // recipients
                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                putExtra(Intent.EXTRA_TEXT, "Email message text")

            }
            startActivity(intent)

        }

        imgMaps.setOnClickListener(){
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=wisma+bca+pondok+indah"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

        imgProfile.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permissions,REQUEST_CODE_PERMISSION)
                } else{
                    captureCamera()
                }
            }
        })
    }

    fun captureCamera(){
        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takeCamera, REQUEST_CAMERA_PERMISSION)

    }

    fun toCalculator(view: View){
        val intent = Intent(this,Portofolio::class.java)
        startActivity(intent)
    }
    fun toMenuActivity(view: View){
        val intent = Intent(this,MenuActivity::class.java)
        startActivity(intent)
    }

    fun saveImage(bitmap:Bitmap){
        try {
            val tanggal = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val extStorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()
            val namaFile = extStorage+"/BCAF"+tanggal+".png"

//        val image = File.createTempFile(namaFile,".png",extStorage)
            var file:File? = null
            file =  File(namaFile)
            file.createNewFile()
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos)
            val bitmapData = bos.toByteArray()
            val fos =FileOutputStream(file)
            fos.write(bitmapData)
            fos.flush()
            fos.close()
        }catch (e:java.lang.Exception){
            e.printStackTrace()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    captureCamera();
                }else{
                    Toast.makeText(this,"Maaf Permission Denied", Toast.LENGTH_LONG).show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== REQUEST_CAMERA_PERMISSION && resultCode== RESULT_OK){
            val bitmapImage = data?.extras?.get("data") as Bitmap
            imgProfile.setImageBitmap(bitmapImage)
            saveImage(bitmapImage)
        }
    }
}