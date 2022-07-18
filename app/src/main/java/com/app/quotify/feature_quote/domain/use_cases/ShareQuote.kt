package com.app.quotify.feature_quote.domain.use_cases

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import com.esmaeel.shareitlib.getUriFromBitmap

fun shareImage(bitmap: Bitmap, context: Context) {
    val uri = context.getUriFromBitmap(bitmap)

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/*"
    }

    context.startActivity(Intent.createChooser(sendIntent, "Share Quote....."))
}