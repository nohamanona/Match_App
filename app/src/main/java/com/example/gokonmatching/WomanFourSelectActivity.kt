package com.example.gokonmatching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_woman_four_select.BackButton2
import kotlinx.android.synthetic.main.activity_woman_four_select.id1
import kotlinx.android.synthetic.main.activity_woman_four_select.radioGroup
import kotlinx.android.synthetic.main.activity_woman_four_select.textView4

class WomanFourSelectActivity : AppCompatActivity() {

    var ParticipantsWomanNo = 0
    var ParticipantsManNo = 0
    val id2 = View.generateViewId()
    val id3 = View.generateViewId()
    val id4 = View.generateViewId()
    val id5 = View.generateViewId()
    val id6 = View.generateViewId()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_woman_four_select)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        ParticipantsManNo = pref.getInt("PARTICIPANTS_MAN_NO",-1)
        textView4.text = ParticipantsManNo.toString()

        var i =1
        while (i <ParticipantsManNo) {
            val r = RadioButton(this)
            var tmpText = ""
            when (i){
                1 -> {
                    tmpText = "B"
                    r.id = id2
                }
                2 -> {
                    tmpText = "C"
                    r.id = id3
                }
                3 -> {
                    tmpText = "D"
                    r.id = id4
                }
                4 -> {
                    tmpText = "E"
                    r.id = id5
                }
                5 -> {
                    tmpText = "F"
                    r.id = id6
                }
            }
            r.text = tmpText
            //r. = "@null"
            val rLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            r.layoutParams = rLayoutParams
            r.buttonDrawable = null
            r.setBackgroundResource(R.drawable.btn_radio_center_m)
            r.gravity = Gravity.CENTER
            r.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30F)
            r.setOnClickListener { onDecisionButtonTapped(it) }
            radioGroup.addView(r)
            i++
        }
        BackButton2.setOnClickListener { finish() }

        id1.setOnClickListener { onDecisionButtonTapped(it) }
    }

    override fun onBackPressed() {
    }

    fun onDecisionButtonTapped(view: View){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        ParticipantsWomanNo = pref.getInt("PARTICIPANTS_WOMAN_NO",-1)
        ParticipantsManNo = pref.getInt("PARTICIPANTS_MAN_NO",-1)

        var ManOneSelectWomanNo = -1
        if(id1.isChecked){
            ManOneSelectWomanNo = 1
        }
        val CheckedRadioButton =
            findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
        when(CheckedRadioButton){
            id1 -> ManOneSelectWomanNo =1
            findViewById<RadioButton>(id2) -> ManOneSelectWomanNo =2
            findViewById<RadioButton>(id3) -> ManOneSelectWomanNo =3
            findViewById<RadioButton>(id4) -> ManOneSelectWomanNo =4
            findViewById<RadioButton>(id5) -> ManOneSelectWomanNo =5
            findViewById<RadioButton>(id6) -> ManOneSelectWomanNo =6
        }
        pref.edit {
            putInt("WOMAN_FOUR_SELECT_MAN_NO", ManOneSelectWomanNo)
        }
        if (ParticipantsWomanNo == 4) {
            val intent = Intent(this, MatchNumActivity::class.java)
            startActivity(intent)
        }
        else{
            val intent = Intent(this, WomanFiveSelectActivity::class.java)
            startActivity(intent)
        }
    }
}
