package com.edwinyosua.maps

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.edwinyosua.core.data.Resource
import com.edwinyosua.maps.databinding.ActivityMapsBinding
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules

class MapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapsBinding
    private val mapsViewModel: MapsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loadKoinModules(mapsModule)
        supportActionBar?.title = "Tourism App"

        getTourismData()
    }

    private fun getTourismData() {
        binding.apply {
            mapsViewModel.tourism.observe(this@MapsActivity) { tourism ->
                if (tourism != null) {
                    when (tourism) {

                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            tvMaps.text = "This is ${tourism.data?.get(0)?.name}"
                        }

                        is Resource.Error -> {
                            progressBar.visibility = View.GONE
                            tvError.visibility = View.VISIBLE
                            tvError.text = tourism.message
                        }

                        is Resource.Loading -> progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}