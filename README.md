# app-users

aplicacion relacionada a usuarios con implementacion de jjwt

## Requisitos Previos

Antes de comenzar, asegúrate de tener instalados los siguientes software:

- [Java JDK 21 o superior](https://adoptium.net/temurin/releases/)
- [Maven](https://maven.apache.org/download.cgi)

---

## Instalación

Sigue estos pasos para clonar el repositorio y levantar la aplicación localmente.

1. Clona el repositorio:

   ```sh
   git clone https://github.com/brendsanchez/app-users.git
   cd app-users

2. Ejecuta la aplicacion:

   ```sh
   mvn spring-boot:run -Plocal

## Acceso a la Base de Datos H2

La aplicación utiliza una base de datos H2 en memoria. Puedes acceder a la consola de H2 en la siguiente URL:

- http://localhost:8080/h2-console

Utiliza las siguientes credenciales para iniciar sesión:

| H2           |                         | 
|--------------|-------------------------|
| Driver Class | `org.h2.Driver`         |
| JDBC URL     | `jdbc:h2:mem:userdb`    |
| User Name    | `SA`                    |
| Password     | (deja este campo vacío) |

## Documentación de la API con Swagger

Swagger se ha configurado para documentar la API. Puedes acceder a la interfaz de Swagger en la siguiente URL:

- http://localhost:8080/swagger-ui/index.html

## Diagrama de solucion
diagrama para crear un nuevo usuario

```
Client
|
| HTTP POST /auth/signup
|
v
AuthenticationController
|
| @PostMapping("/signup")
| @Operation(summary = "Create a new user")
|
v
AuthenticationService (business logic to generate user)
|
| Generates a new user with token
|
v
AuthenticationController
|
| Returns user
|
v
Client
