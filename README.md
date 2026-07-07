# Ikenobo E-Commerce 🌸 | Plataforma Full Stack

¡Bienvenido/a al repositorio oficial de **Ikenobo E-Commerce**!, una plataforma web Full Stack diseñada para la gestión, exhibición y comercialización de arreglos florales inspirados en el arte tradicional japonés. 

Este proyecto evolucionó desde una aplicación básica de consola hacia un sistema web moderno, robusto y escalable, utilizando una arquitectura desacoplada con persistencia real y panel de control dinámico.

---

## ⚙️ Arquitectura y Tecnologías Utilizadas

La aplicación está construida bajo el enfoque de separación de responsabilidades y modularidad técnica:

* **Backend (API REST):** Java 17, Spring Boot, Spring Data JPA, Hibernate y validaciones avanzadas.
* **Base de Datos:** MySQL con persistencia relacional y control de integridad mediante claves foráneas.
* **Frontend:** Interfaz web adaptativa y responsiva construida con HTML5, CSS3 moderno (Flexbox/Grid), Vanilla JavaScript y consumo asíncrono de APIs mediante `Fetch API`.
* **Almacenamiento de Multimedia:** Integración externa con Cloudinary para URLs optimizadas de imágenes de productos.

---

## 🚀 Funcionalidades Principales

* **Catálogo Interactivo:** Navegación fluida de arreglos florales organizados dinámicamente según su categoría.
* **Persistencia Total en BD:** Altas, bajas, modificaciones y lecturas (CRUD completo) sincronizadas directamente en MySQL a través de Spring Data.
* **Panel de Administración Exclusivo (Admin Control Panel):**
    * Formulario limpio y validado para la inyección de nuevos productos al inventario en tiempo real.
    * Mapeo automático de objetos relacionales mediante listas desplegables.
    * Tabla dinámica de inventario con opción de baja inmediata (`DELETE`).

---

## 🛠️ Notas Importantes de Configuración (Para el Evaluador)

Para garantizar una correcta ejecución del proyecto en su entorno local, por favor tome en cuenta las siguientes especificaciones técnicas:

### 🔑 Credenciales del Panel de Administración
El acceso y las acciones de escritura en el módulo de gestión (`pages/admin.html`) están protegidos bajo las siguientes credenciales estáticas en la lógica del controlador/frontend:
* **Clave de acceso / Token de validación:** `admin123`

### 🗂️ Mapeo de Categorías (Claves Foráneas en MySQL)
La base de datos maneja una relación `@ManyToOne` entre la entidad `Producto` y `Categoria`. Debido al comportamiento secuencial de los contadores de autoincremento relacionales en entornos de desarrollo locales, el menú desplegable del Panel de Administración está explícitamente mapeado a los siguientes IDs de la tabla `categoria`:
* `ID 4` ➡️ **GIFTS** (Regalos y detalles)
* `ID 5` ➡️ **DECO** (Decoración)
* `ID 6` ➡️ **ANIVERSARIO** (Presentes extraordinarios)

> **Nota:** Asegúrese de verificar su tabla local `categoria` antes de realizar inserciones. Si sus IDs locales difieren, puede adaptarlos directamente en las propiedades `value` del archivo `pages/admin.html`.

---

## 📂 Estructura del Proyecto

```text
src/main/
├── java/com/ikenobo/ecommerce/
│   ├── controller/   # Endpoints de la API REST (Mapeo de peticiones HTTP)
│   ├── entity/       # Modelos de datos relacionales (Producto, Categoria)
│   ├── repository/   # Interfaces de persistencia (Spring Data JPA)
│   └── service/      # Capa de servicios y lógica de negocio core
└── resources/
    ├── static/       # Archivos del Frontend (index.html, pages/, css/, js/)
    └── application.properties  # Configuración de conexiones de BD
    
-----------------------------------------------------------------------------------

🏃 Cómo Ejecutar el Proyecto
Asegúrese de tener configurada su base de datos local en el archivo application.properties.

Ejecute la aplicación de Spring Boot mediante su IDE (IntelliJ, Eclipse) o usando Maven por terminal:

Bash
./mvnw spring-boot:run
Abra su navegador web e ingrese a: http://localhost:8080/index.html

Desarrollado por ALEJANDRA ESTEO - 2026