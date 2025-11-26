# Script para compilar el APK del juego MultiGame
Write-Host "Iniciando compilación del proyecto MultiGame..." -ForegroundColor Green

# Verificar si existe el wrapper de Gradle
if (!(Test-Path ".\gradlew.bat")) {
    Write-Host "Generando Gradle Wrapper..." -ForegroundColor Yellow
    gradle wrapper --gradle-version 8.0
}

# Limpiar el proyecto
Write-Host "Limpiando proyecto..." -ForegroundColor Yellow
.\gradlew.bat clean

# Compilar el APK de debug
Write-Host "Compilando APK..." -ForegroundColor Yellow
.\gradlew.bat assembleDebug

# Verificar si la compilación fue exitosa
if (Test-Path ".\app\build\outputs\apk\debug\app-debug.apk") {
    Write-Host "¡APK compilado exitosamente!" -ForegroundColor Green
    Write-Host "Ubicación: .\app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor Cyan
} else {
    Write-Host "Error al compilar el APK" -ForegroundColor Red
}
