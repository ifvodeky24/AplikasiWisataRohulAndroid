package com.example.aplikasiwisatarohul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasiwisatarohul.data.source.remote.response.Spbu
import com.example.aplikasiwisatarohul.databinding.ItemSpbuBinding
import com.example.aplikasiwisatarohul.ui.spbu.SpbuCallback

class SpbuAdapter(private val callback: SpbuCallback) :
    RecyclerView.Adapter<SpbuAdapter.SpbuViewHolder>() {

    private var spbuList = ArrayList<Spbu>()

    fun setAtm(spbu: List<Spbu>?) {
        if (spbu == null) return
        this.spbuList.clear()
        this.spbuList.addAll(spbu)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpbuViewHolder {
        val binding =
            ItemSpbuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpbuViewHolder(binding)
    }

    override fun getItemCount(): Int = spbuList.size

    override fun onBindViewHolder(holder: SpbuViewHolder, position: Int) {
        holder.bind(spbuList[position])
    }

    inner class SpbuViewHolder(private val binding: ItemSpbuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(spbu: Spbu) {
            with(binding) {
                tvTitle.text = spbu.nama_spbu
                tvAddress.text = spbu.alamat

//                Glide.with(itemView.context)
//                    .load(ServerConfig.GALLERY_PATH + event.gambar)
//                    .apply(RequestOptions().placeholder(R.drawable.ic_hourglass_empty_black_24dp))
//                    .into(ivEvent)

                root.setOnClickListener {
                    callback.onItemClick(spbu)
                }
            }
        }
    }
}