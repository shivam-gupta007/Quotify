package com.app.quotify.feature_quote.domain.use_cases

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import com.esmaeel.shareitlib.getUriFromBitmap

fun copyImageToClipboard(context: Context, bitmap: Bitmap) {

    //TODO(Execute this code only for the devices that have the feature of copy of image to clipboard)

    val imageUri = context.getUriFromBitmap(bitmap)
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData: ClipData = ClipData.newUri(context.contentResolver, "", imageUri)
    clipboardManager.setPrimaryClip(clipData)
}