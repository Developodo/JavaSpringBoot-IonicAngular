# JavaSpringBoot-IonicAngular
Tutorial para montar un servicio Restful con Java (SpringBoot) y un cliente en Ionic

Se requiere un servidor local Mysql con una base de datos denominada api. Existe una única tabla items con los campos:
id: int, kp y autincrement
title: varchar(128)
description: varchar(256)

El backend es un proyecto Netbeans. Requiere la actualización del archivo application.properties con las configuración de la conexión a la base de datos. Para ejecutar la aplicación y desplegar el servicio solo se requiere la ejecución del mismo. Automáticamente, se el endpoint http://localhost/item estará disponible.

El frontend es un proyecto Ionic (se recomienda abrirlo en Visual Studio Code). Para instalar las dependencias y ejecutarlo se requiere los siguientes comandos:
1) npm install
2) ionic cordova platform add browser
3) (ejecutarlo) ionic cordova run browser --livereload
La aplicación cliente estará disponible en http://localhost:8100

Nota: no olvidar tener el servidor MySQL disponible en el momento de las pruebas.
