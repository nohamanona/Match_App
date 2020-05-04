package com.example.gokonmatching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.RadioButton
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_participants_number_setting.*

class ParticipantsNumberSettingActivity : AppCompatActivity() {
    var ParticipantsManNoString = "1人"
    var ParticipantsWomanNoString = "1人"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_participants_number_setting)

        ManRadioGroup.setOnCheckedChangeListener {
                group, checkedId ->
            ParticipantsManNoString = findViewById<RadioButton>(checkedId).text as String
            testIdText.text = ParticipantsManNoString
        }
        WomanRadioGroup.setOnCheckedChangeListener {
                group, checkedId ->
            ParticipantsWomanNoString = findViewById<RadioButton>(checkedId).text as String
            testIdText.text = ParticipantsWomanNoString
        }

        BackButton2.setOnClickListener { finish() }

        DecisionButton.setOnClickListener { onDecisionButtonTapped(it) }
    }

    fun onDecisionButtonTapped(view: View?){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val ParticipantsManNo:Int =
            when (ParticipantsManNoString) {
                "1人" -> 1
                "2人" -> 2
                "3人" -> 3
                "4人" -> 4
                "5人" -> 5
                "6人" -> 6
                else -> 0
            }
        val ParticipantsWomanNo:Int =
            when (ParticipantsWomanNoString) {
                "1人" -> 1
                "2人" -> 2
                "3人" -> 3
                "4人" -> 4
                "5人" -> 5
                "6人" -> 6
                else -> 0
            }
        pref.edit {
            putInt("PARTICIPANTS_MAN_NO", ParticipantsManNo)
            putInt("PARTICIPANTS_WOMAN_NO", ParticipantsWomanNo)
        }

        val intent = Intent(this, ManOneSelectActivity::class.java)
        //intent.putExtra("PARTICIPANTS_MAN_NO",ParticipantsManNo)
        //intent.putExtra("PARTICIPANTS_WOMAN_NO",participants_woman_no)
        startActivity(intent)
    }


}
