package com.example.dota2statistics

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.viewpager.widget.ViewPager
import com.example.dota2statistics.databinding.ActivityUserTabbedInfoBinding
import com.example.dota2statistics.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout

class UserTabbedInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserTabbedInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserTabbedInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        val args: UserTabbedInfoActivityArgs by navArgs()
        val profile = args.profile
        Log.i("TAG", "onCreate: ${profile.personaname} ===============")
    }
}