package com.example.cinemaapp.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.cinemaapp.utils.data_model.Actor
import com.example.cinemaapp.utils.data_model.Genre
import com.example.cinemaapp.utils.data_model.Movie
import kotlinx.android.synthetic.main.activity_view_movie.view.*
import kotlinx.android.synthetic.main.row_element.view.*
import kotlinx.android.synthetic.main.row_element.view.textView

class CustomAdapterGenre : ArrayAdapter<Genre> {
    val resourceId: Int

    class ViewHolder {
        lateinit var nameGenre : TextView
    }

    constructor(context: Context, resourceId: Int, items: MutableList<Genre>) : super(context, resourceId, items) {
        this.resourceId = resourceId
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(resourceId, null)

        val viewHolder = ViewHolder()
        viewHolder.nameGenre= view.textView as TextView

        view.setTag(viewHolder)
        val value = getItem(position)
        val holder = view!!.tag as ViewHolder
        holder.nameGenre.text = "${value!!.name}"
        Log.d("ADAPTER GET VIEW", value!!.name)
        return view
    }
}