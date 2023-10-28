package pe.idat.frontend.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.idat.frontend.api.models.Notification
import pe.idat.frontend.databinding.ItemNotificationBinding
import java.text.SimpleDateFormat
import java.util.Locale

class NotificationAdapter(
    var notifications: List<Notification>,
    private val onItemClick: (Notification) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.bind(notification)
    }

    override fun getItemCount(): Int = notifications.size

    inner class NotificationViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notification: Notification) {
            binding.apply {
                textViewNotificationTitle.text = notification.title
                textViewNotificationMessage.text = notification.message
                // Convertir la cadena de fecha a un objeto Date
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
                val date = dateFormat.parse(notification.createdDate)

                // Formatear la fecha en el formato deseado (ejemplo: "dd/MM/yyyy")
                val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
                textViewNotificationCreated.text = formattedDate
                root.setOnClickListener {
                    onItemClick.invoke(notification)
                }
                // Handle other bindings as needed
            }
        }
    }
}