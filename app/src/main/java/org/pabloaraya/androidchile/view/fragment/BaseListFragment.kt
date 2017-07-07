package org.pabloaraya.androidchile.view.fragment

import android.view.View
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.DefaultItemAnimator

import kotlinx.android.synthetic.main.fragment_list_base.recyclerView
import org.pabloaraya.androidchile.*
import org.pabloaraya.androidchile.view.adapter.PostAdapter
import org.pabloaraya.androidchile.view.model.Post
import org.pabloaraya.androidchile.view.widget.ItemOffsetDecoration


/**
 * Created by pablo on 7/5/17.
 */
abstract class BaseListFragment : BaseFragment(), RecyclerItemClickListener, PostInterface {

    private var postAdapter: PostAdapter? = null

    override fun onPreparePresenter() {

    }

    override fun onPrepareFragment(view: View) {
        setupRecyclerView()
    }

    override fun setItems(itemsList: ArrayList<Post>) {
        postAdapter = getAdapter(itemsList)
        postAdapter!!.setRecyclerItemClickListener(this)
        recyclerView.adapter = postAdapter
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_list_base
    }

    private fun setupRecyclerView() {
        recyclerView!!.layoutManager = getLayoutManager()
        recyclerView!!.addItemDecoration(ItemOffsetDecoration(recyclerView!!.context, R.dimen.item_decoration))
        recyclerView!!.itemAnimator = DefaultItemAnimator()
    }

    protected abstract fun getLayoutManager(): RecyclerView.LayoutManager

    protected abstract fun getAdapter(itemsList: ArrayList<Post>): PostAdapter
}