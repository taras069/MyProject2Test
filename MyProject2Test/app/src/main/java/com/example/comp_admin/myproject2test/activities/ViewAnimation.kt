package com.example.comp_admin.myproject2test.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View


class ViewAnimation {
    fun rotateFab(v: View, rotate: Boolean): Boolean {
        v.animate().setDuration(200)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                }
            })
            .rotation(if (rotate) 135f else 0f)
        return rotate
    }
}