package com.example.kuali_recipes_app.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.kuali_recipes_app.R
import com.example.kuali_recipes_app.ui.theme.Purple40
import com.example.kuali_recipes_app.ui.theme.PurpleGrey40
import com.rahad.riobottomnavigation.composables.RioBottomNavItemData
import com.rahad.riobottomnavigation.composables.RioBottomNavigation

@Composable
fun RioBottomNav(modifier: Modifier = Modifier) {
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
            BottomNavigationBar(buttons = buttons)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        // Handle the screen content based on the selected index
        ScreenContent(selectedIndex.intValue, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavigationBar(buttons: List<RioBottomNavItemData>) {
    RioBottomNavigation(
        fabIcon = ImageVector.vectorResource(id = R.drawable.ic_google),
        buttons = buttons,
        fabSize = 60.dp,
        barHeight = 60.dp,
        unselectedItemColor = Color.White,
        selectedItemColor = Color(0xFFF66E33),
        fabBackgroundColor = PurpleGrey40,
        backgroundColor = Color(0xFF1C1C1C)
    )
}

@Composable
fun ScreenContent(selectedIndex: Int, modifier: Modifier) {
    when (selectedIndex) {
        0 -> HomeScreen()
        1 -> RecipesScreen()
        2 -> InventoryScreen()
        3 -> HomeScreen()
    }
}