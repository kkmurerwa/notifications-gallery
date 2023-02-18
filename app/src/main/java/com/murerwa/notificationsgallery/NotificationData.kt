package com.murerwa.notificationsgallery

data class NotificationData(
    val id: Int,
    val title: String,
    val body: String,
    val avatar: String? = null,
    val image: String? = null,
    val identifier: String? = null
)
