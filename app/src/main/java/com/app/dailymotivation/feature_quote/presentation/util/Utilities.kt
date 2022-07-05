package com.app.dailymotivation.feature_quote.presentation.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Toast
import com.esmaeel.shareitlib.getUriFromBitmap

class Utilities {
    companion object {
        fun shareImage(bitmap: Bitmap, context: Context) {
            val uri = context.getUriFromBitmap(bitmap)

            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri)
                type = "image/*"
            }

            context.startActivity(Intent.createChooser(sendIntent, "Share Quote....."))
        }

        fun displayToast(text: String, context: Context) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }
}

