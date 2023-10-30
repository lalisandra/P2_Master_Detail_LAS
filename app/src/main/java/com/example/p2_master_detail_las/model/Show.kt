package com.example.p2_master_detail_las.model

import android.content.Context
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import com.example.p2_master_detail_las.R

data class Show(
    var id:Int,
    var name:String,
    var language:String,
    var genres:Array<String>,
    var status:String,
    var premiered:String,
    var officialSite:String,
    var rating:Float,
    var image:String,
    var summary:String
){

    override fun toString(): String {
        return "${this.name}  ${this.language}"
    }

    companion object{
        val showList:MutableList<Show> = mutableListOf()

        fun loadSeries(context: Context){
            val raw = context.resources.openRawResource(R.raw.datos_json)
            val rd = BufferedReader(InputStreamReader(raw))

            val listType: Type = object : TypeToken<MutableList<Show>?>() {}.type

            val gson = Gson()
            showList.clear()

            val shows:List<Show> = gson.fromJson(rd, listType)

            showList.addAll(shows)
        }

        fun getShowById(id:Int?): Show?{
            val series = showList.filter {
                it.id == id
            }


            return if(series.isNotEmpty()) series[0] else null
        }
    }
}
