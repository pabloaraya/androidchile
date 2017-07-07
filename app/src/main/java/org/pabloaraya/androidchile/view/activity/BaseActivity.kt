package org.pabloaraya.androidchile.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.support.annotation.LayoutRes
import android.support.annotation.Nullable
import android.support.v7.app.ActionBar

import kotlinx.android.synthetic.main.activity_main.toolbar

/**
 * Created by Pablo Araya on 5/29/17.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        onPrepareSupportActionBar()
        onPreparePresenter()
        onPrepareActivity()
    }

    private fun onPrepareSupportActionBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            onSetupSupportActionBar(supportActionBar)
        }
    }

    protected fun onSetupSupportActionBar(actionBar: ActionBar?) {

    }

    protected open fun onPrepareActivity() {

    }

    protected fun onPreparePresenter() {

    }

    @Nullable
    fun getBaseToolbar(): Toolbar? {
        return toolbar
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int
}
