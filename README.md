# 📦 Prueba técnica - Desarrollo de la Aplicación Spring Boot

## 📖 Descripción

Este es un servicio web simple que maneja una entidad "Producto". Utiliza **Spring Boot**, **JPA**, **H2 Database**, *
*Docker**, y **Lombok** para ofrecer una solución eficiente y fácil de usar para la gestión de productos.

## 🛠️ Características

1. **Modelo de Producto:**
    - Clase `Product` con los campos `id`, `nombre`, y `precio`.

2. **Repositorio de Producto:**
    - Repositorio JPA para gestionar la entidad `Product`.

3. **Controlador REST de Producto:**
    - Controlador que permite realizar operaciones CRUD en los productos.

4. **Dockerización:**
    - **Dockerfile** para empaquetar la aplicación.
    - **docker-compose.yml** para definir los servicios de la aplicación y la base de datos.

5. **Conexión a la Base de Datos:**
    - Configuración para conectar la aplicación a H2 Database.

6. **Pruebas Unitarias:**
    - Pruebas unitarias para el controlador REST utilizando JUnit y Mockito.

🔍 Clonar el Repositorio

```bash
git clone https://github.com/NICORUIZ93/Spring-Boot-santander.git
```

Acceder al Directorio del Proyecto

```bash
  cd santander
```

Construir la Aplicación

```bash
./gradlew clean build
```

Ejecutar Docker Compose
```bash
docker-compose up --build
```

Acceder a la Aplicación

```bash
http://localhost:8080/api/products
```

Detener los Contenedores
```bash
docker-compose down
```

## 🧪 Ejecutar Pruebas Unitarias

Para ejecutar las pruebas unitarias de la aplicación, utiliza el siguiente comando en la terminal. Asegúrate de estar en
el directorio raíz del proyecto:

```bash
  ./gradlew test
```

## 🔗 Enlace a la Documentación en Postman

Puedes acceder a la documentación de la API utilizando el siguiente enlace:

[Documentación de la API en Postman](https://documenter.getpostman.com/view/11742617/2sA3kXFgDE)

## 📝License

Este proyecto está licenciado bajo los términos de la licencia MIT.

## 👤 Autores

- [@Nicolas Ruiz](https://github.com/NICORUIZ93)

