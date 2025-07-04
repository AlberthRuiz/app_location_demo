package edu.pe.cibertec.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RealLocationMapScreen(){
    val permissionState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    if (permissionState.allPermissionsGranted) {
        UserLocationMap()
    } else {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Button(
                onClick = {
                    permissionState.launchMultiplePermissionRequest()
                }
            ) {
                Text("Soliictar Permisos para ubicacion")
            }

        }
    }

}

@Composable
fun UserLocationMap() {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val locationRequest = remember {
        com.google.android.gms.location.LocationRequest.create().apply {
            interval = 5000 // Actualización cada 5 segundos
            fastestInterval = 2000
            priority = com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
        }
    }

    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    val cameraPosition = rememberCameraPositionState()

    LaunchedEffect(Unit) {
        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                object : com.google.android.gms.location.LocationCallback() {
                    override fun onLocationResult(result: com.google.android.gms.location.LocationResult) {
                        result.lastLocation?.let {
                            val latLng = LatLng(it.latitude, it.longitude)
                            userLocation = latLng
                            cameraPosition.position = CameraPosition.fromLatLngZoom(latLng, 15f)
                        }
                    }
                },
                context.mainLooper
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPosition
    ) {
        userLocation?.let { location ->
            Marker(
                state = MarkerState(position = location),
                title = "Mi ubicación"
            )
        }
    }
}
