package com.example.p2_master_detail_las

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.p2_master_detail_las.databinding.ListItemBinding
import com.example.p2_master_detail_las.model.Show
import com.example.p2_master_detail_las.extensions.toBitmap

class MyItemRecyclerViewAdapter(
    private val showList: List<Show>,
    private val listener:OnItemClick?
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    //Creates a ViewHolder for every item of the list (showList)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    //In this function customize our ViewHolder with its data depending on the position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val show = showList[position]
        holder.tvShowName.text = show.name
        holder.tvLanguage.text = show.language
        holder.rbRating.rating = show.rating


        //We are using an extension toBipmap
        holder.ivShow.setImageBitmap(show.image.toBitmap(holder.ivShow.context))

        //We set the tag with superhero data, in order to obtain its data in the listener
        holder.itemView.tag = show

        holder.itemView.setOnClickListener(holder) //Our ViewHolder implements OnClickListener
    }

    //We need to know the length of the list. It will be the size of out data list
    override fun getItemCount(): Int = showList.size

    //This inner class contains all view of each item on list_item.xml
    inner class ViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        val tvShowName: TextView = binding.tvShowName
        val tvLanguage: TextView = binding.tvLanguage
        val rbRating: RatingBar = binding.rbRating
        val ivShow: ImageView = binding.ivShow


        override fun toString(): String {
            return super.toString() + " ${tvShowName.text} is in ${tvLanguage.text} and has ${rbRating.rating} stars"
        }

        override fun onClick(v: View?) {

            //First we recovery de show through the tag. Remember we have set it before (onBindViewHolder)
            val show = v?.tag as Show

            //later we call our callback with the SuperHero param to inform ListFragment, and then MainActivity
            listener?.OnItemClick(show)
        }
    }

}