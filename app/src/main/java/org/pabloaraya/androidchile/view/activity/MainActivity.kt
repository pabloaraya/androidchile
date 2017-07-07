package org.pabloaraya.androidchile.view.activity

import android.support.v4.app.Fragment
import org.pabloaraya.androidchile.R
import org.pabloaraya.androidchile.view.fragment.PostFragment
import com.facebook.AccessToken
import org.jetbrains.anko.intentFor


class MainActivity : BaseFragmentActivity() {

    override fun onPrepareActivity() {
        super.onPrepareActivity()
        if (!isLoggedIn()) {
            startActivity(intentFor<LoginActivity>())
            finish()
        }
    }

    override fun containerViewId(): Int {
        return R.id.main_container
    }
    override fun fragmentInstance(): Fragment {
        return PostFragment()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    fun isLoggedIn(): Boolean {
        val accessToken = AccessToken.getCurrentAccessToken()
        return accessToken != null
    }
}
