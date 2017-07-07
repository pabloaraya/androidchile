package org.pabloaraya.androidchile.view.adapter

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.item_base.view.message
import kotlinx.android.synthetic.main.item_base.view.name
import kotlinx.android.synthetic.main.item_base.view.picture
import kotlinx.android.synthetic.main.item_base.view.date
import org.pabloaraya.androidchile.R
import org.pabloaraya.androidchile.RecyclerItemClickListener
import org.pabloaraya.androidchile.view.model.Keyword
import org.pabloaraya.androidchile.view.model.Post


/**
 * Created by pablo on 7/5/17.
 */
class PostAdapter(itemsList: ArrayList<Post>, itemLayout: Int): RecyclerView.Adapter<PostAdapter.PostHolder>() {

    private var itemsList: ArrayList<Post> = itemsList
    private var itemLayout: Int = itemLayout
    private var recyclerItemClickListener: RecyclerItemClickListener? = null

    fun setRecyclerItemClickListener(recyclerItemClickListener: RecyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostHolder {
        var view: View = LayoutInflater.from(parent?.context).inflate(itemLayout, parent, false)
        return PostHolder(view, recyclerItemClickListener)
    }

    override fun onBindViewHolder(holder: PostHolder?, position: Int) {

        var message: String = itemsList[position].message

        if (message.contains("[java]") && message.contains("[/java]")) {

            var span: SpannableStringBuilder = SpannableStringBuilder(message)

            val startIndex: Int = message.indexOf("[java]")
            val endIndex: Int = message.indexOf("[/java]")

            span.setSpan(TypefaceSpan("monospace"), startIndex, endIndex, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            span.setSpan(RelativeSizeSpan(0.8f), startIndex, endIndex, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

            // Second, find the indexes
            val wordArray: ArrayList<Keyword> = ArrayList()
            //wordArray.add(Keyword("private", ContextCompat.getColor(holder!!.message.context, R.color.colorKeyword)))
            //wordArray.add(Keyword("public", ContextCompat.getColor(holder!!.message.context, R.color.colorKeyword)))
            wordArray.add(Keyword("void", ContextCompat.getColor(holder!!.message.context, R.color.colorKeyword)))
            wordArray.add(Keyword("new", ContextCompat.getColor(holder!!.message.context, R.color.colorKeyword)))
            wordArray.add(Keyword("true", ContextCompat.getColor(holder!!.message.context, R.color.colorKeyword)))
            wordArray.add(Keyword("false", ContextCompat.getColor(holder!!.message.context, R.color.colorKeyword)))
            wordArray.add(Keyword("int", ContextCompat.getColor(holder!!.message.context, R.color.colorKeyword)))
            wordArray.add(Keyword("long", ContextCompat.getColor(holder!!.message.context, R.color.colorKeyword)))
            wordArray.add(Keyword("double", ContextCompat.getColor(holder!!.message.context, R.color.colorKeyword)))
            wordArray.add(Keyword("char", ContextCompat.getColor(holder!!.message.context, R.color.colorKeyword)))
            wordArray.add(Keyword("boolean", ContextCompat.getColor(holder!!.message.context, R.color.colorKeyword)))
            wordArray.add(Keyword("@Override", ContextCompat.getColor(holder!!.message.context, R.color.colorOverride)))

            val regex = Regex("public|private")
            val matches = regex.findAll(span, 0)
            for (match in matches) {
                val start: Int = message.indexOf(match.value)
                if (start > 0) {
                    span.setSpan(ForegroundColorSpan(ContextCompat.getColor(holder!!.message.context, R.color.colorAccent)), start, start + match.value.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                    //span.setSpan(StyleSpan(Typeface.BOLD), start, start + word.word.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                }

            }
            for (word in wordArray) {
                val start: Int = message.indexOf(word.word)
                if (start > 0) {
                    span.setSpan(ForegroundColorSpan(word.color), start, start + word.word.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                    span.setSpan(StyleSpan(Typeface.BOLD), start, start + word.word.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                }


            }

            span = span.replace(endIndex, endIndex + 7, "\n")
            span = span.replace(startIndex, startIndex + 6, "\n")

            holder!!.message.setText(span, TextView.BufferType.SPANNABLE)

        } else {

            holder!!.message.text = message
        }

        holder!!.name.text = itemsList[position].author.name
        holder!!.date.text = itemsList[position].date

        Picasso.with(holder!!.picture.context).load(itemsList[position].author.picture).into(holder!!.picture)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }


    class PostHolder: RecyclerView.ViewHolder, View.OnClickListener {

        var message: TextView
        var name: TextView
        var picture: ImageView
        var date: TextView
        var listener: RecyclerItemClickListener? = null

        constructor(view: View, listener: RecyclerItemClickListener?) : super(view) {
            view.setOnClickListener(this)
            message = view.message
            name = view.name
            picture = view.picture
            date = view.date
            this.listener = listener
        }

        override fun onClick(view: View?) {
            listener!!.onItemClickListener(adapterPosition)
        }
    }
}