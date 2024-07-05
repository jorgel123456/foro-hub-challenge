create table topicos(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(300) NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    fecha DATETIME NOT NULL,
    curso_id bigint NOT NULL,
    usuario_id bigint NOT NULL,
    activo TINYINT,

   constraint fk_respuestas_usuario_id foreign key(usuario_id) references usuarios(id) ON DELETE CASCADE,
   constraint fk_respuestas_curso_id foreign key(curso_id) references cursos(id) ON DELETE CASCADE
);