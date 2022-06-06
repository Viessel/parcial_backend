DROP TABLE IF EXISTS odontologo;
create table if not exists odontologo (id int auto_increment primary key, nombre varchar(255), apellido varchar(255), matricula varchar(255));

INSERT INTO odontologo(id, nombre, apellido, matricula) VALUES(1, 'alfr', 'edo' , '123123');
INSERT INTO odontologo(id, nombre, apellido, matricula) VALUES(2, 'dam', 'ian' , '96969');
INSERT INTO odontologo(id, nombre, apellido, matricula) VALUES(3, 'car', 'los' , '343242');
