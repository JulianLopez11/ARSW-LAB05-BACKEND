# ARSW Lab 05 — Backend (Tablero Colaborativo)

Desarrollo backend de un tablero interactivo que permite a múltiples usuarios dibujar en
un tablero compartido. El tablero debe permitir a múltiples usuarios dibujar en línea y
proveer un botón de borrado. Lo que cada persona dibuje debe aparecer en el tablero de
todas las otras personas. Cada persona debe iniciar con un color diferente. Cuándo alguien
oprime el botón de borrar el tablero se borra para todas las personas.

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
mvn spring-boot:run
```

El servidor queda corriendo en `http://localhost:8080`.

---

## WebSocket

### ¿Cómo funciona?

El servidor utiliza **STOMP sobre WebSocket** . El flujo general es el siguiente:

1. **Conexión:** el cliente se conecta al endpoint `/ws-boards` usando SockJS + STOMP.
2. **Suscripción:** el cliente se suscribe al topic `/topic/boards.{author}.{name}` para recibir en tiempo real el estado del tablero.
3. **Envío de mensajes:** el cliente envía mensajes con prefijo `/app` que el broker enruta a los `@MessageMapping` del controlador.
4. **Broadcast:** tras procesar el evento el servidor publica el tablero actualizado en el topic, y **todos los clientes suscritos** lo reciben simultáneamente.

### Configuración del broker

| Componente | Valor |
|---|---|
| Endpoint de conexión | `/ws-boards` (SockJS habilitado) |
| Prefijo de aplicación | `/app` |
| Topics de difusión | `/topic`, `/queue` |
| Prefijo de usuario | `/user` |

---

### Mensajes disponibles

#### Dibujar un punto — `/app/draw`
Se hace con el controlador draw en WebSocketController

Agrega un punto al tablero y difunde el tablero actualizado a todos los suscriptores.

- **Destino STOMP:** `/app/draw`
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

- **Broadcast de respuesta:** `/topic/boards.camilo.board1` → objeto `Board` completo con todos los puntos.

---

#### Limpiar el tablero — `/app/clear`

Elimina todos los puntos del tablero y difunde el tablero vacío a todos los suscriptores.

- **Destino STOMP:** `/app/clear`
- **Payload (JSON):**

```json
{
  "author": "camilo",
  "name": "board1"
}
```

- **Broadcast de respuesta:** `/topic/boards.camilo.board1` → objeto `Board` con lista de puntos vacía.

---

### Ejemplo de suscripción (JavaScript)

```js
const socket = new SockJS('http://localhost:8080/ws-boards');
const client = Stomp.over(socket);

client.connect({}, () => {
  client.subscribe('/topic/boards.camilo.board1', (message) => {
    const board = JSON.parse(message.body);
    console.log('Tablero actualizado:', board);
  });
  //Dibujar
  client.send('/app/draw', {}, JSON.stringify({
    author: 'camilo',
    name: 'board1',
    point: { x: 100, y: 200, color: '#ff0000' }
  }));

  //Borrar
  client.send('/app/clear', {}, JSON.stringify({
    author: 'camilo',
    name: 'board1'
  }));
});
```

---

## Swagger

```
http://localhost:8080/swagger-ui.html
```

## Autor

* **Julian Camilo Lopez Barrero** - [JulianLopez11](https://github.com/JulianLopez11)
