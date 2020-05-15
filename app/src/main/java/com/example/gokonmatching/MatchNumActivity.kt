package com.example.gokonmatching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_match_num.*
import org.json.JSONArray

class MatchNumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_num)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        var ManOneSelectWomanNo = -1
        var ManTwoSelectWomanNo = -1
        var ManThreeSelectWomanNo = -1
        var ManFourSelectWomanNo = -1
        var ManFiveSelectWomanNo = -1
        var ManSixSelectWomanNo = -1
        var WomanOneSelectWomanNo = -1
        var WomanTwoSelectWomanNo = -1
        var WomanThreeSelectWomanNo = -1
        var WomanFourSelectWomanNo = -1
        var WomanFiveSelectWomanNo = -1
        var WomanSixSelectWomanNo = -1

        ManOneSelectWomanNo = pref.getInt("MAN_ONE_SELECT_WOMAN_NO",-1)
        ManTwoSelectWomanNo = pref.getInt("MAN_TWO_SELECT_WOMAN_NO",-1)
        ManThreeSelectWomanNo = pref.getInt("MAN_THREE_SELECT_WOMAN_NO",-1)
        ManFourSelectWomanNo = pref.getInt("MAN_FOUR_SELECT_WOMAN_NO",-1)
        ManFiveSelectWomanNo = pref.getInt("MAN_FIVE_SELECT_WOMAN_NO",-1)
        ManSixSelectWomanNo = pref.getInt("MAN_SIX_SELECT_WOMAN_NO",-1)
        WomanOneSelectWomanNo = pref.getInt("WOMAN_ONE_SELECT_MAN_NO",-1)
        WomanTwoSelectWomanNo = pref.getInt("WOMAN_TWO_SELECT_MAN_NO",-1)
        WomanThreeSelectWomanNo = pref.getInt("WOMAN_THREE_SELECT_MAN_NO",-1)
        WomanFourSelectWomanNo = pref.getInt("WOMAN_FOUR_SELECT_MAN_NO",-1)
        WomanFiveSelectWomanNo = pref.getInt("WOMAN_FIVE_SELECT_MAN_NO",-1)
        WomanSixSelectWomanNo = pref.getInt("WOMAN_SIX_SELECT_MAN_NO",-1)

        val ManSelList = arrayOf(ManOneSelectWomanNo,ManTwoSelectWomanNo,ManThreeSelectWomanNo,ManFourSelectWomanNo,ManFiveSelectWomanNo,ManSixSelectWomanNo)
        val WomanSelList = arrayOf(WomanOneSelectWomanNo,WomanTwoSelectWomanNo,WomanThreeSelectWomanNo,WomanFourSelectWomanNo,WomanFiveSelectWomanNo,WomanSixSelectWomanNo)

        //var matchNum=0
        //if(MatchJudge(1,ManOneSelectWomanNo,WomanOneSelectWomanNo,))
        var i=1
        var MatchCount = 0
        var MatchList = arrayOf<Array<Int>>()

        for (ManSel in ManSelList){
            //if(MatchJudge(i,ManSel,WomanSelList)){
            if(ManSel>=1) {
                if (i == WomanSelList[ManSel-1]) {
                    MatchCount++
                    val MatchPair = arrayOf(i,ManSel)
                    MatchList += MatchPair
                }
            }
            i++
        }

        //Match listをpreferenceに保存
        val PrefEditor = pref.edit()
        val jsonArray = JSONArray(MatchList)
        PrefEditor.putString("MatchList", jsonArray.toString())
        PrefEditor.apply()
        var MatchPairAll = "Match Pair = "
        MatchList.forEach {
            MatchPairAll += "("
            it.forEach {
                MatchPairAll += "${it}"+","
            }
            MatchPairAll += ")"
        }

        MatchPair.text = MatchPairAll
        tesTextM.text = "Man Sel = " + ManOneSelectWomanNo.toString() + ManTwoSelectWomanNo.toString() + ManThreeSelectWomanNo.toString() + ManFourSelectWomanNo.toString()+ ManFiveSelectWomanNo.toString()+ ManSixSelectWomanNo.toString()
        tesTextW.text = "Woman Sel = " + WomanOneSelectWomanNo.toString() + WomanTwoSelectWomanNo.toString() + WomanThreeSelectWomanNo.toString() + WomanFourSelectWomanNo.toString()+ WomanFiveSelectWomanNo.toString()+ WomanSixSelectWomanNo.toString()

        MutchNumText.text = MatchCount.toString() + "組"
        BackToTopButton.setOnClickListener { onBackToTopButtonTapped(it) }
        NextButton.setOnClickListener { onNextButtonTapped(it) }
    }
    fun onBackToTopButtonTapped(view: View){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit { clear() }
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

    fun onNextButtonTapped(view: View){
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
    }
}
