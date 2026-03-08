# ARSW Lab 05 — Backend (Tablero Colaborativo)

Backend del laboratorio 05 de ARSW. Se implementa para el backend del tablero de dibujo colaborativo en tiempo real, fue implementado siguiendo la estructura del laboratorio de BluePrint por lo que se implemento el metodo que de Stomp.

---

## Tecnologías

- Java 21
- Spring Boot 4.0.3
- WebSockets con STOMP
- Swagger / OpenAPI (springdoc)
- Maven

---

## Estructura del proyecto

```
src/main/java/edu/eci/arsw/tablero/
├── config/
│   ├── CorsConfig.java          # Configuración de CORS
│   ├── OpenApiConfig.java       # Configuración de Swagger
│   └── WebSocketConfig.java     # Configuración de STOMP/WebSocket
├── controller/
│   └── WebSocketController.java # Manejo de eventos de dibujo en tiempo real
├── dto/
│   └── DrawEvent.java           # DTO para los eventos de dibujo
├── model/
│   ├── Board.java               # Modelo del tablero
│   └── Point.java               # Modelo de un punto (x, y, color)
├── repository/
│   ├── BoardRepository.java          # Interfaz del repositorio
│   └── InMemoryBoardRepository.java  # Implementación en memoria (ConcurrentHashMap)
└── services/
    └── BoardService.java        # Lógica de negocio
```

---

## Cómo correr el proyecto

### Requisitos

- Java 21
- Maven 3.9+

### Pasos

```bash
git clone <url-del-repo>
cd ARSW-LAB05-BACKEND
./mvnw spring-boot:run
```

El servidor queda corriendo en `http://localhost:8080`.

---

## WebSocket

El endpoint de conexión STOMP es:

```
ws://localhost:8080/ws-boards
```

### Enviar un punto al tablero

- **Destino:** `/app/draw`
- **Payload (JSON):**

```json
{
  "author": "camilo",
  "name": "board1",
  "point": {
    "x": 100,
    "y": 200,
    "color": "#ff0000"
  }
}
```

## Documentación de la API

Con el servidor corriendo, la documentación de Swagger está disponible en:

```
http://localhost:8080/swagger-ui.html
```

## Autor

* **Julian Camilo Lopez Barrero** - [JulianLopez11](https://github.com/JulianLopez11)