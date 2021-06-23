package com.example.aplikasiwisatarohul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasiwisatarohul.data.source.remote.response.Masjid
import com.example.aplikasiwisatarohul.databinding.ItemMasjidBinding
import com.example.aplikasiwisatarohul.ui.masjid.MasjidCallback

class MasjidAdapter(private val callback: MasjidCallback) :
    RecyclerView.Adapter<MasjidAdapter.MasjidViewHolder>() {

    private var masjidList = ArrayList<Masjid>()

    fun setMasjid(masjid: List<Masjid>?) {
        if (masjid == null) return
        this.masjidList.clear()
        this.masjidList.addAll(masjid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasjidViewHolder {
        val binding =
            ItemMasjidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MasjidViewHolder(binding)
    }

    override fun getItemCount(): Int = masjidList.size

    override fun onBindViewHolder(holder: MasjidViewHolder, position: Int) {
        holder.bind(masjidList[position])
    }

    inner class MasjidViewHolder(private val binding: ItemMasjidBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(masjid: Masjid) {
            with(binding) {
                tvTitle.text = masjid.nama_masjid
                tvAddress.text = masjid.alamat

//                Glide.with(itemView.context)
//                    .load(ServerConfig.GALLERY_PATH + event.gambar)
//                    .apply(RequestOptions().placeholder(R.drawable.ic_hourglass_empty_black_24dp))
//                    .into(ivEvent)

                root.setOnClickListener {
                    callback.onItemClick(masjid)
                }
            }
        }
    }
}