package edu.pe.cibertec

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import edu.pe.cibertec.screens.BasicMapScreen
import edu.pe.cibertec.screens.PermissionMapScreen
import edu.pe.cibertec.screens.RealLocationMapScreen
import edu.pe.cibertec.ui.theme.App_location_demoTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.pe.cibertec.screens.SearchMapScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_location_demoTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf<MapVersion?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Selecciona una versión para probar:", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { currentScreen = MapVersion.VERSION_1 }) {
            Text("Versión 1: Mapa básico")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { currentScreen = MapVersion.VERSION_2 }) {
            Text("Versión 2: Mapa con permisos")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { currentScreen = MapVersion.VERSION_3 }) {
            Text("Versión 3: Ubicación actual")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { currentScreen = MapVersion.VERSION_4 }) {
            Text("Versión 4: Buscar dirección")
        }
        Spacer(modifier = Modifier.height(24.dp))
        when (currentScreen) {
            MapVersion.VERSION_1 -> BasicMapScreen()
            MapVersion.VERSION_2 -> PermissionMapScreen()
            MapVersion.VERSION_3 -> RealLocationMapScreen()
            MapVersion.VERSION_4 -> SearchMapScreen()
            null -> {}
        }
    }
}
enum class MapVersion {
    VERSION_1, VERSION_2, VERSION_3, VERSION_4
}