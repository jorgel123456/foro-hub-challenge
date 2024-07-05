create table cursos(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    categoria varchar(100) NOT NULL,
    activo TINYINT
);