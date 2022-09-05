package com.santiagocoronel.mozpertest.features.splash.presenter.view

import android.content.Intent
import com.santiagocoronel.androidbase.presenter.BaseActivity
import com.santiagocoronel.mozpertest.databinding.ActivitySplashBinding
import com.santiagocoronel.mozpertest.features.home.presenter.view.HomeActivity
import com.santiagocoronel.mozpertest.features.login.presenter.view.LoginActivity
import com.santiagocoronel.mozpertest.preference.PreferencesManager
import com.santiagocoronel.mozpertest.preference.PreferencesManager.KEY_LOGGED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun init() {

        GlobalScope.launch(Dispatchers.IO) {

            Thread.sleep(2500)

            if (PreferencesManager.getInstance().getKey(KEY_LOGGED) == "true"){
                val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }


    }

}