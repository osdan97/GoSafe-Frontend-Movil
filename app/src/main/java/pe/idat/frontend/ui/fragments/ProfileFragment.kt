package pe.idat.frontend.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pe.idat.frontend.MainActivity
import pe.idat.frontend.R
import pe.idat.frontend.api.ApiClient
import pe.idat.frontend.api.models.Customer
import pe.idat.frontend.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val customerService = ApiClient.customerService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uuid = MainActivity.prefs.getUuid()


        // Realiza la llamada a la API para obtener los detalles del cliente por UUID
        customerService.getCustomerDetails(uuid).enqueue(object : Callback<Customer> {
            override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                if (response.isSuccessful) {
                    val customer = response.body()

                    if (customer != null) {
                        // Asigna los valores a los EditText utilizando Editable
                        binding.editTextEmail.text = customer.email.toEditable()
                        binding.editTextName.text = customer.name.toEditable()
                        binding.editTextLastName.text = customer.lastName.toEditable()
                        binding.editTextAddress.text = customer.address?.toEditable() ?: "".toEditable()
                        binding.editTextDocumentNumber.text = customer.documentNumber?.toEditable()
                    } else {
                        showToast("No se pudo obtener los detalles del cliente")
                    }
                } else {
                    showToast("Error en la respuesta de la API")
                }
            }

            override fun onFailure(call: Call<Customer>, t: Throwable) {
                showToast("Error de conexión: ${t.message}")
            }
        })
        binding.btnUpdateProfile.setOnClickListener {
            val updatedCustomer = Customer(
                accountUuid = uuid,
                email = binding.editTextEmail.text.toString(),
                name = binding.editTextName.text.toString(),
                lastName = binding.editTextLastName.text.toString(),
                address = binding.editTextAddress.text.toString(),
                documentNumber = binding.editTextDocumentNumber.text.toString()
            )

            customerService.updateCustomer(uuid, updatedCustomer).enqueue(object : Callback<Customer> {
                override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                    if (response.isSuccessful) {
                        val updatedCustomer = response.body()

                        if (updatedCustomer != null) {
                            showToast("Perfil actualizado exitosamente")
                        }
                    } else {
                        Log.e("ProfileFragment", "Error en la respuesta de la API al actualizar: ${response.message()}")
                        showToast("Error al actualizar el perfil")
                    }
                }

                override fun onFailure(call: Call<Customer>, t: Throwable) {
                    Log.e("ProfileFragment", "Error de conexión al actualizar: ${t.message}")
                    showToast("Error de conexión al actualizar el perfil")
                }
            })
        }
    }

    // Extensión para convertir String en Editable
    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    // ... (otros códigos)
}