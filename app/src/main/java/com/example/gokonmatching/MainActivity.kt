package com.example.gokonmatching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StartButton.setOnClickListener { onStartButtonTapped(it) }
    }
    private fun onStartButtonTapped(view: View?){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit { clear() }
        val intent = Intent(this, ParticipantsNumberSettingActivity::class.java)
        startActivity(intent)
    }

}
