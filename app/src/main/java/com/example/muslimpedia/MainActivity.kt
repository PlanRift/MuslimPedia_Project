package com.example.muslimpedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.muslimpedia.adapter.SectionPageAdapter
import com.example.muslimpedia.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
    }

    private fun setupViewPager() {
        binding.vpNews.adapter = SectionPageAdapter(this)
        val tabList = arrayOf(
            "Common",
            "About Quran",
            "Al Jazeera",
            "Warn for Muslims"
        )
        TabLayoutMediator(binding.tabs, binding.vpNews) {tab, position ->
            tab.text = tabList[position]
        }.attach()
    }
}