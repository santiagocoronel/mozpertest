package com.santiagocoronel.mozpertest.features.splash.presenter.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.santiagocoronel.androidbase.presenter.BaseActivity
import com.santiagocoronel.mozpertest.R
import com.santiagocoronel.mozpertest.databinding.ActivitySplashBinding
import com.santiagocoronel.mozpertest.features.home.presenter.view.HomeActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun init() {
        val intent = Intent(this@SplashActivity, HomeActivity::class.java)
        startActivity(intent)
    }

}