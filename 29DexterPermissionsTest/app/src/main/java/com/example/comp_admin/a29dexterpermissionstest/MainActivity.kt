package com.example.comp_admin.a29dexterpermissionstest

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        button_single.setOnClickListener(this)
        button_multiple.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button_single ->{
                requestCameraPermission()
            }
            R.id.button_multiple ->{
                requestMultiplePermissions()
            }
        }
    }

    private fun requestMultiplePermissions() {
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .withListener(object: MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    // check if all permissions are granted
                    if(report!!.areAllPermissionsGranted()){
                        Toast.makeText(applicationContext, "All permission are granted", Toast.LENGTH_SHORT).show()
                    }

                    // check for permanent denial of any permission
                    if(report.isAnyPermissionPermanentlyDenied){
                        Toast.makeText(applicationContext, "permission denied permanently", Toast.LENGTH_SHORT).show()
                        showDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
                    token?.continuePermissionRequest()
                }

            })
            .onSameThread()
            .check()
    }

    private fun requestCameraPermission() {
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    // permission is granted
                    openCamera()
                }

                override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                    token?.continuePermissionRequest()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    // check for permission denial
                    if (response!!.isPermanentlyDenied) {
                        Toast.makeText(applicationContext, "Permission denied", Toast.LENGTH_SHORT).show()
                    }
                }

            }).check()
    }

    private fun openCamera() {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE)
    }
    private fun showDialog(){
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permissions")
        builder.setMessage("Please please please plase give me this permission")
        builder.setPositiveButton("Go to setting", object: DialogInterface.OnClickListener{
            override fun onClick(dialoge: DialogInterface, p1: Int) {
                dialoge.dismiss()
                openSettings()
            }

        })
        builder.setNegativeButton("Cancel", object: DialogInterface.OnClickListener{
            override fun onClick(dialoge: DialogInterface?, p1: Int) {
                dialoge?.dismiss()
            }

        })
        builder.show()

    }

    private fun openSettings() {
        var intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        var uri = Uri.fromParts("package", packageName, null)
        intent.setData(uri)
        startActivityForResult(intent, 101)
    }
}
