package com.ericmenis.listviewkotlin.Activitie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ListView
import android.widget.Toast
import com.ericmenis.listviewkotlin.Adapter.MyAdapter
import com.ericmenis.listviewkotlin.Model.App
import com.ericmenis.listviewkotlin.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.ListView)
        val gridView = findViewById<GridView>(R.id.GridView)

        //var adapterlistView = MyAdapter(this, R.layout.list_item_view, app)
        var adaptergridView = MyAdapter(this, R.layout.grid_item_view, app)


        //listView.adapter = adapterlistView
        gridView.adapter = adaptergridView

        gridView.setOnItemClickListener { parent:AdapterView<*>, view:View, position:Int, id:Long ->

            Toast.makeText(this, "click on " + app[position].title , Toast.LENGTH_SHORT).show()
        }

       /* listView.setOnItemClickListener { parent:AdapterView<*>, view:View, position:Int, id:Long ->

            Toast.makeText(this, "click on " + app[position].title , Toast.LENGTH_SHORT).show()
        }*/
    }

    val app : ArrayList<App>
        get(){
            val item_liste : ArrayList<App> = ArrayList<App>()

            item_liste.add(App("Facebook", "facebook description...", R.mipmap.ic_facebook))
            item_liste.add(App("WhatsApp", "whatsapp description...", R.mipmap.ic_wpp))
            item_liste.add(App("Tumbler", "tumbler description...", R.mipmap.ic_tumble))
            item_liste.add(App("Twitter", "twitter description...", R.mipmap.ic_tw))
            item_liste.add(App("Instagram", "instagram description...", R.mipmap.ic_ig))

            return item_liste
        }
}
