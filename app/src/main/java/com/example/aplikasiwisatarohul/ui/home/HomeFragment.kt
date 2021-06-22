package com.example.aplikasiwisatarohul.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.databinding.FragmentHomeBinding
import com.example.aplikasiwisatarohul.ui.wisata.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private var dotscount: Int = 0
    private var dots: Array<ImageView?>? = null
    private var viewPagerAdapter: BannerViewPagerAdapter? = null
    private var timer: Timer? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fbWisataSejarah?.setOnClickListener {
            startActivity(Intent(activity, WisataSejarahActivity::class.java))
        }

        binding?.fbWisataKuliner?.setOnClickListener {
            startActivity(Intent(activity, WisataKulinerActivity::class.java))
        }

        binding?.fbWisataBuatan?.setOnClickListener {
            startActivity(Intent(activity, WisataBuatanActivity::class.java))
        }

        binding?.fbWisataAlam?.setOnClickListener {
            startActivity(Intent(activity, WisataAlamActivity::class.java))
        }

        binding?.fbWisataReligi?.setOnClickListener {
            startActivity(Intent(activity, WisataReligiActivity::class.java))
        }

        binding?.fbWisataBudaya?.setOnClickListener {
            startActivity(Intent(activity, WisataBudayaActivity::class.java))
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
}