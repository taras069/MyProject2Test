package com.example.comp_admin.myproject2test.activities

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comp_admin.myproject2test.R
import com.example.comp_admin.myproject2test.activities.adapters.ImageAdapter
import com.example.comp_admin.myproject2test.activities.helpers.Config
import com.example.comp_admin.myproject2test.activities.models.Image
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_home.*
import java.io.ByteArrayOutputStream
import java.io.IOException


class HomeActivity : AppCompatActivity(), View.OnClickListener {

    val REQUEST_CODE_GALLERY  = 101
    val REQUEST_CODE_CAMERA = 102

    lateinit var dbReference: DatabaseReference
    var auth = FirebaseAuth.getInstance()

    lateinit var mAdapter : ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        requestPermission()
        dbReference = FirebaseDatabase.getInstance().getReference(Config.FIREBASE_DATABASE_NAME)

        init()
    }

    private fun init() {

        setupAdapter()
        updateRecyclerView()

        button_gallery.setOnClickListener(this)
        button_camera.setOnClickListener(this)

    }

    private fun updateRecyclerView() {
        //read from database here , then update the recycler view
        dbReference.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {TODO("Not yet implemented") }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var imageList: ArrayList<Image> = ArrayList()
                for (data in dataSnapshot.children){
                    var image: Image? = data.getValue(
                        Image::class.java)
                    image?.imageId = data.key
                    imageList.add(image!!)
                }
                mAdapter.setData(imageList)
            }
        })
    }

    private fun setupAdapter() {
        rv_images.adapter = ImageAdapter(this)
        rv_images.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button_gallery -> {
                choosePhotoFromGallery()
            }
            R.id.button_camera-> {
                takePhotoFromCamera()
            }
        }
    }

    private fun requestPermission(){
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
            .withListener(object: MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        Toast.makeText(
                            applicationContext,
                            "Permnission Granted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }


            })
            .onSameThread()
            .check()
    }

    private fun takePhotoFromCamera() {
        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intentCamera, REQUEST_CODE_CAMERA)

    }

    private fun choosePhotoFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE_GALLERY){
            if(data !=null){
                var contentUri = data.data

                try{
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,contentUri)
                    Toast.makeText(applicationContext, "Gallery", Toast.LENGTH_SHORT).show()

                    var path =  getImagePath(bitmap)//change bitmap to path string
                    Log.i("pathLog", "$path")
                    uploadToFirebase(path) //save it into the firebase
                    updateRecyclerView()  //update the recycler view

                } catch (e: IOException){
                    e.printStackTrace()
                    Toast.makeText(applicationContext,e.message,Toast.LENGTH_SHORT).show()
                }
            }
        }else if(requestCode == REQUEST_CODE_CAMERA){
            val thumbnail = data?.extras?.get("data") as Bitmap
            //change bitmap to path string
            var path =  getImagePath(thumbnail)
            Log.i("pathLog", "$path")

            uploadToFirebase(path) //save the image into the firebase
            updateRecyclerView() //update the recycler view
            Toast.makeText(applicationContext,"Camera ",Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadToFirebase(path: String) {
        var imageId = dbReference.push().key
        var image = Image(imageId, path)
        dbReference.child(imageId!!).setValue(image)
    }

    fun getImagePath(inImage:Bitmap):String {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(this.getContentResolver(), inImage, "Title", null)
        return path
    }
}