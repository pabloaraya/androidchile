package org.pabloaraya.androidchile.view.presenter

import com.facebook.GraphRequest
import com.facebook.AccessToken
import android.os.Bundle
import org.json.JSONArray
import org.json.JSONObject
import org.pabloaraya.androidchile.view.model.Author
import org.pabloaraya.androidchile.view.model.Post
import java.util.*


/**
 * Created by pablo on 7/6/17.
 */
class PostPresenter: Presenter<PostPresenter.View>() {

    fun getPosts() {

        val request = GraphRequest.newGraphPathRequest(
                AccessToken.getCurrentAccessToken(),
                "/187694778228655"
        ) {

            var postList: ArrayList<Post> = ArrayList()

            val feedObject: JSONObject = it.jsonObject.getJSONObject("feed")
            val dataArray: JSONArray = feedObject.getJSONArray("data")

            for (i in 0..(dataArray.length() - 1)) {
                val item = dataArray.getJSONObject(i)
                val picture = "https://graph.facebook.com/" + item.getJSONObject("from").getString("id") +"/picture?type=square"

                postList.add(Post(
                        item.getString("message"),
                        Author(item.getJSONObject("from").getString("name"), picture),
                        item.getString("created_time")))
            }

            view!!.showPosts(postList)
        }

        val parameters = Bundle()
        parameters.putString("fields", "feed.limit(20){message,from,created_time}")
        request.parameters = parameters
        request.executeAsync()
    }

    interface View: Presenter.View {
        fun showPosts(postList: ArrayList<Post>)
    }
}
