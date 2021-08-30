insert into categoria (nome) values ('Hardware');
insert into categoria (nome) values ('Eletrônico');
insert into categoria (nome) values ('UD');
insert into categoria (nome) values ('Cozinha');
insert into categoria (nome) values ('Móveis');

insert into marca (nome) values ('LG');
insert into marca (nome) values ('Razer');
insert into marca (nome) values ('AMD');
insert into marca (nome) values ('Nintendo');
insert into marca (nome) values ('Acer');

insert into produto(descricao, nome, valor, categoria_id, marca_id) values ('','Processador AMD Ryzen 5 5600X, Cache 35MB, 3.7GHz (4.6GHz Max Turbo), AM4 - 100-100000065BOX', 1689.90, 1, 3);
insert into produto(descricao, nome, valor, categoria_id, marca_id) values ('','Headset Gamer Razer Kraken V3 X USB, Chroma RGB, Surround 7.1, Drivers 40mm - RZ04-03750100-R3U1', 479.90, 1, 2);
insert into produto(descricao, nome, valor, categoria_id, marca_id) values ('','Nintendo Switch 32GB, 1x Joycon, Neon Azul/Vermelho - HBDSKABA2', 2499.00, 2, 4);
insert into produto(descricao, nome, valor, categoria_id, marca_id) values ('','Notebook Acer Aspire 3 AMD Ryzen 7-3700U, 8GB, 256GB SSD, 15.6 HD 1366x768, Windows 10 Home, Preto - A315-23-R3L9', 3799.90, 2, 5);

insert into imagem_produto (nome, produto_id) values ('processador-amd-ryzen-9-5950x-cache-72mb-3-4ghz-4-9ghz-max-turbo-am4-100-100000065box_1602603581_gg.jpg', 1);
insert into imagem_produto (nome, produto_id) values ('processador-amd-ryzen-9-5950x-cache-72mb-3-4ghz-4-9ghz-max-turbo-am4-100-100000065box_1602603580_gg.png', 1);
insert into imagem_produto (nome, produto_id) values ('headset-gamer-razer-kraken-v3-x-usb-chroma-rgb-surround-7-1-drivers-40mm-rz04-03750100-r3u1_1626445754_gg.jpg', 2);
insert into imagem_produto (nome, produto_id) values ('headset-gamer-razer-kraken-v3-x-usb-chroma-rgb-surround-7-1-drivers-40mm-rz04-03750100-r3u1_1626445755_gg.jpg', 2);
insert into imagem_produto (nome, produto_id) values ('headset-gamer-razer-kraken-v3-x-usb-chroma-rgb-surround-7-1-drivers-40mm-rz04-03750100-r3u1_1626445759_gg.jpg', 2);
insert into imagem_produto (nome, produto_id) values ('nintendo-switch-32gb-1x-joycon-neon-azul-vermelho-hbdskaba2_1610110214_gg.jpg', 3);
insert into imagem_produto (nome, produto_id) values ('nintendo-switch-32gb-1x-joycon-neon-azul-vermelho-hbdskaba2_1610110217_gg.jpg', 3);
insert into imagem_produto (nome, produto_id) values ('notebook-acer-aspire-3-amd-ryzen-7-3700u-8gb-256gb-ssd-15-6-hd-1366x768-windows-10-home-preto-a315-23-r3l9_1621355853_gg.jpg', 4);
insert into imagem_produto (nome, produto_id) values ('notebook-acer-aspire-3-amd-ryzen-7-3700u-8gb-256gb-ssd-15-6-hd-1366x768-windows-10-home-preto-a315-23-r3l9_1621355854_gg.jpg', 4);
insert into imagem_produto (nome, produto_id) values ('notebook-acer-aspire-3-amd-ryzen-7-3700u-8gb-256gb-ssd-15-6-hd-1366x768-windows-10-home-preto-a315-23-r3l9_1621355855_gg.jpg', 4);

INSERT INTO permissao (nome) values ('ROLE_ADMIN');
INSERT INTO permissao (nome) values ('ROLE_USER');

INSERT INTO usuario(nome, username, password) VALUES ('Administrador', 'admin', '$2a$10$D9WmHCEuUIhwfW8BEZ2ak.sOykECKZu.bZ4QJsjcxbfdlHnL5pkLG');
INSERT INTO usuario(nome, username, password) VALUES ('Teste', 'teste', '$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');

INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (1, 1);
INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (1, 2);
INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (2, 2);

insert into fornecedor (nome, cnpj) values ('Fornecedor 1', '73.661.932/0001-59');
insert into fornecedor (nome, cnpj) values ('Fornecedor 2', '20.148.302/0001-19');
insert into fornecedor (nome, cnpj) values ('Fornecedor 3', '15.481.327/0001-08');
