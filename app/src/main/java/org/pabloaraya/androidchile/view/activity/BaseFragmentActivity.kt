package org.pabloaraya.androidchile.view.activity

import android.support.v4.app.Fragment
import org.pabloaraya.androidchile.view.activity.BaseActivity


/**
 * Created by Pablo Araya on 5/30/17.
 */
abstract class BaseFragmentActivity : BaseActivity() {

    override fun onPrepareActivity() {
        super.onPrepareActivity()
        onPrepareFragment()
    }

    private fun onPrepareFragment() {
        var targetNestedFragment = supportFragmentManager.findFragmentById(containerViewId())
        /*if (targetNestedFragment == null) {
            targetNestedFragment = fragmentInstance
            supportFragmentManager.beginTransaction()
                    .add(containerViewId, targetNestedFragment)
                    .commit()
        }*/

        targetNestedFragment = fragmentInstance()
        supportFragmentManager.beginTransaction()
                .add(containerViewId(), targetNestedFragment)
                .commit()
    }

    protected abstract fun containerViewId(): Int

    protected abstract fun fragmentInstance(): Fragment
}