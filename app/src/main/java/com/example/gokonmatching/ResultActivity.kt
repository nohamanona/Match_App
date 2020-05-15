package com.example.gokonmatching

import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import androidx.core.view.setMargins
import org.json.JSONArray


class ResultActivity : AppCompatActivity() {

    var ParticipantsWomanNo = 0
    var ParticipantsManNo = 0
    val id2 = View.generateViewId()
    val id3 = View.generateViewId()
    val id4 = View.generateViewId()
    val id5 = View.generateViewId()
    val id6 = View.generateViewId()
    val idw2 = View.generateViewId()
    val idw3 = View.generateViewId()
    val idw4 = View.generateViewId()
    val idw5 = View.generateViewId()
    val idw6 = View.generateViewId()

    var MatchList = arrayOf<Array<Int>>()

    var Animation_start_end_list = arrayOf<Array<Float>>()

    var TimeCnt = 0
    val handl = Handler()
    var cnt = 0

    val runnable:Runnable = object:Runnable{
        override fun run() {
            MatchAnimation(Animation_start_end_list)
            if(TimeCnt<6){
                handl.postDelayed(this,600)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        ParticipantsManNo = pref.getInt("PARTICIPANTS_MAN_NO",-1)
        ParticipantsWomanNo = pref.getInt("PARTICIPANTS_WOMAN_NO",-1)

        val jsonArray = JSONArray(pref.getString("MatchList", "[]"));
        //val arrayList : ArrayList<String> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            var tmp = jsonArray.getString(i)
            val MatchMan = tmp.substring(1,2)
            val MatchWoman = tmp.substring(3,4)

            MatchList += arrayOf(MatchMan.toInt(),MatchWoman.toInt())//add(jsonArray.get(i) as String)
        }


        for (i in 0 until jsonArray.length()) {
            var tmp = jsonArray.getString(i)
            //Log.i("loadArrayList", "[$i] -> " + jsonArray.getString(i) + tmp.substring(1,2))
            Log.i("loadArrayList", "[$i] -> " + MatchList[i][0] + MatchList[i][1] +  MatchList.size.toString())
        }

        ManOneNameText.text = "A"
        ManOneNameText.setBackgroundResource(R.drawable.result_name_text_background)

        var i =1
        while (i <ParticipantsManNo) {
            val t = TextView(this)
            var tmpText = ""
            when (i) {
                1 -> {
                    tmpText = "B"
                    t.id = id2
                }
                2 -> {
                    tmpText = "C"
                    t.id = id3
                }
                3 -> {
                    tmpText = "D"
                    t.id = id4
                }
                4 -> {
                    tmpText = "E"
                    t.id = id5
                }
                5 -> {
                    tmpText = "F"
                    t.id = id6
                }
            }
            t.text = tmpText
            val tLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    60f,
                    resources.displayMetrics
                ).toInt()//LinearLayout.LayoutParams.WRAP_CONTENT
            )
            tLayoutParams.setMargins(0,TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,16f, resources.displayMetrics).toInt(),0,0)
            t.layoutParams = tLayoutParams
            t.gravity = Gravity.CENTER
            t.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24F)
            ManLinearLayout.addView(t)
            i++
        }

        WomanOneNameText.text = "A"
        WomanOneNameText.setBackgroundResource(R.drawable.result_name_text_background)
        var j =1
        while (j <ParticipantsWomanNo) {
            val t = TextView(this)
            var tmpText = ""
            when (j) {
                1 -> {
                    tmpText = "B"
                    t.id = idw2
                }
                2 -> {
                    tmpText = "C"
                    t.id = idw3
                }
                3 -> {
                    tmpText = "D"
                    t.id = idw4
                }
                4 -> {
                    tmpText = "E"
                    t.id = idw5
                }
                5 -> {
                    tmpText = "F"
                    t.id = idw6
                }
            }
            t.text = tmpText
            val tLayoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    60f,
                    resources.displayMetrics
                ).toInt()//LinearLayout.LayoutParams.WRAP_CONTENT
            )
            tLayoutParams.setMargins(0,TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,16f, resources.displayMetrics).toInt(),0,0)
            t.layoutParams = tLayoutParams
            t.gravity = Gravity.CENTER
            t.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24F)
            WomanLinearLayout.addView(t)
            j++
        }

        ResultStartButton.setOnClickListener { onResultButtonTapped(it) }
    }

    fun onResultButtonTapped(view: View){
        val ManOnePointX =  ManOneNameText.x
        val ManOnePointY = ManOneNameText.y
        val ManOneWidth = ManOneNameText.width

        /*
        var im = ImageView(this)
        im.setImageResource(R.drawable.lump)
        val imsize = im.height
        im.x = ManOnePointX + 20
        val im_height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, resources.displayMetrics).toInt()
        im.y = ManOnePointY + ManOneNameText.height/2 - im_height/2
        val imLayoutParams:LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, resources.displayMetrics).toInt(),
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, resources.displayMetrics).toInt()
        )
        im.layoutParams = imLayoutParams
        AnimationFrameLayout.addView(im)
         */

        StartAnimation(MatchList)
        //debugText.text = ManOnePointX.toString() + "," + ManOnePointY.toString()+ " , " + ManOneWidth + " , "+ ManOneNameText.height + " , " + im.y + " , " + im_height
    }

    fun StartAnimation(MatchList: Array<Array<Int>>){
        var Animation_start_x = 0F
        var Animation_start_y = 0F
        var Animation_end_x = 0F
        var Animation_end_y = 0F

        val im_size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, resources.displayMetrics).toInt()
        for(i in MatchList.indices) {
            when (MatchList[i][0]) {
                1 -> {
                    Animation_start_x = ManOneNameText.x
                    Animation_start_y = ManOneNameText.y + ManOneNameText.height / 2 - im_size / 2
                }
                2 -> {
                    Animation_start_x = findViewById<TextView>(id2).x
                    Animation_start_y =
                        findViewById<TextView>(id2).y + ManOneNameText.height / 2 - im_size / 2
                }
                3 -> {
                    Animation_start_x = findViewById<TextView>(id3).x
                    Animation_start_y =
                        findViewById<TextView>(id3).y + ManOneNameText.height / 2 - im_size / 2
                }
                4 -> {
                    Animation_start_x = findViewById<TextView>(id4).x
                    Animation_start_y =
                        findViewById<TextView>(id4).y + ManOneNameText.height / 2 - im_size / 2
                }
                5 -> {
                    Animation_start_x = findViewById<TextView>(id5).x
                    Animation_start_y =
                        findViewById<TextView>(id5).y + ManOneNameText.height / 2 - im_size / 2
                }
                6 -> {
                    Animation_start_x = findViewById<TextView>(id6).x
                    Animation_start_y =
                        findViewById<TextView>(id6).y + ManOneNameText.height / 2 - im_size / 2
                }
            }
            when (MatchList[i][1]) {
                1 -> {
                    Animation_end_x =
                        AnimationFrameLayout.width.toFloat() - im_size //WomanOneNameText.x
                    Animation_end_y = WomanOneNameText.y + WomanOneNameText.height / 2 - im_size / 2
                }
                2 -> {
                    Animation_end_x =
                        AnimationFrameLayout.width.toFloat() - im_size //findViewById<TextView>(idw2).x
                    Animation_end_y =
                        findViewById<TextView>(idw2).y + ManOneNameText.height / 2 - im_size / 2
                }
                3 -> {
                    Animation_end_x =
                        AnimationFrameLayout.width.toFloat() - im_size //findViewById<TextView>(idw3).x
                    Animation_end_y =
                        findViewById<TextView>(idw3).y + ManOneNameText.height / 2 - im_size / 2
                }
                4 -> {
                    Animation_end_x =
                        AnimationFrameLayout.width.toFloat() - im_size //findViewById<TextView>(idw4).x
                    Animation_end_y =
                        findViewById<TextView>(idw4).y + ManOneNameText.height / 2 - im_size / 2
                }
                5 -> {
                    Animation_end_x =
                        AnimationFrameLayout.width.toFloat() - im_size //findViewById<TextView>(idw5).x
                    Animation_end_y =
                        findViewById<TextView>(idw5).y + ManOneNameText.height / 2 - im_size / 2
                }
                6 -> {
                    Animation_end_x =
                        AnimationFrameLayout.width.toFloat() - im_size //findViewById<TextView>(idw6).x
                    Animation_end_y =
                        findViewById<TextView>(idw6).y + ManOneNameText.height / 2 - im_size / 2
                }
            }

            val Animation_start_end =
                arrayOf(Animation_start_x, Animation_start_y, Animation_end_x, Animation_end_y)
            Animation_start_end_list += Animation_start_end
        }
        //MatchAnimation(Animation_start_end_list)
        handl.post(runnable)
        //ShowOneImage(Animation_start_x, Animation_start_y, im_size)
        //ShowOneImage(Animation_end_x, Animation_end_y, im_size)
        Log.i("loadArrayList", "[start_x,start_y] -> " +Animation_start_x.toString() +","  + Animation_start_y.toString())
        Log.i("loadArrayList", "[end_x,end_y] -> " +Animation_end_x.toString() +","  + Animation_end_y.toString())
    }

    fun ShowOneImage(x: Float, y: Float, im_size: Int){//x,y　は表示する画像の左上とする
        var im = ImageView(this)

        im.setImageResource(R.drawable.lump)
        im.x = x
        im.y = y
        val imLayoutParams:LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            im_size,
            im_size
        )
        im.layoutParams = imLayoutParams
        AnimationFrameLayout.addView(im)
    }

    fun MatchAnimation(Animation_start_end_list: Array<Array<Float>>){
        val im_size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, resources.displayMetrics).toInt()
        val OneTimeShowWidth = AnimationFrameLayout.width.toFloat()/6 //一回で表示する幅
        val OneTimeNum = 3 //一回で表示する数
        for (i in Animation_start_end_list.indices) {
            Log.i("loadArrayList", "MatchAnimation [$i] -> " + Animation_start_end_list[i][0].toString() + Animation_start_end_list[i][1].toString() +  Animation_start_end_list[i][2].toString() + Animation_start_end_list[i][3].toString())
            //y = ax + b
            val a = (Animation_start_end_list[i][3]-Animation_start_end_list[i][1])/(Animation_start_end_list[i][2]-Animation_start_end_list[i][0])
            val b = Animation_start_end_list[i][1] - a * Animation_start_end_list[i][0]
            for (j in 0..OneTimeNum){
                val x = TimeCnt * OneTimeShowWidth + j * OneTimeShowWidth/OneTimeNum
                val y = a*x +b
                ShowOneImage(x, y, im_size)
            }
        }
        TimeCnt += 1
    }
}
