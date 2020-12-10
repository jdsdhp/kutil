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
import android.content.DialogInterface
import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun alertDialog(
    context: Context,
    @StringRes title: Int? = null, @StringRes message: Int? = null,
    @StringRes positiveText: Int? = null, @StringRes negativeText: Int? = null,
    @DrawableRes icon: Int? = null, isCancelable: Boolean? = true,
    positiveListener: DialogInterface.OnClickListener? = null,
    negativeListener: DialogInterface.OnClickListener? = null
): AlertDialog {
    val builder = MaterialAlertDialogBuilder(context)
    isCancelable?.let { builder.setCancelable(it) }
    message?.let { builder.setMessage(it) }
    icon?.let { builder.setIcon(it) }
    title?.let { builder.setTitle(it) }
    positiveText?.let { builder.setPositiveButton(it, positiveListener) }
    negativeText?.let { builder.setNegativeButton(it, negativeListener) }
    return builder.create()
}

fun dialogBuilder(
    context: Context,
    @StringRes title: Int? = null, @StringRes message: Int? = null,
    @StringRes positiveText: Int? = null, @StringRes negativeText: Int? = null,
    @DrawableRes icon: Int? = null, isCancelable: Boolean? = true,
    positiveListener: DialogInterface.OnClickListener? = null,
    negativeListener: DialogInterface.OnClickListener? = null
): MaterialAlertDialogBuilder {
    val builder = MaterialAlertDialogBuilder(context)
    isCancelable?.let { builder.setCancelable(it) }
    message?.let { builder.setMessage(it) }
    icon?.let { builder.setIcon(it) }
    title?.let { builder.setTitle(it) }
    positiveText?.let { builder.setPositiveButton(it, positiveListener) }
    negativeText?.let { builder.setNegativeButton(it, negativeListener) }
    return builder
}

fun choiceDialogBuilder(
    context: Context,
    @StringRes title: Int? = null, @StringRes message: Int? = null,
    @StringRes positiveText: Int? = null, @StringRes negativeText: Int? = null,
    @DrawableRes icon: Int? = null, isCancelable: Boolean? = true,
    positiveListener: DialogInterface.OnClickListener? = null,
    negativeListener: DialogInterface.OnClickListener? = null,
    @ArrayRes items: Int,
    checkedItem: Int,
    clickListener: DialogInterface.OnClickListener? = null
): MaterialAlertDialogBuilder {
    val builder = MaterialAlertDialogBuilder(context)
    isCancelable?.let { builder.setCancelable(it) }
    message?.let { builder.setMessage(it) }
    icon?.let { builder.setIcon(it) }
    title?.let { builder.setTitle(it) }
    positiveText?.let { builder.setPositiveButton(it, positiveListener) }
    negativeText?.let { builder.setNegativeButton(it, negativeListener) }
    builder.setSingleChoiceItems(items, checkedItem, clickListener)
    return builder
}

fun multiChoiceDialogBuilder(
    context: Context,
    @StringRes title: Int? = null, @StringRes message: Int? = null,
    @StringRes positiveText: Int? = null, @StringRes negativeText: Int? = null,
    @DrawableRes icon: Int? = null, isCancelable: Boolean? = true,
    positiveListener: DialogInterface.OnClickListener? = null,
    negativeListener: DialogInterface.OnClickListener? = null,
    @ArrayRes items: Int,
    checkedItems: BooleanArray,
    clickListener: DialogInterface.OnMultiChoiceClickListener? = null
): MaterialAlertDialogBuilder {
    val builder = MaterialAlertDialogBuilder(context)
    isCancelable?.let { builder.setCancelable(it) }
    message?.let { builder.setMessage(it) }
    icon?.let { builder.setIcon(it) }
    title?.let { builder.setTitle(it) }
    positiveText?.let { builder.setPositiveButton(it, positiveListener) }
    negativeText?.let { builder.setNegativeButton(it, negativeListener) }
    return builder.setMultiChoiceItems(items, checkedItems, clickListener)
}

fun progressDialog(
    context: Context, @StringRes title: Int? = null, @StringRes message: Int? = null,
    @DrawableRes icon: Int? = null, isCancelable: Boolean? = true
) = dialogBuilder(
    context, title, message, null, null, icon, isCancelable,
    null, null
).setView(R.layout.loading_layout).create()