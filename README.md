# README - MultiGame Android

## Descripción
MultiGame es un juego de Android que consta de 10 niveles diferentes, cada uno con mecánicas únicas:

### Niveles:
1. **Simón Dice** - Memoriza y repite secuencias de colores
2. **Atrapa Estrellas** - Toca las estrellas que caen antes de que desaparezcan
3. **Puzzle Deslizante** - Resuelve el puzzle de números del 1 al 8
4. **Memoria de Cartas** - Encuentra los pares de cartas iguales
5. **Laberinto** - Navega por el laberinto hasta la meta
6. **Emparejar Colores** - Selecciona el color correcto a contrarreloj
7. **Trivia** - Responde correctamente las preguntas de cultura general
8. **Timing Perfecto** - Toca en el momento preciso cuando la barra esté en la zona verde
9. **Esquiva Obstáculos** - Mueve el jugador para evitar los obstáculos
10. **Patrón de Bloqueo** - Memoriza y repite el patrón mostrado

## Requisitos
- Android Studio Arctic Fox o superior
- JDK 8 o superior
- Android SDK API 24 o superior

## Compilación

### Opción 1: Con Android Studio
1. Abre el proyecto en Android Studio
2. Espera a que Gradle sincronice
3. Ve a Build > Build Bundle(s) / APK(s) > Build APK(s)
4. El APK se generará en `app/build/outputs/apk/debug/`

### Opción 2: Desde línea de comandos
1. Abre PowerShell en la carpeta del proyecto
2. Ejecuta: `.\build-apk.ps1`

### Opción 3: Manualmente con Gradle
```powershell
# Generar wrapper (si no existe)
gradle wrapper

# Compilar
.\gradlew.bat assembleDebug
```

## Instalación
1. Transfiere el APK a tu dispositivo Android
2. Habilita "Orígenes desconocidos" en Configuración > Seguridad
3. Instala el APK

## Características
- 10 niveles únicos con diferentes mecánicas de juego
- Sistema de progresión: desbloquea niveles al completar el anterior
- Interfaz intuitiva y colorida
- Guardado automático del progreso

## Estructura del Proyecto
```
app/
├── src/main/
│   ├── java/com/antigravity/multigame/
│   │   ├── MainActivity.kt
│   │   ├── ProgressManager.kt (dentro de MainActivity.kt)
│   │   └── levels/
│   │       ├── Level1SimonActivity.kt
│   │       ├── Level2CatchStarsActivity.kt
│   │       ├── Level3SlidePuzzleActivity.kt
│   │       ├── Level4MemoryCardsActivity.kt
│   │       ├── Level5MazeActivity.kt
│   │       ├── Level6ColorMatchActivity.kt
│   │       ├── Level7QuizActivity.kt
│   │       ├── Level8TapTimingActivity.kt
│   │       ├── Level9AvoidObstaclesActivity.kt
│   │       └── Level10PatternLockActivity.kt
│   ├── res/
│   │   ├── layout/ (layouts de cada actividad)
│   │   ├── values/ (colores, strings, themes)
│   │   └── mipmap/ (iconos del launcher)
│   └── AndroidManifest.xml
└── build.gradle
```

## Notas de Desarrollo
- Desarrollado en Kotlin
- Utiliza Material Components
- Compatible con Android 7.0 (API 24) y superior
- Orientación forzada a vertical para mejor experiencia de juego

---
Desarrollado por Antigravity
