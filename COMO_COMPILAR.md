# GUÍA DE COMPILACIÓN - MultiGame Android

## ⚠️ IMPORTANTE
Para compilar este proyecto necesitas tener instalado:
1. **Android Studio** (recomendado) O
2. **JDK 8+** y **Android SDK**

## MÉTODO 1: Con Android Studio (RECOMENDADO)

### Instalación de Android Studio
1. Descarga Android Studio desde: https://developer.android.com/studio
2. Instala Android Studio siguiendo el asistente
3. Durante la instalación, asegúrate de que se instale:
   - Android SDK
   - Android SDK Platform
   - Android Virtual Device

### Compilar el proyecto
1. Abre Android Studio
2. Selecciona "Open an Existing Project"
3. Navega a la carpeta: `G:\Mi unidad\Antigravity\New Proyecto`
4. Espera a que Gradle sincronice el proyecto (puede tardar varios minutos la primera vez)
5. Una vez sincronizado:
   - **Opción A:** Ve a `Build > Build Bundle(s) / APK(s) > Build APK(s)`
   - **Opción B:** Click en el menú `Build` y selecciona `Generate Signed Bundle / APK`
6. El APK se generará en: `app\build\outputs\apk\debug\app-debug.apk`

## MÉTODO 2: Desde Línea de Comandos (Requiere Android SDK)

### Requisitos previos
1. Instalar JDK 8 o superior
2. Instalar Android SDK Command Line Tools
3. Configurar variables de entorno:
   - JAVA_HOME: ruta al JDK
   - ANDROID_HOME: ruta al Android SDK

### Comandos
```powershell
# Navegar al proyecto
cd "G:\Mi unidad\Antigravity\New Proyecto"

# Primera vez: descargar Gradle Wrapper
.\gradlew.bat --version

# Limpiar proyecto
.\gradlew.bat clean

# Compilar APK Debug
.\gradlew.bat assembleDebug

# Compilar APK Release (sin firma)
.\gradlew.bat assembleRelease
```

## MÉTODO 3: Compilación en línea (Sin instalaciones)

Si no puedes instalar Android Studio localmente, puedes usar servicios en línea:

1. **GitHub Actions** (Gratis)
   - Sube el proyecto a un repositorio GitHub
   - Crea un workflow de GitHub Actions para compilar
   
2. **Codemagic** (https://codemagic.io)
   - Plan gratuito disponible
   - Conecta tu repositorio y compila

## UBICACIÓN DEL APK

Después de compilar exitosamente, encontrarás el APK en:
```
app\build\outputs\apk\debug\app-debug.apk
```

## INSTALACIÓN EN DISPOSITIVO ANDROID

1. Copia el archivo APK a tu dispositivo Android
2. En el dispositivo:
   - Ve a Configuración > Seguridad
   - Habilita "Orígenes desconocidos" o "Instalar aplicaciones desconocidas"
3. Abre el archivo APK en tu dispositivo
4. Toca "Instalar"

## SOLUCIÓN DE PROBLEMAS

### Error: "SDK location not found"
**Solución:** Crea un archivo `local.properties` en la raíz del proyecto con:
```
sdk.dir=C\:\\Users\\TuUsuario\\AppData\\Local\\Android\\Sdk
```
(Ajusta la ruta según tu instalación)

### Error: "JAVA_HOME not set"
**Solución:** Instala JDK y configura la variable de entorno JAVA_HOME

### Error de sincronización de Gradle
**Solución:** 
1. En Android Studio: File > Invalidate Caches / Restart
2. Desde terminal: `.\gradlew.bat clean`

### El APK no se instala en el dispositivo
**Solución:**
- Verifica que "Orígenes desconocidos" esté habilitado
- Asegúrate de que el dispositivo tenga Android 7.0 (API 24) o superior

## COMPILACIÓN EXITOSA

Si todo funciona correctamente, verás un mensaje similar a:
```
BUILD SUCCESSFUL in Xs
```

Y encontrarás el APK listo para instalar en:
`app\build\outputs\apk\debug\app-debug.apk`

---

## CONTACTO Y SOPORTE

Si tienes problemas compilando el proyecto:
1. Verifica que todas las dependencias estén instaladas
2. Revisa los logs de error completos
3. Asegúrate de tener conexión a internet (para descargar dependencias)

---

**Proyecto:** MultiGame - 10 Niveles  
**Desarrollador:** Antigravity  
**Fecha:** Noviembre 2025
