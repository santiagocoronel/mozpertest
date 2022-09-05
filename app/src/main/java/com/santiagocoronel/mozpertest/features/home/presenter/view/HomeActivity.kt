package com.santiagocoronel.mozpertest.features.home.presenter.view

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.santiagocoronel.androidbase.presenter.BaseActivity
import com.santiagocoronel.mozpertest.R
import com.santiagocoronel.mozpertest.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>() {


    override fun init() {

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostHome) as NavHostFragment
        navHostFragment.navController.setGraph(R.navigation.home_navigation, Bundle())

    }
}