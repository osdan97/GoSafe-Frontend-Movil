package pe.idat.frontend.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pe.idat.frontend.R
import pe.idat.frontend.api.ApiClient
import pe.idat.frontend.api.models.Gym
import pe.idat.frontend.api.models.Notification
import pe.idat.frontend.databinding.FragmentNotificationBinding
import pe.idat.frontend.ui.adapters.NotificationAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerViewNotfications
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val notificationAdapter = NotificationAdapter(emptyList()){selectedNotification ->

        }

        recyclerView.adapter = notificationAdapter

        val notificationService = ApiClient.notificationService

        notificationService.getNotifications().enqueue(object: Callback<List<Notification>>{
            override fun onResponse(
                call: Call<List<Notification>>,
                response: Response<List<Notification>>
            ) {
                if(response.isSuccessful){
                    val notifications = response.body()
                    if (notifications != null){
                        notificationAdapter.apply {
                            this.notifications = notifications
                            notifyDataSetChanged()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<List<Notification>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}