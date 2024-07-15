                                                                                  CHALLENGE FORO HUB

Este es un proyecto de Spring Boot 3, para gestionar tópicos y usuarios. El proyecto incluye características como creación, actualización, eliminación y búsqueda de tópicos,  usuarios, respuestas, cursos y la creación de perfiles , además de funcionalidades de autenticación y autorización.
1.	Tecnologías Utilizadas para la creación y utlizacion del API REST 

•	Java JDK v17
•	Maven v4
•	Spring Boot v3.2.6
•	Dependencias Utilizadas
a.	Spring Data JPA
b.	Spring Security
c.	Spring Web
d.	SpringDoc-OpenAPI
e.	Auth0
f.	MySQL v8
g.	Flyway Migration
h.	Validation
i.	Lombok
2.	Funcionamiento de las Endpoints

2.1.	Tópicos

a.	GET /topicos: Lista todos los tópicos.
b.	 POST /topicos: Crea un nuevo tópico.
c.	 GET /topicos/{id}: Muestra un tópico por ID.
d.	  PUT /topicos/{id}: Actualiza un tópico por ID.
e.	  DELETE /topicos/{id}: Elimina un tópico por ID.

2.2.	Usuarios
a.	GET /usuarios: Lista todos los usuarios.
b.	POST /usuarios: Crea un nuevo usuario.
c.	PUT /usuarios/{id}: Actualiza un usuario por ID.
d.	DELETE /usuarios/{id}`: Elimina un usuario por ID.

2.3.	Respuestas
a.	GET /repuestas: Lista todos las respuestas.
b.	POST /respuestas: Crea una nueva respuesta .
c.	PUT /respuestas/{id}: Actualiza una respuesta por ID.
d.	DELETE /respuestas/{id}`: Elimina una respuesta por ID.

2.4.	Cursos

a.	POST /cursos: Crea un nuevo curso.
2.5.	Perfiles

a.	POST /cursos: Crea un nuevo curso.

                                                                                              
                                                                                    Imagnes de los Enpoints 



