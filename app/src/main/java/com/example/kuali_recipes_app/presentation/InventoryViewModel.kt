package com.example.kuali_recipes_app.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Kitchen
import androidx.lifecycle.ViewModel
import com.example.kuali_recipes_app.models.InventoryItem
import java.time.LocalDate

class InventoryViewModel : ViewModel() {

    private val _inventoryItems = mutableStateListOf<InventoryItem>()
    val inventoryItems: List<InventoryItem> get() = _inventoryItems

    init {

        _inventoryItems.add(
            InventoryItem("Huevos", "12 unidades", LocalDate.now().plusDays(3), Icons.Filled.Egg, Color(0xFF4CAF50))
        )
        _inventoryItems.add(
            InventoryItem("Jugo", "750 ml", LocalDate.now().plusDays(15), Icons.Filled.LocalDrink, Color(0xFF4CAF50))
        )
    }

    fun addItem(name: String, quantity: String, days: String) {
        val newItem = InventoryItem(
            name = name,
            quantity = quantity,
            expiryDate = LocalDate.now().plusDays(days.toLongOrNull() ?: 7),
            icon = Icons.Default.Kitchen,
            iconColor = Color(0xFF4FFFCD)
        )
        _inventoryItems.add(0, newItem)
    }
}


