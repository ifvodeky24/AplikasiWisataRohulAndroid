package com.example.aplikasiwisatarohul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasiwisatarohul.data.source.remote.response.Wisata
import com.example.aplikasiwisatarohul.databinding.ItemWisataBinding
import com.example.aplikasiwisatarohul.ui.wisata.WisataCallback

class WisataAdapter(private val callback: WisataCallback) :
    RecyclerView.Adapter<WisataAdapter.WisataViewHolder>() {

    private var wisataList = ArrayList<Wisata>()

    fun setWisata(wisata: List<Wisata>?) {
        if (wisata == null) return
        this.wisataList.clear()
        this.wisataList.addAll(wisata)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WisataViewHolder {
        val binding =
            ItemWisataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WisataViewHolder(binding)
    }

    override fun getItemCount(): Int = wisataList.size

    override fun onBindViewHolder(holder: WisataViewHolder, position: Int) {
        holder.bind(wisataList[position])
    }

    inner class WisataViewHolder(private val binding: ItemWisataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(wisata: Wisata) {
            with(binding) {
                tvTitle.text = wisata.nama_wisata
                tvAddress.text = wisata.alamat
                tvKategori.text = wisata.kategori

//                Glide.with(itemView.context)
//                    .load(ServerConfig.GALLERY_PATH + event.gambar)
//                    .apply(RequestOptions().placeholder(R.drawable.ic_hourglass_empty_black_24dp))
//                    .into(ivEvent)

                root.setOnClickListener {
                    callback.onItemClick(wisata)
                }
            }
        }
    }
}