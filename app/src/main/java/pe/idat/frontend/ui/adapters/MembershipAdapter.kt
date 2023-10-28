package pe.idat.frontend.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.idat.frontend.databinding.ItemMembershipBinding
import pe.idat.frontend.api.models.Membership

class MembershipAdapter(
    var memberships: List<Membership>,
    private val onItemClick: (Membership) -> Unit
) : RecyclerView.Adapter<MembershipAdapter.MembershipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MembershipViewHolder {
        val binding = ItemMembershipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MembershipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MembershipViewHolder, position: Int) {
        val membership = memberships[position]
        holder.bind(membership)
    }

    override fun getItemCount(): Int = memberships.size

    inner class MembershipViewHolder(private val binding: ItemMembershipBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(membership: Membership) {
            binding.apply {
                textViewMembershipName.text = membership.name
                textViewMembershipPrice.text = "Precio: $${membership.price}"
                textViewMembershipDescription.text = membership.description
                root.setOnClickListener {
                    onItemClick.invoke(membership)
                }
                // Handle other bindings as needed
            }
        }
    }
}