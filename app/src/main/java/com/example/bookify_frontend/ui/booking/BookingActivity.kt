package com.example.bookify_frontend.ui.booking

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bookify_frontend.R
import com.example.bookify_frontend.ui.confirmation.ConfirmationActivity
import com.example.testing.Event
import com.squareup.picasso.Picasso
import android.content.Intent


class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val event = intent.getParcelableExtra<Event>("Event")
        val titleTextView = findViewById<TextView>(R.id.textView3)
        val dateTextView = findViewById<TextView>(R.id.textView7)
        val cityTextView = findViewById<TextView>(R.id.textView9)
        val placeTextView = findViewById<TextView>(R.id.textView2)
        val timeTextView = findViewById<TextView>(R.id.textView8)
        val descriptionTextView = findViewById<TextView>(R.id.textView6)
        val priceTextView = findViewById<TextView>(R.id.textView10)
        val imageView = findViewById<ImageView>(R.id.burger)
//        imageurl einai dummy na doume pws leitourgei
        val imageUrl: String? = "https://www.fosonline.gr/media/news/2019/02/24/42431/main/live-paok-aris.jpg"



        event?.let {
            titleTextView.text = it.title
            dateTextView.text = it.date
            cityTextView.text = it.city
            descriptionTextView.text = it.description
            priceTextView.text = it.price.toString()
            placeTextView.text = it.location
            timeTextView.text = it.date



        }

        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.error_image)
            .into(imageView)



            // Find the "Book Now" button in the layout
            val bookNowButton: Button = findViewById(R.id.button3)

            // Set a click listener for the button
            bookNowButton.setOnClickListener {
                // Create an Intent to start the ConfirmationActivity
                val intent = Intent(this, ConfirmationActivity::class.java)

                // Start the ConfirmationActivity
                startActivity(intent)
            }
        }
    }
