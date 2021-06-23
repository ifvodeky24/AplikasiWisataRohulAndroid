package com.example.aplikasiwisatarohul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasiwisatarohul.data.source.remote.response.Atm
import com.example.aplikasiwisatarohul.data.source.remote.service.ApiConfig
import com.example.aplikasiwisatarohul.databinding.ItemAtmBinding
import com.example.aplikasiwisatarohul.ui.atm.AtmCallback

class AtmAdapter(private val callback: AtmCallback) :
    RecyclerView.Adapter<AtmAdapter.AtmViewHolder>() {

    private var atmList = ArrayList<Atm>()

    fun setAtm(atm: List<Atm>?) {
        if (atm == null) return
        this.atmList.clear()
        this.atmList.addAll(atm)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtmViewHolder {
        val binding =
            ItemAtmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AtmViewHolder(binding)
    }

    override fun getItemCount(): Int = atmList.size

    override fun onBindViewHolder(holder: AtmViewHolder, position: Int) {
        holder.bind(atmList[position])
    }

    inner class AtmViewHolder(private val binding: ItemAtmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(atm: Atm) {
            with(binding) {
                tvTitle.text = atm.nama_atm
                tvAddress.text = atm.alamat

                Glide.with(itemView.context)
                    .load(ApiConfig.atm_images + atm.foto)
                    .into(ivAtm)

                root.setOnClickListener {
                    callback.onItemClick(atm)
                }
            }
        }
    }
}