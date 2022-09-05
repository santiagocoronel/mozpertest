package com.santiagocoronel.mozpertest.features.login.presenter.view

import android.content.Intent
import android.widget.Toast
import com.santiagocoronel.androidbase.presenter.BaseActivity
import com.santiagocoronel.mozpertest.R
import com.santiagocoronel.mozpertest.databinding.ActivityLoginBinding
import com.santiagocoronel.mozpertest.features.home.presenter.view.HomeActivity
import com.santiagocoronel.mozpertest.preference.PreferencesManager
import com.santiagocoronel.mozpertest.preference.PreferencesManager.KEY_LOGGED

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun init() {

        binding.buttonLogin.setOnClickListener {
            if (binding.editTextTextEmailAddress.text.isNotEmpty() && binding.editTextNumberPassword.text.isNotEmpty()) {

                PreferencesManager.getInstance().saveKey(KEY_LOGGED, "true")

                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    resources.getString(R.string.complete_the_fields),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

}