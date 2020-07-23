⚒ KUtil
=======

Utility pack to simplify application development.

[![JitPack](https://jitpack.io/v/jdsdhp/kutil.svg)](https://jitpack.io/#jdsdhp/kutil) 
[![API](https://img.shields.io/badge/API-17%2B-red.svg?style=flat)](https://android-arsenal.com/api?level=17) 
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/jdsdhp/kutil/blob/master/LICENSE) 
[![Twitter](https://img.shields.io/badge/Twitter-@jdsdhp-9C27B0.svg)](https://twitter.com/jdsdhp)

## Including in your project

#### Gradle

```gradle
allprojects  {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
dependencies {
    implementation 'com.github.jdsdhp:kutil:$version'
}
```

## Utilities Pack
#### Alert
* alertDialog
* dialogBuilder
* choiceDialogBuilder
* multiChoiceDialogBuilder
#### Date (In progress)
* shortDateToLongDate
#### Image
* bitmapToBase64
* base64ToBitmap
* areDrawablesIdentical
* drawableToBitmap
* bitmapToDrawable
* createImageFile
#### Intent
* sendEmail
* openWebPage
* downloadFileThroughWeb
* createAlarm
* startTimer
* addEventToCalendar
* dialPhoneNumber
* openWifiSettings
* composeMmsMessage
#### Navigation (In progress)
* replaceFragment
* addFragment
* removeFragment
* navToActivity
#### Network
* hasNetwork
#### General 
* Util constants
* log
* requestDialogPermissions
* base64Encode
* base64Decode
* base64EncodeUTF8
* base64DecodeUTF8
* readTextFromAsset
* downloadFile
* appPackageName
#### Listeners
* OnLoadListener
#### Animations (Not Yet)

License
=======

    Copyright (c) 2020 jesusd0897.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
