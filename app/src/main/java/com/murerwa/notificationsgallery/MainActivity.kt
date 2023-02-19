package com.murerwa.notificationsgallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.murerwa.notificationsgallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        val notificationData = NotificationData(
            id = 1,
            title = "Notification Title",
            body = "Notification Body",
            avatar = "https://avatars.githubusercontent.com/u/16824702?v=4",
            image = "https://avatars.githubusercontent.com/u/16824702?v=4",
            identifier = "https://avatars.githubusercontent.com/u/16824702?v=4",
            notificationType = NotificationType.GENERAL
        )

        val notificationBuilder = NotificationBuilder(this, notificationData)

        binding.cardGeneralNotification.setOnClickListener {
            notificationBuilder.createNotification()
        }
    }
}