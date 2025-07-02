
# App Localización y Servicios en Segundo Plano

## Descripción

Esta aplicación Android, desarrollada con **Jetpack Compose**, permite:

- Visualizar un mapa usando Google Maps.
- Solicitar permisos de ubicación.
- Obtener y mostrar la ubicación actual del usuario.
- Iniciar un servicio en segundo plano con notificación persistente.
- Buscar direcciones por texto, visualizar sugerencias y seleccionar en el mapa.

## Tecnologías utilizadas

- Jetpack Compose
- Google Maps Compose
- Location Services (Google Play Services)
- Foreground Service
- Accompanist Permissions
- Kotlin
- Android Studio 2024.3.1

## Estructura del Proyecto

```
app_location_demo/
├── screens/
│   ├── BasicMapScreen.kt
│   ├── PermissionMapScreen.kt
│   ├── RealLocationMapScreen.kt
│   ├── SearchMapScreen.kt
├── services/
│   └── LocationService.kt
├── MainActivity.kt
└── AndroidManifest.xml
```

## Configuración previa

1. Crear un proyecto en Google Cloud Platform.
2. Habilitar las siguientes APIs:
   - Maps SDK for Android
   - Places API (opcional)
3. Obtener una API KEY.
4. Colocar la API KEY en AndroidManifest.xml.

## Permisos requeridos

Declarados en AndroidManifest.xml:

```
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE_LOCATION" />
```

## Pasos para ejecutar y probar la app

### 1. Compilar y ejecutar

- Abre el proyecto en Android Studio 2024.3.1.
- Ejecuta en un emulador o dispositivo real.
- En el emulador, establece una ubicación manual en Extended Controls.

### 2. Menú de prueba

- Versión 1: Mapa básico.
- Versión 2: Mapa con permisos.
- Versión 3: Ubicación actual.
- Versión 4: Buscar dirección (sugerencias y selección).
- Iniciar Servicio de Ubicación.
- Detener Servicio de Ubicación.

## Uso del emulador para probar ubicación

1. Abre Extended Controls.
2. En Location, puedes:
   - Enviar una ubicación puntual.
   - Simular movimiento.

## Generación del APK

- Build > Build Bundle(s) / APK(s) > Build APK(s).
- El APK estará en `app/build/outputs/apk/debug/app-debug.apk`.

Para un APK firmado:
- Build > Generate Signed Bundle / APK.

## Notas

- Requiere conexión a internet para los mapas.
- En dispositivos reales, se deben otorgar permisos de ubicación.
- Puede integrarse Places API para mejorar sugerencias.

## Autor

Proyecto educativo para el tema de Servicios basados en localización y generación de APK.
