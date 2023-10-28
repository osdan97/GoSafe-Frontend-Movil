package pe.idat.frontend.ui.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import pe.idat.frontend.MainActivity
import pe.idat.frontend.api.ApiClient
import pe.idat.frontend.api.models.SignUpRequest
import pe.idat.frontend.api.models.SignUpResponse
import pe.idat.frontend.databinding.FragmentRegisterBinding
import pe.idat.frontend.ui.activities.MembershipActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val authService = ApiClient.authService
    private val membershipService = ApiClient.membershipService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegister.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val name = binding.editTextName.text.toString()
            val lastName = binding.editTextLastName.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && lastName.isNotEmpty()) {
                signUp(email, password, name, lastName)
            } else {
                showErrorToast("Por favor complete todos los campos")
            }
        }
    }

    private fun signUp(email: String, password: String, name: String, lastName: String) {
        val signUpRequest = SignUpRequest(email, password, name, lastName)

        authService.signUp(signUpRequest).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if (response.isSuccessful) {
                    val signUpResponse = response.body()
                    if (signUpResponse != null) {
                        showSuccessToast("Registro exitoso")
                        //MainActivity.prefs.setEmail(email)
                        MainActivity.prefs.setEmail(response.body()!!.email)
                        MainActivity.prefs.setNombre(response.body()!!.name)
                        MainActivity.prefs.setApellidos(response.body()!!.lastname)
                        MainActivity.prefs.setUuid(response.body()!!.uuid)

                        val intent = Intent(requireContext(), MembershipActivity::class.java)
                        startActivity(intent)
                    } else {
                        showErrorToast("Respuesta inválida del servidor")
                    }
                } else {
                    showErrorToast("Respuesta de error del servidor")
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                showErrorToast("Error en la conexión o respuesta del servidor")
            }
        })
    }



    private fun showSuccessToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}




