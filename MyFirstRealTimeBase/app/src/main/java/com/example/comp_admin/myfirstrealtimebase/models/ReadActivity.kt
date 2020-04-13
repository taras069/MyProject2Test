package com.example.comp_admin.myfirstrealtimebase.models

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.comp_admin.myfirstrealtimebase.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ReadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        init()
    }

    private fun init() {
        val database = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("users")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // This method is called once with the initial value and again
// whenever data at this location is updated.
                val value =
                    dataSnapshot.getValue(User::class.java)
                //Log.d("value ", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) { // Failed to read value
                Log.w("value ", "Failed to read value.", error.toException())
            }
        })
    }
}
