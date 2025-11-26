# 🚀 GUÍA: COMPILAR APK CON GITHUB (Sin instalar nada)

## ✅ VENTAJAS DE USAR GITHUB

- ✅ **No necesitas instalar Android Studio** en tu computadora
- ✅ **Compilación automática** en la nube
- ✅ **GitHub es gratis** para proyectos públicos
- ✅ **Descarga el APK** listo para instalar
- ✅ **Funciona en cualquier computadora** (Windows, Mac, Linux)

---

## 📋 PASOS PARA COMPILAR CON GITHUB

### PASO 1: Crear cuenta en GitHub (si no tienes)

1. Ve a: **https://github.com**
2. Click en **"Sign up"** (Registrarse)
3. Completa el formulario con tu email
4. Verifica tu email
5. ¡Listo! Cuenta creada gratis

---

### PASO 2: Crear un nuevo repositorio

1. **Inicia sesión** en GitHub
2. Click en el botón **"+"** (arriba a la derecha)
3. Selecciona **"New repository"** (Nuevo repositorio)
4. Completa los datos:
   - **Repository name:** `multigame-android` (o el nombre que quieras)
   - **Description:** "Juego Android con 10 niveles"
   - Selecciona **Public** (público)
   - ❌ **NO** marques "Initialize this repository with a README"
5. Click en **"Create repository"**

---

### PASO 3: Subir el proyecto a GitHub

Abre **PowerShell** en la carpeta del proyecto y ejecuta estos comandos:

```powershell
# Ya hicimos esto, pero por si acaso:
cd "G:\Mi unidad\Antigravity\New Proyecto"

# Ver el estado actual
git status

# Conectar con tu repositorio de GitHub (CAMBIA TU_USUARIO)
git remote add origin https://github.com/TU_USUARIO/multigame-android.git

# Verificar la conexión
git remote -v

# Cambiar a la rama main
git branch -M main

# Subir el proyecto a GitHub
git push -u origin main
```

**IMPORTANTE:** Reemplaza `TU_USUARIO` con tu nombre de usuario de GitHub.

**Ejemplo:**
```powershell
git remote add origin https://github.com/juanperez/multigame-android.git
```

**Nota:** GitHub te pedirá autenticarte. Usa tus credenciales o un token de acceso personal.

---

### PASO 4: GitHub compilará automáticamente

1. Ve a tu repositorio en GitHub
2. Click en la pestaña **"Actions"** (Acciones)
3. Verás que se está ejecutando el workflow **"Build Android APK"**
4. Espera de **5 a 10 minutos** (primera compilación)
5. Cuando termine, verás un ✅ verde

---

### PASO 5: Descargar el APK compilado

1. En la pestaña **"Actions"**
2. Click en la última ejecución exitosa (con ✅ verde)
3. Baja hasta **"Artifacts"** (Artefactos)
4. Verás dos archivos:
   - **multigame-debug-apk** ⬅️ Descarga este
   - multigame-release-apk (si está disponible)
5. Click para descargar
6. Descomprime el archivo ZIP
7. ¡Ya tienes tu **app-debug.apk**!

---

## 📱 INSTALAR EL APK EN TU TELÉFONO

### Opción A: Por cable USB

1. Conecta tu teléfono a la computadora
2. Copia el APK a tu teléfono
3. En el teléfono, abre el archivo APK
4. Permite "Instalar apps desconocidas"
5. Toca **"Instalar"**

### Opción B: Por email/WhatsApp

1. Envíate el APK por email o WhatsApp
2. Descárgalo en tu teléfono
3. Abre el archivo APK
4. Permite "Instalar apps desconocidas"
5. Toca **"Instalar"**

### Opción C: Por Google Drive/Dropbox

1. Sube el APK a Google Drive o Dropbox
2. Descárgalo en tu teléfono
3. Abre el archivo APK
4. Permite "Instalar apps desconocidas"
5. Toca **"Instalar"**

---

## 🔧 COMANDOS GIT ÚTILES

```powershell
# Ver el estado del repositorio
git status

# Ver el historial de commits
git log --oneline

# Ver las ramas
git branch

# Crear una nueva rama
git checkout -b nueva-caracteristica

# Volver a la rama main
git checkout main

# Ver los cambios
git diff

# Hacer un nuevo commit después de cambios
git add .
git commit -m "Descripción de los cambios"
git push
```

---

## ❓ PREGUNTAS FRECUENTES

### ¿Cuánto cuesta GitHub?
**Es gratis** para repositorios públicos. También hay plan gratuito para repositorios privados.

### ¿Cuánto tarda en compilar?
La primera vez tarda **5-10 minutos**. Las siguientes compilaciones son más rápidas (2-5 minutos).

### ¿Puedo hacer el repositorio privado?
Sí, pero GitHub Actions tiene un límite mensual gratuito. Para uso personal es más que suficiente.

### ¿Necesito instalar Git?
Sí, pero ya lo tienes instalado (verificado). Si no, descárgalo de: https://git-scm.com/

### ¿Qué es GitHub Actions?
Es un servicio de GitHub que ejecuta tareas automáticas, como compilar tu app.

### ¿Puedo compilar varias veces?
Sí, cada vez que hagas `git push`, GitHub compilará automáticamente.

### ¿El APK es seguro?
Sí, es tu propio código compilado en los servidores de GitHub.

---

## 🎯 RESUMEN RÁPIDO

1. ✅ Crear cuenta en GitHub
2. ✅ Crear nuevo repositorio
3. ✅ Ejecutar: `git remote add origin https://github.com/TU_USUARIO/tu-repo.git`
4. ✅ Ejecutar: `git push -u origin main`
5. ✅ Esperar 5-10 minutos
6. ✅ Descargar APK desde "Actions" → "Artifacts"
7. ✅ Instalar en tu teléfono

---

## 🆘 SOLUCIÓN DE PROBLEMAS

### "Permission denied" al hacer push
**Solución:** GitHub necesita autenticación. Opciones:
1. Usa un **Personal Access Token** en lugar de contraseña
2. Configura **SSH keys**
3. Usa **GitHub Desktop** (interfaz gráfica)

Para crear un token:
1. GitHub → Settings → Developer settings → Personal access tokens → Tokens (classic)
2. Generate new token
3. Marca "repo"
4. Copia el token y úsalo como contraseña

### "Build failed" en GitHub Actions
**Solución:**
1. Ve a Actions → Click en la ejecución fallida
2. Lee el log de error
3. Generalmente es un problema de dependencias (se resuelve solo reintentando)

### No aparece el APK en Artifacts
**Solución:**
1. Espera a que termine la compilación (✅ verde)
2. Refresca la página
3. Si aún no aparece, revisa el log del workflow

---

## 📞 SIGUIENTE NIVEL: FIRMAR EL APK (OPCIONAL)

Para publicar en Google Play Store necesitas un APK firmado. Guía básica:

1. Genera un keystore
2. Agrega secretos en GitHub
3. Modifica el workflow para firmar

(Esto es avanzado, solo necesario para Play Store)

---

## ✨ ¡LISTO!

Con GitHub Actions tienes un sistema de compilación profesional sin instalar nada en tu computadora. Cada vez que hagas cambios, GitHub compilará automáticamente.

**Archivos importantes del repositorio:**
- `.github/workflows/build-apk.yml` - Configuración de GitHub Actions
- `.gitignore` - Archivos que Git ignora

---

📅 **Estado actual:** ✅ Git inicializado y primer commit creado  
🎯 **Siguiente paso:** Subir a GitHub y obtener tu APK

**¡A compilar con GitHub!** 🚀
