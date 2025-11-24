package com.example.kuali_recipes_app.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kuali_recipes_app.models.InventoryItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen() {
    var searchQuery by remember { mutableStateOf("") }

    val darkPurple = Color(0xFF2D2A47)
    val cardBackground = Color(0xFF3D3A57)
    val greenIcon = Color(0xFF4CAF50)
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0F1817),
            Color(0xFF1A1614),
            Color(0xFF132223),
        )
    )

    val inventoryItems = remember {
        listOf(
            InventoryItem(
                name = "Huevos",
                quantity = "12 unidades",
                expiryDate = LocalDate.now().plusDays(3),
                icon = Icons.Default.Check,
                iconColor = greenIcon
            ),
            InventoryItem(
                name = "Pechuga de Pollo",
                quantity = "500 gr",
                expiryDate = LocalDate.now(),
                icon = Icons.Default.Check,
                iconColor = greenIcon
            ),
            InventoryItem(
                name = "Leche de Almendras",
                quantity = "1 Litro",
                expiryDate = LocalDate.of(2024, 12, 25),
                icon = Icons.Default.Check,
                iconColor = greenIcon
            ),
            InventoryItem(
                name = "Tomates",
                quantity = "6 unidades",
                expiryDate = LocalDate.now().minusDays(1),
                icon = Icons.Default.Check,
                iconColor = greenIcon
            ),
            InventoryItem(
                name = "Jugo de Naranja",
                quantity = "750 ml",
                expiryDate = LocalDate.now().plusDays(15),
                icon = Icons.Default.Check,
                iconColor = greenIcon
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Mi Inventario",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    actions = {
                        IconButton(onClick = { /* Ordenar */ }) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = "Ordenar",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            },
            containerColor = Color.Transparent
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Barra de búsqueda
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            "Buscar en inventario...",
                            color = Color.Gray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = Color.Gray
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White.copy(alpha = 0.08f),
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Lista de items
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(inventoryItems) { item ->
                        InventoryItemCard(item)
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun InventoryItemCard(item: InventoryItem) {
    val cardBackground = Color.White.copy(alpha = 0.08f)
    val today = LocalDate.now()
    val daysUntilExpiry = ChronoUnit.DAYS.between(today, item.expiryDate)

    val (expiryText, expiryColor) = when {
        daysUntilExpiry < 0 -> "Venció ayer" to Color(0xFF808080)
        daysUntilExpiry == 0L -> "Vence hoy" to Color(0xFFFF5252)
        daysUntilExpiry <= 3 -> "Vence en $daysUntilExpiry días" to Color(0xFFFFA726)
        daysUntilExpiry <= 15 -> "Vence en $daysUntilExpiry días" to Color(0xFF66BB6A)
        else -> {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yy")
            "Vence el ${item.expiryDate.format(formatter)}" to Color(0xFFAB47BC)
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = cardBackground
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icono
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        item.iconColor.copy(alpha = 0.2f),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    item.icon,
                    contentDescription = null,
                    tint = item.iconColor,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Información del item
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.quantity,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Indicador de vencimiento
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(expiryColor, CircleShape)
                )

                Text(
                    text = expiryText,
                    fontSize = 13.sp,
                    color = expiryColor
                )
            }
        }
    }
}

@Preview
@Composable
private fun InvetoryScreenPreview() {
    InventoryScreen()
}