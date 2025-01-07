package com.example.finalproject

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView




class MainActivity : AppCompatActivity() {

    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // TODO: 1 - Set MainActivity Launcher ✅
        // TODO: 2 - Create fragment from xml ✅
        // TODO: 3 - Create a fragment programmatically ✅
        // TODO: 4 - Manage nav args ✅
        // TODO: 5 - Create a tab bar with multiple fragments 👨‍🎓
        // TODO: 6 - Refactor students list
        // TODO: 7 - GPS/Firebase

        // TODO: 1 - Set up project
        // TODO: 2 - Create nav_graph.xml and connect to nav host
        // TODO: 3 - Connect list fragment with blue fragment action with back button
        // TODO: 4 - Set navArgs for blue fragment

        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        val navHostFragment: NavHostFragment? = supportFragmentManager.findFragmentById(R.id.main_nav_host) as? NavHostFragment
        navController = navHostFragment?.navController
        navController?.let {
            NavigationUI.setupActionBarWithNavController(
                activity = this,
                navController = it
            )
        }
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_bar)
        navController?.let { NavigationUI.setupWithNavController(bottomNavigationView, it) }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navController?.popBackStack()
            else -> navController?.let { NavigationUI.onNavDestinationSelected(item, it) }
        }
        return true
    }

}
