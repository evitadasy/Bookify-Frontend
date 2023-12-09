package com.example.bookify_frontend.ui.category

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookify_frontend.R
import com.example.bookify_frontend.api_service.ApiService
import com.example.bookify_frontend.api_service.RetrofitService
import com.example.bookify_frontend.ui.booking.BookingActivity
import com.example.testing.CategoryAdapter
import com.example.testing.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class CategoryActivity : AppCompatActivity(), CategoryAdapter.OnItemClickListener {

    data class CategoryItem(val imageUrl: String, val title: String, val description: String)

    private val categoryItemList = mutableListOf<CategoryItem>()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // Get the selected category position from intent
        val categoryPosition = intent.getIntExtra("CATEGORY_POSITION", -1)

        if (categoryPosition != -1) {
            // Make an API call to fetch data for the selected category
            fetchDataForCategory(categoryPosition)
        } else {
//            if (cityPosition!=-1) fetchDataForCity(cityPosition)
        }
    }

    private fun fetchDataForCity(cityPosition: Int){
        val apiService = RetrofitService.createApiService()

        val selectedCity = when (cityPosition) {
            0 -> "Thessaloniki"
            1 -> "Athens"
            2 -> "Patra"
            3 -> "Katerini"
            4 -> "Volos"
            else ->  "Heraklion"
        }

        val eventsByCityCall = apiService.getEventsByCity(selectedCity)

        eventsByCityCall.enqueue(object : Callback<List<Event>?> {
            override fun onResponse(
                call: Call<List<Event>?>,
                response: Response<List<Event>?>) {
                TODO("Not yet implemented")
                //PANAGIOTIS CALL
            }

            override fun onFailure(call: Call<List<Event>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })



    }

    private fun fetchDataForCategory(categoryPosition: Int) {
        val apiService = RetrofitService.createApiService()

        val selectedCategory = when (categoryPosition) {
            0 -> "cinema"
            1 -> "live"
            2 -> "food"
            3 -> "sports"
            4 -> "seminar"
            5 -> "theatre"
            6 -> "kids"
            else -> "museums"
        }

        val eventsByTypeCall = apiService.getEventsByType(selectedCategory.capitalize(Locale.ROOT))

        eventsByTypeCall.enqueue(object : Callback<List<Event>?> {
            override fun onResponse(
                call: Call<List<Event>?>,
                response: Response<List<Event>?>
            ) {
                val eventsResponse = response.body()

                if (eventsResponse != null) {
                    val recyclerView: RecyclerView = findViewById(R.id.categoryRecyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this@CategoryActivity)
                    categoryAdapter = CategoryAdapter(eventsResponse, this@CategoryActivity)
                    recyclerView.adapter = categoryAdapter

                    categoryAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Event>?>, t: Throwable) {
                // Handle failure
            }

        })
    }

    // Implement the onItemClick method
    override fun onItemClick(event: Event) {
        // Create an Intent to start the BookingActivity
        val intent = Intent(this@CategoryActivity, BookingActivity::class.java)

//        // Pass data to BookingActivity using Intent extras
//        intent.putExtra("EVENT_TITLE", event.title)
//        intent.putExtra("EVENT_DATE", event.date)
//        intent.putExtra("EVENT_CITY", event.city)
//        intent.putExtra("EVENT_DESCRIPTION",event.description)
//        intent.putExtra("PRICE",event.price)
//        intent.putExtra("EVENT_IMAGE",event.img)

        intent.putExtra("Event", event)


        // Start the BookingActivity
        startActivity(intent)
    }
}