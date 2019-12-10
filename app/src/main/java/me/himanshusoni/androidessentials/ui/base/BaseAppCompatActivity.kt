package me.himanshusoni.androidessentials.ui.base

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import me.himanshusoni.androidessentials.R

abstract class BaseAppCompatActivity : AppCompatActivity() {

    private var mDrawerLayout: androidx.drawerlayout.widget.DrawerLayout? = null
    private var mToolbar: Toolbar? = null


    fun setToolbar(toolbar: Toolbar) {
        mToolbar = toolbar

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setDrawerLayout(drawerLayout: androidx.drawerlayout.widget.DrawerLayout) {
        mDrawerLayout = drawerLayout

        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (mDrawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
            mDrawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


}