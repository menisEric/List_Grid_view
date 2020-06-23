package com.ericmenis.listviewkotlin.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ericmenis.listviewkotlin.Model.App
import com.ericmenis.listviewkotlin.R

class MyAdapter(var cont:Context, var resources:Int, var custom_item : ArrayList<App>) :
    ArrayAdapter<App>(cont, resources, custom_item) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var row = convertView
        val viewholder : ViewHolder

        if (row == null) {
            val inflater = (cont as Activity).layoutInflater
            row = inflater.inflate(resources, parent, false)
            viewholder = ViewHolder()
            viewholder.title = row!!.findViewById(R.id.titleTextView) as TextView
            viewholder.img = row!!.findViewById(R.id.imgView) as ImageView
            viewholder.descripcion = row!!.findViewById(R.id.descriptionTextView) as TextView
            row.setTag(viewholder)
        }else{
            viewholder = row.getTag() as ViewHolder
        }

        val item = custom_item[position]
        viewholder.title!!.setText(item.title)
        viewholder.img!!.setImageResource(item.image)
        viewholder.descripcion!!.setText(item.description)

        return row
    }

    class ViewHolder{
        internal var title:TextView? = null
        internal var img:ImageView? = null
        internal var descripcion:TextView? = null
    }
}