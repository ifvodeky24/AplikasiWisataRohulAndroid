package com.example.aplikasiwisatarohul.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.aplikasiwisatarohul.data.source.remote.response.Event
import com.example.aplikasiwisatarohul.data.source.remote.service.ApiConfig
import com.example.aplikasiwisatarohul.databinding.ItemEventBinding
import com.example.aplikasiwisatarohul.ui.event.EventCallback

class EventAdapter(private val callback: EventCallback) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private var eventList = ArrayList<Event>()

    fun setEvent(event: List<Event>?) {
        if (event == null) return
        this.eventList.clear()
        this.eventList.addAll(event)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding =
            ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    inner class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            with(binding) {
                tvTitle.text = event.nama_event
                tvDate.text = event.createdAt
                tvAddress.text = event.alamat

                Glide.with(itemView.context)
                    .load(ApiConfig.event_images + event.foto)
                    .into(ivEvent)

                root.setOnClickListener {
                    callback.onItemClick(event)
                }
            }
        }
    }

}