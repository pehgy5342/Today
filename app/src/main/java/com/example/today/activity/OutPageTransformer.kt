package com.example.today.activity

import android.view.View
import androidx.viewpager.widget.ViewPager

class OutPageTransformer : ViewPager.PageTransformer {
    val MIN_SCALE = 0.85f
    val MIN_ALPHA = 0.6f

    override fun transformPage(page: View, position: Float) {
        val width = page.width
        val height = page.height

        //向左滑
        if (position < -1) {

            page.alpha = 0f

        } else if (position <= 1) {

            val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
            val horzMargin = width * (1 - scaleFactor) / 2
            val vertMargin = height * (1 - scaleFactor) / 2

            if (position < 0) {

                page.translationX = horzMargin - vertMargin / 2

            } else {

                page.translationX = -horzMargin + vertMargin / 2

            }
            page.scaleX = scaleFactor
            page.scaleY = scaleFactor
            page.alpha = Math.max(MIN_ALPHA, 1 - Math.abs(position))


        } else {

            page.alpha = 0f
        }


    }
}