package com.android.acronymsearchapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.acronymsearchapp.databinding.ViewAcronymItemRowBinding
import com.android.acronymsearchapp.domain.network.responses.AcronymSearchResponse

class AcronymListAdapter() :
    RecyclerView.Adapter<AcronymListAdapter.AcronymViewHolder>() {

    var itemList = ArrayList<AcronymSearchResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder {

        val itemBinding =
            ViewAcronymItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return AcronymViewHolder(itemBinding)
    }

    inner class AcronymViewHolder(itemBinding: ViewAcronymItemRowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) {
        val generalItem = itemList.get(position)
       // holder.itemBinding.txtTitle.text = generalItem.serviceName

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setItems(items: List<AcronymSearchResponse>) {
        itemList.addAll(items)
    }

}