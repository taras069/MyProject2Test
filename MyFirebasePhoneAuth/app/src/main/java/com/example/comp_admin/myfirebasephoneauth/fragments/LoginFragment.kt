package com.example.comp_admin.myfirebasephoneauth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth


import kotlinx.android.synthetic.main.fragment_login.view.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_login, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        view.button_continue.setOnClickListener {
            var mobile = view.edit_text_mobile.text.toString()
            var verifyFragment = VerifyFragment.newInstance(mobile)
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, verifyFragment).commit()
        }
    }

}
