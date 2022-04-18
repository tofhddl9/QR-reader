package com.lgtm.qr_reader.utils

import android.util.Patterns

fun String?.isValidWebUrl(): Boolean {
    if (this?.startsWith("http") != true) {
        return false
    }
    return Patterns.WEB_URL.matcher(this).matches()
}