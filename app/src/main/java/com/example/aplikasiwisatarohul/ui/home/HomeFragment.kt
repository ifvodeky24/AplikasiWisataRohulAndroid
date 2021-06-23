package com.example.aplikasiwisatarohul.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.databinding.FragmentHomeBinding
import com.example.aplikasiwisatarohul.ui.wisata.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private var dotscount: Int = 0
    private var dots: Array<ImageView?>? = null
    private var viewPagerAdapter: BannerViewPagerAdapter? = null
    private var timer: Timer? = null
    private val binding get() = _binding

    private val markerOptions = MarkerOptions()
    private lateinit var cameraPosition: CameraPosition
    private var mMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

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
    }

    inner class MyTimerTask : TimerTask() {
        override fun run() {
            CoroutineScope(Dispatchers.Main).launch {
                when (binding?.vpGaleri?.currentItem) {
                    0 -> binding?.vpGaleri?.currentItem = 1
                    1 -> binding?.vpGaleri?.currentItem = 2
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
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        cameraPosition = CameraPosition.Builder().target(pekanbaru).zoom(2f).build()
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
//        getMarker()
    }
}