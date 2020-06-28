package com.ericmenis.listviewkotlin.Activitie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ListView
import com.ericmenis.listviewkotlin.Adapter.MyAdapter
import com.ericmenis.listviewkotlin.Model.App
import com.ericmenis.listviewkotlin.R

class MainActivity : AppCompatActivity() {

    private var SWITCH_TO_LIST_VIEW: Int = 0
    private var SWITCH_TO_GRID_VIEW: Int = 1
    private var counter: Int = 0

    lateinit var listView: ListView
    lateinit var gridView: GridView
    lateinit var item_list: MenuItem
    lateinit var item_grid: MenuItem
    lateinit var adapterList: MyAdapter
    lateinit var adapterGrid: MyAdapter

    lateinit var apps: ArrayList<App>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.ListView)
        gridView = findViewById(R.id.GridView)

        apps = getAllApp()

        adapterList = MyAdapter(this, R.layout.list_item_view, apps)
        adapterGrid = MyAdapter(this, R.layout.grid_item_view, apps)

        listView.adapter = adapterList
        gridView.adapter = adapterGrid

        registerForContextMenu(listView)
        registerForContextMenu(gridView)

        /*gridView.setOnItemClickListener { parent:AdapterView<*>, view:View, position:Int, id:Long ->

            Toast.makeText(this, "click on " + apps[position].title , Toast.LENGTH_SHORT).show()
        }

        listView.setOnItemClickListener { parent:AdapterView<*>, view:View, position:Int, id:Long ->

            Toast.makeText(this, "click on " + apps[position].title , Toast.LENGTH_SHORT).show()
        }*/
    }

    private fun getAllApp(): ArrayList<App> {

        val item_liste : ArrayList<App> = ArrayList<App>()

        item_liste.add(App("Facebook", "facebook description...", R.mipmap.ic_facebook))
        item_liste.add(App("WhatsApp", "whatsapp description...", R.mipmap.ic_wpp))
        item_liste.add(App("Tumbler", "tumbler description...", R.mipmap.ic_tumble))
        item_liste.add(App("Twitter", "twitter description...", R.mipmap.ic_tw))
        item_liste.add(App("Instagram", "instagram description...", R.mipmap.ic_ig))

        return item_liste

    }

    /*val app : ArrayList<App>
        get(){
            val item_liste : ArrayList<App> = ArrayList<App>()

            item_liste.add(App("Facebook", "facebook description...", R.mipmap.ic_facebook))
            item_liste.add(App("WhatsApp", "whatsapp description...", R.mipmap.ic_wpp))
            item_liste.add(App("Tumbler", "tumbler description...", R.mipmap.ic_tumble))
            item_liste.add(App("Twitter", "twitter description...", R.mipmap.ic_tw))
            item_liste.add(App("Instagram", "instagram description...", R.mipmap.ic_ig))

            return item_liste
        }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        item_grid = menu.findItem(R.id.grid_view)
        item_list = menu.findItem(R.id.list_view)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.add_view ->{
                addApp(App("Added n° " + (++counter), "Unknown", R.mipmap.ic_app))
                true
            }
            R.id.list_view -> {
                switchListGridView(SWITCH_TO_LIST_VIEW)
                true
            }
            R.id.grid_view -> {
                switchListGridView(SWITCH_TO_GRID_VIEW)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        var info = menuInfo as AdapterView.AdapterContextMenuInfo
        menu?.setHeaderTitle(apps[info.position].title)

        menuInflater.inflate(R.menu.menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when(item.itemId){
            R.id.delete_app ->{
                deleteApp(info.position)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun switchListGridView(option: Int){

        if (option == SWITCH_TO_LIST_VIEW){
            if (listView.visibility == View.INVISIBLE){
                gridView.visibility = View.INVISIBLE
                item_list.isVisible = false
                item_grid.isVisible = true
                listView.visibility = View.VISIBLE
                title = "Listview"
            }
        }else if (option == SWITCH_TO_GRID_VIEW){
            if (gridView.visibility == View.INVISIBLE){
                listView.visibility = View.INVISIBLE
                item_grid.isVisible = false
                item_list.isVisible = true
                gridView.visibility = View.VISIBLE
                title = "Gridview"
            }
        }
    }

    private fun addApp(app: App) {
        apps.add(app)

        adapterGrid.notifyDataSetChanged()
        adapterList.notifyDataSetChanged()
    }

    private fun deleteApp(position: Int){
        apps.remove(apps[position])

        adapterList.notifyDataSetChanged()
        adapterGrid.notifyDataSetChanged()
    }

}


