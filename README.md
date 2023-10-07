# Task management web description

This is a jsp/servlet web that has some features below:

- Admin and other users can log in and log out, all have active sessions with specific times.
- Admin manages staff's information, admin can create, edit, delete and grant permissions to other staff.
- Admin can create project by setting its name, start day and end date, admin can also edit and delete any project.
- Admin can create task related to project by setting its name, start day and end date, admin can also edit and delete any task.
- Every task can be assigned to only a staff, it can be changed every time.
- Admin can track staff's progress and view task statistics.
- Staff can view their tasks and profiles.

# Steps to install my project

- My project runs on IntelliJ IDEA, so install it first.
- Install Docker, it is a virtualized operating system that helps us install pre-packaged applications.
- Docker packages what we need into an image. Image is a term for an application encapsulated in an image.
- Images deployed to Docker are called containers.
- My project needs database to store data, in this project I choose MySQL. So download image of MySQL.
- To download the image, open cmd and type: "docker pull mysql".
- After downloading it, all downloaded images will be in the image bar at Docker.
- To start an image, open cmd and type: "-- name: mysql-java19 -p 3307:3306 -e MYSQL_ROOT_PASSWORD=admin123 -d mysql:latest".
- This Docker command above creates a MySQL container with the name "mysql-java19," maps port 3307 on your host to port 3306 inside the container, sets the root password to "admin123," and runs the container in the background using the latest MySQL image. This allows you to run a MySQL server in a Docker container with the specified configuration.
- After running the command successfully, a container named mysql-java19 will appear in the container bar at Docker.
- To use MySQL, download MySQL Workbench first.
- At MySQL Workbench, create a new connection in MySQL Workbench: Enter name, ip address, port (here it is 3307), username (here it is root) and password (here it is admin123).
- After connecting successfully, use my file named "database_script.sql" to create database.
