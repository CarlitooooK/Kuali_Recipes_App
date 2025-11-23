package com.example.kuali_recipes_app.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kuali_recipes_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesScreen() {
    var textInput by remember { mutableStateOf("") }
    var selectedFilters by remember { mutableStateOf(setOf<String>()) }

    val darkBackground = Color(0xFF0A1F1F)
    val primaryGreen = Color(0xFF00D9A3)
    val cardBackground = Color(0xFF1A2F2F)

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0F1817),
            Color(0xFF1A1614),
            Color(0xFF132223),
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        Scaffold(
            containerColor = Color.Transparent
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(50.dp))

                    // Título principal
                    Text(
                        text = "¿Qué tienes en tu nevera?",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        lineHeight = 38.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Subtítulo
                    Text(
                        text = "Introduce ingredientes, tipo de plato o lo que se te ocurra.",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }

                item {
                    // Campo de entrada con botones
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = cardBackground
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Avatar
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF4A5F5F))
                                ) {
                                    Icon(
                                        painterResource(R.drawable.ic_profile),
                                        contentDescription = null
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                // Campo de texto
                                TextField(
                                    value = textInput,
                                    onValueChange = { textInput = it },
                                    placeholder = {
                                        Text(
                                            "El pollo, arroz, 30 minutos...",
                                            color = Color.Gray
                                        )
                                    },
                                    modifier = Modifier.weight(1f),
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent,
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        cursorColor = primaryGreen,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Botones de acción
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Button(
                                    onClick = { /* Generar */ },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = primaryGreen
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        "Generar",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    // Filtros
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip("Vegano", Icons.Default.Call, selectedFilters) {
                            selectedFilters = if (it in selectedFilters) {
                                selectedFilters - it
                            } else {
                                selectedFilters + it
                            }
                        }
                        FilterChip("Rápido", Icons.Default.Call, selectedFilters) {
                            selectedFilters = if (it in selectedFilters) {
                                selectedFilters - it
                            } else {
                                selectedFilters + it
                            }
                        }
                        FilterChip("Postre", Icons.Default.Call, selectedFilters) {
                            selectedFilters = if (it in selectedFilters) {
                                selectedFilters - it
                            } else {
                                selectedFilters + it
                            }
                        }
                    }
                }

                item {
                    // Card de receta 1
                    RecipeCard(
                        title = "Ensalada de Quinoa Fresca",
                        tag1 = "Salud",
                        tag2 = "Fácil",
                        backgroundColor = cardBackground,
                        imageColor = Color(0xFF2D4F2D)
                    )
                }

                item {
                    // Card de receta 2
                    RecipeCard(
                        title = "Pizza Casera Express",
                        tag1 = "25min",
                        tag2 = "Media",
                        backgroundColor = cardBackground,
                        imageColor = Color(0xFF4A3520)
                    )
                }

                item {
                    // Indicador de carga
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp),
                            color = primaryGreen
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            "Buscando recetas...",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun FilterChip(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selectedFilters: Set<String>,
    onToggle: (String) -> Unit
) {
    val isSelected = label in selectedFilters
    val primaryGreen = Color(0xFF00D9A3)

    FilterChip(
        selected = isSelected,
        onClick = { onToggle(label) },
        label = { Text(label) },
        leadingIcon = {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color(0xFF2A3F3F),
            labelColor = Color.White,
            iconColor = Color.White,
            selectedContainerColor = primaryGreen.copy(alpha = 0.2f),
            selectedLabelColor = primaryGreen,
            selectedLeadingIconColor = primaryGreen
        )
    )
}

@Composable
fun RecipeCard(
    title: String,
    tag1: String,
    tag2: String,
    backgroundColor: Color,
    imageColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            // Imagen placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(imageColor)
            )

            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(tag1, color = Color.Gray, fontSize = 12.sp)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(tag2, color = Color.Gray, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun RecipesScreenPreview() {
    RecipesScreen()
}