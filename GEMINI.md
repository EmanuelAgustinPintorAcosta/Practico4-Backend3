
# Resumen del Proyecto: AppAlumno

Este documento contiene el contexto completo del proyecto `AppAlumno` para reanudar el trabajo en futuras sesiones.

## 1. Objetivo del Proyecto

El proyecto es una aplicación web de gestión de estudiantes desarrollada en Java. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para las entidades principales: `Alumno`, `Carrera` y `Materia`.

## 2. Pila Tecnológica (Stack)

- **Lenguaje**: Java
- **Backend**: Jakarta Servlets (utilizando el namespace `jakarta.*`)
- **Frontend**: JavaServer Pages (JSP) con Jakarta Standard Tag Library (JSTL).
- **Base de Datos**: MySQL
- **Servidor de Aplicaciones**: GlassFish 7.0.15 (o cualquier servidor compatible con Jakarta EE 10).
- **Driver de Base de Datos**: MySQL Connector/J 8.x
- **Herramienta de Build**: Apache Ant (gestionado a través de NetBeans IDE).

## 3. Estructura de Paquetes

El código fuente Java (`src/java`) está organizado en los siguientes paquetes para seguir una arquitectura en capas:

- `com.app.modelo`: Contiene las clases de entidad (POJOs) que representan las tablas de la base de datos (`Alumno.java`, `Carrera.java`, etc.).
- `com.app.persistencia`: Contiene la clase de utilidad para la conexión a la base de datos (`ConexionDB.java`).
- `com.app.dao`: Contiene las clases del patrón Data Access Object (DAO), responsables de toda la interacción con la base de datos (`AlumnoDAO.java`, etc.).
- `com.app.controlador`: Contiene los Servlets que actúan como controladores en el patrón MVC (`AlumnoServlet.java`, etc.).

## 4. Configuración de la Base de Datos

### Credenciales de Conexión

La conexión se gestiona en `ConexionDB.java` con los siguientes parámetros:

- **URL**: `jdbc:mysql://localhost:3306/bdalumnos?useSSL=false`
- **Usuario**: `root`
- **Contraseña**: `1234`

### Esquema SQL

La estructura de la base de datos `bdalumnos` es la siguiente:

```sql
-- -----------------------------------------------------
-- Schema bdalumnos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdalumnos` ;
USE `bdalumnos` ;

-- -----------------------------------------------------
-- Table `bdalumnos`.`facultad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalumnos`.`facultad` (
  `idfacultad` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`idfacultad`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdalumnos`.`carrera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalumnos`.`carrera` (
  `idcarrera` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `facultad_idfacultad` INT NOT NULL,
  PRIMARY KEY (`idcarrera`, `facultad_idfacultad`),
  INDEX `fk_carrera_facultad_idx` (`facultad_idfacultad` ASC) VISIBLE,
  CONSTRAINT `fk_carrera_facultad`
    FOREIGN KEY (`facultad_idfacultad`)
    REFERENCES `bdalumnos`.`facultad` (`idfacultad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdalumnos`.`alumno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalumnos`.`alumno` (
  `idalumno` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `registro` INT NULL,
  `carrera_idcarrera` INT NOT NULL,
  `carrera_facultad_idfacultad` INT NOT NULL,
  PRIMARY KEY (`idalumno`),
  INDEX `fk_alumno_carrera1_idx` (`carrera_idcarrera` ASC, `carrera_facultad_idfacultad` ASC) VISIBLE,
  CONSTRAINT `fk_alumno_carrera1`
    FOREIGN KEY (`carrera_idcarrera` , `carrera_facultad_idfacultad`)
    REFERENCES `bdalumnos`.`carrera` (`idcarrera` , `facultad_idfacultad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdalumnos`.`materia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalumnos`.`materia` (
  `idmateria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`idmateria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bdalumnos`.`examen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdalumnos`.`examen` (
  `alumno_idalumno` INT NOT NULL,
  `materia_idmateria` INT NOT NULL,
  `fecha` TIMESTAMP(6) NULL,
  `nota` INT NULL,
  PRIMARY KEY (`alumno_idalumno`, `materia_idmateria`),
  INDEX `fk_alumno_has_materia_materia1_idx` (`materia_idmateria` ASC) VISIBLE,
  INDEX `fk_alumno_has_materia_alumno1_idx` (`alumno_idalumno` ASC) VISIBLE,
  CONSTRAINT `fk_alumno_has_materia_alumno1`
    FOREIGN KEY (`alumno_idalumno`)
    REFERENCES `bdalumnos`.`alumno` (`idalumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_alumno_has_materia_materia1`
    FOREIGN KEY (`materia_idmateria`)
    REFERENCES `bdalumnos`.`materia` (`idmateria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
```

## 5. Arquitectura y Puntos Clave de Implementación

- **Patrón MVC**: La aplicación sigue un patrón Modelo-Vista-Controlador, donde los Servlets actúan como Controladores, las clases del paquete `modelo` son el Modelo y los archivos JSP son la Vista.
- **Patrón DAO**: Se utiliza para abstraer y encapsular todo el acceso a la base de datos, dejando los Servlets libres de código SQL.
- **Hoja de Estilos Centralizada**: Toda la apariencia visual es controlada por el archivo `web/css/style.css`, que es enlazado desde todas las páginas JSP.
- **Enlaces dinámicos**: Todos los enlaces internos y las acciones de los formularios usan `${pageContext.request.contextPath}` para asegurar que funcionen correctamente sin importar dónde se despliegue la aplicación.

## 6. Puntos Críticos Resueltos (Contexto Vital)

1.  **Namespace de Jakarta EE**: El proyecto fue actualizado para usar `jakarta.servlet.*` en lugar de `javax.servlet.*` para ser compatible con servidores modernos como GlassFish 7. Este es un punto crucial a recordar.
2.  **Conexión a MySQL sin SSL**: Se encontró un error de conexión `SQLNonTransientConnectionException` relacionado con un `keystore.jks`. Se solucionó agregando el parámetro `?useSSL=false` a la URL de conexión JDBC en `ConexionDB.java`. Esta es la configuración correcta para el entorno de desarrollo local.
