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

package com.jesusd0897.kutil.extension

import java.math.RoundingMode

fun Long.roundDecimals(newScale: Int = 1, roundingMode: RoundingMode = RoundingMode.HALF_EVEN) =
        this.toBigDecimal().setScale(newScale, roundingMode).toLong()

fun Double.roundDecimals(newScale: Int = 1, roundingMode: RoundingMode = RoundingMode.HALF_EVEN) =
        this.toBigDecimal().setScale(newScale, roundingMode).toDouble()

fun Float.roundDecimals(newScale: Int = 1, roundingMode: RoundingMode = RoundingMode.HALF_EVEN) =
        this.toBigDecimal().setScale(newScale, roundingMode).toFloat()

fun Int.roundDecimals(newScale: Int = 1, roundingMode: RoundingMode = RoundingMode.HALF_EVEN) =
        this.toBigDecimal().setScale(newScale, roundingMode).toFloat()
