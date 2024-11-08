# Prueba técnica para Inditex

## Tecnologías Utilizadas

- IntelliJ IDEA
- Java 17
- Spring Boot 3
- H2 BBDD
- Liquibase
- JUnit 5
- PiTest
- Karate
- OpenAPI 3
- Maven

## Estructura del Proyecto

El proyecto está montado en base a una arquitectura hexagonal con los siguientes paquetes:

- `domain`: Contiene las entidades e interfaces de repositorios y servicios.
- `infrastructure`: Contiene la implementación de los repositorios y la configuración de servicios, BBDD y OpenApi
- `application`: Contiene la lógica de negocio de la aplicación.

El objetivo de esta estructura es desacoplar la lógica de negocio de la implementación de los adaptadores para conexiones
tanto de entrada (REST Adapter), como de salida (Repositorios, cloud, etc...)

## Enfoque API First

En este proyecto no se ha utilizado la aproximación con API First, pero en caso de haberlo hecho, una vez definido el fichero
openapi.yaml se utilizaría en el pom.xml el plugin `springdoc-openapi-maven-plugin`

## Documentación y Test de la API

- [Documentación de la API](http://localhost:8080/swagger-ui/index.html)

## Ejemplos de Uso del Endpoint REST

A continuación, se presenta un ejemplo de cómo realizar una petición al servicio REST utilizando cURL:

```bash
# Peticion para obtener el precio del producto 35455 del brand 1 el 2020-06-14 a las 10:00:00
curl -X 'POST' \
  'http://localhost:8080/api/v1/price' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "productId": 35455,
  "brandId": 1,
  "priceDate": "2020-06-14 10:00:00"
}'
```

Se ha implementado el endpoint de tipo POST por razones de seguridad, ya que la request no es visible y consume el body en
Json.

## Test

Se adjuntan los test unitarios, los de integración del Controller y la feature de karate para ejecutar los e2e. Los tests están implementados con Junit 5. En caso
de que la BBDD no fuera un H2, se podrían utilizar TestContainers para obtener una instancia de cualquier tipo de BBDD.

## Base de Datos

Se ha usado una BBDD en memoria H2, y se ha utilizado Liquibase como herramienta de gestión de cambios en la
misma.

## Instalación y Ejecución

1. Clona el repositorio: `git clone https://github.com/estebanmate/inditex-api.git`
2. Abre el proyecto mediante IDE y compílalo (clean package) o bien en la línea de comandos `mvn clean package`
3. Ejecuta el proyecto en el IDE o bien en el shell: java -jar target/inditex-api-1.0.0.jar
4. Existe un archivo en la raíz con la colección de Postman (INDITEX.postman_collection.json para poder probarlo o bien desde
   el interfaz de swagger)

## Ejecución de Tests

Para ejecutar las pruebas, se puede ejecutar directamente el archivo PriceControllerTest para los de integración o bien el archivo ChallengeTest para los e2e
o bien desde el shell: `mvn test` para ejecutarlos todos.
Los test de Mutación se pueden lanzar desde intellij con el plugin PIT Mutation Testing.
