package pe.idat.frontend.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import pe.idat.frontend.MainActivity
import pe.idat.frontend.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textViewWelcome: TextView = view.findViewById(R.id.textViewWelcome)
        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)

        // Obtener los datos del usuario, membresía y gimnasio de los argumentos
        val userEmail = MainActivity.prefs.getEmail()
        val membershipName = MainActivity.prefs.getDescripcion()
        val userName = MainActivity.prefs.getNombre()
        val gymDirection = MainActivity.prefs.getDirection()

        // Mostrar los datos en la interfaz de usuario
        textViewWelcome.text = "Bienvenido, $userName\n"
        textViewWelcome.append("Correo: $userEmail\n")
        textViewWelcome.append("Tu menbresía: $membershipName\n")
        textViewWelcome.append("Ubicación del gimnasio: $gymDirection\n")

        textViewWelcome.append("Proximamente implementaremos más cosas en esta vista")

        // Hacer que el BottomNavigationView esté visible
        bottomNavigationView.visibility = View.VISIBLE
    }
}