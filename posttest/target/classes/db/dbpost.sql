CREATE TABLE post (
    id_post BIGINT PRIMARY KEY  UNIQUE NOT NULL,
    titulo_post VARCHAR(255) NOT NULL,
    image_post VARCHAR(255),
    conteudo_post TEXT NOT NULL,
    data_post DATE
);
CREATE TABLE user_db (
    id_user BIGINT PRIMARY KEY  UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    createAt VARCHAR(255)
);