# Script para conectar el proyecto con GitHub y subirlo
Write-Host "╔══════════════════════════════════════════════════════════════════════╗" -ForegroundColor Cyan
Write-Host "║     SCRIPT DE CONEXIÓN CON GITHUB - MultiGame Android              ║" -ForegroundColor Cyan
Write-Host "╚══════════════════════════════════════════════════════════════════════╝" -ForegroundColor Cyan
Write-Host ""

# Verificar que estamos en el directorio correcto
if (-not (Test-Path ".\app\build.gradle")) {
    Write-Host "❌ Error: Ejecuta este script desde la carpeta raíz del proyecto" -ForegroundColor Red
    exit 1
}

# Verificar que git está inicializado
if (-not (Test-Path ".\.git")) {
    Write-Host "❌ Error: Git no está inicializado" -ForegroundColor Red
    Write-Host "Ejecuta: git init" -ForegroundColor Yellow
    exit 1
}

Write-Host "✅ Proyecto detectado correctamente`n" -ForegroundColor Green

# Solicitar datos del repositorio de GitHub
Write-Host "Para continuar, necesitas:" -ForegroundColor Yellow
Write-Host "  1. Una cuenta en GitHub (gratis)" -ForegroundColor White
Write-Host "  2. Haber creado un repositorio nuevo en GitHub`n" -ForegroundColor White

Write-Host "Si aún no tienes repositorio, ve a:" -ForegroundColor Cyan
Write-Host "  https://github.com/new`n" -ForegroundColor White

$continue = Read-Host "¿Ya creaste tu repositorio en GitHub? (s/n)"

if ($continue -ne "s" -and $continue -ne "S") {
    Write-Host "`n📝 PASOS PARA CREAR UN REPOSITORIO:" -ForegroundColor Yellow
    Write-Host "1. Ve a: https://github.com/new" -ForegroundColor White
    Write-Host "2. Repository name: multigame-android" -ForegroundColor White
    Write-Host "3. Description: Juego Android con 10 niveles" -ForegroundColor White
    Write-Host "4. Selecciona: Public" -ForegroundColor White
    Write-Host "5. NO marques 'Initialize with README'" -ForegroundColor White
    Write-Host "6. Click en 'Create repository'`n" -ForegroundColor White
    Write-Host "Cuando lo hayas creado, vuelve a ejecutar este script." -ForegroundColor Green
    exit 0
}

Write-Host ""
$username = Read-Host "Ingresa tu nombre de usuario de GitHub"
$reponame = Read-Host "Ingresa el nombre de tu repositorio (ej: multigame-android)"

if ([string]::IsNullOrWhiteSpace($username) -or [string]::IsNullOrWhiteSpace($reponame)) {
    Write-Host "❌ Error: Debes proporcionar usuario y nombre del repositorio" -ForegroundColor Red
    exit 1
}

$repoUrl = "https://github.com/$username/$reponame.git"

Write-Host "`n📡 Configurando conexión con GitHub..." -ForegroundColor Yellow
Write-Host "   URL: $repoUrl`n" -ForegroundColor Cyan

# Verificar si ya existe un remote
$existingRemote = git remote get-url origin 2>$null

if ($existingRemote) {
    Write-Host "⚠️  Ya existe una conexión remota: $existingRemote" -ForegroundColor Yellow
    $overwrite = Read-Host "¿Deseas reemplazarla? (s/n)"
    
    if ($overwrite -eq "s" -or $overwrite -eq "S") {
        git remote remove origin
        Write-Host "✅ Conexión anterior eliminada" -ForegroundColor Green
    } else {
        Write-Host "❌ Operación cancelada" -ForegroundColor Red
        exit 0
    }
}

# Agregar el remote
Write-Host "🔗 Conectando con GitHub..." -ForegroundColor Yellow
git remote add origin $repoUrl

if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Conexión establecida exitosamente`n" -ForegroundColor Green
} else {
    Write-Host "❌ Error al conectar con GitHub" -ForegroundColor Red
    exit 1
}

# Verificar la conexión
Write-Host "🔍 Verificando conexión..." -ForegroundColor Yellow
git remote -v
Write-Host ""

# Cambiar a rama main
Write-Host "🌿 Configurando rama principal (main)..." -ForegroundColor Yellow
git branch -M main

if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Rama configurada`n" -ForegroundColor Green
} else {
    Write-Host "⚠️  Advertencia: No se pudo cambiar el nombre de la rama" -ForegroundColor Yellow
}

