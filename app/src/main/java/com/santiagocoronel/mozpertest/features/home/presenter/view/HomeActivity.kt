package com.santiagocoronel.mozpertest.features.home.presenter.view

import com.santiagocoronel.androidbase.presenter.BaseActivity
import com.santiagocoronel.mozpertest.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    private val viewModel: HomeViewModel by viewModel()

    override fun init() {
        viewModel.getEmployees()
    }
}