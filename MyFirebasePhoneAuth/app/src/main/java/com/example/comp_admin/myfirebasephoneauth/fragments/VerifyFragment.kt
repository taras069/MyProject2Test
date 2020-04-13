package com.example.comp_admin.myfirebasephoneauth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskExecutors
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

import kotlinx.android.synthetic.main.fragment_verify.view.*
import java.util.concurrent.TimeUnit

private const val KEY_MOBILE = "mobile"

class VerifyFragment : Fragment() {

    var verificationId: String? = null

    lateinit var mAuth: FirebaseAuth

    private var mobile: String? = null

    var editText: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mobile = it.getString(KEY_MOBILE)
        }

        mAuth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_verify, container, false)
       initView(view)
        return view
    }

    private fun initView(view: View) {
        editText = view.edit_text_code

        sendVerificationCode(mobile!!)

        view.button_verify.setOnClickListener {
            var code = view.edit_text_code.text.toString()
            sendVerificationCode(code)
        }
    }

    private fun sendVerificationCode(mobile : String){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91$mobile",
            60,
            TimeUnit.SECONDS,
            TaskExecutors.MAIN_THREAD,
            mCallBack
        )
    }



    var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
          var code =  phoneAuthCredential.smsCode
            if(code != null){
                editText?.setText(code)
                verifyCode(code)
            }
        }

        override fun onVerificationFailed(e: FirebaseException) {
           Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
            Log.d("varun", e.message)
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(p0, p1)
            verificationId = p0

        }

    }

    private fun verifyCode(code: String){
        var credential = PhoneAuthProvider.getCredential(verificationId!!, code)

        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(activity!!, object: OnCompleteListener<AuthResult>{
                override fun onComplete(task: Task<AuthResult>) {
                    if(task.isSuccessful){
                        activity!!.startActivity(Intent(activity, HomeActivity::class.java))
                    }else{
                        Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }

            })
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            VerifyFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_MOBILE, param1)

                }
            }
    }
}
