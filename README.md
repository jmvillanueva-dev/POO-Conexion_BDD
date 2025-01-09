
# 🔧🌐 App Escritorio: Inicio de sesión, conexión y validación con base de datos.

## Descripción

Este proyecto consiste en una aplicación de escritorio desarrollada en Java utilizando Swing para la interfaz gráfica. El sistema permite gestionar información de usuarios, visualizando datos relacionados a través de una conexión con una base de datos MySQL. 📊

El sistema cuenta con una interfaz para el inicio de sesión y un panel donde se despliega la información del usuario autenticado. La información incluye detalles personales, académicos y de contacto almacenados en la base de datos. 



## Características
1. **Inicio de sesión seguro:**
 - Verificación del email y contraseña contra los datos almacenados en la base de datos.

- Mensajes de error amigables si las credenciales son incorrectas o no están registradas.

2. **Panel de información del usuario:**

- Visualización de datos detallados del usuario autenticado en un JTable.

- Mostrar el nombre completo del usuario en un JLabel.

3. **Conexión con base de datos:**

- Integración con MySQL para gestionar la información.

- Tablas relacionadas: datos_usuario y nombres_usuario.

- Interfaz amigable y responsiva:

4. **Diseñada utilizando Swing.**

- Adaptación automática de componentes a diferentes resoluciones de ventana.

## Requisitos 💻

- Java 17 o superior.

- Java Swing

- MySQL Server.

- Librería de conectores JDBC para MySQL.


# 📄📊 Configuración de la Base de Datos 

1. Crear una base de datos llamada *universidad.*

2. Ejecutar las siguientes instrucciones SQL para crear las tablas necesarias:

        CREATE TABLE datos_usuario (
            id_datos INT AUTO_INCREMENT PRIMARY KEY,
            id_usuario INT NOT NULL,
            direccion VARCHAR(255),
            telefono VARCHAR(20),
            celular VARCHAR(20),
            facultad VARCHAR(100),
            carrera VARCHAR(100),
            semestre VARCHAR(50),
            FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
                ON DELETE CASCADE
        );

        CREATE TABLE nombres_usuario (
            id_nombres INT AUTO_INCREMENT PRIMARY KEY,
            id_usuario INT NOT NULL,
            nombre VARCHAR(100) NOT NULL,
            apellido_paterno VARCHAR(100) NOT NULL,
            apellido_materno VARCHAR(100),
            FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
                ON DELETE CASCADE
        );

        CREATE TABLE usuarios (
            id_usuario INT AUTO_INCREMENT PRIMARY KEY,
            email VARCHAR(100) UNIQUE NOT NULL,
            contrasena VARCHAR(100) NOT NULL
        );

3. Insertar datos de prueba:
        
        INSERT INTO usuarios (email, contrasena) VALUE('usuario@example.com', '123456');

        INSERT INTO nombres_usuario (id_usuario, nombre, apellido_paterno, apellido_materno) VALUES (1, 'Juan', 'Pérez', 'López');

        INSERT INTO datos_usuario (id_usuario, direccion, telefono, celular, facultad, carrera, semestre) VALUES (1, 'Calle Falsa 123', '555-1234', '555-5678', 'Ingeniería', 'Sistemas', 'Quinto');

# 🖥️ Capturas de Pantalla

## 1. Pantalla de Inicio de Sesión

[![image.png](https://i.postimg.cc/qBxNd6zH/image.png)](https://postimg.cc/grrzVJVg)

## ✅ ❌ Validaciones y errores comunes:

[![image.png](https://i.postimg.cc/vmKNTmTQ/image.png)](https://postimg.cc/cKQm50fj)

[![image.png](https://i.postimg.cc/qR54k0Vn/image.png)](https://postimg.cc/cgwVmpJ4)

[![image.png](https://i.postimg.cc/Z5nk52Hx/image.png)](https://postimg.cc/vcJ2PPp4)

##  2. Panel de Información del Usuario

[![image.png](https://i.postimg.cc/nhJkdDjj/image.png)](https://postimg.cc/Czck5d4S)

# 👤 Autor
- Jhonny Villanueva M. | GitHub: [@jmvillanueva-dev](https://github.com/jmvillanueva-dev)
