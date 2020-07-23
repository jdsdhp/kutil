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
import android.content.Intent
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

const val EXTRA_ID_TAG = "extra_id"
const val EXTRA_ITEM_TAG = "extra_item"
const val EXTRA_ITEMS_TAG = "extra_items"
const val EXTRA_POSITION_TAG = "position_extra"
const val EXTRA_RESULT = "extra_result"

fun replaceFragment(
    fm: FragmentManager,
    fragment: Fragment,
    @IdRes containerViewId: Int,
    transition: Int = FragmentTransaction.TRANSIT_FRAGMENT_FADE
) = fm.beginTransaction().setTransition(transition).replace(containerViewId, fragment).commit()

fun addFragment(
    fm: FragmentManager,
    fragment: Fragment,
    @IdRes containerViewId: Int,
    transition: Int = FragmentTransaction.TRANSIT_FRAGMENT_FADE
) = fm.beginTransaction().setTransition(transition).add(containerViewId, fragment).commit()

fun removeFragment(
    fm: FragmentManager,
    fragment: Fragment,
    @IdRes containerViewId: Int,
    transition: Int = FragmentTransaction.TRANSIT_FRAGMENT_FADE
) = fm.beginTransaction().setTransition(transition).remove(fragment).commit()

fun navToActivity(context: Context, clazz: Class<*>) =
    context.startActivity(Intent(context, clazz))
