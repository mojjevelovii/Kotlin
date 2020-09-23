package ru.shumilova.kotlinapp.screen.splash

import androidx.lifecycle.ViewModelProvider
import ru.shumilova.kotlinapp.screen.base.BaseActivity
import ru.shumilova.kotlinapp.screen.main.MainActivity

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {
    override val viewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override val layoutRes = null

    override fun onResume() {
        super.onResume()
        viewModel.requestUser()
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let {
            startMainActivity()
        }
    }

    fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }

}