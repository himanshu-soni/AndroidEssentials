package me.himanshusoni.androidessentials.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import me.himanshusoni.androidessentials.R
import me.himanshusoni.androidessentials.ui.base.BaseAppCompatActivity

class MainActivity : BaseAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setToolbar(toolbar)
        setDrawerLayout(drawerLayout)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                }
                R.id.nav_recycler_view -> {
                    openRecyclerViewActivity()
                }
                R.id.nav_slideshow -> {
                }
                R.id.nav_tools -> {

                }
                R.id.nav_share -> {

                }
                R.id.nav_send -> {

                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openRecyclerViewActivity() {
        val intent = Intent(this, RecyclerViewActivity::class.java)
        startActivity(intent)
    }
}
