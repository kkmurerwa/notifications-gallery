package com.murerwa.notificationsgallery

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.murerwa.notificationsgallery.NotificationType.*

class NotificationBuilder(
    private val context: Context,
    private val notificationData: NotificationData
) {
    fun createNotification() {
        val notificationManager = context
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channelId, "notification", NotificationManager.IMPORTANCE_HIGH)

            // Set the channel to the notification manager
            notificationManager.createNotificationChannel(notificationChannel)
        }

        // Create notification builder based on notification type
        val notificationBuilder: NotificationCompat.Builder = when (notificationData.notificationType) {
            GENERAL -> {
                getTextMessageNotificationBuilder()
            }
            TEXT_MESSAGE -> {
                getTextMessageNotificationBuilder()
            }
            IMAGE_MESSAGE -> {
                getGeneralNotificationBuilder()
            }
            UPLOAD_DOWNLOAD -> {
                getGeneralNotificationBuilder()
            }
            PATIENT_UPDATE -> {
                getGeneralNotificationBuilder()
            }
            CALENDAR_EVENT -> {
                getGeneralNotificationBuilder()
            }
        }

        //Display notification
        notificationManager.notify(notificationData.id, notificationBuilder.build())
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun getGeneralNotificationBuilder(): NotificationCompat.Builder {
        // Create intent to be passed to the notification
        val intent = Intent(context, MainActivity::class.java)

        // Create pending intent
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(context, 0, intent, 0)
        }

        // Create notification builder
        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(notificationData.title)
            .setContentText(notificationData.body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.BigTextStyle().bigText(notificationData.body))
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(pendingIntent)
    }

    private fun getTextMessageNotificationBuilder(): NotificationCompat.Builder {
        // Create intent to be passed to the notification
        val intent = Intent(context, MainActivity::class.java)

        // Create pending intent
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(context, 0, intent, 0)
        }

//        val drawable = AppCompatResources.getDrawable(
//            context,
//            coreR.drawable.ic_add_plain
//        )

//        val bitmap = try {
//            Glide.with(context)
//                .asBitmap()
//                .load(notificationData.avatar)
//                .submit()
//                .get()
//        } catch (e: Exception) {
//            drawable?.toBitmap()
//        }

//        val notificationLayoutCollapsed = RemoteViews(context.packageName, R.layout.notification_collapsed)
//        notificationLayoutCollapsed.setTextViewText(R.id.title, notificationData.title)
//        notificationLayoutCollapsed.setTextViewText(R.id.text, notificationData.body)
//
//
//        val notificationLayoutExpanded = RemoteViews(context.packageName, R.layout.notification_expanded)
//        notificationLayoutExpanded.setImageViewBitmap(R.id.image, drawable?.toBitmap())
//        notificationLayoutExpanded.setTextViewText(R.id.title, notificationData.title)
//        notificationLayoutExpanded.setTextViewText(R.id.text, notificationData.body)

        // Create notification builder
        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setLargeIcon(bitmap)
            .setContentTitle(notificationData.title)
            .setContentText(notificationData.body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(notificationData.body)
            )
//            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
//            .setCustomHeadsUpContentView(notificationLayoutCollapsed)
//            .setCustomContentView(notificationLayoutCollapsed)
//            .setCustomBigContentView(notificationLayoutExpanded)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(pendingIntent)
    }

    companion object {
        private const val channelId = "tibalink-notifications"
    }
}