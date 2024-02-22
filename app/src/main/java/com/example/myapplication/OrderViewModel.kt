package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val ONEPRICE = 100.00
private const val EXPRESS = 500.00

class OrderViewModel : ViewModel() {

    private val quantity_ = MutableLiveData<Int>()
    val quantity : LiveData<Int> = quantity_

    private val price_ = MutableLiveData<Double>()
    val price : LiveData<Double> = price_

    private val flavor_ = MutableLiveData<String>()
    val flavor : LiveData<String> = flavor_

    private val date_ = MutableLiveData<String>()
    val date : LiveData<String> = date_

    val datelist = getPickupDates()

    fun setDate(d: String) {
        date_.value = d
        calcTotal()
    }

    fun setFlavor(f: String) {
        flavor_.value = f
    }

    fun setQuantity(number: Int) {
        quantity_.value = number
        calcTotal()
    }

    fun calcTotal() {
        var total = (quantity_.value ?: 0) * ONEPRICE
        if(datelist[0] == date_.value) {
            total += EXPRESS
        }
        price_.value = total
    }

    fun getPickupDates() : List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE,1)
        }
        return options
    }

}