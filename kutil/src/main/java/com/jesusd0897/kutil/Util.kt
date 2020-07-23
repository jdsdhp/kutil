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

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Base64
import android.util.Log
import androidx.core.app.ActivityCompat
import java.io.InputStream
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

const val TAG_DEBUG = " - tag/dev"

const val PATTERN_NAME = "^(?!\\s*$)[-a-zñáéíóúA-ZÑÁÉÍÓÚ. ]*$"
const val PATTERN_EMAIL =
    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

const val PASSWORD_MIN_LENGTH = 8
const val PASSWORD_MAX_LENGTH = 50
const val PHONE_LENGTH = 8
const val COST_MAX_LENGTH = 10
const val ID_CARD_LENGTH = 11
const val NAME_MAX_LENGTH = 50
const val LAST_NAME_MAX_LENGTH = 50
const val EMAIL_MAX_LENGTH = 30
const val DEFAULT_TEXT_LENGTH = 255

const val REQUEST_CODE_CALL_PHONE = 665
const val REQUEST_CODE_CAMERA = 666
const val REQUEST_CODE_W_EXTERNAL_STORAGE = 667
const val REQUEST_CODE_CAMERA_AND_W_EXTERNAL_STORAGE = 6767
const val REQUEST_CODE_RESULT = 1234
const val REQUEST_CODE_RESULT_2 = 1235

const val SMALL_AVATAR_IMAGE_MAX_WIDTH = 200
const val SMALL_AVATAR_IMAGE_MAX_HEIGHT = 200
const val DEFAULT_AVATAR_IMAGE_MAX_WIDTH = 400
const val DEFAULT_AVATAR_IMAGE_MAX_HEIGHT = 400
const val BIG_AVATAR_IMAGE_MAX_WIDTH = 600
const val BIG_AVATAR_IMAGE_MAX_HEIGHT = 600

fun log(msg: String) = Log.d(TAG_DEBUG, msg)
fun log(className: String, msg: String) = Log.d(TAG_DEBUG, "$className: $msg")

/**
 * Request permission through Dialog.
 * @param activity           Activity who request.
 * @param manifestPermission Permission from manifest.
 * @param idPermission       Permission ID.
 */
fun requestDialogPermissions(activity: Activity, manifestPermission: String, idPermission: Int) =
    ActivityCompat.requestPermissions(activity, arrayOf(manifestPermission), idPermission)

/**
 * Request permission through Dialog.
 * @param activity            Activity who request.
 * @param manifestPermissions Permissions from manifest.
 * @param idPermission        Permission ID.
 */
fun requestDialogPermissions(
    activity: Activity,
    manifestPermissions: Array<String>,
    idPermission: Int
) =
    ActivityCompat.requestPermissions(activity, manifestPermissions, idPermission)

/**
 * Codify a string has Base64.
 * @param toEncode String to encode.
 */
fun base64Encode(toEncode: String): String =
    Base64.encodeToString(toEncode.toByteArray(), Base64.NO_WRAP).trim()

/**
 * Codify a byteArray has Base64.
 * @param toEncode ByteArray to encode.
 */
fun base64Encode(toEncode: ByteArray): String =
    Base64.encodeToString(toEncode, Base64.NO_WRAP).trim()

/**
 * Codify a byteArray has Base64 with UTF-8 Charset.
 * @param toEncode String to encode.
 */
fun base64EncodeUTF8(toEncode: String): String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        Base64.encodeToString(toEncode.toByteArray(StandardCharsets.UTF_8), Base64.DEFAULT).trim()
    else Base64.encodeToString(toEncode.toByteArray(Charset.forName("UTF-8")), Base64.DEFAULT)
        .trim()

/**
 * Decode a Base64 to string.
 * @param encodedString Encoded string to decode.
 */
fun base64Decode(encodedString: String): String =
    String(Base64.decode(encodedString, Base64.NO_WRAP)).trim()

/**
 * Decode a Base64 with UTF-8 Charset to string.
 * @param encodedString Encoded UTF-8 string to decode.
 */
fun base64DecodeUTF8(encodedString: String): String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        String(Base64.decode(encodedString, Base64.DEFAULT), StandardCharsets.UTF_8).trim()
    else String(Base64.decode(encodedString, Base64.DEFAULT), Charset.forName("UTF-8")).trim()

/**
 * Read text from assets file.
 * @param context App context.
 * @param path File path to read in assets directory with format: "folder_name/file_name.json".
 */
fun readTextFromAsset(context: Context, path: String): String? {
    val json: String?
    try {
        val inputStream: InputStream = context.assets.open(path)
        json = inputStream.bufferedReader().use { it.readText() }
    } catch (ex: Exception) {
        ex.printStackTrace()
        return null
    }
    return json
}

/**
 * Ask the Download Manager to download a file from an URL.
 * @param context App context.
 * @param downloadURL Url to download apk file.
 * @param title Download manager notification title.
 * @param description Download manager notification description.
 */
fun downloadFile(context: Context, downloadURL: String, title: String, description: String?) {
    val request = DownloadManager.Request(Uri.parse(downloadURL))
        .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        .setTitle(title)
        .setDescription(description)
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title)
    request.allowScanningByMediaScanner()
    val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    dm.enqueue(request)
}

fun appPackageName(activity: Activity): String =
    activity.packageManager.getPackageInfo(activity.packageName, 0).versionName