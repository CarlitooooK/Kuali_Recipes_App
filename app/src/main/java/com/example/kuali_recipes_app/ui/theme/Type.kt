package com.example.kuali_recipes_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.kuali_recipes_app.R

// 1. Definimos la Familia de Fuentes (ya lo tienes)
val Luxora = FontFamily(
    Font(R.font.luxora, FontWeight.Normal),
    Font(R.font.luxora_bold, FontWeight.Bold),
    Font(R.font.luxora_medium, FontWeight.Medium)
)

// 2. Creamos una configuración base para no repetir código
// Esta función toma el estilo por defecto y le aplica tu fuente
fun defaultTextStyle(
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: Int = 16
) = TextStyle(
    fontFamily = Luxora,
    fontWeight = fontWeight,
    fontSize = fontSize.sp,
    lineHeight = (fontSize * 1.5).sp,
    letterSpacing = 0.5.sp
)

// 3. Definimos la Tipografía de Material 3 completa
val Typography = Typography(
    displayLarge = defaultTextStyle(FontWeight.Bold, 57),
    displayMedium = defaultTextStyle(FontWeight.Bold, 45),
    displaySmall = defaultTextStyle(FontWeight.Bold, 36),

    headlineLarge = defaultTextStyle(FontWeight.Bold, 32),
    headlineMedium = defaultTextStyle(FontWeight.Bold, 28),
    headlineSmall = defaultTextStyle(FontWeight.Bold, 24),

    titleLarge = defaultTextStyle(FontWeight.Bold, 32),
    titleMedium = defaultTextStyle(FontWeight.Medium, 16),
    titleSmall = defaultTextStyle(FontWeight.Medium, 14),

    bodyLarge = defaultTextStyle(FontWeight.Normal, 16),
    bodyMedium = defaultTextStyle(FontWeight.Normal, 14),
    bodySmall = defaultTextStyle(FontWeight.Normal, 12),

    labelLarge = defaultTextStyle(FontWeight.Medium, 14),
    labelMedium = defaultTextStyle(FontWeight.Medium, 12),
    labelSmall = defaultTextStyle(FontWeight.Medium, 11)
)
