package com.versilistyson.foodieapp.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.versilistyson.foodieapp.R


import com.versilistyson.foodieapp.fragments.ReviewListFragment.OnListFragmentInteractionListener
import com.versilistyson.foodieapp.model.FoodEntry
import com.versilistyson.foodieapp.views.RatingCustomView
import kotlinx.android.synthetic.main.rating_bar.view.*
import kotlinx.android.synthetic.main.review_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyReviewRecyclerViewAdapter(
    private val mValues: List<FoodEntry>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyReviewRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as FoodEntry
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }
    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val foodImage: ImageView = mView.cv_image_view
        val foodDetail: TextView = mView.cv_textview
        val ratingCustomView: RatingCustomView = mView.cv_rating_bar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.foodImage.setImageURI(item.photoUri)
        holder.foodDetail.text = item.item_name
        holder.ratingCustomView.ratingBar.rating = item.food_rating
        holder.ratingCustomView.changeRating(item.food_rating)
        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size


}
