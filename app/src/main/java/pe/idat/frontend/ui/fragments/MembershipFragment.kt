package pe.idat.frontend.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pe.idat.frontend.MainActivity
import pe.idat.frontend.api.ApiClient
import pe.idat.frontend.api.models.Membership
import pe.idat.frontend.databinding.FragmentMembershipBinding
import pe.idat.frontend.ui.activities.GymActivity
import pe.idat.frontend.ui.activities.MembershipActivity
import pe.idat.frontend.ui.adapters.MembershipAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MembershipFragment : Fragment() {

    private lateinit var binding: FragmentMembershipBinding
    private lateinit var userEmail: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMembershipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerViewMemberships
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        userEmail = sharedPreferences.getString("userEmail", "") ?: ""

        val membershipAdapter = MembershipAdapter(emptyList()) { selectedMembership ->
            val editor = sharedPreferences.edit()
            MainActivity.prefs.setPrice(selectedMembership.price.toFloat())
            MainActivity.prefs.setDescripcion(selectedMembership.name)
            editor.putString("selectedMembershipUuid", selectedMembership.membershipUuid)
            editor.putString("selectedMembershipName", selectedMembership.name)
            editor.putString("selectedMembershipPrice", selectedMembership.price.toString())
            editor.apply()

            val intent = Intent(requireContext(), GymActivity::class.java)
            startActivity(intent)
        }
        recyclerView.adapter = membershipAdapter

        // Fetch and display memberships
        val membershipService = ApiClient.membershipService
        membershipService.getMemberships().enqueue(object : Callback<List<Membership>> {
            override fun onResponse(
                call: Call<List<Membership>>,
                response: Response<List<Membership>>
            ) {
                if (response.isSuccessful) {
                    val memberships = response.body()
                    if (memberships != null) {
                        membershipAdapter.apply {
                            this.memberships = memberships
                            notifyDataSetChanged()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Membership>>, t: Throwable) {
                Log.e("MembershipAPI", "Error en la llamada a la API de membresías", t)
                Log.e("MembershipAPI", "Mensaje de error: ${t.message}")
            }
        })
    }
}