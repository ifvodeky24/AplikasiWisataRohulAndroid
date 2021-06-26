package com.example.aplikasiwisatarohul.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.data.source.remote.response.*
import com.example.aplikasiwisatarohul.databinding.FragmentHomeBinding
import com.example.aplikasiwisatarohul.ui.atm.AtmViewModel
import com.example.aplikasiwisatarohul.ui.atm.DetailAtmActivity
import com.example.aplikasiwisatarohul.ui.event.DetailEventActivity
import com.example.aplikasiwisatarohul.ui.event.EventViewModel
import com.example.aplikasiwisatarohul.ui.maps.MapsActivity
import com.example.aplikasiwisatarohul.ui.masjid.DetailMasjidActivity
import com.example.aplikasiwisatarohul.ui.masjid.MasjidViewModel
import com.example.aplikasiwisatarohul.ui.penginapan.DetailPenginapanActivity
import com.example.aplikasiwisatarohul.ui.penginapan.PenginapanViewModel
import com.example.aplikasiwisatarohul.ui.spbu.DetailSpbuActivity
import com.example.aplikasiwisatarohul.ui.spbu.SpbuViewModel
import com.example.aplikasiwisatarohul.ui.travel.DetailTravelActivity
import com.example.aplikasiwisatarohul.ui.travel.TravelViewModel
import com.example.aplikasiwisatarohul.ui.wisata.*
import com.example.aplikasiwisatarohul.vo.Status
import com.example.aplikasiwisatarohul.vo.ViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var atmViewModel: AtmViewModel
    private lateinit var masjidiewModel: MasjidViewModel
    private lateinit var penginapanViewModel: PenginapanViewModel
    private lateinit var spbuViewModel: SpbuViewModel
    private lateinit var travelViewModel: TravelViewModel
    private lateinit var wisataViewModel: WisataViewModel
    private lateinit var eventViewModel: EventViewModel
    private var _binding: FragmentHomeBinding? = null

    private var dotscount: Int = 0
    private var dots: Array<ImageView?>? = null
    private var viewPagerAdapter: BannerViewPagerAdapter? = null
    private var timer: Timer? = null
    private val binding get() = _binding

    private var atmList: MutableList<Atm> = mutableListOf()
    private var masjidList: MutableList<Masjid> = mutableListOf()
    private var penginapanList: MutableList<Penginapan> = mutableListOf()
    private var spbuList: MutableList<Spbu> = mutableListOf()
    private var travelList: MutableList<Travel> = mutableListOf()
    private var wisataList: MutableList<Wisata> = mutableListOf()
    private var eventList: MutableList<Event> = mutableListOf()

    private var mMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val factory = ViewModelFactory.getInstance(requireActivity())
        atmViewModel = ViewModelProvider(this, factory)[AtmViewModel::class.java]
        masjidiewModel = ViewModelProvider(this, factory)[MasjidViewModel::class.java]
        penginapanViewModel = ViewModelProvider(this, factory)[PenginapanViewModel::class.java]
        spbuViewModel = ViewModelProvider(this, factory)[SpbuViewModel::class.java]
        travelViewModel = ViewModelProvider(this, factory)[TravelViewModel::class.java]
        wisataViewModel = ViewModelProvider(this, factory)[WisataViewModel::class.java]
        eventViewModel = ViewModelProvider(this, factory)[EventViewModel::class.java]


        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fbWisataSejarah?.setOnClickListener {
            val intent = Intent(activity, WisataActivity::class.java)
            intent.putExtra(WisataActivity.NAME, "sejarah")
            startActivity(intent)
        }

        binding?.fbWisataKuliner?.setOnClickListener {
            val intent = Intent(activity, WisataActivity::class.java)
            intent.putExtra(WisataActivity.NAME, "kuliner")
            startActivity(intent)
        }

        binding?.fbWisataBuatan?.setOnClickListener {
            val intent = Intent(activity, WisataActivity::class.java)
            intent.putExtra(WisataActivity.NAME, "buatan")
            startActivity(intent)
        }

        binding?.fbWisataAlam?.setOnClickListener {
            val intent = Intent(activity, WisataActivity::class.java)
            intent.putExtra(WisataActivity.NAME, "alam")
            startActivity(intent)
        }

        binding?.fbWisataReligi?.setOnClickListener {
            val intent = Intent(activity, WisataActivity::class.java)
            intent.putExtra(WisataActivity.NAME, "religi")
            startActivity(intent)
        }

        binding?.fbWisataBudaya?.setOnClickListener {
            val intent = Intent(activity, WisataActivity::class.java)
            intent.putExtra(WisataActivity.NAME, "budaya")
            startActivity(intent)
        }

        viewPagerAdapter = BannerViewPagerAdapter(requireActivity())
        binding?.vpGaleri?.adapter = viewPagerAdapter

        viewPagerAdapter?.let { viewPagerAdapter ->
            dotscount = viewPagerAdapter.count
        }

        dots = arrayOfNulls(dotscount)

        val params = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = 8
        params.marginEnd = 8
        params.setMargins(8, 0, 8, 0)

        if (dots != null) {
            for (i in dots!!.indices) {
                val img = ImageView(activity)
                dots!![i] = img
                dots!![i]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.nonactive_dot
                    )
                )
                binding?.sliderDots?.addView(dots!![i], params)
            }

            dots!![0]?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.active_dot
                )
            )
        }

        binding?.vpGaleri?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

                if (dots != null) {
                    for (i in dots!!.indices) {
                        dots!![i]?.setImageDrawable(
                            ContextCompat.getDrawable(
                                context!!,
                                R.drawable.nonactive_dot
                            )
                        )
                    }

                    dots!![position]?.setImageDrawable(
                        ContextCompat.getDrawable(
                            context!!,
                            R.drawable.active_dot
                        )
                    )
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        timer = Timer()
        timer?.scheduleAtFixedRate(MyTimerTask(), 2000, 8000)

        binding?.tvMaps?.setOnClickListener {
            startActivity(Intent(activity, MapsActivity::class.java))
        }
    }

    inner class MyTimerTask : TimerTask() {
        override fun run() {
            CoroutineScope(Dispatchers.Main).launch {
                when (binding?.vpGaleri?.currentItem) {
                    0 -> binding?.vpGaleri?.currentItem = 1
                    1 -> binding?.vpGaleri?.currentItem = 2
                    2 -> binding?.vpGaleri?.currentItem = 3
                    3 -> binding?.vpGaleri?.currentItem = 4
                    4 -> binding?.vpGaleri?.currentItem = 5
                    else -> binding?.vpGaleri?.currentItem = 0
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewPagerAdapter = null
        dots = null
        timer = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mMap?.isMyLocationEnabled = true

        // Add a marker in Sydney and move the camera
        val pekanbaru = LatLng(-6.200000, 106.816666)
//        cameraPosition = CameraPosition.Builder().target(pekanbaru).zoom(2f).build()
//        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pekanbaru))
        mMap?.uiSettings?.isZoomControlsEnabled = true
        mMap?.uiSettings?.isZoomGesturesEnabled = true

//        val sydney = LatLng(-34.0, 151.0)
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(pekanbaru))

        atmViewModel.getAllAtm().observe(viewLifecycleOwner, { atm ->
            when (atm.status) {
                Status.SUCCESS -> {
                    if (atm.data != null) {
                        for (i in atm.data.indices) {
                            val latLng = LatLng(
                                atm.data[i].latitude.toDouble(),
                                atm.data[i].longitude.toDouble()
                            )
                            addMarkerAtm(atm.data[i], latLng, atm.data[i].nama_atm)

                        }

                        atmList.addAll(atm.data)
                    }
                }

                Status.LOADING -> {

                }

                Status.ERROR -> {
                    Toast.makeText(context, "Terjadi kesalahan ${atm.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        masjidiewModel.getAllMasjid().observe(viewLifecycleOwner, { masjid ->
            when (masjid.status) {
                Status.SUCCESS -> {
                    if (masjid.data != null) {
                        for (i in masjid.data.indices) {
                            val latLng = LatLng(
                                masjid.data[i].latitude.toDouble(),
                                masjid.data[i].longitude.toDouble()
                            )
                            addMarkerMasjid(
                                masjid.data[i],
                                latLng,
                                masjid.data[i].nama_masjid
                            )
                        }

                        masjidList.addAll(masjid.data)
                    }


                }

                Status.LOADING -> {

                }

                Status.ERROR -> {
                    Toast.makeText(
                        context,
                        "Terjadi kesalahan ${masjid.message}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        })

        penginapanViewModel.getAllPenginapan()
            .observe(viewLifecycleOwner, { penginapan ->
                when (penginapan.status) {
                    Status.SUCCESS -> {
                        if (penginapan.data != null) {
                            for (i in penginapan.data.indices) {
                                val latLng = LatLng(
                                    penginapan.data[i].latitude.toDouble(),
                                    penginapan.data[i].longitude.toDouble()
                                )
                                addMarkerPenginapan(
                                    penginapan.data[i],
                                    latLng,
                                    penginapan.data[i].nama_penginapan
                                )
                            }

                            penginapanList.addAll(penginapan.data)
                        }


                    }

                    Status.LOADING -> {

                    }

                    Status.ERROR -> {
                        Toast.makeText(
                            context,
                            "Terjadi kesalahan ${penginapan.message}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            })

        spbuViewModel.getAllSpbu()
            .observe(viewLifecycleOwner, { spbu ->
                when (spbu.status) {
                    Status.SUCCESS -> {
                        if (spbu.data != null) {
                            for (i in spbu.data.indices) {
                                val latLng = LatLng(
                                    spbu.data[i].latitude.toDouble(),
                                    spbu.data[i].longitude.toDouble()
                                )
                                addMarkerSpbu(
                                    spbu.data[i],
                                    latLng,
                                    spbu.data[i].nama_spbu
                                )
                            }

                            spbuList.addAll(spbu.data)
                        }
                    }

                    Status.LOADING -> {

                    }

                    Status.ERROR -> {
                        Toast.makeText(
                            context,
                            "Terjadi kesalahan ${spbu.message}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            })

        travelViewModel.getAllTravel()
            .observe(
                viewLifecycleOwner,
                { travel ->
                    when (travel.status) {
                        Status.SUCCESS -> {
                            if (travel.data != null) {
                                for (i in travel.data.indices) {
                                    val latLng =
                                        LatLng(
                                            travel.data[i].latitude.toDouble(),
                                            travel.data[i].longitude.toDouble()
                                        )
                                    addMarkerTravel(
                                        travel.data[i],
                                        latLng,
                                        travel.data[i].nama_travel
                                    )
                                }

                                travelList.addAll(travel.data)
                            }

                        }

                        Status.LOADING -> {

                        }

                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                "Terjadi kesalahan ${travel.message}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                })

        wisataViewModel.getAllWisata()
            .observe(
                viewLifecycleOwner,
                { wisata ->
                    when (wisata.status) {
                        Status.SUCCESS -> {
                            if (wisata.data != null) {
                                for (i in wisata.data.indices) {
                                    val latLng =
                                        LatLng(
                                            wisata.data[i].latitude.toDouble(),
                                            wisata.data[i].longitude.toDouble()
                                        )
                                    addMarkerWisata(
                                        wisata.data[i],
                                        latLng,
                                        wisata.data[i].nama_wisata
                                    )
                                }

                                wisataList.addAll(wisata.data)
                            }
                        }

                        Status.LOADING -> {

                        }

                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                "Terjadi kesalahan ${wisata.message}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                })

        eventViewModel.getAllEvent()
            .observe(
                viewLifecycleOwner,
                { event ->
                    when (event.status) {
                        Status.SUCCESS -> {
                            if (event.data != null) {
                                for (i in event.data.indices) {
                                    val latLng =
                                        LatLng(
                                            event.data[i].latitude.toDouble(),
                                            event.data[i].longitude.toDouble()
                                        )
                                    addMarkerEvent(
                                        event.data[i],
                                        latLng,
                                        event.data[i].nama_event
                                    )
                                }

                                eventList.addAll(event.data)
                            }
                        }

                        Status.LOADING -> {

                        }

                        Status.ERROR -> {
                            Toast.makeText(
                                context,
                                "Terjadi kesalahan ${event.message}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                })

        mMap?.setOnInfoWindowClickListener { marker ->

            when (marker.snippet) {
                "masjid" -> {
                    Timber.d("ddddd ${Gson().toJson(masjidList)}")

                    var masjidListing: List<Masjid> = listOf()
                    for (i in masjidList.indices) {
                        masjidListing = masjidList.filter { a -> a.nama_masjid == marker.title }
                    }

                    val moveWithObjectIntent =
                        Intent(activity, DetailMasjidActivity::class.java)
                    moveWithObjectIntent.putExtra(DetailMasjidActivity.DATA, masjidListing[0])
                    startActivity(moveWithObjectIntent)
                }
                "atm" -> {
                    Timber.d("ddddd ${Gson().toJson(atmList)}")

                    var atmListing: List<Atm> = listOf()
                    for (i in atmList.indices) {
                        atmListing = atmList.filter { a -> a.nama_atm == marker.title }
                    }

                    val moveWithObjectIntent =
                        Intent(activity, DetailAtmActivity::class.java)
                    moveWithObjectIntent.putExtra(DetailAtmActivity.DATA, atmListing[0])
                    startActivity(moveWithObjectIntent)
                }
                "penginapan" -> {
                    Timber.d("ddddd ${Gson().toJson(penginapanList)}")

                    var penginapanListing: List<Penginapan> = listOf()
                    for (i in penginapanList.indices) {
                        penginapanListing =
                            penginapanList.filter { a -> a.nama_penginapan == marker.title }
                    }

                    val moveWithObjectIntent =
                        Intent(activity, DetailPenginapanActivity::class.java)
                    moveWithObjectIntent.putExtra(DetailPenginapanActivity.DATA, penginapanListing[0])
                    startActivity(moveWithObjectIntent)
                }
                "travel" -> {
                    Timber.d("ddddd ${Gson().toJson(travelList)}")

                    var travelListing: List<Travel> = listOf()
                    for (i in travelList.indices) {
                        travelListing = travelList.filter { a -> a.nama_travel == marker.title }
                    }

                    val moveWithObjectIntent =
                        Intent(activity, DetailTravelActivity::class.java)
                    moveWithObjectIntent.putExtra(DetailTravelActivity.DATA, travelListing[0])
                    startActivity(moveWithObjectIntent)
                }
                "event" -> {
                    Timber.d("ddddd ${Gson().toJson(eventList)}")

                    var eventListing: List<Event> = listOf()
                    for (i in eventList.indices) {
                        eventListing = eventList.filter { a -> a.nama_event == marker.title }
                    }

                    val moveWithObjectIntent =
                        Intent(activity, DetailEventActivity::class.java)
                    moveWithObjectIntent.putExtra(DetailEventActivity.DATA, eventListing[0])
                    startActivity(moveWithObjectIntent)
                }
                "spbu" -> {
                    Timber.d("ddddd ${Gson().toJson(spbuList)}")

                    var spbuListing: List<Spbu> = listOf()
                    for (i in spbuList.indices) {
                        spbuListing = spbuList.filter { a -> a.nama_spbu == marker.title }
                    }

                    val moveWithObjectIntent =
                        Intent(activity, DetailSpbuActivity::class.java)
                    moveWithObjectIntent.putExtra(DetailSpbuActivity.DATA, spbuListing[0])
                    startActivity(moveWithObjectIntent)
                }
            }
        }
    }

    private fun addMarkerAtm(data: Atm, latLng: LatLng, nama_atm: String) {
        mMap?.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(nama_atm)
                .snippet("atm")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_atm_1))
        )
    }

    private fun addMarkerMasjid(data: Masjid, latLng: LatLng, namaMasjid: String) {
        mMap?.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(namaMasjid)
                .snippet("masjid")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_masjid_1))
        )
    }

    private fun addMarkerPenginapan(data: Penginapan, latLng: LatLng, namaPenginapan: String) {
        mMap?.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(namaPenginapan)
                .snippet("penginapan")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_penginapan_1))
        )
    }

    private fun addMarkerSpbu(data: Spbu, latLng: LatLng, namaSpbu: String) {
        mMap?.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(namaSpbu)
                .snippet("spbu")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_spbu_1))
        )
    }

    private fun addMarkerTravel(data: Travel, latLng: LatLng, namaTravel: String) {
        mMap?.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(namaTravel)
                .snippet("travel")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_travel_1))
        )
    }

    private fun addMarkerWisata(data: Wisata, latLng: LatLng, namaWisata: String) {
        mMap?.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(namaWisata)
                .snippet("wisata")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_wisata_1))
        )
    }

    private fun addMarkerEvent(data: Event, latLng: LatLng, namaEvent: String) {
        mMap?.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(namaEvent)
                .snippet("event")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_event_1))
        )
    }
}