package pe.idat.frontend.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import pe.idat.frontend.R
import pe.idat.frontend.databinding.ActivityBottomNavigationBinding
import pe.idat.frontend.ui.fragments.HomeFragment
import pe.idat.frontend.ui.fragments.NotificationFragment
import pe.idat.frontend.ui.fragments.ProfileFragment

class BottomNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> replaceFragment(HomeFragment())
                R.id.navigation_notifications -> replaceFragment(NotificationFragment())
                R.id.navigation_profile -> replaceFragment(ProfileFragment())
                else ->{

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
    }
}