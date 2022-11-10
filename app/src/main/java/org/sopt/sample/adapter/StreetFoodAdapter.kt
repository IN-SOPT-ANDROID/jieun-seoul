package org.sopt.sample.adapter

import android.view.LayoutInflater
import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.StreetFood
import org.sopt.sample.databinding.LayoutStreetfoodListBinding
import org.sopt.sample.databinding.LayoutTitleBinding

class StreetFoodAdapter(context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var foodlist:List<StreetFood> = emptyList()

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TITLE_ID
            else -> FOODLIST_ID
        }
    }

    class TitleViewHolder(
        private var binding: LayoutTitleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: StreetFood){
            binding.txtTitle.text=data.name
        }
    }

    class FoodlistViewHolder(
        private val binding: LayoutStreetfoodListBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: StreetFood){
            binding.menuImg.setImageResource(data.image)
            binding.txtName.text=data.name
            binding.txtLocation.text=data.location
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TITLE_ID -> {
                TitleViewHolder(
                    LayoutTitleBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                )
            }
            else -> {
                FoodlistViewHolder(
                    LayoutStreetfoodListBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FoodlistViewHolder -> holder.onBind(foodlist[position])
            is TitleViewHolder -> holder.onBind(foodlist[position])
            else -> throw IllegalArgumentException("holder : $holder")
        }
    }

    override fun getItemCount()=foodlist.size

    fun setRepoList(foodlist: List<StreetFood>){
        this.foodlist=foodlist.toList()
        notifyDataSetChanged()
    }

    companion object {
        private const val TITLE_ID = 0
        private const val FOODLIST_ID = 1
    }

}