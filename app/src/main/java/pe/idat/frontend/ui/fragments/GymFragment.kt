package pe.idat.frontend.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pe.idat.frontend.MainActivity
import pe.idat.frontend.R
import pe.idat.frontend.api.ApiClient
import pe.idat.frontend.databinding.FragmentGymBinding
import pe.idat.frontend.api.models.Gym
import pe.idat.frontend.ui.activities.PaymentActivity
import pe.idat.frontend.ui.adapters.GymAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GymFragment : Fragment() {

    private lateinit var binding: FragmentGymBinding
    private lateinit var membershipUuid: String // Pasar el ID del membership seleccionado desde el fragment anterior


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGymBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView: RecyclerView = binding.recyclerViewGyms
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val gymAdapter = GymAdapter(emptyList()) { selectedGym ->
            MainActivity.prefs.setDirection(selectedGym.direction)

            val intent = Intent(requireContext(), PaymentActivity::class.java)
            startActivity(intent)
        }
        recyclerView.adapter = gymAdapter

        // Fetch and display gyms based on selected membership
        val gymService = ApiClient.gymService
        gymService.getGyms().enqueue(object : Callback<List<Gym>> {
            override fun onResponse(call: Call<List<Gym>>, response: Response<List<Gym>>) {
                if (response.isSuccessful) {
                    val gyms = response.body()
                    if (gyms != null) {
                        gymAdapter.apply {
                            this.gyms = gyms
                            notifyDataSetChanged()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Gym>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}