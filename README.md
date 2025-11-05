# Consultorio Médico

Este proyecto es una API REST para la gestión de consultorios médicos, desarrollada en **Java** usando **Spring Boot** y **Maven**.

## Tecnologías y dependencias

- Java 17+
- Spring Web
- Spring Data JPA
- Spring Boot DevTools
- PostgreSQL Driver
- Liquibase Migration
- Lombok
- Swagger/OpenAPI (documentación y pruebas de endpoints)

## Base de datos

Se utiliza **PostgreSQL** como base de datos principal. Configura la conexión en `src/main/resources/application.yaml` con tus credenciales de PostgreSQL.

## Cómo ejecutar el proyecto

1. Clona el repositorio:
```bash
git clone https://github.com/MamaniMarioOrlando/challenge-_consultorioMedico.git
cd challenge-_consultorioMedico
```
2. Compila y ejecuta la aplicación:

   Asegúrate de tener Maven instalado. Ejecuta el siguiente comando en la terminal dentro del directorio del proyecto:

   ```bash
   mvn spring-boot:run
## Acceso a Swagger

Una vez que la aplicación esté en ejecución, puedes acceder a la documentación y probar los endpoints desde el siguiente enlace en tu navegador:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Swagger te permite visualizar, probar y validar todos los endpoints disponibles en la API de manera sencilla e interactiva.

## Migraciones de base de datos

Las migraciones se gestionan automáticamente con Liquibase al iniciar la aplicación. Los scripts se encuentran en `src/main/resources/db/changelog`.

## Estructura del proyecto

- `controller`: Endpoints REST
- `service`: Lógica de negocio
- `model`: Entidades JPA
- `repository`: Acceso a datos

## Recomendaciones

- Modifica el archivo `application.yaml` según tu entorno y credenciales.
- Utiliza Swagger para probar los endpoints fácilmente.
- Revisa los modelos y controladores para entender la estructura de la API.
