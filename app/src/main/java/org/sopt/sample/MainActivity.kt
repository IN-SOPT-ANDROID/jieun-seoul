package org.sopt.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.sopt.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_menu -> replaceFragment(HomeFragment())
                R.id.gallery_menu -> replaceFragment(GalleryFragment())
                R.id.search_menu -> replaceFragment(SearchFragment())
            }
            true
        }

        val currentFragment = supportFragmentManager.findFragmentById(R.id.home_container)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.home_container, HomeFragment.newInstance())
                .commit()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home_container, fragment)
        fragmentTransaction.commit()
    }
}
