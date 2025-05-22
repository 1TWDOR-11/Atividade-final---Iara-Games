-- Criação da tabela de usuários
CREATE TABLE IF NOT EXISTS user_account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(90),
    email VARCHAR(255),
    password VARCHAR(128)
);

-- Criação da tabela de jogos
CREATE TABLE IF NOT EXISTS game (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60),
    genre VARCHAR(20),
    image VARCHAR(255)
);

-- Criação da tabela de favoritos
CREATE TABLE IF NOT EXISTS favorited (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    game_id BIGINT NOT NULL,
    CONSTRAINT fk_favorited_user_id FOREIGN KEY (user_id) REFERENCES user_account(id),
    CONSTRAINT fk_favorited_game_id FOREIGN KEY (game_id) REFERENCES game(id)
);

-- Criação da tabela de carrinhos
CREATE TABLE IF NOT EXISTS cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    items_quantity INT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_cart_user_id FOREIGN KEY (user_id) REFERENCES user_account(id)
);

-- Criação da tabela de itens do carrinho
CREATE TABLE IF NOT EXISTS cart_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_id BIGINT NOT NULL,
    cart_id BIGINT NOT NULL,
    CONSTRAINT fk_cart_item_item_id FOREIGN KEY (item_id) REFERENCES game(id),
    CONSTRAINT fk_cart_item_cart_id FOREIGN KEY (cart_id) REFERENCES cart(id)
    -- Se o "item_id" refere-se a um jogo, pode-se definir uma FK para games(id)
);

-- Criação da tabela de pedidos
CREATE TABLE IF NOT EXISTS order_table (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total DECIMAL(19, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_order_user_id FOREIGN KEY (user_id) REFERENCES user_account(id)
);

-- Criação da tabela de itens do pedido
CREATE TABLE IF NOT EXISTS order_item (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    CONSTRAINT fk_order_item_game_id FOREIGN KEY (game_id) REFERENCES game(id),
    CONSTRAINT fk_order_item_order_id FOREIGN KEY (order_id) REFERENCES order_table(id)
);

-- Criação da tabela de relacionamento entre usuário e jogo
CREATE TABLE IF NOT EXISTS user_game (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user_game_user_id FOREIGN KEY (user_id) REFERENCES user_account(id),
    CONSTRAINT fk_user_game_game_id FOREIGN KEY (game_id) REFERENCES game(id)
);
