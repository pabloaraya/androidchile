package org.pabloaraya.androidchile.view.presenter

/**
 * Created by pablo on 7/6/17.
 */
abstract class Presenter<T : Presenter.View> {

    var view: T? = null

    fun initialize() {

    }

    interface View {

        fun showLoading()

        fun hideLoading()

    }
}
