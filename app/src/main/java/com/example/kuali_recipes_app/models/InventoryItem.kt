package com.example.kuali_recipes_app.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalDate

data class InventoryItem(
    val name: String,
    val quantity: String,
    val expiryDate: LocalDate,
    val icon: ImageVector,
    val iconColor: Color = Color(0xFF4CAF50)
)
