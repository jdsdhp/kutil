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

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Environment
import android.util.Base64
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

fun bitmapToBase64(bitmap: Bitmap): String? =
    try {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val b = stream.toByteArray()
        Base64.encodeToString(b, Base64.DEFAULT)
    } catch (e: Exception) {
        null
    }

fun base64ToBitmap(avatarBase64: String?): Bitmap? =
    if (avatarBase64 != null) try {
        val decodedString = Base64.decode(avatarBase64, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    } catch (e: Exception) {
        null
    } else null

fun areDrawablesIdentical(drawableA: Drawable, drawableB: Drawable): Boolean {
    val stateA = drawableA.constantState
    val stateB = drawableB.constantState
    // If the constant state is identical, they are using the same drawable resource.
    // However, the opposite is not necessarily true.
    return stateA != null && stateA == stateB || drawableToBitmap(drawableA).sameAs(
        drawableToBitmap(drawableB)
    )
}

fun drawableToBitmap(drawable: Drawable): Bitmap {
    val result: Bitmap
    if (drawable is BitmapDrawable) result = drawable.bitmap else {
        var width = drawable.intrinsicWidth
        var height = drawable.intrinsicHeight
        // Some drawables have no intrinsic width - e.g. solid colours.
        if (width <= 0) width = 1
        if (height <= 0) height = 1
        result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
    }
    return result
}

fun bitmapToDrawable(context: Context, @DrawableRes drawableId: Int): Bitmap {
    var drawable = ContextCompat.getDrawable(context, drawableId)
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        drawable = DrawableCompat.wrap(drawable!!).mutate()
    }
    val bitmap = Bitmap.createBitmap(
        drawable!!.intrinsicWidth,
        drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

@Throws(IOException::class)
fun createImageFile(context: Context, imageFileName: String, format: String = "jpg"): File {
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val filePic = File(storageDir, "$imageFileName.$format")
    filePic.createNewFile()
    return filePic
}