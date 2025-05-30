package com.bagzag.app.ui.activity

import android.os.Bundle
import android.view.View
import com.bagzag.app.R
import com.bagzag.app.databinding.IsolatedAcitivtyFullBinding
import com.bagzag.app.ui.base.BaseActivity
import com.bagzag.app.ui.base.BaseFragment
import com.bagzag.app.ui.manager.ActivityStarter
import com.bagzag.app.utils.serializable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IsolatedActivity : BaseActivity() {

    private lateinit var isolatedFullActivityBinding: IsolatedAcitivtyFullBinding

    override fun findFragmentPlaceHolder(): Int {
        return R.id.placeHolder
    }

    override fun createViewBinding(): View {
        isolatedFullActivityBinding = IsolatedAcitivtyFullBinding.inflate(layoutInflater)
        return isolatedFullActivityBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val page : Class<BaseFragment<*>> = intent.serializable(ActivityStarter.ACTIVITY_FIRST_PAGE)
            load(page)
                .setBundle(intent.extras!!)
                .replace(false)
        }
    }
}