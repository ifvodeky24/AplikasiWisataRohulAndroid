package com.example.aplikasiwisatarohul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasiwisatarohul.data.source.remote.response.Penginapan
import com.example.aplikasiwisatarohul.data.source.remote.service.ApiConfig
import com.example.aplikasiwisatarohul.databinding.ItemPenginapanBinding
import com.example.aplikasiwisatarohul.ui.penginapan.PenginapanCallback

class PenginapanAdapter(private val callback: PenginapanCallback) :
    RecyclerView.Adapter<PenginapanAdapter.PenginapanViewHolder>() {

    private var penginapanList = ArrayList<Penginapan>()

    fun setPenginapan(penginapan: List<Penginapan>?) {
        if (penginapan == null) return
        this.penginapanList.clear()
        this.penginapanList.addAll(penginapan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenginapanViewHolder {
        val binding =
            ItemPenginapanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PenginapanViewHolder(binding)
    }

    override fun getItemCount(): Int = penginapanList.size

    override fun onBindViewHolder(holder: PenginapanViewHolder, position: Int) {
        holder.bind(penginapanList[position])
    }

    inner class PenginapanViewHolder(private val binding: ItemPenginapanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(penginapan: Penginapan) {
            with(binding) {
                tvTitle.text = penginapan.nama_penginapan
                tvAddress.text = penginapan.alamat

                Glide.with(itemView.context)
                    .load(ApiConfig.penginapan_images + penginapan.foto)
                    .into(ivPenginapan)

                root.setOnClickListener {
                    callback.onItemClick(penginapan)
                }
            }
        }
    }
}