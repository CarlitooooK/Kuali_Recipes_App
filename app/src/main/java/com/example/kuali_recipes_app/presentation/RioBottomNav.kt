package com.example.kuali_recipes_app.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kuali_recipes_app.R
import com.example.kuali_recipes_app.ui.theme.PurpleGrey40
import com.rahad.riobottomnavigation.composables.RioBottomNavItemData
import com.rahad.riobottomnavigation.composables.RioBottomNavigation

@Composable
fun RioBottomNav(modifier: Modifier = Modifier) {

    // 1. Instanciamos el ViewModel aquí para que sobreviva a los cambios de pantalla
    val inventoryViewModel: InventoryViewModel = viewModel()

    // 2. Elevamos el estado del diálogo aquí (el padre)
    var showAddDialog by remember { mutableStateOf(false) }

    val items = listOf(
        R.drawable.ic_fridge,
        R.drawable.ic_recipes,
        R.drawable.ic_inventory,
        R.drawable.ic_profile,
    )

    val labels = listOf(
        "Home",
        "Recipes",
        "Inventory",
        "Profile",
    )

    val selectedIndex = rememberSaveable { mutableIntStateOf(0) }

    val buttons = items.mapIndexed { index, iconData ->
        RioBottomNavItemData(
            imageVector = ImageVector.vectorResource(iconData),
            selected = index == selectedIndex.intValue,
            onClick = { selectedIndex.intValue = index },
            label = labels[index]
        )
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                buttons = buttons,
                // 3. Pasamos la acción de abrir el diálogo al componente hijo
                onFabClick = {
                    showAddDialog = true
                    selectedIndex.intValue = 2
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        // Handle the screen content based on the selected index
        ScreenContent(
            selectedIndex = selectedIndex.intValue,
            modifier = Modifier.padding(innerPadding),
            inventoryViewModel = inventoryViewModel,
            showDialog = showAddDialog,
            onDismissDialog = { showAddDialog = false }
        )
    }
}

@Composable
fun BottomNavigationBar(
    buttons: List<RioBottomNavItemData>,
    onFabClick: () -> Unit // Recibimos la función como parámetro
) {
    RioBottomNavigation(
        fabIcon = Icons.Default.Add, // Cambié ic_google por un icono de + (Add) que tiene más sentido
        buttons = buttons,
        fabSize = 60.dp,
        barHeight = 60.dp,
        unselectedItemColor = Color.White,
        selectedItemColor = Color(0xFFF66E33),
        fabBackgroundColor = PurpleGrey40,
        backgroundColor = Color(0xFF1C1C1C),
        onFabClick = onFabClick // Ejecutamos la función recibida
    )
}

@Composable
fun ScreenContent(
    selectedIndex: Int,
    modifier: Modifier,
    inventoryViewModel: InventoryViewModel, // Recibimos el VM
    showDialog: Boolean,                    // Recibimos el estado
    onDismissDialog: () -> Unit             // Recibimos la acción de cerrar
) {
    when (selectedIndex) {
        0 -> HomeScreen() // Asegúrate de que HomeScreen acepte modifier si es necesario
        1 -> RecipesScreen()
        2 -> InventoryScreen(
            viewModel = inventoryViewModel,
            showDialog = showDialog,
            onDismissDialog = onDismissDialog
        )
        3 -> ProfileScreen()
    }
}
