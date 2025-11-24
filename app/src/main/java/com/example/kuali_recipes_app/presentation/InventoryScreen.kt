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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
fun InventoryScreen(
    viewModel: InventoryViewModel,
    showDialog: Boolean,
    onDismissDialog: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    // Estado para controlar la visibilidad del diálogo
    var showAddDialog by remember { mutableStateOf(false) }

    val darkPurple = Color(0xFF2D2A47)
    val greenIcon = Color(0xFF4CAF50)

    // Colores para el diálogo
    val dialogContainerColor = Color(0xFF252836) // Un gris oscuro azulado

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0F1817),
            Color(0xFF1A1614),
            Color(0xFF132223),
        )
    )

    // 1. CAMBIO IMPORTANTE: Usar mutableStateListOf para poder agregar items dinámicamente
    val inventoryItems = viewModel.inventoryItems
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
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            },

            floatingActionButton = {
                FloatingActionButton(
                    onClick = { showAddDialog = true },
                    containerColor = Color(0xFF4FFFCD), // Tu color verde menta
                    contentColor = Color.Black
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar item")
                }
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
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Buscar...", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White.copy(alpha = 0.08f),
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White, // Corrección visual
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))


                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(inventoryItems) { item ->
                        InventoryItemCard(item)
                    }
                    item { Spacer(modifier = Modifier.height(80.dp)) } // Espacio para que el FAB no tape el último item
                }
            }
        }


        if (showDialog) {
            AddInventoryItemDialog(
                onDismiss = onDismissDialog,
                onConfirm = { name, quantity, days ->
                    viewModel.addItem(name, quantity, days) // Llamamos al ViewModel
                    onDismissDialog()
                },
                containerColor = Color(0xFF252836)
            )
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

@Composable
fun AddInventoryItemDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, String) -> Unit,    containerColor: Color
) {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var daysToExpiry by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = containerColor,
        title = {
            Text("Nuevo Producto", color = Color.White, fontWeight = FontWeight.Bold)
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                // Campo Nombre
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre del producto", color = Color.Gray) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF4FFFCD),
                        unfocusedBorderColor = Color.Gray
                    )
                )

                // Campo Cantidad
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Cantidad (ej. 2 litros)", color = Color.Gray) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF4FFFCD),
                        unfocusedBorderColor = Color.Gray
                    )
                )

                // Campo Días para vencer
                OutlinedTextField(
                    value = daysToExpiry,
                    onValueChange = { if (it.all { char -> char.isDigit() }) daysToExpiry = it },
                    label = { Text("Días para vencer", color = Color.Gray) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF4FFFCD),
                        unfocusedBorderColor = Color.Gray
                    )
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotEmpty() && quantity.isNotEmpty()) {
                        onConfirm(name, quantity, daysToExpiry)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4FFFCD),
                    contentColor = Color.Black
                )
            ) {
                Text("Agregar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = Color.White.copy(alpha = 0.7f))
            }
        }
    )
}


