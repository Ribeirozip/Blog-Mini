CREATE TABLE post (
    id_post BIGINT PRIMARY KEY  UNIQUE NOT NULL,
    titulo_post VARCHAR(255) NOT NULL,
    image_post VARCHAR(255),
    conteudo_post TEXT NOT NULL,
    data_post DATE
);