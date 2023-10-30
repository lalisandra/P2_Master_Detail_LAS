package com.example.p2_master_detail_las

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.p2_master_detail_las.extensions.toBitmap
import com.example.p2_master_detail_las.model.Show


private const val ARG_SHOW = "ARG_SHOW"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentDetail : Fragment() {

    private var showID: Int? = null
    private var show: Show? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            showID = it.getInt(ARG_SHOW,-1)
            show = Show.getShowById(showID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tvTitle).text = show?.name
        view.findViewById<TextView>(R.id.tvSummary).text = show?.summary
        view.findViewById<TextView>(R.id.tvGenre).text = show?.genres.toString()
        view.findViewById<TextView>(R.id.tvLanguage).text = show?.language
        view.findViewById<TextView>(R.id.tvPremiered).text = show?.premiered
        view.findViewById<TextView>(R.id.tvURL).text = show?.officialSite

        view.findViewById<ImageView>(R.id.ivShow).setImageBitmap(show?.image?.toBitmap(view.context))
        view.findViewById<RatingBar>(R.id.rbRating).rating = show?.rating ?: 0f
    }

    companion object {
       @JvmStatic
        fun newInstance(showId: Int) =
            FragmentDetail().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SHOW, showId)
                }
            }
    }
}