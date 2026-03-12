package com.example.wizkids.presentation.UserProfile.ui

import android.content.ContentValues
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.graphics.toColorInt
import com.example.core_domain.model.DomainUserModel
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class PDFDownloader(private val context: Context) {

    fun createAndDownloadPDF(userInfo: DomainUserModel) {
        try {
            val document = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
            val page = document.startPage(pageInfo)
            val canvas = page.canvas
            drawBackground(canvas)
            drawResumeContent(canvas, userInfo)
            document.finishPage(page)
            val fileName = "Резюме_${sanitizeFileName(userInfo.name)}.pdf"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                savePDFWithMediaStore(document, fileName)
            } else {
                savePDFLegacy(
                    document, fileName
                )
            }
            document.close()
            showToast("Резюме успешно сохранено в папке Загрузки")
        } catch (e: Exception) {
            showToast("Ошибка при создании резюме: ${e.message}")
            e.printStackTrace()
        }
    }

    private fun drawBackground(canvas: Canvas) {
        // Градиентный фон
        val gradient = LinearGradient(
            0f, 0f, 595f, 842f, Color.parseColor("#FFFFFF"), // Белый
            Color.parseColor("#F8F9FA"), // Светло-серый
            Shader.TileMode.CLAMP
        )

        val backgroundPaint = Paint().apply {
            shader = gradient
        }
        canvas.drawRect(0f, 0f, 595f, 842f, backgroundPaint)
        val stripePaint = Paint().apply {
            color = "#4A6FA5".toColorInt() // Синий
        }
        canvas.drawRect(0f, 0f, 40f, 842f, stripePaint)
    }

    private fun drawResumeContent(canvas: Canvas, user: DomainUserModel) {
        var currentY = 70f
        val titlePaint = Paint().apply {
            color = "#2C3E50".toColorInt() // Темно-синий
            textSize = 32f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("РЕЗЮМЕ", 297f, currentY, titlePaint)

        currentY += 50f

        // === ЛИНИЯ ПОД ЗАГОЛОВКОМ ===
        val linePaint = Paint().apply {
            color = "#4A6FA5".toColorInt()
            strokeWidth = 2f
        }
        canvas.drawLine(50f, currentY, 545f, currentY, linePaint)

        currentY += 40f

        // === БЛОК ДЛЯ ФОТО ===
        drawPhotoPlaceholder(canvas, user, currentY)

        // === ОСНОВНАЯ ИНФОРМАЦИЯ (справа от фото) ===
        val infoStartX = 180f
        var infoY = currentY

        // Заголовок "Личная информация"
        val sectionTitlePaint = Paint().apply {
            color = "#2C3E50".toColorInt()
            textSize = 16f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isAntiAlias = true
        }
        canvas.drawText("ЛИЧНАЯ ИНФОРМАЦИЯ", infoStartX, infoY, sectionTitlePaint)

        infoY += 25f

        val labelPaint = Paint().apply {
            color = "#34495E".toColorInt() // Темно-серый
            textSize = 12f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isAntiAlias = true
        }

        val valuePaint = Paint().apply {
            color = "#2C3E50".toColorInt() // Темно-синий
            textSize = 12f
            typeface = Typeface.DEFAULT
            isAntiAlias = true
        }
        canvas.drawText("ФИО:", infoStartX, infoY, labelPaint)
        canvas.drawText(user.name, infoStartX + 60f, infoY, valuePaint)
        infoY += 20f
        canvas.drawText("Дата рождения:", infoStartX, infoY, labelPaint)
        canvas.drawText(user.dateOfBirth, infoStartX + 120f, infoY, valuePaint)
        infoY += 20f
        canvas.drawText("Опыт работы:", infoStartX, infoY, labelPaint)
        val experienceText = "${user.workExperience}}"
        canvas.drawText(experienceText, infoStartX + 100f, infoY, valuePaint)
        infoY += 30f
        canvas.drawText("КОНТАКТЫ", infoStartX, infoY, sectionTitlePaint)
        infoY += 25f
        canvas.drawText("Телефон:", infoStartX, infoY, labelPaint)
        canvas.drawText(formatPhone(user.phone), infoStartX + 80f, infoY, valuePaint)
        infoY += 20f
        canvas.drawText("Email:", infoStartX, infoY, labelPaint)
        canvas.drawText(user.email, infoStartX + 60f, infoY, valuePaint)
        infoY += 30f
        canvas.drawText("ОБРАЗОВАНИЕ", infoStartX, infoY, sectionTitlePaint)
        infoY += 25f
        canvas.drawText("Уровень:", infoStartX, infoY, labelPaint)
        canvas.drawText(user.educationLevel, infoStartX + 80f, infoY, valuePaint)
        infoY += 20f
        canvas.drawText("Специализация:", infoStartX, infoY, labelPaint)
        val specializationLines = breakTextIntoLines(user.specialization, valuePaint, 200f)
        var specY = infoY
        specializationLines.forEach { line ->
            canvas.drawText(line, infoStartX + 120f, specY, valuePaint)
            specY += 15f
        }
        val separatorY = infoY.coerceAtLeast(specY) + 40f
        canvas.drawLine(50f, separatorY, 545f, separatorY, linePaint)
        val aboutStartY = separatorY + 40f
        canvas.drawText("О СЕБЕ", 50f, aboutStartY, Paint().apply {
            color = Color.parseColor("#2C3E50")
            textSize = 18f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isAntiAlias = true
        })
        val aboutBgPaint = Paint().apply {
            color = Color.parseColor("#F1F5F9") // Очень светлый синий
            style = Paint.Style.FILL
        }
        val aboutTextStartY = aboutStartY + 30f
        val aboutRect = RectF(50f, aboutTextStartY - 15f, 545f, aboutTextStartY + 130f)
        canvas.drawRoundRect(aboutRect, 8f, 8f, aboutBgPaint)
        val aboutPaint = Paint().apply {
            color = Color.parseColor("#2C3E50")
            textSize = 12f
            typeface = Typeface.DEFAULT
            isAntiAlias = true
        }
        val aboutText = user.about
        val aboutLines = breakTextIntoLines(aboutText, aboutPaint, 470f)
        var aboutY = aboutTextStartY
        aboutLines.forEach { line ->
            canvas.drawText(line, 60f, aboutY, aboutPaint)
            aboutY += 16f
            if (aboutY > aboutRect.bottom - 10f) {
                if (aboutLines.indexOf(line) < aboutLines.size - 1) {
                    canvas.drawText("...", 60f, aboutY, aboutPaint)
                }
                return@forEach
            }
        }
        val skillsStartY = aboutY + 40f
        if (skillsStartY < 700f) {
            canvas.drawText("НАВЫКИ И КОМПЕТЕНЦИИ", 50f, skillsStartY, Paint().apply {
                color = "#2C3E50".toColorInt()
                textSize = 18f
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                isAntiAlias = true
            })
            val skillsY = skillsStartY + 30f
            val skills = listOf(
                "Работа с детьми",
                "Методическая работа",
                "Планирование занятий",
                "Взаимодействие с родителями",
                "Развивающие методики",
                "Дошкольная педагогика"
            )
            drawSkills(canvas, skills, 50f, skillsY)
        }
        val footerPaint = Paint().apply {
            color = "#7F8C8D".toColorInt() // Серый
            textSize = 10f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        canvas.drawText("Резюме создано: $currentDate", 297f, 820f, footerPaint)
    }

    private fun drawPhotoPlaceholder(canvas: Canvas, user: DomainUserModel, startY: Float) {
        val photoX = 50f
        val photoY = startY
        val photoSize = 120f
        val framePaint = Paint().apply {
            color = "#4A6FA5".toColorInt()
            style = Paint.Style.STROKE
            strokeWidth = 3f
        }
        val photoRect = RectF(photoX, photoY, photoX + photoSize, photoY + photoSize)
        val bgPaint = Paint().apply {
            color = "#E8F4FD".toColorInt() // Светло-голубой
            style = Paint.Style.FILL
        }
        canvas.drawRoundRect(photoRect, 8f, 8f, bgPaint)
        canvas.drawRoundRect(photoRect, 8f, 8f, framePaint)
        val textPaint = Paint().apply {
            color = Color.parseColor("#7F8C8D")
            textSize = 10f
            typeface = Typeface.DEFAULT
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }

        canvas.drawText(
            "ФОТО", photoX + photoSize / 2, photoY + photoSize / 2, textPaint
        )
        if (user.imagePath.isNotEmpty()) {
            val cameraPaint = Paint().apply {
                color = Color.parseColor("#4A6FA5")
                textSize = 18f
                typeface = Typeface.DEFAULT_BOLD
                textAlign = Paint.Align.CENTER
            }
            canvas.drawText(
                "📷", photoX + photoSize / 2, photoY + photoSize / 2 - 20f, cameraPaint
            )
            canvas.drawText(
                "(доступно онлайн)",
                photoX + photoSize / 2,
                photoY + photoSize + 15f,
                Paint().apply {
                    color = "#3498DB".toColorInt()
                    textSize = 8f
                    typeface = Typeface.DEFAULT
                })
        }
    }

    private fun drawSkills(canvas: Canvas, skills: List<String>, startX: Float, startY: Float) {
        var currentX = startX
        var currentY = startY
        val skillPaint = Paint().apply {
            color = Color.WHITE
            textSize = 10f
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            isAntiAlias = true
        }
        val skillBgPaint = Paint().apply {
            color = "#4A6FA5".toColorInt() // Синий
            style = Paint.Style.FILL
        }
        val padding = 12f
        val verticalPadding = 6f
        val horizontalSpacing = 15f
        val verticalSpacing = 35f
        skills.forEach { skill ->
            val textBounds = Rect()
            skillPaint.getTextBounds(skill, 0, skill.length, textBounds)
            val textWidth = skillPaint.measureText(skill)
            if (currentX + textWidth + padding * 2 > 545f) {
                currentX = startX
                currentY += verticalSpacing
            }
            val rect = RectF(
                currentX - padding,
                currentY - textBounds.height() - verticalPadding,
                currentX + textWidth + padding,
                currentY + verticalPadding
            )
            canvas.drawRoundRect(rect, 15f, 15f, skillBgPaint)
            canvas.drawText(skill, currentX, currentY, skillPaint)
            currentX += textWidth + padding * 2 + horizontalSpacing
        }
    }

    private fun getExperienceWord(years: Int): String {
        return when {
            years % 10 == 1 && years % 100 != 11 -> "год"
            years % 10 in 2..4 && years % 100 !in 12..14 -> "года"
            else -> "лет"
        }
    }

    private fun formatPhone(phone: String): String {
        return try {
            val cleanPhone = phone.replace(Regex("[^0-9+]"), "")
            if (cleanPhone.length >= 12) {
                "${cleanPhone.substring(0, 4)} (${
                    cleanPhone.substring(
                        4, 6
                    )
                }) ${cleanPhone.substring(6, 9)}-${
                    cleanPhone.substring(
                        9, 11
                    )
                }-${cleanPhone.substring(11)}"
            } else {
                phone
            }
        } catch (e: Exception) {
            phone
        }
    }

    private fun breakTextIntoLines(text: String, paint: Paint, maxWidth: Float): List<String> {
        val lines = mutableListOf<String>()
        val words = text.split(" ")
        var currentLine = StringBuilder()

        for (word in words) {
            val testLine = if (currentLine.isEmpty()) word else "$currentLine $word"
            val width = paint.measureText(testLine)

            if (width <= maxWidth) {
                if (currentLine.isNotEmpty()) {
                    currentLine.append(" ")
                }
                currentLine.append(word)
            } else {
                if (currentLine.isNotEmpty()) {
                    lines.add(currentLine.toString())
                }
                currentLine = StringBuilder(word)
            }
        }

        if (currentLine.isNotEmpty()) {
            lines.add(currentLine.toString())
        }

        return lines
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun savePDFWithMediaStore(document: PdfDocument, fileName: String) {
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        }

        val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let {
            val outputStream: OutputStream? = resolver.openOutputStream(it)
            outputStream?.use { stream ->
                document.writeTo(stream)
            }
        }
    }

    private fun savePDFLegacy(document: PdfDocument, fileName: String) {
        val downloadsDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS
        )
        if (!downloadsDir.exists()) {
            downloadsDir.mkdirs()
        }
        val file = File(downloadsDir, fileName)
        val outputStream = FileOutputStream(file)
        document.writeTo(outputStream)
        outputStream.close()
    }

    private fun sanitizeFileName(name: String): String {
        return name.replace(" ", "_").replace("[^a-zA-ZА-Яа-я0-9._-]".toRegex(), "")
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}