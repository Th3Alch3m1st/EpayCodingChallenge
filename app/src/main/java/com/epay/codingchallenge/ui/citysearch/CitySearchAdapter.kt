package com.epay.codingchallenge.ui.citysearch

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.epay.codingchallenge.databinding.SingleItemCityBinding
import com.epay.codingchallenge.network.model.City

/**
 * Created By Rafiqul Hasan
 */

class CitySearchAdapter(private val callBack: CitySearchAdapterCallBack) :
    RecyclerView.Adapter<CitySearchAdapter.CityViewHolder>() {
    private var cityList = listOf<City>()
    private var filteredList = listOf<City>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            SingleItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(filteredList[position].cityName)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    fun getFilter(): Filter {
        return citySearch
    }

    fun setCityList(list: List<City>) {
        this.cityList = list
        this.filteredList = list
    }

    private val citySearch = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList = cityList.filter { city ->
                city.cityName.contains(constraint, true)
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is List<*>) {
                val list = results.values as List<City>
                filteredList = list
                notifyDataSetChanged()
                callBack.onFilterComplete(list.isEmpty())
            }
        }
    }

    inner class CityViewHolder(val binding: SingleItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String) = with(binding) {
            city = name
        }
    }

    interface CitySearchAdapterCallBack {
        fun onFilterComplete(isEmpty: Boolean)
    }
}