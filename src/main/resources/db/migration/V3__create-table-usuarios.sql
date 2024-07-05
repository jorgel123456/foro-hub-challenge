create table usuarios(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    clave VARCHAR(300) NOT NULL,
    perfil_id bigint NOT NULL,
    activo TINYINT,

    constraint fk_usuario_perfil_id foreign key(perfil_id) references perfiles(id)
);