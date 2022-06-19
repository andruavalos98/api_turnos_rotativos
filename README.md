# API de turnos rotativos
### Autor: Avalos Andrea Luciana

## Content

Esta api de turnos rotativos hecho para el lab de neoris, 2do tp 

## Desiciones para el desarrollo de la api
* La base de datos utilizada es h2 y fue adjunta como archivo dentro del proyecto
* Los empleados por simplicidad solo tienen nombre y id.
* Las pruebas fueron realizadas en Postman y las colecciones fueron adjuntadas en la carpeta "Postman"
* El codigo fue hecho lo mas descriptivo posible
* Supongo que los tipos principales de jornada estan creados por defecto para el correcto funcionamiento de la api pero se pueden crear mas

## Funcionamiento de la api
Para probar esta api dejo unas colecciones de postman con las peticiones pre cargadas y las rutas establecidas. 
Se pueden dar de alta tanto empleados como jornadas laborales, la api esta mas centrada en el modelo de 
jornadas laborales y no tanto en la de empleados. 

Carga de empleado con su jornada laboral, fecha, hora de entrada y salida
cuyos atributos pueden ser modificados

Mostrar cada empleado con todas sus jornadas laborales cargadas
