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

package com.jesusd0897.kutil.model

data class StatusResource(val status: Status, val message: String? = null) {

    enum class Status { IDLE, LOADING, SUCCESS, ERROR, }

    companion object {
        fun success(): StatusResource =
            StatusResource(status = Status.SUCCESS, message = "Success")

        fun error(message: String? = "Error"): StatusResource =
            StatusResource(status = Status.ERROR, message = message)

        fun loading(): StatusResource =
            StatusResource(status = Status.LOADING, message = "Loading")

        fun idle(): StatusResource =
            StatusResource(status = Status.IDLE, message = "Idle")
    }
}

