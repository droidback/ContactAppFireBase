package uz.gita.contactappfirebase.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.contactappfirebase.R
import uz.gita.contactappfirebase.data.model.ContactData
import uz.gita.contactappfirebase.databinding.ItemContactBinding

class ContactAdapter : ListAdapter<ContactData, ContactAdapter.MyViewHolder>(ContactDiffUtil) {
    private var onCLickMoreButtonListener: ((ContactData) -> Unit)? = null

    object ContactDiffUtil : DiffUtil.ItemCallback<ContactData>() {
        override fun areItemsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
            return oldItem == newItem
        }

    }

    inner class MyViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonMore.setOnClickListener {
                onCLickMoreButtonListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            binding.textName.text = getItem(absoluteAdapterPosition).name
            binding.textNumber.text = getItem(absoluteAdapterPosition).phone
            binding.imageLetter.text = getItem(absoluteAdapterPosition).name?.get(0)
                ?.uppercaseChar()
                .toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return MyViewHolder(ItemContactBinding.bind(view))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }

    fun setOnClickMoreButtonListener(block: (ContactData) -> Unit) {
        onCLickMoreButtonListener = block
    }
}