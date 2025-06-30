package edu.pe.cibertec.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun BasicMapScreen(){
    val defaultLocation = LatLng(-12.0464,-77.0428)
    val cameraPosition = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(defaultLocation, 12f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState =  cameraPosition
    ){
        Marker(
            state = MarkerState(position = defaultLocation),
            title = "Markee en lima"
        )
    }
}