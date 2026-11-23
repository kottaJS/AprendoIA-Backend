# ☕ AprendoIA Backend - Microservicio REST (Spring Boot)

Microservicio backend desarrollado para la plataforma educativa móvil **"Aprendo IA Jugando en Santiago"** (Asignatura DSY1105 - Desarrollo de Aplicaciones Móviles).

---

## 🏗️ 1. Arquitectura y Paquetes

El backend sigue una arquitectura limpia por capas desacopladas:

```text
cl.duoc.aprendoia.backend
├── config/
│   ├── CorsConfig.java          # Habilita peticiones cruzadas para emulador Android (10.0.2.2) y celular
│   └── DataLoader.java          # Carga automática de datos de prueba en la base de datos (H2)
├── controller/
│   ├── PuntoInteresController.java  # Endpoints REST para CRUD de Puntos de Interés
│   └── UsuarioController.java       # Endpoints REST para Autenticación, Registro y Puntajes
├── dto/
│   ├── ApiResponse.java         # Envoltorio estándar de respuesta JSON (éxito, mensaje, datos, timestamp)
│   ├── LoginRequest.java        # DTO para recepción de credenciales
│   └── ActualizarPuntajeRequest.java # DTO para incremento de puntos y QRs
├── model/
│   ├── PuntoInteres.java        # Entidad JPA para persistencia de hitos turísticos/educativos
│   └── Usuario.java             # Entidad JPA para jugadores y administradores
├── repository/
│   ├── PuntoInteresRepository.java  # Acceso a datos con Spring Data JPA
│   └── UsuarioRepository.java       # Acceso a datos de usuarios
└── service/
    ├── PuntoInteresService.java     # Lógica de negocio y transacciones de puntos
    └── UsuarioService.java          # Lógica de negocio de autenticación y puntuación
```

---

## 🚀 2. Ejecución y Pruebas

### Levantar el servidor:
```bash
./mvnw spring-boot:run
```
El servidor quedará escuchando en `http://localhost:8080`.

### Ejecutar Pruebas Unitarias (JUnit 5 + Mockito):
```bash
./mvnw test
```

### Acceso a Base de Datos (Consola Web H2):
* **URL:** `http://localhost:8080/h2-console`
* **Driver Class:** `org.h2.Driver`
* **JDBC URL:** `jdbc:h2:mem:aprendoia_db`
* **User Name:** `sa`
* **Password:** *(dejar vacío)*

---

## 📡 3. Catálogo de Endpoints REST

### 📍 Puntos de Interés (`/api/puntos`)

| Método | Endpoint | Descripción | Body / Parámetros |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/puntos` | Listar todos los puntos o filtrar | Opcional: `?comuna=Santiago Centro` o `?categoria=Visión Artificial` |
| **GET** | `/api/puntos/{id}` | Obtener detalle de un punto por ID | Ninguno |
| **GET** | `/api/puntos/qr/{codigoQr}` | Buscar y validar hito por código QR | Parámetro `codigoQr` en URL |
| **POST** | `/api/puntos` | Crear nuevo punto (CRUD) | JSON con datos del punto |
| **PUT** | `/api/puntos/{id}` | Modificar datos de un punto (CRUD) | JSON con nuevos datos |
| **DELETE** | `/api/puntos/{id}` | Eliminar un punto (CRUD) | Ninguno |
| **PATCH** | `/api/puntos/{id}/completar` | Marcar punto como completado | Opcional: `?selfie=true` |

### 👤 Usuarios y Autenticación (`/api/usuarios`)

| Método | Endpoint | Descripción | Body / Parámetros |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/usuarios` | Listar todos los usuarios | Ninguno |
| **GET** | `/api/usuarios/{id}` | Obtener usuario por ID | Ninguno |
| **POST** | `/api/usuarios` | Registrar nuevo usuario | JSON de Usuario |
| **POST** | `/api/usuarios/login` | Iniciar sesión / Autenticación | `{"emailOrAlias": "...", "password": "..."}` |
| **PUT** | `/api/usuarios/{id}/puntaje` | Sumar puntos al jugador | `{"puntosGanados": 20, "incrementoQr": true}` |
| **PUT** | `/api/usuarios/{id}/perfil` | Modificar datos de perfil | JSON con campos a actualizar |

---

## 🛡️ 4. Datos Iniciales Precargados (DataLoader)
Al iniciar, el sistema precarga automáticamente:
* **3 Usuarios**:
  * `admin@duocuc.cl` / clave `123` (Admin)
  * `gencont@duocuc.cl` / clave `123` (Generador de Contenido)
  * `estudiante@duocuc.cl` / clave `123` (Cliente - Maxi Dinamarca)
* **6 Hitos Urbanos de Santiago**:
  * Palacio de La Moneda (Ética y Sociedad)
  * Plaza de Armas (Visión Artificial)
  * Parque Forestal (Modelos de Lenguaje)
  * Sede Duoc UC CITT Alonso de Ovalle (Robótica e IA)
  * Cerro Santa Lucía (Sistemas Autónomos)
  * Museo Nacional de Bellas Artes (Arte y Generación)
