create table respuestas(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    mensaje VARCHAR(500) NOT NULL UNIQUE,
    fecha DATETIME NOT NULL,
    topico_id bigint NOT NULL,
    usuario_id bigint NOT NULL,
    activo TINYINT,
    soluccion TINYINT,

   constraint fk_respuesta_usuario_id foreign key(usuario_id) references usuarios(id) ON DELETE CASCADE,
   constraint fk_respuesta_topico_id foreign key(topico_id) references topicos(id) ON DELETE CASCADE
);