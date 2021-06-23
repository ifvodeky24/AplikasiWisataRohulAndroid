package com.example.aplikasiwisatarohul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasiwisatarohul.data.source.remote.response.Berita
import com.example.aplikasiwisatarohul.data.source.remote.service.ApiConfig
import com.example.aplikasiwisatarohul.databinding.ItemBeritaBinding
import com.example.aplikasiwisatarohul.ui.berita.BeritaCallback

class BeritaAdapter(private val callback: BeritaCallback) :
    RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    private var beritaList = ArrayList<Berita>()

    fun setAtm(berita: List<Berita>?) {
        if (berita == null) return
        this.beritaList.clear()
        this.beritaList.addAll(berita)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding =
            ItemBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeritaViewHolder(binding)
    }

    override fun getItemCount(): Int = beritaList.size

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        holder.bind(beritaList[position])
    }

    inner class BeritaViewHolder(private val binding: ItemBeritaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(berita: Berita) {
            with(binding) {
                tvTitle.text = berita.judul_berita
                tvPengarang.text = berita.nama_pengarang

                Glide.with(itemView.context)
                    .load(ApiConfig.berita_images + berita.foto)
                    .into(ivBerita)

                root.setOnClickListener {
                    callback.onItemClick(berita)
                }
            }
        }
    }
}