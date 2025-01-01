# Literalura 📚  

**Literalura** es una aplicación desarrollada con Java Spring que permite a los usuarios interactuar con libros y autores a través de diversas opciones. Utiliza la API de [Gutendex](https://gutendex.com/) para buscar información y almacena los resultados en una base de datos PostgreSQL.  

## Características 🚀  

La aplicación ofrece las siguientes opciones:  
1. **Buscar libro por título:**  
   - Realiza una búsqueda en la API de Gutendex utilizando el título del libro ingresado.  
   - Los resultados encontrados (libros y autores) se guardan automáticamente en la base de datos PostgreSQL.  

2. **Listar libros registrados:**  
   - Muestra todos los libros almacenados en la base de datos.  

3. **Listar autores registrados:**  
   - Muestra la lista de autores almacenados en la base de datos.  

4. **Listar autores vivos en un determinado año:**  
   - Filtra y muestra los autores que estaban vivos en el año especificado.  

5. **Listar libros por idioma:**  
   - Filtra y muestra los libros almacenados en la base de datos según el idioma seleccionado.  

---

## Tecnologías utilizadas 🛠️  

- **Backend:** Java Spring  
- **Base de datos:** PostgreSQL  
- **Consumo de API:** Gutendex API  
- **Gestión de dependencias:** Maven  
- **Servidor:** Tomcat (integrado en Spring Boot)  

---
