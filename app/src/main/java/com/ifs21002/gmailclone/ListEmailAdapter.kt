package com.ifs21002.gmailclone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21002.gmailclone.databinding.ItemRowEmailBinding
import android.widget.ImageView
import de.hdodenhof.circleimageview.CircleImageView



class ListEmailAdapter(private val listEmail: ArrayList<Email>): RecyclerView.Adapter<ListEmailAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback : OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback : OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Email)
    }

    class ListViewHolder(var binding: ItemRowEmailBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListEmailAdapter.ListViewHolder {
        val binding =
            ItemRowEmailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListEmailAdapter.ListViewHolder, position: Int) {
        val email = listEmail[position]
        holder.binding.ivItemEmail.setImageResource(email.image)
        holder.binding.tvTitleEmail.text =email.text
        holder.binding.tvSubEmail.text = email.texts
        holder.binding.tvItemEmail.text = email.textss
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listEmail[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listEmail.size
}