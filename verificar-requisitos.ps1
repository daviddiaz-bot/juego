# Script de Verificación de Requisitos para Compilar MultiGame
Write-Host "==================================================" -ForegroundColor Cyan
Write-Host "   VERIFICACIÓN DE REQUISITOS - MultiGame APK    " -ForegroundColor Cyan
Write-Host "==================================================" -ForegroundColor Cyan
Write-Host ""

$allOk = $true

# Verificar Java
Write-Host "[1/3] Verificando Java/JDK..." -ForegroundColor Yellow
try {
    $javaVersion = java -version 2>&1 | Select-String "version"
    if ($javaVersion) {
        Write-Host "  ✓ Java encontrado: $javaVersion" -ForegroundColor Green
    }
} catch {
    Write-Host "  ✗ Java NO encontrado" -ForegroundColor Red
    Write-Host "    Descarga JDK desde: https://adoptium.net/" -ForegroundColor White
    $allOk = $false
}

# Verificar Android Studio
Write-Host ""
Write-Host "[2/3] Verificando Android Studio..." -ForegroundColor Yellow
$androidStudioPaths = @(
    "${env:ProgramFiles}\Android\Android Studio",
    "${env:ProgramFiles(x86)}\Android\Android Studio",
    "${env:LOCALAPPDATA}\Android\Sdk"
)

$androidStudioFound = $false
foreach ($path in $androidStudioPaths) {
    if (Test-Path $path) {
        Write-Host "  ✓ Android Studio/SDK encontrado en: $path" -ForegroundColor Green
        $androidStudioFound = $true
        break
    }
}

if (-not $androidStudioFound) {
    Write-Host "  ✗ Android Studio NO encontrado" -ForegroundColor Red
    Write-Host "    Descarga desde: https://developer.android.com/studio" -ForegroundColor White
    $allOk = $false
}

# Verificar Gradle Wrapper
Write-Host ""
Write-Host "[3/3] Verificando Gradle Wrapper..." -ForegroundColor Yellow
if (Test-Path ".\gradlew.bat") {
    Write-Host "  ✓ Gradle Wrapper presente" -ForegroundColor Green
} else {
    Write-Host "  ✗ Gradle Wrapper NO encontrado" -ForegroundColor Red
    $allOk = $false
}

Write-Host ""
Write-Host "==================================================" -ForegroundColor Cyan

if ($allOk) {
    Write-Host "✓ TODOS LOS REQUISITOS ESTÁN INSTALADOS" -ForegroundColor Green
    Write-Host ""
    Write-Host "Puedes compilar el proyecto ejecutando:" -ForegroundColor White
    Write-Host "  .\gradlew.bat assembleDebug" -ForegroundColor Yellow
} else {
    Write-Host "✗ FALTAN REQUISITOS" -ForegroundColor Red
    Write-Host ""
    Write-Host "OPCIONES PARA COMPILAR:" -ForegroundColor White
    Write-Host ""
    Write-Host "OPCIÓN 1 (RECOMENDADA): Usar Android Studio" -ForegroundColor Cyan
    Write-Host "  1. Descarga e instala Android Studio: https://developer.android.com/studio" -ForegroundColor White
    Write-Host "  2. Abre Android Studio" -ForegroundColor White
    Write-Host "  3. Selecciona 'Open' y abre esta carpeta del proyecto" -ForegroundColor White
    Write-Host "  4. Espera a que sincronice (primera vez tarda varios minutos)" -ForegroundColor White
    Write-Host "  5. Ve a Build > Build Bundle(s) / APK(s) > Build APK(s)" -ForegroundColor White
    Write-Host "  6. El APK estará en: app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor White
    Write-Host ""
    Write-Host "OPCIÓN 2: Instalar solo JDK + Android SDK" -ForegroundColor Cyan
    Write-Host "  1. Descarga JDK: https://adoptium.net/" -ForegroundColor White
    Write-Host "  2. Descarga Android SDK Command Line Tools" -ForegroundColor White
    Write-Host "  3. Configura variables JAVA_HOME y ANDROID_HOME" -ForegroundColor White
    Write-Host "  4. Ejecuta: .\gradlew.bat assembleDebug" -ForegroundColor White
    Write-Host ""
    Write-Host "OPCIÓN 3: Usar servicio en línea (sin instalación)" -ForegroundColor Cyan
    Write-Host "  - Sube el proyecto a GitHub" -ForegroundColor White
    Write-Host "  - Usa GitHub Actions o Codemagic.io para compilar" -ForegroundColor White
}

Write-Host ""
Write-Host "Consulta COMO_COMPILAR.md para instrucciones detalladas" -ForegroundColor Yellow
Write-Host "==================================================" -ForegroundColor Cyan
