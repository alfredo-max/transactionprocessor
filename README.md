# Transaction Processor

## Instrucciones para ejecutar la aplicación

### Prerrequisitos

- Docker y Docker Compose

### Clonar el repositorio

git clone https://github.com/alfredo-max/transactionprocessor.git

cd transactionprocessor

### Ejecución del Proyecto con Docker
Para ejecutar el proyecto, sigue estos pasos:

* Construir las Imágenes:
  Ejecuta el siguiente comando para construir las imágenes de Docker definidas en tu archivo docker-compose.yml:

   docker-compose build

* Iniciar los Contenedores:
  Una vez construidas las imágenes, inicia los contenedores  con el siguiente comando:

   docker-compose up

### Probar endpoints

Nota: Archivo de Postman
Para facilitar la prueba de los endpoints, puedes importar el archivo de Postman incluido en el repositorio llamado: Peticiones.json


### Uso de los Endpoints
A continuación se detalla cómo utilizar los endpoints disponibles en esta API.

1. Obtener Registros de Transacciones
    Endpoint: GET /obtener-registros
    Descripción: Devuelve una lista de todos los registros de transacciones almacenados en la base de datos.
    Respuesta Exitosa: Lista de transacciones con los detalles de cada una.
    Ejemplo de Respuesta:
    ```
    [
      {
        "transactionId": "999",
        "timestamp": "2024-07-29T14:30:00.000+00:00",
        "deviceNumber": "67890",
        "userId": "user1",
        "geoPosition": "40.712776, -74.005974",
        "amount": 350.0
      }
    ]
    ```
2. Crear una Transacción
    Endpoint: POST /
    Descripción: Guarda un nuevo registro de transacción en la base de datos y lo envía a través de un broker de mensajería (ActiveMQ).
    Cuerpo de la Solicitud:
    ```
    {
        "transactionId": "12345",
        "timestamp": "2024-07-28T14:30:00Z",
        "deviceNumber": "67890",
        "userId": "user1",
        "geoPosition": "40.712776, -74.005974",
        "amount": 100.50
    }
    ```
    Respuesta Exitosa: Detalles de la transacción creada.
    Ejemplo de Respuesta:
    ```
    {
    "id": 1,
    "transactionId": "12345",
    "timestamp": "2024-07-28T14:30:00Z",
    "deviceNumber": "67890",
    "userId": "user1",
    "geoPosition": "40.712776, -74.005974",
    "amount": 100.50
    }
    ```
3. Obtener Resúmenes Diarios de Transacciones
    Endpoint: GET /obtener-registros-summary
    Descripción: Devuelve una lista de resúmenes diarios, donde cada resumen contiene la sumatoria de las transacciones por día.
    Respuesta Exitosa: Lista de resúmenes diarios con fecha y monto total.
    Ejemplo de Respuesta:
    ```
    [
        {
            "date": "2024-07-30",
            "totalAmount": 500.00
        }
    ]
    ```
