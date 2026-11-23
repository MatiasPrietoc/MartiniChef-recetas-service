# MartiniChef — Servicio de recetas

Backend académico de MartiniChef para la asignatura
DSY1105 Desarrollo de Aplicaciones Móviles, Duoc UC.

Implementación del servicio: Matías Prieto.
Proyecto: Grupo 14.

## Tecnologías

- Java 21.
- Spring Boot 4.0.10.
- Spring Web MVC.
- Spring Data JPA.
- Jakarta Validation.
- Base de datos H2 con almacenamiento en archivo.
- Maven Wrapper.

## Funcionalidades

- Crear recetas.
- Consultar todas las recetas.
- Consultar una receta por identificador.
- Actualizar recetas.
- Eliminar recetas.
- Validar los datos recibidos.
- Devolver errores JSON con mensajes por campo.

Cada receta contiene título, descripción, tiempo de preparación,
ingredientes y pasos. Su identificador se genera automáticamente.

## Ejecución

1. Instalar Java 21.
2. Abrir la carpeta que contiene pom.xml en IntelliJ IDEA.
3. Esperar la descarga de dependencias.
4. Ejecutar RecetasServiceApplication.

También puede ejecutarse desde PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

El servidor utiliza el puerto 8080.

La primera ejecución necesita Internet para descargar dependencias.

## Base de datos

H2 guarda la información en:

```text
./data/martinichef_recetas
```

La ruta es relativa al directorio de ejecución.

Hibernate configura las tablas a partir de las entidades.
Los datos se conservan al reiniciar el servidor.

La base de datos de desarrollo no se incluye en el repositorio.
Al ejecutar el proyecto se crea una nueva base de datos.

## Endpoints

| Método | Ruta | Operación |
|---|---|---|
| GET | /api/recetas | Listar recetas |
| GET | /api/recetas/{id} | Consultar una receta |
| POST | /api/recetas | Crear una receta |
| PUT | /api/recetas/{id} | Actualizar una receta |
| DELETE | /api/recetas/{id} | Eliminar una receta |

Dirección local:

```text
http://localhost:8080/api/recetas
```

Ejemplo del JSON para crear o actualizar:

```json
{
  "titulo": "Tostadas con palta",
  "descripcion": "Una preparación rápida.",
  "tiempoMinutos": 5,
  "ingredientes": ["2 rebanadas de pan", "1 palta"],
  "pasos": ["Tostar el pan.", "Agregar la palta."]
}
```

## Respuestas HTTP

- 200: consulta o actualización correcta.
- 201: receta creada.
- 204: receta eliminada.
- 400: datos inválidos o JSON incorrecto.
- 404: receta inexistente.

## Conexión con Android

La aplicación Android utiliza Retrofit.

Para la demostración local se utiliza un túnel ADB:

```text
adb reverse tcp:8080 tcp:8080
```

La dirección configurada en Android es:

```text
http://127.0.0.1:8080/
```

El backend debe permanecer ejecutándose.
Si se reinicia el dispositivo, puede ser necesario recrear el túnel.

## Comprobaciones realizadas

Se comprobaron manualmente creación, consulta, actualización,
eliminación y rechazo de datos inválidos.

Se comprobó que las recetas permanecen guardadas después de
reiniciar el backend.

También se probaron las operaciones CRUD desde la aplicación Android.