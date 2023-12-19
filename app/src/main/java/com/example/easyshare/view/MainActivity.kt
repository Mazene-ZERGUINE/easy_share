package com.example.easyshare.view
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.example.easyshare.R
import com.example.easyshare.viewmodel.ProductViewModel
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
    }
}
