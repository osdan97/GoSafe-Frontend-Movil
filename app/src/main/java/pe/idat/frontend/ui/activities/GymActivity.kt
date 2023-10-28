package pe.idat.frontend.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import pe.idat.frontend.R
import pe.idat.frontend.ui.fragments.GymFragment

class GymActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gym)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, GymFragment())
            }
        }
    }
}