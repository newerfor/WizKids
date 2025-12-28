package com.example.wizkids.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageSaveHelper {
    fun saveImageToLocalStorage(
        context: Context,
        imageUri: Uri,
        fileName: String
    ): String? {
        try {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)

            val directory = File(context.filesDir, "images")
            if (!directory.exists()) {
                directory.mkdirs()
            }

            val file = File(directory, fileName)
            FileOutputStream(file).use { fos ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            }

            return file.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
}