package com.david.spanisleague.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.david.spanisleague.R
import kotlinx.android.synthetic.main.activity_post.email
import kotlinx.android.synthetic.main.activity_post.name
import kotlinx.android.synthetic.main.activity_post.phone

class PostActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "com.david.spanisleague.view.EXTRA_ID"
        const val EXTRA_NAME = "com.david.spanisleague.view.EXTRA_NAME"
        const val EXTRA_PHONE = "com.david.spanisleague.view.EXTRA_PHONE"
        const val EXTRA_EMAIL = "com.david.spanisleague.view.EXTRA_EMAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        name.text = intent.getStringExtra(EXTRA_NAME)
        phone.text = intent.getStringExtra(EXTRA_PHONE)
        email.text = intent.getStringExtra(EXTRA_EMAIL)
    }
}
