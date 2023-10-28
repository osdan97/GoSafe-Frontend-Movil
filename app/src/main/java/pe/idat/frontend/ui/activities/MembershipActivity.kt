package pe.idat.frontend.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pe.idat.frontend.R
import pe.idat.frontend.ui.fragments.MembershipFragment

class MembershipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_membership)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MembershipFragment())
                .commit()
        }
    }
}