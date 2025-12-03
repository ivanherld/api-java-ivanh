# Proyecto Talento – API CRUD de Artículos

API REST construida con Spring Boot 3 (Java 17) para gestionar artículos/productos. Incluye persistencia con Spring Data JPA y MySQL, uso de Lombok para reducir boilerplate y configuración lista para desarrollo local.

## Tecnologías
- Java 17
- Spring Boot 3.5.x (Web, Data JPA)
- Hibernate 6, Jakarta Persistence
- MySQL 8 (Connector/J)
- Lombok
- Maven

## Requisitos previos
- JDK 17 instalado y en `PATH`
- MySQL 8 en `localhost:3306`
- Base de datos: `articulos_db` (se crea automáticamente con el flag `createDatabaseIfNotExist=true`)
- VS Code/IDE con plugin de Lombok habilitado

## Configuración
Archivo `src/main/resources/application.properties`:
```
spring.application.name=proyectoTalento
spring.datasource.url=jdbc:mysql://localhost:3306/articulos_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password= # Establece tu contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```
Notas:
- Si ves errores de autenticación MySQL (auth plugin), define una contraseña para `root` y asegúrate de usar un plugin soportado (`mysql_native_password` o `caching_sha2_password`).
- Puedes omitir el `dialect`; Hibernate 6 lo detecta automáticamente.

## Instalación y ejecución
Compila y ejecuta con Maven Wrapper:
```
./mvnw.cmd clean compile
./mvnw.cmd spring-boot:run
```
La aplicación se levanta en `http://localhost:8080`.

## Estructura del proyecto
```
src/
  main/
    java/
      com/talento/crud/
        ProyectoTalentoApplication.java
        controller/  # Controladores REST
        service/     # Servicios de negocio
        repository/  # Repositorios JPA (sugerido)
        model/       # Entidades JPA
        dto/         # Data Transfer Objects
        mapper/      # Conversión Entity <-> DTO
    resources/
      application.properties
```

## Endpoints (ejemplos sugeridos)
- `GET /api/productos` — lista de productos
- `GET /api/productos/{id}` — detalle por ID
- `POST /api/productos` — crear
- `PUT /api/productos/{id}` — actualizar
- `DELETE /api/productos/{id}` — eliminar
- `GET /api/productos/buscar?texto=...` — búsqueda por nombre (contiene)
- `GET /api/productos/categoria/{cat}` — por categoría

> Ajusta los paths según tu `ProductoController` y `IProductoService`.

## Consultas y repositorios
Usa Spring Data JPA:
```java
public interface ProductoRepository extends JpaRepository<Producto, Long> {
  Optional<Producto> findByNombre(String nombre);
  List<Producto> findByCategoria(String categoria);
  List<Producto> findByNombreContaining(String texto);
}
```
Para casos avanzados, utiliza `@Query` (JPQL/SQL nativa) o Criteria API.

## CORS
Para habilitar CORS global:
```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("http://localhost:3000")
      .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
      .allowedHeaders("*")
      .allowCredentials(true)
      .maxAge(3600);
  }
}
```
Si usas Spring Security, añade `cors()` y un `CorsConfigurationSource` en la `SecurityFilterChain`.

## Buenas prácticas
- Inyección por constructor en servicios/controladores.
- DTOs para exposición pública; evita exponer entidades directas.
- Validación con `jakarta.validation` (`@NotNull`, `@Size`, etc.).
- Manejo de errores consistente con `@ControllerAdvice`.
- Índices/constraints en columnas usadas por búsquedas.

## Scripts SQL útiles
Crear usuario/contraseña y plugin compatible:
```sql
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'TuPasswordSegura';
FLUSH PRIVILEGES;
```

## Testing
Ejecuta tests:
```
./mvnw.cmd test
```
Agrega tests de servicio/repositorio y de controlador (MockMvc) para asegurar el CRUD.

## 
Uso educativo/demostración. Ajusta según tus necesidades.
