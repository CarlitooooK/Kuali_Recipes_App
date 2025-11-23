@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.kuali_recipes_app.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import com.example.kuali_recipes_app.R
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen() {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0F1817),
            Color(0xFF1A1614),
            Color(0xFF132223),
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(40.dp))
            HeaderSection()
            Spacer(Modifier.height(24.dp))
            RecentlyAddedSection()
            Spacer(Modifier.height(24.dp))
            LastCreationsSection()
            Spacer(Modifier.height(24.dp))
            ExpiringSoonSection()
            Spacer(Modifier.height(100.dp))
        }
    }
}

@Composable
fun HeaderSection() {
    Text(
        text = "Hola, Usuario!",
        style = MaterialTheme.typography.titleLarge
    )
    Text(
        text = "¿Qué cocinamos hoy?",
        style = MaterialTheme.typography.headlineSmall
    )

    Spacer(Modifier.height(16.dp))

    var query by remember { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = { query = it },
        placeholder = { Text("Buscar ingredientes o recetas") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White.copy(alpha = 0.08f),
            focusedTextColor = Color.White,
            focusedPlaceholderColor = Color.White.copy(alpha = 0.6f),
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(999.dp))
    )
}

data class IngredientCard(
    val name: String,
    val subtitle: String,
    val imageRes: Int? = null
)

@Composable
fun RecentlyAddedSection() {
    Text(
        text = "Añadidos recientemente",
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    )

    Spacer(Modifier.height(12.dp))

    val items = listOf(
        IngredientCard("Tomates", "2 unidades"),
        IngredientCard("Aguacate", "1 unidad"),
        IngredientCard("Pollo", "500g")
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { item ->
            GlassCard(
                modifier = Modifier
                    .width(120.dp)
                    .height(150.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Si quieres usar imagen real coloca un drawable y úsalo aquí
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item.name.take(1),
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column {
                        Text(
                            text = item.name,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = item.subtitle,
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

data class RecipeCard(
    val title: String,
    val time: String,
    val imageRes: Int? = null
)

@Composable
fun LastCreationsSection() {
    Text(
        text = "Tus últimas creaciones",
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    )
    Spacer(Modifier.height(12.dp))

    val recipes = listOf(
        RecipeCard("Ensalada de Quinoa", "Tiempo: 20 min"),
        RecipeCard("Pizza Casera", "Tiempo: 45 min")
    )

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        recipes.forEach { recipe ->
            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White.copy(alpha = 0.12f))
                    ) {
                        // Si tienes drawable, úsalo con Image(...)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = recipe.title,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = recipe.time,
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 13.sp
                        )
                    }
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.7f),
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}

data class ExpiringItem(
    val name: String,
    val detail: String,
    val quantity: String,
    val color: Color
)

@Composable
fun ExpiringSoonSection() {
    Text(
        text = "¡Aprovéchalos pronto!",
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    )
    Spacer(Modifier.height(12.dp))

    val items = listOf(
        ExpiringItem(
            name = "Leche",
            detail = "Caduca en 2 días",
            quantity = "1 litro",
            color = Color(0xFFEF4444) // rojo
        ),
        ExpiringItem(
            name = "Yogurt",
            detail = "Caduca en 5 días",
            quantity = "2 unidades",
            color = Color(0xFFF59E0B) // amarillo
        ),
        ExpiringItem(
            name = "Yogurt",
            detail = "Caduca en 5 días",
            quantity = "2 unidades",
            color = Color(0xFFF59E0B) // amarillo
        ),
    )

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.06f))
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(999.dp))
                        .background(item.color)
                )
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.name,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = item.detail,
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 13.sp
                    )
                }
                Text(
                    text = item.quantity,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.08f))
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.18f),
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        content()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        Surface {
            HomeScreen()
        }
    }
}
