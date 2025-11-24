package com.example.kuali_recipes_app.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(true) }

    val darkGreen = Color(0xFF0D2020)
    val cardBackground = Color(0xFF1A2F2F)
    val accentGreen = Color(0xFF00D9A3)

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            darkGreen,
            Color(0xFF121215),
            Color(0xFF151524),
            Color(0xFF111315),
            Color(0xFF231719),
            Color(0xFF1A1415),
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Foto de perfil con indicador online
            Box(
                contentAlignment = Alignment.BottomEnd
            ) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF4A6B6B)),
                    contentAlignment = Alignment.Center
                ) {
                    // Placeholder para imagen
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Foto de perfil",
                        modifier = Modifier.size(60.dp),
                        tint = Color(0xFF7A9A9A)
                    )
                }

                // Indicador online
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .border(3.dp, darkGreen, CircleShape)
                        .background(accentGreen, CircleShape)
                ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Editar",
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.Center),
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre
            Text(
                text = "Carlo Cabrera",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Username
            Text(
                text = "cabreracarlo772@gmail.com",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Opciones de menú
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Recetas Favoritas
                MenuOption(
                    icon = Icons.Default.Favorite,
                    iconBackground = Color(0xFF6B3F9B),
                    title = "Recetas Favoritas",
                    onClick = { /* Navegar */ }
                )

                // Preferencias Dietéticas
                MenuOption(
                    icon = Icons.Default.Home,
                    iconBackground = Color(0xFF9B6B3F),
                    title = "Preferencias Dietéticas",
                    onClick = { /* Navegar */ }
                )

                // Notificaciones
                ToggleOption(
                    icon = Icons.Default.Notifications,
                    iconBackground = Color(0xFF3F4F4F),
                    title = "Notificaciones",
                    isChecked = notificationsEnabled,
                    onToggle = { notificationsEnabled = it }
                )

                // Modo Oscuro
                ToggleOption(
                    icon = Icons.Default.MailOutline,
                    iconBackground = Color(0xFF3F4F4F),
                    title = "Modo Oscuro",
                    isChecked = darkModeEnabled,
                    onToggle = { darkModeEnabled = it }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botón Cerrar Sesión
            Button(
                onClick = { /* Cerrar sesión */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4A2020)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "Cerrar Sesión",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6B6B)
                )
            }

            Spacer(modifier = Modifier.height(90.dp))
        }
    }
}

@Composable
fun MenuOption(
    icon: ImageVector,
    iconBackground: Color,
    title: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A2F2F)
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
                    .background(iconBackground, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Título
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )

            // Flecha
            Icon(
                Icons.Default.DateRange,
                contentDescription = "Ir",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ToggleOption(
    icon: ImageVector,
    iconBackground: Color,
    title: String,
    isChecked: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A2F2F)
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
                    .background(iconBackground, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Título
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )

            // Switch
            Switch(
                checked = isChecked,
                onCheckedChange = onToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color.White.copy(alpha = 0.08f),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color(0xFF3F4F4F),
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}