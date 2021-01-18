package com.example.skywebproandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.skywebproandroidapp.fragment.AuthFragment
import com.example.skywebproandroidapp.fragment.PhotoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

  private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.photo_bottom -> {
        toolbar.title = "Songs"
        val songsFragment = PhotoFragment.newInstance()
        openFragment(songsFragment)
        return@OnNavigationItemSelectedListener true
      }
      R.id.auth_bottom -> {
        toolbar.title = "Albums"
        val albumsFragment = AuthFragment.newInstance()
        openFragment(albumsFragment)
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }
  lateinit var toolbar: ActionBar

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    toolbar = supportActionBar!!
    val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)

    bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    setCurrentFragment(PhotoFragment())
  }


  private fun openFragment(fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.container, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
  }

  private fun setCurrentFragment(fragment:Fragment)=
    supportFragmentManager.beginTransaction().apply {
      replace(R.id.container,fragment)
      commit()
    }
}