![image](https://github.com/user-attachments/assets/6a978f2d-fda5-441c-ade8-0390e8473165)
![image](https://github.com/user-attachments/assets/68fa8263-105a-4686-bd75-52261c45af64)
![image](https://github.com/user-attachments/assets/91cb4eb7-d1e7-4129-acdb-73b468d2b20a)
![image](https://github.com/user-attachments/assets/b88abd13-0d1b-4414-b14e-8aec7e460aee)

3. Funcionalidad del foroHub
    a.	Crear, leer, actualizar y borrar usuario
    b.	Login de usuario
    c.	Crear, leer, leer por id, actualizar y borrar de un tópico.
    d.	Crear, leer, actualizar y borrar una respuesta.
    e.  Crerar un Curso.
    f.  Crear un perfil.

5. Comentarios del código
    a. Implementación de buenas prácticas del modelo REST
    •	Patrón de Arquitectura MVC (Modelo-vista-controlador) en donde en el paquete Domain (Model) están todos los datos de Topicos, Respuestas, Cursos, Usuarios y perfil,
      junto con sus servicios en donde se implementan las reglas de negocio. View que representaría el REST Client (Insomnia) y los controllers con los que se comunica:
      TopicoController, RespuestaController, UsuarioController y AutenticationController.
    •	Uso de la clase ResponseEntity de Spring con DTOs para personalizar cada uno de los retornos de las clases controllers.
    b. Validaciones según reglas de negocio y de integridad
    •	Una de las reglas de negocio de un foro es la de no permitir crear tópicos duplicados. Para eso se creó el paquete Validaciones, con la interface ValidadorDeTopicos
      dentro, en la que cada regla de negocio que se agregue implemente su método validar. Estas clases llevan la anotación de @Component, con lo cual en el servicio se
      puede llamar al método validar de cada una de las reglas de negocio. La entidad Usuario también tiene sus validaciones, por ejemplo que no se permita registrar un
      usuario con un perfil que no sea la de Estudiante.
      validadores.forEach(v -> v.validar(datosRegistroTopico));
    •	En cuanto a las validaciones de integridad, se usa la clase ValidacionDeIntegridad que extiende de RuntimeException, y es llamada cuando los datos ingresados no son encontrados.
    c. Tratamiento de errores
    •	Para evitar exponer información sensible como el stacktrace de una excepción al generarse un error, por ejemplo, al consultar un tópico inexistente. Para resolver esto se
      configura la propiedad server.error.stacktrace en applications.properties.
    •	En cuanto al tratamiento de los errores en sí, se crea la clase TratadorDeErrores en la que captura las excepciones lanzadas, ya sean tanto de errores 400, 404, errores de
      validación de integridad y/o de negocio.


3.	Autenticación y Autorización

El proyecto utiliza JWT para la autenticación y autorización. Asegúrate de configurar las variables necesarias en el archivo de propiedades.

![image](https://github.com/user-attachments/assets/883eb42f-459d-416b-aab5-73ab55524bf8)

4.	Documentacion SpringDoc(Springdoc-OpenAPI v2.6.0)
Para realizar la configuración del API se creo una clase que contiene la configuración específica para springdoc-openapi.
a.	customOpenAPI():
Este método configura un objeto OpenAPI personalizado.
b.	new OpenAPI():
Crea una nueva instancia de OpenAPI, que es el objeto principal que    representa la especificación OpenAPI.
c.	components(new Components()...):
Configura los componentes del objeto OpenAPI. Components es una clase que contiene varias configuraciones relacionadas con la seguridad, parámetros, esquemas, etc.
d.	addSecuritySchemes("bearer-key", new SecurityScheme()...):
Añade un esquema de seguridad con el nombre "bearer-key". Este esquema se utilizará para la autenticación.


e.	new SecurityScheme():
Crea una nueva instancia de SecurityScheme, que representa un esquema de seguridad para la API.
f.	type(SecurityScheme.Type.HTTP):
Establece el tipo de esquema de seguridad como HTTP.
g.	scheme("bearer"):
Define el esquema de autenticación HTTP como "bearer", que es comúnmente utilizado para tokens de portador.
h.	bearerFormat("JWT"):
Especifica que el formato del token de portador es JWT (JSON Web Token).

![image](https://github.com/user-attachments/assets/49faf50d-b038-46ec-8e1f-3d6304a6bb86)


Asimismo configuración en el archivo SecurityConfigurations.java en la siguiente línea de código, asignado todos los permisos a la API.

.requestMatchers("/swagger-ui.html", "/v3/api-docs/**","/swagger-ui/**").permitAll()

![image](https://github.com/user-attachments/assets/4546c7d7-66f3-4b58-a7d3-f533608d6be7)

5.	Instalación
Sigue estos pasos para configurar el proyecto localmente:
5.1.	Clona el repositorio:

•	Sh git clone https://github.com/tu-usuario/proyecto-SpringAlura.git
•	  cd proyecto-SpringAlura
5.2.	Configura la base de datos
    - Crea una base de datos en MySql.
    - Configura las credenciales en el archivo `application.properties` o `application.yml` ubicado en `src/main/resources/`.
5.3.	Instala las dependencias y compila el proyecto
•	    sh mvn clean install
5.4.	Ejecuta la aplicación
•	sh mvn spring-boot:run




6.	Uso

La aplicación estará disponible en `http://localhost:8080`.Utilizar los algunos Endpoints descrito líneas arriba:

7.	Contribuciones

Las contribuciones son bienvenidas. Para contribuir, por favor sigue estos pasos:

1.	Fork el repositorio.
2.	Clona tu fork localmente.
3.	Crea una nueva rama para tu característica (`git checkout -b feature-nueva-caracteristica`).
4.	Commit tus cambios (`git commit -m 'Agrega una nueva característica'`).
5.	Push tu rama (`git push origin feature-nueva-caracteristica`).
6.	Crea un Pull Request.

8.	Contacto

Para cualquier consulta o problema, por favor contacta a Jorge Luis Cabrejos Acosta, (jcabrejosacosta@gmail.com.pe).

¡Gracias por usar este proyecto!




