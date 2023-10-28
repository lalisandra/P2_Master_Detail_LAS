package com.example.p2_master_detail_las

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.example.p2_master_detail_las.databinding.ActivityMainBinding
import com.example.p2_master_detail_las.model.Show

class MainActivity : AppCompatActivity(), OnItemClick {

    private val layoutList: FrameLayout by lazy { findViewById(R.id.containerList) }
    private val layoutDetail:FrameLayout? by lazy { findViewById(R.id.containerDetail) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
    }

    override fun OnItemClick(show: Show) {

    }
}