package com.oluwasegun.flashlightapp

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var cameraFlash: CameraManager
    private lateinit var imageBtn: ImageButton
    var isFlashOn = false
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imageBtn = findViewById(R.id.torchBtn)
        cameraFlash = getSystemService(Context.CAMERA_SERVICE) as CameraManager


        imageBtn.setOnClickListener { torchOnOrOff(it) }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun torchOnOrOff(v: View?) {
        if(!isFlashOn){
            val cameraListId = cameraFlash.cameraIdList[0]
            cameraFlash.setTorchMode(cameraListId, true)
            isFlashOn = true
            imageBtn.setImageResource(R.drawable.ic_power_on)
            flashMessage("Flash Light is On", this)
        }
        else{
            val cameraListId = cameraFlash.cameraIdList[0]
            cameraFlash.setTorchMode(cameraListId, false)
            isFlashOn = false
            imageBtn.setImageResource(R.drawable.ic_power_off)
            flashMessage("Flash Light is Off", this)
        }
    }

    private fun flashMessage(s: String, c:Context) {
    Toast.makeText(c,s, Toast.LENGTH_LONG).show()
    }

}