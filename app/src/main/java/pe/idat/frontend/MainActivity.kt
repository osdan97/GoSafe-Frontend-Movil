package pe.idat.frontend

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.idat.frontend.api.models.Prefs

class MainActivity : Application() {
    companion object{
        lateinit var prefs:Prefs
    }
    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}