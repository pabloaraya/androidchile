package org.pabloaraya.androidchile.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment


/**
 * Created by Pablo Araya on 5/30/17.
 */
abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(getLayoutId(), container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onPrepareFragment(view)
        onPreparePresenter()
    }

    protected abstract fun onPreparePresenter()

    protected abstract fun onPrepareFragment(view: View)

    @LayoutRes
    protected abstract fun getLayoutId(): Int
}