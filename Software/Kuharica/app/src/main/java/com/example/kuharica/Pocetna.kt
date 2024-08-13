package com.example.kuharica

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.kuharica.adapters.MainPagerAdapter
import com.example.kuharica.fragments.BaseFragment
import com.example.kuharica.fragments.WeekFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.kuharica.fragments.NewFragment

class Pocetna : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pocetna)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)
            val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val mainPagerAdapter = MainPagerAdapter(supportFragmentManager, lifecycle)

        mainPagerAdapter.addFragment(
            MainPagerAdapter.FragmentItem(
                R.string.new_fragment, // Promijenite u odgovarajuće resurse za vaš fragment
                R.drawable.baseline_post_add_24,
                NewFragment::class // Zamijenite s pravim fragmentom
            )
        )
        mainPagerAdapter.addFragment(
            MainPagerAdapter.FragmentItem(
                R.string.base_fragment, // Promijenite u odgovarajuće resurse za vaš fragment
                R.drawable.baseline_storage_24,
                BaseFragment::class // Zamijenite s pravim fragmentom
            )
        )
        mainPagerAdapter.addFragment(
            MainPagerAdapter.FragmentItem(
                R.string.week_fragment, // Promijenite u odgovarajuće resurse za vaš fragment
                R.drawable.baseline_calendar_today_24,
                WeekFragment::class // Zamijenite s pravim fragmentom
            )
        )

        // Povezivanje adaptera s ViewPager2
        viewPager2.adapter = mainPagerAdapter

        // Povezivanje TabLayouta s ViewPager2
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.setText(mainPagerAdapter.fragmentItems[position].titleRes)
            tab.setIcon(mainPagerAdapter.fragmentItems[position].iconRes)
        }.attach()

        val poslaniTekst = intent.getStringExtra("poslani_tekst")
        val tvPozdrav = findViewById<TextView>(R.id.tvPozdrav)
        tvPozdrav.text = "Bok, $poslaniTekst!"
    }


    }