# Preguntar si desea hacer push
Write-Host "═══════════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "¿Deseas subir el proyecto a GitHub ahora?" -ForegroundColor Yellow
Write-Host "Esto subirá todos los archivos y comenzará la compilación." -ForegroundColor White
Write-Host "═══════════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host ""

$doPush = Read-Host "¿Subir ahora? (s/n)"

if ($doPush -eq "s" -or $doPush -eq "S") {
    Write-Host "`n🚀 Subiendo proyecto a GitHub..." -ForegroundColor Yellow
    Write-Host "   (Esto puede tardar unos minutos)`n" -ForegroundColor White
    
    git push -u origin main
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "`n╔══════════════════════════════════════════════════════════════════════╗" -ForegroundColor Green
        Write-Host "║                    ✅ ¡SUBIDO EXITOSAMENTE!                          ║" -ForegroundColor Green
        Write-Host "╚══════════════════════════════════════════════════════════════════════╝" -ForegroundColor Green
        Write-Host ""
        Write-Host "🎯 PRÓXIMOS PASOS:" -ForegroundColor Cyan
        Write-Host ""
        Write-Host "1. Ve a tu repositorio:" -ForegroundColor White
        Write-Host "   https://github.com/$username/$reponame" -ForegroundColor Yellow
        Write-Host ""
        Write-Host "2. Click en la pestaña 'Actions' (Acciones)" -ForegroundColor White
        Write-Host ""
        Write-Host "3. Verás 'Build Android APK' ejecutándose" -ForegroundColor White
        Write-Host "   Tarda aproximadamente 5-10 minutos" -ForegroundColor Yellow
        Write-Host ""
        Write-Host "4. Cuando termine (✅ verde), click en la ejecución" -ForegroundColor White
        Write-Host ""
        Write-Host "5. Baja hasta 'Artifacts' y descarga:" -ForegroundColor White
        Write-Host "   📦 multigame-debug-apk" -ForegroundColor Yellow
        Write-Host ""
        Write-Host "6. Descomprime el ZIP y obtendrás:" -ForegroundColor White
        Write-Host "   📱 app-debug.apk" -ForegroundColor Green
        Write-Host ""
        Write-Host "7. Transfiere el APK a tu teléfono e instálalo" -ForegroundColor White
        Write-Host ""
        Write-Host "═══════════════════════════════════════════════════════════════" -ForegroundColor Cyan
        Write-Host "¡GitHub está compilando tu APK ahora mismo! 🎉" -ForegroundColor Green
        Write-Host "═══════════════════════════════════════════════════════════════" -ForegroundColor Cyan
        Write-Host ""
        
        # Abrir el navegador
        $openBrowser = Read-Host "¿Abrir GitHub en el navegador? (s/n)"
        if ($openBrowser -eq "s" -or $openBrowser -eq "S") {
            Start-Process "https://github.com/$username/$reponame/actions"
        }
        
    } else {
        Write-Host "`n❌ Error al subir a GitHub" -ForegroundColor Red
        Write-Host "Posibles causas:" -ForegroundColor Yellow
        Write-Host "  • Credenciales incorrectas" -ForegroundColor White
        Write-Host "  • Repositorio no existe" -ForegroundColor White
        Write-Host "  • Sin permisos de escritura`n" -ForegroundColor White
        Write-Host "💡 Solución:" -ForegroundColor Cyan
        Write-Host "GitHub ya no acepta contraseñas. Necesitas un Personal Access Token:" -ForegroundColor White
        Write-Host "1. Ve a: https://github.com/settings/tokens" -ForegroundColor Yellow
        Write-Host "2. Generate new token (classic)" -ForegroundColor Yellow
        Write-Host "3. Marca 'repo'" -ForegroundColor Yellow
        Write-Host "4. Copia el token y úsalo como contraseña" -ForegroundColor Yellow
    }
} else {
    Write-Host "`n📝 Conexión configurada pero no se subió el proyecto." -ForegroundColor Yellow
    Write-Host "Cuando quieras subirlo, ejecuta:" -ForegroundColor White
    Write-Host "   git push -u origin main`n" -ForegroundColor Yellow
}

Write-Host "═══════════════════════════════════════════════════════════════" -ForegroundColor Cyan
Write-Host "✅ Script completado" -ForegroundColor Green
Write-Host "═══════════════════════════════════════════════════════════════" -ForegroundColor Cyan
