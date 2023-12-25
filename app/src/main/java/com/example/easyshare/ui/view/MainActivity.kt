package com.example.easyshare.ui.view

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshare.R
import com.example.easyshare.ui.viewmodel.ProductViewModel
import com.example.easyshare.utilis.TokenManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var productsListRv: RecyclerView
    private lateinit var loadingProgressBar: ProgressBar
    private val productViewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navController = Navigation.findNavController(this, R.id.host_frag)
        NavigationUI.setupWithNavController(bottomNavigation, navController)

        this.listenToBottomNavigation(bottomNavigation, navController)
    }

    private fun listenToBottomNavigation(
        bottomNavigation: BottomNavigationView,
        navController: NavController
    ) {
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    this.onNavigationItemClick(navController, R.id.homeFragment)
                }
                R.id.favoriteFragment -> {
                    this.onNavigationItemClick(navController, R.id.favoriteFragment)
                }
                R.id.add -> {
                    // TODO
                    true
                }
                R.id.accountFragment -> {
                    this.onNavigationItemClick(navController, R.id.accountFragment)
                }
                R.id.logout -> {
                    this.onLogOut(navController)
                }
                else -> false
            }
        }
    }

    private fun onNavigationItemClick(
        navController: NavController,
        id: Int
    ): Boolean {
        navController.navigate(id)

        return true
    }

    private fun onLogOut(navController: NavController): Boolean {
        TokenManager.clearToken()
        navController.navigate(R.id.loginActivity)
        finish()

        return true
    }
}
