package com.ashish.ignitebookapp.Util

/**
 * Created by Ashish Kr on 02,July,2025
 */
fun getBookUrl(formats: Map<String, String>): String? {
    val preferredTypes = listOf("text/html", "application/pdf", "text/plain")
    return preferredTypes.firstNotNullOfOrNull { type ->
        formats.entries.firstOrNull {
            it.key.startsWith(type) && !it.value.endsWith(".zip")
        }?.value
    }
}