package com.example.aplikasiwisatarohul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasiwisatarohul.data.source.remote.response.Penginapan
import com.example.aplikasiwisatarohul.data.source.remote.response.Travel
import com.example.aplikasiwisatarohul.data.source.remote.service.ApiConfig
import com.example.aplikasiwisatarohul.databinding.ItemPenginapanBinding
import com.example.aplikasiwisatarohul.databinding.ItemTravelBinding
import com.example.aplikasiwisatarohul.ui.penginapan.PenginapanCallback
import com.example.aplikasiwisatarohul.ui.travel.TravelCallback

class TravelAdapter(private val callback: TravelCallback) :
    RecyclerView.Adapter<TravelAdapter.TravelViewHolder>() {

    private var travelList = ArrayList<Travel>()

    fun setTravel(travel: List<Travel>?) {
        if (travel == null) return
        this.travelList.clear()
        this.travelList.addAll(travel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelViewHolder {
        val binding =
            ItemTravelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TravelViewHolder(binding)
    }

    override fun getItemCount(): Int = travelList.size

    override fun onBindViewHolder(holder: TravelViewHolder, position: Int) {
        holder.bind(travelList[position])
    }

    inner class TravelViewHolder(private val binding: ItemTravelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(travel: Travel) {
            with(binding) {
                tvTitle.text = travel.nama_travel
                tvAddress.text = travel.alamat

                Glide.with(itemView.context)
                    .load(ApiConfig.travel_images + travel.foto)
                    .into(ivTravel)

                root.setOnClickListener {
                    callback.onItemClick(travel)
                }
            }
        }
    }
}