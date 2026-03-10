package com.example.wizkids.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.runtime.MutableState
import com.example.wizkids.util.UtilLogicConstant.IMAGE_DIRECTORY_NAME
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageHelper {
    fun saveImageToLocalStorage(
        context: Context,
        imageUri: Uri,
        fileName: String,
        value: MutableState<String?>? = null
    ): String? {
        try {
            value?.value?.let { deleteImageByPath(it) }
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)

            val directory = File(context.filesDir, IMAGE_DIRECTORY_NAME)
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

    fun deleteImageByPath(filePath: String): Boolean {
        return try {
            val file = File(filePath)
            if (file.exists()) {
                file.delete()
            } else {
                false
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
            false
        }
    }
}