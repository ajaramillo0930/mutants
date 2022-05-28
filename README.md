# Detector de Mutantes Mercadolibre versión 1.0
Microservicio encargado de detectar si humano es mutante basándose en su secuencia de ADN.

## Tecnologías Usadas
| Tecnología | |
|------| ------| 
| Java | Spring boot
| Log4j | Postgresql
| Lombok | Bucket4j
| Junit |  Coverge
| Swagger |

## Ejecutando Mutants Localmente
Mutants es una aplicación de [Spring Boot](https://spring.io/guides/gs/spring-boot) creada con [Maven](https://spring.io/guides/gs/maven/). Puede crear un archivo jar y ejecutarlo desde la línea de comando:

```
git clone https://github.com/ajaramillo0930/mutants.git
cd mutants
./mvn package
java -jar target/*.jar
```

A continuación, puede acceder a mutants aquí: http://localhost:8080/mercadolibre/webjars/swagger-ui/index.html

## Trabajando con Mutants en tu IDE

## Pre-Requisitos:

* Java 8 o más reciente.
* Herramienta de línea de comando git.  (https://help.github.com/articles/set-up-git)
* Su IDE preferido.
    * [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/)
    * [Spring Tools Suite](https://spring.io/tools) (STS)
    * [Eclipse](https://www.eclipse.org/downloads/) con el complemento m2e. Nota: siga el proceso de instalación aquí: https://www.eclipse.org/m2e/

## Pasos:
1) En la línea de comando
  ```
    git clone https://github.com/ajaramillo0930/mutants.git
 ```
2) Dentro de IntelliJ IDEA

     En el menú principal, elija `Archivo -> Abrir` y seleccione Mutants (pom.xml). Haga clic en el botón `Abrir`.
	 
     Abra el archivo `application.properties` y configure las propiedades de acuerdo a sus preferencias y/o necesidades.
     
     Ejecute la aplicación haciendo clic derecho en la clase principal `MutantsApplication` y elija `Run 'MutantsApplication'`.
    
3) Navegar a Mutants

     Visite [http://localhost:8080/mercadolibre/webjars/swagger-ui/index.html](http://localhost:8080/mercadolibre/webjars/swagger-ui/index.html) en su navegador.

## Configuración de la Base de Datos
En su configuración predeterminada, Mutants utiliza Postgresql como base de datos persistente que se genera automáticamente al inicio de la aplicación.

Puede iniciar Postgres localmente con cualquier instalador que funcione para su sistema operativo: [Postgresql](https://www.postgresql.org/download/)

A continuación se muestran las propiedades más relevantes para la configuración de la base de datos:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=postgres
```
## Limitación de Peticiones (Rate Limiting - Bucket)
A continuación se muestran las propiedades para la configuración de la cantidad de peticiones por segundo:
```
bucket.requestsAllowed=1000
bucket.requestsDuration=1
```

## Registros (Logs)
El manejo de logs es gestionado por medio de la librería Log4j y su configuración se define en el archivo `log4j.properties`.
```
log4j.appender.file=org.apache.log4j.DailyRollingFileAppender
log4j.appender.file.append=true
log4j.appender.file.File=./logs/mutants.log
log4j.appender.file.threshold=INFO
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
```

## Autor
* **Andres Jaramillo**