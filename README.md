# Retail Price Optimization Service

Este microservicio se encarga de gestionar y consultar las tarifas de precios aplicables a los productos del grupo de retail, resolviendo de manera eficiente la concurrencia y prioridad de ofertas en rangos de fechas específicos.

El proyecto ha sido desarrollado siguiendo los más altos estándares de ingeniería de software, aplicando **Arquitectura Hexagonal**, principios **DDD (Domain-Driven Design)** y **Clean Code**.

---

## Arquitectura y Diseño

Para garantizar la mantenibilidad, escalabilidad y el aislamiento de la lógica de negocio frente a las tecnologías de infraestructura, se ha implementado una **Arquitectura Hexagonal (Ports & Adapters)**:

* **Dominio (`domain`)**: Contiene el núcleo puro del negocio. Aquí residen las entidades (`Price`) y los Objetos de Valor (`Money`), completamente desacoplados de cualquier framework o base de datos.
* **Aplicación (`application`)**: Orquesta los casos de uso del sistema. Define los contratos de entrada y salida (**Ports**) y contiene la lógica de servicio (`GetApplicablePriceService`).
* **Infraestructura (`infrastructure`)**: Capa externa que maneja los detalles técnicos.
    * `inbound/rest`: Adaptador que expone la API REST mediante Spring Web (`PriceRestController`).
    * `outbound/db`: Adaptador de persistencia que interactúa con la base de datos relacional mediante Spring Data JPA.

### Decisiones Clave de Diseño
1.  **Money como Value Object**: El dinero se encapsula en su propio tipo inmutable (`Money`) protegiendo al sistema de montos negativos o inconsistencias de divisa (evitando el antipatrón *Primitive Obsession*).
2.  **Optimización en Base de Datos**: La lógica de selección por prioridad (`ORDER BY p.priority DESC LIMIT 1`) se delega directamente al motor SQL mediante JPQL, evitando la transferencia innecesaria de múltiples registros a la memoria de la JVM.
3.  **Segregación por Casos de Uso**: En lugar de un servicio genérico e inflado, se utiliza un componente exclusivo para la consulta (`GetApplicablePriceService`), respetando el Principio de Responsabilidad Única (SRP).

---

## Tecnologías Utilizadas

* **Java 17**
* **Spring Boot 3.3.1** (Línea comercial estable GA)
* **Spring Data JPA** (Persistencia)
* **H2 Database** (Base de datos relacional en memoria)
* **Springdoc OpenAPI v2.6.0** (Documentación interactiva de Swagger)
* **JUnit 5 & MockMvc** (Suite de pruebas unitarias y de integración)
* **Maven** (Gestor de dependencias)

---

## Instalación y Ejecución

### Requisitos Previos
* Disponer de un JDK 17 configurado en el sistema (verificar con `java -version`).

### Pasos para Arrancar
1. Descarga o clona el repositorio en tu máquina local.
2. Abre una terminal en la raíz del proyecto y ejecuta el empaquetado y limpieza de Maven:
   ```bash
   ./mvnw clean compile
