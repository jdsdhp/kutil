/*
 * Copyright (c) 2020 jesusd0897.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jesusd0897.kutil

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission

/**
 * To compose an email, use one of the actions based on whether you'll include attachments,
 * and include email details such as the recipient and subject using the extra keys.
 * @return true if the intent can be solved. Otherwise it will return false.
 */
fun sendEmail(
    context: Context,
    emails: Array<String?>,
    subject: String?,
    message: String?
): Boolean {
    val intent = Intent(Intent.ACTION_SENDTO)
        .setType("message/rfc822")
        .setData(Uri.parse("mailto:"))
        .putExtra(Intent.EXTRA_EMAIL, emails)
        .putExtra(Intent.EXTRA_SUBJECT, subject)
        .putExtra(Intent.EXTRA_TEXT, message)
    return if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
        true
    } else false
}

/**
 * To open a web page, use the ACTION_VIEW action and specify the web URL in the intent data.
 * @return true if the intent can be solved. Otherwise it will return false.
 */
@RequiresPermission(Manifest.permission.INTERNET)
fun openWebPage(context: Context, url: String): Boolean {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    return if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
        true
    } else false
}

/**
 * Send Download Intent to Navigator.
 * @param context App context.
 * @param downloadURL Url to download apk file.
 */
@RequiresPermission(Manifest.permission.INTERNET)
fun downloadFileThroughWeb(context: Context, downloadURL: String) =
    openWebPage(context, downloadURL)

/**
 * To create a new alarm, use the ACTION_SET_ALARM action and specify alarm details such as the time and message using extras.
 * @return true if the intent can be solved. Otherwise it will return false.
 */
@RequiresPermission(Manifest.permission.SET_ALARM)
fun createAlarm(context: Context, message: String, hour: Int, minutes: Int): Boolean {
    val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
        putExtra(AlarmClock.EXTRA_MESSAGE, message)
        putExtra(AlarmClock.EXTRA_HOUR, hour)
        putExtra(AlarmClock.EXTRA_MINUTES, minutes)
    }
    return if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
        true
    } else false
}

/**
 * To create a countdown timer, use the ACTION_SET_TIMER action and specify timer details.
 * @return true if the intent can be solved. Otherwise it will return false.
 */
@RequiresApi(Build.VERSION_CODES.KITKAT)
@RequiresPermission(Manifest.permission.SET_ALARM)
fun startTimer(context: Context, message: String, seconds: Int): Boolean {
    val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
        putExtra(AlarmClock.EXTRA_MESSAGE, message)
        putExtra(AlarmClock.EXTRA_LENGTH, seconds)
        putExtra(AlarmClock.EXTRA_SKIP_UI, true)
    }
    return if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
        true
    } else false
}

/**
 * To add a new event to the user's calendar.
 * You can then specify various event details using extras.
 * @return true if the intent can be solved. Otherwise it will return false.
 */
fun addEventToCalendar(
    context: Context,
    title: String,
    location: String? = null,
    description: String? = null,
    begin: Long? = null,
    end: Long? = null,
    isAllDay: Boolean? = null,
    availability: Int? = null,
): Boolean {
    val intent = Intent(Intent.ACTION_INSERT).apply {
        data = CalendarContract.Events.CONTENT_URI
        putExtra(CalendarContract.Events.TITLE, title)
        location?.let { putExtra(CalendarContract.Events.EVENT_LOCATION, it) }
        description?.let { putExtra(CalendarContract.Events.DESCRIPTION, it) }
        begin?.let { putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, it) }
        end?.let { putExtra(CalendarContract.EXTRA_EVENT_END_TIME, it) }
        isAllDay?.let { putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, it) }
        availability?.let { putExtra(CalendarContract.Events.AVAILABILITY, it) }
    }
    return if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
        true
    } else false
}

/**
 * To open the phone app and dial a phone number, use the ACTION_DIAL action and specify a
 * phone number using the URI scheme. When the phone app opens, it displays
 * the phone number but the user must press the Call button to begin the phone call.
 * @return true if the intent can be solved. Otherwise it will return false.
 */
@RequiresPermission(Manifest.permission.CALL_PHONE)
fun dialPhoneNumber(context: Context, phoneNumber: String): Boolean {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    return if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
        true
    } else false
}

/**
 * Open a specific section of Settings.
 * To open a screen in the system settings when your app requires the user to change something,
 * use one of the following intent actions to open the settings screen respective to the action name.
 *
 * Sample: "Settings.ACTION_WIFI_SETTINGS"
 * @return true if the intent can be solved. Otherwise it will return false.
 */
fun openWifiSettings(context: Context, settingAction: String): Boolean {
    val intent = Intent(settingAction)
    return if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
        true
    } else false
}

/**
 * To initiate an SMS or MMS text message, use one of the intent actions and specify message
 * details such as the phone number, subject, and message body using the extra keys.
 * @return true if the intent can be solved. Otherwise it will return false.
 */
fun composeSmsMessage(context: Context, message: String, attachment: Uri): Boolean {
    val intent = Intent(Intent.ACTION_SEND).apply {
        data = Uri.parse("smsto:")  // This ensures only SMS apps respond
        putExtra("sms_body", message)
        putExtra(Intent.EXTRA_STREAM, attachment)
    }
    return if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
        true
    } else false
}

/**
 * Locate coordinates at map.
 * @return true if the intent can be solved. Otherwise it will return false.
 */
fun locateAtMap(context: Context, latitude: String, longitude: String): Boolean {
    val geo = Uri.parse("geo:$latitude,$longitude")
    val intent = Intent(Intent.ACTION_VIEW).apply { data = geo }
    return if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
        true
    } else false
}

fun showSoftKeyboard(view: View) {
    if (view.requestFocus()) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}