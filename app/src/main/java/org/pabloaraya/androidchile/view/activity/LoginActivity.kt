package org.pabloaraya.androidchile.view.activity

import android.content.Intent
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import org.jetbrains.anko.intentFor
import org.pabloaraya.androidchile.R

/**
 * Created by pablo on 7/6/17.
 */
class LoginActivity: BaseActivity() {

    private val callbackManager : CallbackManager = CallbackManager.Factory.create()

    override fun onPrepareActivity() {
        super.onPrepareActivity()

        val loginButton: LoginButton = findViewById(R.id.login_button) as LoginButton
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult>{
            override fun onCancel() {

            }

            override fun onError(p0: FacebookException?) {

            }

            override fun onSuccess(p0: LoginResult?) {
                startActivity(intentFor<MainActivity>())
                finish()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }
}
