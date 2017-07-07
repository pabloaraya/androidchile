package org.pabloaraya.androidchile.view.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import org.pabloaraya.androidchile.R
import org.pabloaraya.androidchile.view.adapter.PostAdapter
import org.pabloaraya.androidchile.view.model.Post
import org.pabloaraya.androidchile.view.presenter.PostPresenter


/**
 * Created by pablo on 7/5/17.
 */
class PostFragment : BaseListFragment(), PostPresenter.View {

    private lateinit var presenter: PostPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPrepareFragment(view: View) {
        super.onPrepareFragment(view)
    }

    override fun onPreparePresenter() {
        super.onPreparePresenter()
        presenter = PostPresenter()
        presenter.view = this
        presenter.getPosts()
    }

    override fun onItemClickListener(position: Int) {
        Toast.makeText(context, "Funciona!", Toast.LENGTH_SHORT).show()
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return getLinearLayoutManager()
    }

    override fun getAdapter(itemsList: ArrayList<Post>): PostAdapter {
        return PostAdapter(itemsList, R.layout.item_base)
    }

    private fun getLinearLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun showLoading() {
        TODO("not implemented")
    }

    override fun hideLoading() {
        TODO("not implemented")
    }

    override fun showPosts(postList: ArrayList<Post>) {
        setItems(postList)
    }
}
