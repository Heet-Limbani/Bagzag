package com.bagzag.app.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bagzag.app.R
import com.bagzag.app.databinding.DashboardAcitivtyBinding
import com.bagzag.app.ui.base.BaseActivity
import com.bagzag.app.ui.base.BaseFragment
import com.bagzag.app.ui.manager.ActivityStarter
import com.bagzag.app.utils.serializable

class DashboardActivity : BaseActivity() {

    private lateinit var dashboardActivityBinding: DashboardAcitivtyBinding

    override fun findFragmentPlaceHolder(): Int {
        return R.id.placeHolder
    }

    override fun createViewBinding(): View {
        dashboardActivityBinding = DashboardAcitivtyBinding.inflate(layoutInflater)
        return dashboardActivityBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val page: Class<BaseFragment<*>> =
                intent.serializable(ActivityStarter.ACTIVITY_FIRST_PAGE)
            load(page)
                .setBundle(intent.extras!!)
                .replace(false)
        }
    }
}