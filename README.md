# Práctica 2 – Registro de Usuarios (Android Studio)

Aplicación Android (Kotlin + XML) con un formulario de **Registro de Usuarios** construido con
componentes de Material Design 3.

## Contenido del formulario

| Campo | Componente |
|---|---|
| Nombres / Apellidos | `TextInputLayout` + `TextInputEditText` con icono |
| Tipo Doc (Cédula, Pasaporte, RUC) | Lista desplegable (`ExposedDropdownMenu`) |
| Número ID | `TextInputEditText` numérico, máximo 10 dígitos, con contador |
| Género | `RadioGroup` horizontal con 3 `RadioButton` (Masculino, Femenino, Otro) e iconos |
| Fecha de nacimiento | 3 listas desplegables: Día, Mes y Año |
| Teléfono | `TextInputEditText` tipo teléfono |
| Grabar | `MaterialButton` con icono; valida los campos y muestra un resumen |

Al pulsar **Grabar** se validan los campos obligatorios (los errores se muestran debajo de cada campo)
y, si todo es correcto, se muestra un diálogo con los datos registrados.

## Capturas de pantalla (emulador)

| Pantalla inicial | Lista desplegable (Mes) |
|---|---|
| <img src="capturas/captura_inicial.png" width="260"> | <img src="capturas/captura_lista.png" width="260"> |

| Formulario lleno | Al pulsar "Grabar" |
|---|---|
| <img src="capturas/captura_llena.png" width="260"> | <img src="capturas/captura_grabar.png" width="260"> |

## Estructura principal

- `app/src/main/res/layout/activity_main.xml` – diseño del formulario (`ScrollView` + `ConstraintLayout`)
- `app/src/main/java/com/uteq/software/registro_usuario_app/MainActivity.kt` – carga de listas, validación y botón Grabar
- `app/src/main/res/drawable/` – iconos usados en el formulario

## Cómo ejecutar

1. Abrir el proyecto en Android Studio.
2. Sincronizar Gradle.
3. Ejecutar en un emulador o dispositivo con Android API 37.
