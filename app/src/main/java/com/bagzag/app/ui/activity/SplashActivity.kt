package com.bagzag.app.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.bagzag.app.databinding.SplashActivityBinding
import com.bagzag.app.ui.auth.fragment.LoginFragment
import com.bagzag.app.ui.auth.fragment.WelcomeScreenFragment
import com.bagzag.app.ui.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    //Data store on after user login
    lateinit var splashActivityBinding: SplashActivityBinding
    override fun findFragmentPlaceHolder(): Int {
        return 0
    }

    override fun createViewBinding(): View {
        splashActivityBinding = SplashActivityBinding.inflate(layoutInflater)
        return splashActivityBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({

            loadActivity(
                IsolatedActivity::class.java,
                WelcomeScreenFragment::class.java
            ).byFinishingCurrent().start()

            //loadActivity(HomeActivity::class.java).byFinishingCurrent().start()

        }, 2000)
    }
}