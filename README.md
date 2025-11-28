# API de Gestión de Recetas

API REST desarrollada con Spring Boot para la gestión completa de recetas de cocina, ingredientes y categorías. 

## Descripción

Esta API permite gestionar un sistema de recetas de cocina, donde puedes crear, consultar, actualizar y eliminar recetas, ingredientes y categorías. Además, permite asociar ingredientes a recetas con cantidades y unidades de medida específicas.

## Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3. 5.8**
- **Spring Data JPA** - Persistencia de datos
- **H2 Database** - Base de datos en memoria
- **Lombok** - Reducción de código boilerplate
- **SpringDoc OpenAPI (Swagger)** - Documentación de la API
- **Maven** - Gestión de dependencias
- **Kotlin 2.2.0** - Soporte adicional

## Requisitos Previos

- Java 21 o superior
- Maven 3.6 o superior
- IDE recomendado: IntelliJ IDEA / Eclipse / VS Code

## Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/PedritoSerrano/apiGestionRecetas.git
cd apiGestionRecetas
```

### 2. Compilar el proyecto

```bash
cd apiGestionRecetas
mvn clean install
```

### 3. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La aplicación se iniciará en `http://localhost:8080`

## Documentación de la API

Una vez iniciada la aplicación, puedes acceder a la documentación interactiva de Swagger en:

```
http://localhost:8080/swagger-ui. html
```

## Modelo de Datos

### Entidades Principales

#### Receta
- `id` (Long): Identificador único
- `nombre` (String): Nombre único de la receta
- `tiempoPreparacionMin` (Integer): Tiempo de preparación en minutos
- `dificultad` (Enum): Nivel de dificultad (FACIL, MEDIA, DIFICIL)
- `categoria` (Categoria): Categoría asociada
- `ingredienteRecetas` (List): Lista de ingredientes con cantidades

#### Ingrediente
- `id` (Long): Identificador único
- `nombre` (String): Nombre único del ingrediente
- `ingredienteRecetas` (List): Relación con recetas

#### Categoria
- `id` (Long): Identificador único
- `nombre` (String): Nombre único de la categoría
- `descripcion` (String): Descripción de la categoría
- `recetas` (List): Lista de recetas asociadas

#### IngredienteReceta
Tabla intermedia que relaciona ingredientes con recetas:
- `id` (Long): Identificador único
- `cantidad` (Double): Cantidad del ingrediente
- `unidadMedida` (String): Unidad de medida (gramos, kilogramo, litros, etc.)
- `receta` (Receta): Receta asociada
- `ingrediente` (Ingrediente): Ingrediente asociado

## Endpoints Principales

### Recetas (`/api/v1/recetas`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/recetas` | Obtener todas las recetas |
| GET | `/api/v1/recetas/{id}` | Obtener receta por ID |
| POST | `/api/v1/recetas` | Crear nueva receta |
| PUT | `/api/v1/recetas/{id}` | Actualizar receta existente |
| DELETE | `/api/v1/recetas/{id}` | Eliminar receta |
| POST | `/api/v1/recetas/{recetaId}/ingredientes` | Añadir ingrediente a receta |

### Ingredientes (`/api/v1/ingredientes`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/ingredientes` | Obtener todos los ingredientes |
| GET | `/api/v1/ingredientes/{id}` | Obtener ingrediente por ID |
| POST | `/api/v1/ingredientes` | Crear nuevo ingrediente |

### Categorías (`/api/v1/categorias`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/categorias` | Obtener todas las categorías |
| GET | `/api/v1/categorias/{id}` | Obtener categoría por ID |
| POST | `/api/v1/categorias` | Crear nueva categoría |
| PUT | `/api/v1/categorias/{id}` | Actualizar categoría |
| DELETE | `/api/v1/categorias/{id}` | Eliminar categoría |

## Ejemplos de Uso

### Crear una Receta

```bash
POST /api/v1/recetas
Content-Type: application/json

{
  "nombre": "Migas extremeñas",
  "tiempoPreparacionMin": 60,
  "dificultad": "MEDIA",
  "categoriaId": 1
}
```

### Añadir Ingrediente a una Receta

```bash
POST /api/v1/recetas/1/ingredientes
Content-Type: application/json

{
  "ingredienteId": 1,
  "cantidad": "1",
  "unidad": "kilogramo"
}
```

### Crear un Ingrediente

```bash
POST /api/v1/ingredientes
Content-Type: application/json

{
  "nombre": "Pan duro"
}
```

### Crear una Categoría

```bash
POST /api/v1/categorias
Content-Type: application/json

{
  "nombre": "Platos tradicionales",
  "descripcion": "Recetas de cocina tradicional española"
}
```

## Características Destacadas

- **Validación de datos**: Evita duplicados de nombres en recetas, ingredientes y categorías
- **Manejo de errores personalizado**: Respuestas HTTP claras y específicas
- **Documentación automática**: Swagger/OpenAPI integrado
- **Relaciones complejas**: Gestión de relaciones Many-to-Many con atributos adicionales
- **Base de datos en memoria**: H2 para desarrollo y testing rápido
- **DTOs separados**: Request y Response DTOs para mejor control de datos

## Arquitectura

El proyecto sigue una arquitectura en capas:

```
├── controller/      # Controladores REST
├── dto/            # Data Transfer Objects
├── error/          # Excepciones personalizadas
├── model/          # Entidades JPA
├── repository/     # Repositorios Spring Data JPA
└── service/        # Lógica de negocio
```

## Base de Datos

La aplicación utiliza H2 Database en modo memoria.  Para acceder a la consola H2:

```
http://localhost:8080/h2-console
```

**Credenciales por defecto:**
- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: _(vacío)_

## Testing

Para ejecutar los tests:

```bash
mvn test
```

## Códigos de Estado HTTP

- `200 OK`: Operación exitosa
- `201 Created`: Recurso creado exitosamente
- `404 Not Found`: Recurso no encontrado
- `409 Conflict`: Conflicto (ej: nombre duplicado)

## Autor

**PedritoSerrano**
- GitHub: [@PedritoSerrano](https://github.com/PedritoSerrano)

## Licencia

Este proyecto es de código abierto y está disponible bajo los términos de la licencia especificada en el repositorio. 

## Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3.  Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request
