package com.example.omela

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.omela.databinding.ActivityLoginBinding
import com.example.omela.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)

        val mainFragment = FlowersListFragment()
        val basketFragment = BasketFragment()
        val favoritesFragment = FavoritesFragment()
        val accountFragment = AccountFragment()

        setCurrentFragment(mainFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(mainFragment)
                R.id.basket->setCurrentFragment(basketFragment)
                R.id.favorite->setCurrentFragment(favoritesFragment)
                R.id.account->setCurrentFragment(accountFragment) 
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

}