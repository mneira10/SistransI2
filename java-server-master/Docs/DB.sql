
DROP TABLE ZONAS_PREFERIDAS ;--
DROP TABLE PRODUCTOS_PREFERIDOS;--
DROP TABLE HISTORIAL;--
DROP TABLE CAPACIDADES_TECNICAS;--
DROP TABLE ITEMS;--
DROP TABLE INGREDIENTES_PRODUCTIND;--
DROP TABLE MENU_PRODUCTO_INDIVIDUAL;
DROP TABLE RESERVAS;--
DROP TABLE MENUS;--
DROP TABLE INGREDIENTES;--
DROP TABLE CARRITOS;--
DROP TABLE PRODUCTOS_INDIVIDUALES;--
DROP TABLE PRODUCTOS;--
DROP TABLE RESTAURANTES;--
DROP TABLE USUARIOS_REGISTRADOS;--
DROP TABLE ZONAS;--
DROP TABLE USUARIOS;--


CREATE TABLE USUARIOS (
id NUMBER(10)NOT NULL PRIMARY KEY,
nombres VARCHAR2(30) NOT NULL,
apellidos VARCHAR2(30) NOT NULL,
tipoId VARCHAR2(5) NOT NULL,
numId NUMBER(10) NOT NULL,
email VARCHAR2(50));

CREATE TABLE ZONAS(
nombre VARCHAR(20)NOT NULL PRIMARY KEY,
cerrado NUMBER(1) NOT NULL CHECK(cerrado=1 OR cerrado=0),
tipo VARCHAR(20),
aptoDiscap NUMBER(1)NOT NULL CHECK(aptoDiscap=1 OR aptoDiscap=0),
capacidad NUMBER(10) NOT NULL
);

CREATE TABLE USUARIOS_REGISTRADOS (
  ID NUMBER(10) NOT NULL PRIMARY KEY ,
  LOGIN VARCHAR2(20) NOT NULL,
  PASSWORD VARCHAR2(20) NOT NULL,
  TIPO VARCHAR(20) CHECK ((TIPO LIKE 'RESTAURANTE') OR (TIPO LIKE 'ADMIN') OR (TIPO=NULL)),
  CONSTRAINT FK_USUARIOS FOREIGN KEY (ID) REFERENCES USUARIOS(ID)
);

CREATE TABLE RESTAURANTES (
  ID NUMBER(10) PRIMARY KEY ,
  NOMBRE VARCHAR2(50) NOT NULL ,
  TIPO VARCHAR2(20),
  PAGINA_WEB VARCHAR2(100),
  ZONA VARCHAR2(20) NOT NULL,
  ID_ADMIN NUMBER(10) NOT NULL,
  CONSTRAINT FK_ZONA FOREIGN KEY (ZONA) REFERENCES ZONAS(NOMBRE),
  CONSTRAINT FK_ID_ADMIN FOREIGN KEY (ID_ADMIN) REFERENCES USUARIOS_REGISTRADOS(ID)
);

CREATE TABLE PRODUCTOS(
id NUMBER(10) NOT NULL PRIMARY KEY,
nombre VARCHAR2(50) NOT NULL,
descrEsp VARCHAR2(200),
descrIng VARCHAR2(200),
tPrep NUMBER(15,5) NOT NULL,
costo NUMBER(15,5) NOT NULL,
precio NUMBER(15,5) NOT NULL,
restaurantes_id NUMBER(10) NOT NULL,
CONSTRAINT FK_RESTAURANTES_PRODUCTOS
        FOREIGN KEY (restaurantes_id)
        REFERENCES RESTAURANTES(id)
);

CREATE TABLE PRODUCTOS_INDIVIDUALES(
id NUMBER(10) NOT NULL PRIMARY KEY,
categoria VARCHAR2(20) NOT NULL,
grupo NUMBER(10) NOT NULL,
cantidadDisponible NUMBER(10) NOT NULL,
maximo NUMBER(10) NOT NULL,
CONSTRAINT FK_PRODUCTOS
        FOREIGN KEY (id)
        REFERENCES PRODUCTOS(id)
);

CREATE TABLE CARRITOS(
id NUMBER(10) NOT NULL PRIMARY KEY,
nombre VARCHAR2(20) NOT NULL,
costo NUMBER(15,5) NOT NULL,
usuario_id NUMBER(10) NOT NULL,
CONSTRAINT FK_CARRITOS
        FOREIGN KEY (usuario_id)
        REFERENCES USUARIOS(id)
);

CREATE TABLE INGREDIENTES (
  ID NUMBER(10) NOT NULL PRIMARY KEY,
  NOMBRE VARCHAR2(30) NOT NULL,
  DESCRESP VARCHAR2(200) ,
  DESCRING VARCHAR(200) ,
  GRUPO INTEGER NOT NULL,
  RESTAURANTE NUMBER(10),
  CONSTRAINT FK_RESTAURANTE FOREIGN KEY (RESTAURANTE) REFERENCES RESTAURANTES(ID)
);

CREATE TABLE MENUS (
  ID NUMBER(10) NOT NULL PRIMARY KEY,
  CONSTRAINT FK_PRODUCTOS_MENUS FOREIGN KEY (ID) REFERENCES PRODUCTOS(ID)
);

CREATE TABLE RESERVAS(
id NUMBER(10) PRIMARY KEY,
fechaInicio DATE NOT NULL,
fechaFin DATE NOT NULL,
numComensales NUMBER(10) NOT NULL,
usuariosRegistrados_id NUMBER(10) NOT NULL,
zonas_nombre VARCHAR(20) NOT NULL,
menus_productos_id NUMBER(10),
CONSTRAINT FK_USUARIOSREG FOREIGN KEY(usuariosRegistrados_id)
REFERENCES USUARIOS_REGISTRADOS(ID),
CONSTRAINT FK_ZONASNOM FOREIGN KEY(zonas_nombre)
REFERENCES ZONAS(NOMBRE),
CONSTRAINT FK_MENUSPRODUCTO FOREIGN KEY(menus_productos_id)
REFERENCES MENUS(ID)
);

CREATE TABLE MENU_PRODUCTO_INDIVIDUAL (
  ID_MENU NUMBER(10) ,
  ID_PROD_INDIVIDUAL NUMBER(10) ,
  CONSTRAINT FK_MENU_PRODINF FOREIGN KEY (ID_MENU) REFERENCES MENUS(ID),
  CONSTRAINT FK_PRODINF_MENU FOREIGN KEY (ID_PROD_INDIVIDUAL) REFERENCES PRODUCTOS_INDIVIDUALES(ID),
  CONSTRAINT MENU_PRODUCTO_INDIVIDUAL_PK PRIMARY KEY (ID_MENU,ID_PROD_INDIVIDUAL)
);
CREATE TABLE INGREDIENTES_PRODUCTIND (
  ID_INGREDIENTE NUMBER(10),
  ID_PRODINDIVIDUAL NUMBER(10),
  CONSTRAINT FK_INGRED_PRODIND FOREIGN KEY (ID_INGREDIENTE) REFERENCES INGREDIENTES(ID),
  CONSTRAINT FK_PRODIND_INGRED FOREIGN KEY (ID_PRODINDIVIDUAL) REFERENCES PRODUCTOS_INDIVIDUALES(ID),
  CONSTRAINT INGRED_PRODUCTIND_PK PRIMARY KEY (ID_INGREDIENTE,ID_PRODINDIVIDUAL)
);
CREATE TABLE ITEMS (
  ID NUMBER(10) PRIMARY KEY ,
  PERSONALIZACION VARCHAR2(100),
  ID_PRODUCTO NUMBER(10) NOT NULL ,
  ID_CARRITO NUMBER(10),
  CONSTRAINT ITEM_PRODUCTO_FK FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTOS(ID),
  CONSTRAINT ITEM_CARRITO_FK FOREIGN KEY (ID_CARRITO) REFERENCES CARRITOS(ID)
);
CREATE TABLE CAPACIDADES_TECNICAS (
  NOMBRE VARCHAR2(30) PRIMARY KEY ,
  DESCRIPCION VARCHAR2(200),
  ZONA VARCHAR2(20) NOT NULL ,
  CONSTRAINT CAPACIDADES_TECNICAS_ZONA_FK FOREIGN KEY (ZONA) REFERENCES ZONAS(NOMBRE)
);
CREATE TABLE HISTORIAL (
  ID NUMBER(20) NOT NULL,
  ID_PRODUCTO NUMBER(10) NOT NULL,
  ID_USUARIO_REGISTRADO NUMBER(10) NOT NULL,
  FECHA DATE NOT NULL,
  CONSTRAINT HISTORIAL_PROD_FK FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTOS(ID),
  CONSTRAINT HISTORIAL_USR_REG_FK FOREIGN KEY (ID_USUARIO_REGISTRADO) REFERENCES USUARIOS_REGISTRADOS(ID),
  CONSTRAINT HISTORIAL_PK PRIMARY KEY (ID)
);
CREATE TABLE PRODUCTOS_PREFERIDOS (
  ID_PRODUCTO NUMBER(10) NOT NULL,
  ID_USUARIO_REGISTRADO NUMBER(10) NOT NULL,
  CONSTRAINT PROD_PREF_PROD_FK FOREIGN KEY (ID_PRODUCTO) REFERENCES PRODUCTOS(ID),
  CONSTRAINT PROD_PREF_USR_REG FOREIGN KEY (ID_USUARIO_REGISTRADO) REFERENCES USUARIOS_REGISTRADOS(ID),
  CONSTRAINT PRODUCTOS_PREFERIDOS_PK PRIMARY KEY (ID_PRODUCTO,ID_USUARIO_REGISTRADO)
);

CREATE TABLE ZONAS_PREFERIDAS(
usuariosRegistrados_id NUMBER(10) NOT NULL,
zonas_nombre VARCHAR(20) NOT NULL,
CONSTRAINT FK_USUARIOSREG1 FOREIGN KEY(usuariosRegistrados_id)
REFERENCES USUARIOS_REGISTRADOS(ID),
CONSTRAINT FK_ZONASNOM1 FOREIGN KEY(zonas_nombre)
REFERENCES ZONAS(NOMBRE),
CONSTRAINT PK_ZONAS_PREFERIDAS PRIMARY KEY(usuariosRegistrados_id, zonas_nombre)
);



insert into USUARIOS (id, nombres, apellidos, tipoId, numId, email) values (1, 'Trudi', 'Verdy', 'III', 6901366213, 'tVerdy@hotmail.com');
insert into USUARIOS (id, nombres, apellidos, tipoId, numId, email) values (2, 'Freedman', 'Morriarty', 'III', 6967895656, 'fMorri@live.con');
insert into USUARIOS (id, nombres, apellidos, tipoId, numId, email) values (3, 'Free', 'Yuill', 'II', 0877777683, 'FreYu@outlook.com');
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (4, 'Tommie', 'Knowler', 'III', 4659921875);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (5, 'Purcell', 'Rowlson', 'III', 0260703893);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId, email) values (6, 'Farlie', 'Staddart', 'III', 4167106299, 'staddart@gmail.com');
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (7, 'Danya', 'Blandamore', 'IV', 2257896254);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (8, 'Costa', 'Furbank', 'Jr', 6805010527);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (9, 'Lura', 'Serfati', 'IV', 4569603211);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (10, 'Renelle', 'Kleuer', 'IV', 1980475512);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (11, 'Will', 'Kerrod', 'IV', 2629542521);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (12, 'Vanny', 'Dullard', 'II', 2938374283);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (13, 'Humfrid', 'Putten', 'Jr', 2141417112);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (14, 'Lucille', 'Giovanizio', 'IV', 7894868949);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (15, 'Gar', 'Davern', 'Sr', 7555664135);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (16, 'Elvera', 'Mugford', 'III', 705782158);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (17, 'Lazaro', 'Cowwell', 'Sr', 4598700068);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (18, 'Donelle', 'Kobierra', 'II', 0929554574);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (19, 'Dacy', 'Piatti', 'Sr', 6750167489);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (20, 'Eudora', 'Ioannou', 'Jr', 0880230037);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (21, 'Danella', 'Esche', 'III', 6109529265);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (22, 'Mab', 'Scotchbrook', 'III', 3352857725);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (23, 'Glynn', 'Spera', 'II', 9860426910);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (24, 'Monika', 'Olliffe', 'II', 6593611778);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (25, 'Anica', 'Andrioli', 'IV', 0734882777);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (26, 'Henrik', 'Cathro', 'II', 9675024755);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (27, 'Valentina', 'Sanderson', 'Jr', 8281165758);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (28, 'Franz', 'Spiller', 'II', 5551296032);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (29, 'Kai', 'Belcham', 'III', 8012639416);
insert into USUARIOS (id, nombres, apellidos, tipoId, numId) values (30, 'Debbi', 'Denyukin', 'II', 3006210964);


insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Colombia', 1, 'odio', 1, '262');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Venezuela', 0, 'orci', 1, '087');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Argentina', 1, 'justo', 1, '399');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Brasil', 0, 'maecenas', 0, '253');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Peru', 0, 'libero', 1, '921');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Bolivia', 1, 'nullam', 1, '122');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Chile', 1, 'ut', 1, '224');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Ecuador', 0, 'sociis', 1, '721');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Mexico', 0, 'pede', 1, '175');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Guatemala', 1, 'lorem', 1, '916');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Nicaragua', 0, 'placerat', 1, '562');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Jamaica', 1, 'justo', 1, '232');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Cuba', 0, 'lobortis', 0, '799');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Haiti', 1, 'dapibus', 0, '220');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Republica Dominicana', 0, 'ante', 0, '350');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Panama', 1, 'sapien', 0, '037');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Puerto Rico', 0, 'quam', 0, '968');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('U.S.A.', 1, 'nulla', 0, '973');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Canada', 0, 'auctor', 0, '892');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Costa Rica', 0, 'nam', 1, '092');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Paraguay', 1, 'ridiculus', 0, '921');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Uruguay', 0, 'est', 0, '080');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Honduras', 0, 'proin', 1, '556');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Belize', 1, 'interdum', 0, '218');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('El Salvador', 1, 'vestibulum', 0, '292');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Guyana', 1, 'molestie', 0, '515');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Suriname', 0, 'volutpat', 1, '858');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Guyana Francesa', 0, 'a', 0, '670');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Las Bahamas', 0, 'vel', 0, '372');
insert into ZONAS (nombre, cerrado, tipo, aptoDiscap, capacidad) values ('Barbados', 1, 'quis', 0, '557');

insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (30, 'aramsted0', 'jvL8CjeAQSD', 'RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (6, 'restaurante', 'password', 'RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (22, 'admin', 'password', 'ADMIN');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (3, 'abirbeck2', '3qZyk5LU','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (19, 'usuario', 'password','USUARIO');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (9, 'gmapam4', '8EI2G96Shh7','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (21, 'fhusk5', 'Dxrfs5a2','USUARIO');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (29, 'kbaglin6', 'Wl9s6CnsDEQe','USUARIO');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (1, 'bcraythorn7', 'ogAtxh0D','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (18, 'zherion8', 'mnxQytZ4w','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (24, 'rfildes9', 'Fo5CBv8','USUARIO');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (13, 'dmarplea', 'goLePW5wr','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (4, 'gdallmanb', 'u5QdGVOI7nz','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (7, 'stumiotoc', 'RBZ5FshKRSwQ','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (5, 'lkiddd', 'gTTBQCmh95i','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (16, 'rwigglesworthe', 'TtZAoIhHo3Ea','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (2, 'mhorsellf', 'oW7n38DGK6SM','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (11, 'bcarcassg', 'v3qu00c6','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (17, 'pkupish', 'RykBUYdV','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (15, 'myarrowi', '2SDKRmgooYv','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (8, 'lsloatj', '3zTyXyEjnDk','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (10, 'lsloat5', '3zTyXyEjnDk','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (12, 'lsloat35WE', '3zTyXyEjnDk','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (14, 'lsloSDGWt35WE', '3zTyXyEjnDk','RESTAURANTE');
insert into USUARIOS_REGISTRADOS (ID, LOGIN, PASSWORD, TIPO) values (20, 'lslefsdvWE', '3zTyXfdjsdjnDk','RESTAURANTE');

insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (1, 'Realblab', 'jcb', 'http://over-blog.com', 'national',1);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (2, 'Thoughtstorm', 'jcb', 'http://wufoo.com', 'national',2);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (3, 'Izio', 'americanexpress', 'http://alexa.com', 'national',3);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (4, 'Zooxo', 'jcb', 'https://whitehouse.gov', 'national',4);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (5, 'Wordpedia', 'jcb', 'http://github.io', 'national',5);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (6, 'Quire', 'jcb', 'http://wikipedia.org', 'national',6);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (7, 'Zoombox', 'switch', 'https://huffingtonpost.com', 'national',7);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (8, 'Yacero', 'visa-electron', 'https://e-recht24.de', 'national',8);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (9, 'Dynabox', 'maestro', 'http://networksolutions.com', 'national',9);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (10, 'Blogspan', 'americanexpress', 'https://wunderground.com', 'national',10);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (11, 'Aimbu', 'mastercard', 'https://barnesandnoble.com', 'complexity',11);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (12, 'Skynoodle', 'maestro', 'https://edublogs.org', 'complexity',12);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (13, 'Tazz', 'mastercard', 'https://fema.gov', 'complexity',13);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (14, 'Photolist', 'instapayment', 'http://reddit.com', 'complexity',14);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (16, 'Dynazzy', 'diners-club-us-ca', 'https://japanpost.jp', 'complexity',15);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (15, 'Eimbee', 'bankcard', 'http://usa.gov', 'complexity',16);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (17, 'Fliptune', 'jcb', 'https://vinaora.com', 'complexity',17);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (18, 'Realblab', 'diners-club', 'http://tripadvisor.com', 'complexity',18);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (19, 'Eabox', 'visa', 'https://sourceforge.net', 'complexity',19);
insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (20, 'Centidel', 'jcb', 'https://soundcloud.com', 'complexity',20);

-- insert into RESTAURANTES (ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN) values (120, 'CURTIS BAY ATHLETIC CLUB', 'Thai', 'CURTISBAYATHLETICCLUB130@whatev.com', 'Paraguay', 162026);

insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (1, 'Mandras tree shrew', 'molestie hendrerit at vulputate vitae nisl aenean', 'aliquet ultrices erat tortor', 22, 21339.69, 45243, 6);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (2, 'Netted rock dragon', 'nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis natoque', 'habitasse platea dictumst maecenas ut', 32, 35442.15, 271765, 11);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (3, 'Anaconda ', 'est risus auctor sed tristique', 'vestibulum aliquet ultrices erat', 30, 121782.73, 287390, 1);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (4, 'Goanna lizard', 'amet diam in magna bibendum imperdiet nullam orci pede venenatis non', 'nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus at velit vivamus vel nulla eget', 23, 116930.92, 133493, 11);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (5, 'Yellow mongoose', 'ultrices phasellus', 'ut erat id mauris', 6, 104299.79, 147051, 5);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (6, 'Eagle', 'aenean auctor', 'sociis natoque penatibus et magnis dis parturient montes nascetur', 43, 193678.29, 149901, 2);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (7, 'Blacksmith plover', 'nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis', 'eget tempus vel pede morbi porttitor lorem id', 45, 128723.01, 295963, 11);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (8, 'Eagle, bald', 'euismod scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula', 'sem sed sagittis nam congue risus semper porta volutpat quam', 54, 120765.08, 69856, 9);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (9, 'Possum, ring-tailed', 'purus phasellus in felis donec semper sapien', 'consequat metus sapien ut nunc vestibulum ante ipsum primis', 53, 18500.02, 221601, 18);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (10, 'Butterfly ', 'cubilia curae nulla dapibus dolor vel est donec odio', 'lacinia eget tincidunt eget tempus vel pede', 12, 162153.6, 25586, 10);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (11, 'Red-shouldered ', 'primis in', 'non mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non sodales', 37, 22461.11, 250959, 7);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (12, 'Southern boubou', 'ipsum integer a nibh in quis justo maecenas rhoncus aliquam lacus morbi', 'sed accumsan felis ut at dolor quis odio consequat varius integer ac leo pellentesque ultrices mattis odio donec vitae', 2, 179693.89, 193134, 7);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (13, 'Gonolek, burchell''s', 'ligula suspendisse ornare consequat lectus in', 'felis donec semper sapien a libero nam dui proin leo odio porttitor id consequat', 56, 52920.64, 295642, 1);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (14, 'Grey lourie', 'morbi odio odio elementum eu interdum eu tincidunt in leo maecenas pulvinar lobortis est phasellus', 'eget elit sodales scelerisque mauris sit amet eros suspendisse accumsan', 22, 109958.09, 236561, 5);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (15, 'Greater ', 'at vulputate vitae nisl aenean lectus', 'quam nec dui luctus rutrum nulla tellus in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet', 48, 77988.05, 31566, 12);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (16, 'Whale, killer', 'tempor turpis nec euismod scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula nec sem duis', 'risus praesent lectus vestibulum quam sapien varius ut blandit non interdum in ante', 12, 145000.8, 200346, 16);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (17, 'Bushbuck', 'ut mauris eget massa tempor convallis nulla neque libero convallis eget eleifend', 'nam congue risus semper porta volutpat quam pede lobortis ligula', 57, 114686.56, 18629, 1);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (18, 'Flicker, field', 'cras pellentesque volutpat dui maecenas tristique', 'felis fusce posuere felis', 9, 81187.77, 154605, 14);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (19, 'Whale', 'nulla suscipit ligula in lacus curabitur at ipsum ac tellus semper interdum mauris ullamcorper purus sit', 'est donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia nisi venenatis tristique fusce congue', 12, 9747.54, 143452, 13);
insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values (20, 'Russian dragonfly', 'viverra eget congue eget semper rutrum nulla nunc purus phasellus in felis donec', 'ultrices mattis odio donec vitae nisi nam ultrices', 46, 150999.92, 130181, 16);

-- insert into PRODUCTOS (id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id) values  (21, 'Emincés de champignons', 'bla bla espanol', 'bla bla ingles', 110, 21416.1352081,18755.9254858, 67);

insert into PRODUCTOS_INDIVIDUALES (id, categoria, grupo, cantidadDisponible, maximo) values (1, 'plato_fuerte', 5, 23, 20);
insert into PRODUCTOS_INDIVIDUALES (id, categoria, grupo, cantidadDisponible, maximo) values (2, 'acompanamiento', 5, 5, 10);
insert into PRODUCTOS_INDIVIDUALES (id, categoria, grupo, cantidadDisponible, maximo) values (3, 'acompanamiento', 1, 6, 10);
insert into PRODUCTOS_INDIVIDUALES (id, categoria, grupo, cantidadDisponible, maximo) values (4, 'acompanamiento', 1, 14, 20);
insert into PRODUCTOS_INDIVIDUALES (id, categoria, grupo, cantidadDisponible, maximo) values (5, 'bebida', 3, 18, 20);
insert into PRODUCTOS_INDIVIDUALES (id, categoria, grupo, cantidadDisponible, maximo) values (6, 'acompanamiento', 2, 15, 20);
insert into PRODUCTOS_INDIVIDUALES (id, categoria, grupo, cantidadDisponible, maximo) values (7, 'acompanamiento', 1, 17, 20);
insert into PRODUCTOS_INDIVIDUALES (id, categoria, grupo, cantidadDisponible, maximo) values (8, 'plato_fuerte', 3, 28, 30);
insert into PRODUCTOS_INDIVIDUALES (id, categoria, grupo, cantidadDisponible, maximo) values (9, 'bebida', 4, 3, 10);
insert into PRODUCTOS_INDIVIDUALES (id, categoria, grupo, cantidadDisponible, maximo) values (10, 'acompanamiento', 5, 30, 30);

insert into CARRITOS (id, nombre, costo, usuario_id) values (1, 'Roombo', 95755.76, 8);
insert into CARRITOS (id, nombre, costo, usuario_id) values (2, 'Abatz', 68426.11, 27);
insert into CARRITOS (id, nombre, costo, usuario_id) values (3, 'Skibox', 59257.69, 26);
insert into CARRITOS (id, nombre, costo, usuario_id) values (4, 'Skimia', 42776.63, 18);
insert into CARRITOS (id, nombre, costo, usuario_id) values (5, 'Flashspan', 61776.57, 8);
insert into CARRITOS (id, nombre, costo, usuario_id) values (6, 'Mynte', 23244.2, 23);
insert into CARRITOS (id, nombre, costo, usuario_id) values (7, 'Meevee', 25332.18, 6);
insert into CARRITOS (id, nombre, costo, usuario_id) values (8, 'Oyoyo', 14840.28, 9);
insert into CARRITOS (id, nombre, costo, usuario_id) values (9, 'Demimbu', 67973.76, 7);
insert into CARRITOS (id, nombre, costo, usuario_id) values (10, 'Meezzy', 83282.25, 17);
insert into CARRITOS (id, nombre, costo, usuario_id) values (11, 'Kwilith', 61545.76, 3);
insert into CARRITOS (id, nombre, costo, usuario_id) values (12, 'Photospace', 59393.66, 3);
insert into CARRITOS (id, nombre, costo, usuario_id) values (13, 'Oodoo', 76766.76, 10);
insert into CARRITOS (id, nombre, costo, usuario_id) values (14, 'Eimbee', 72368.4, 17);
insert into CARRITOS (id, nombre, costo, usuario_id) values (15, 'Feedspan', 88585.59, 17);
insert into CARRITOS (id, nombre, costo, usuario_id) values (16, 'Livetube', 35366.39, 24);
insert into CARRITOS (id, nombre, costo, usuario_id) values (17, 'Zoozzy', 79910.37, 20);
insert into CARRITOS (id, nombre, costo, usuario_id) values (18, 'Meembee', 61690.41, 4);
insert into CARRITOS (id, nombre, costo, usuario_id) values (19, 'Chatterbridge', 99232.8, 8);
insert into CARRITOS (id, nombre, costo, usuario_id) values (20, 'Voonyx', 14733.24, 8);
insert into CARRITOS (id, nombre, costo, usuario_id) values (21, 'Demivee', 39196.92, 6);
insert into CARRITOS (id, nombre, costo, usuario_id) values (22, 'Skinder', 81082.57, 27);
insert into CARRITOS (id, nombre, costo, usuario_id) values (23, 'Yabox', 57161.17, 15);
insert into CARRITOS (id, nombre, costo, usuario_id) values (24, 'Shuffledrive', 22956.42, 13);
insert into CARRITOS (id, nombre, costo, usuario_id) values (25, 'Flipstorm', 50346.27, 8);
insert into CARRITOS (id, nombre, costo, usuario_id) values (26, 'Jabbercube', 67452.76, 13);
insert into CARRITOS (id, nombre, costo, usuario_id) values (27, 'DabZ', 86912.23, 30);
insert into CARRITOS (id, nombre, costo, usuario_id) values (28, 'Devshare', 87112.3, 3);
insert into CARRITOS (id, nombre, costo, usuario_id) values (29, 'Camimbo', 97195.8, 16);
insert into CARRITOS (id, nombre, costo, usuario_id) values (30, 'Jabberstorm', 64928.74, 3);
insert into CARRITOS (id, nombre, costo, usuario_id) values (31, 'Twitterworks', 88997.57, 2);
insert into CARRITOS (id, nombre, costo, usuario_id) values (32, 'Yadel', 4408.08, 20);
insert into CARRITOS (id, nombre, costo, usuario_id) values (33, 'Kazio', 76255.43, 1);
insert into CARRITOS (id, nombre, costo, usuario_id) values (34, 'Photobean', 26436.14, 26);
insert into CARRITOS (id, nombre, costo, usuario_id) values (35, 'Yodoo', 81593.63, 29);
insert into CARRITOS (id, nombre, costo, usuario_id) values (36, 'InnoZ', 55460.66, 18);
insert into CARRITOS (id, nombre, costo, usuario_id) values (37, 'Tanoodle', 74090.27, 5);
insert into CARRITOS (id, nombre, costo, usuario_id) values (38, 'Gevee', 46621.9, 30);
insert into CARRITOS (id, nombre, costo, usuario_id) values (39, 'Blogspan', 82764.98, 30);
insert into CARRITOS (id, nombre, costo, usuario_id) values (40, 'Livepath', 37103.53, 22);

insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (1, 'Parmelinopsis', 'tincidunt eget tempus vel pede morbi porttitor lorem', 'natoque penatibus et magnis dis parturient montes nascetur ridiculus', 7, 21);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (2, 'Toothed Brake', 'primis in faucibus', 'dui', 7, 21);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (3, 'Woodrush Flatsedge', 'volutpat quam pede lobortis ligula', 'tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed', 3, 22);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (4, 'Little Weaselsnout', 'quisque id', 'purus phasellus in felis donec semper sapien a libero nam dui', 6, 22);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (5, 'Desert Suncup', 'amet lobortis sapien sapien non mi integer', 'non mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam orci pede', 10, 22);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (6, 'Medlar', 'eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor duis mattis egestas metus aenean fermentum', 'porttitor id consequat in consequat ut nulla sed accumsan felis ut at', 8, 23);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (7, 'South American Tobacco', 'eu mi nulla ac enim in tempor turpis nec euismod scelerisque quam', 'vel', 6, 23);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (8, 'Hemlock Dwarf Mistletoe', 'ultrices posuere cubilia curae nulla dapibus dolor vel', 'sapien a', 4, 23);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (9, 'Sweetwater Draba', 'est quam pharetra magna ac consequat', 'curae mauris viverra diam vitae quam suspendisse potenti nullam porttitor lacus at turpis donec posuere', 4, 23);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (10, 'Jacob''s-ladder', 'ipsum primis in faucibus orci', 'nulla integer', 4, 23);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (11, 'Lespedeza', 'massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur', 'mauris ullamcorper purus sit amet nulla quisque arcu libero rutrum ac lobortis vel', 3, 23);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (12, 'Summer Farewell', 'faucibus accumsan odio', 'leo pellentesque ultrices mattis odio donec vitae nisi', 4, 24);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (13, 'Smallflower Lupine', 'primis in faucibus', 'nullam orci pede venenatis non sodales sed tincidunt', 4, 24);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (14, 'Hybrid Pitcherplant', 'a ipsum integer a nibh', 'nullam orci pede venenatis non sodales sed', 4, 25);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (15, 'Diamondleaf Lacefern', 'aliquet massa id lobortis convallis tortor risus dapibus augue vel accumsan tellus nisi eu orci', 'eu interdum eu tincidunt', 2, 25);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (16, 'Clokey''s Milkvetch', 'nonummy maecenas tincidunt lacus at velit vivamus vel nulla eget eros', 'in tempus sit amet sem fusce consequat nulla nisl nunc nisl', 5, 25);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (17, 'Elko Rockcress', 'justo pellentesque viverra pede ac diam cras pellentesque volutpat dui maecenas tristique est et tempus semper est quam pharetra magna', 'vulputate nonummy maecenas tincidunt', 1, 25);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (18, 'Showy Rattlebox', 'in hac habitasse platea dictumst morbi vestibulum velit', 'nulla dapibus dolor', 6, 25);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (19, 'Hamelia', 'nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit', 'integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed magna at nunc commodo placerat praesent blandit nam nulla', 7, 26);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (20, 'Chinese Licorice', 'in magna bibendum', 'mauris morbi non lectus aliquam sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non sodales sed tincidunt', 8, 26);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (21, 'Milktree', 'aliquet', 'primis in faucibus orci luctus et ultrices posuere cubilia curae mauris viverra', 5, 26);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (22, 'Vervain', 'mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id massa', 'sem praesent id massa id nisl venenatis', 4, 26);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (23, 'San Diego Raspberry', 'in sapien iaculis congue vivamus metus arcu adipiscing molestie hendrerit at vulputate vitae', 'libero convallis eget', 5, 26);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (24, 'Smooth Northern-rockcress', 'augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur gravida nisi at nibh in hac habitasse platea', 'sit amet justo morbi ut odio cras', 3, 27);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (25, 'Bright Green Spikerush', 'integer aliquet massa id lobortis convallis tortor', 'sed vestibulum', 4, 27);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (26, 'Tamarind Anisomeridium Lichen', 'suspendisse potenti in eleifend quam a odio in hac habitasse platea dictumst maecenas ut', 'duis bibendum morbi non quam nec dui luctus rutrum nulla tellus in sagittis dui vel', 2, 27);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (27, 'Gypsum-lovingtownsend Daisy', 'ipsum primis in faucibus orci luctus et ultrices posuere cubilia', 'diam id ornare imperdiet sapien urna pretium nisl ut volutpat sapien arcu sed augue aliquam erat', 1, 27);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (28, 'Golden Columbine', 'nonummy integer', 'nunc rhoncus dui vel sem sed sagittis nam congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend', 9, 27);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (29, 'Blazing Star', 'a libero nam dui proin leo odio porttitor', 'vestibulum eget vulputate ut ultrices vel augue vestibulum ante', 2, 27);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (30, 'Japanese Tree Lilac', 'in tempor turpis nec euismod scelerisque quam turpis adipiscing lorem vitae mattis nibh ligula nec sem duis', 'morbi vestibulum velit id pretium iaculis diam erat fermentum justo nec condimentum neque sapien placerat', 8, 27);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (31, 'Knapweed', 'faucibus orci luctus et ultrices posuere cubilia curae donec pharetra magna vestibulum aliquet ultrices erat tortor sollicitudin mi', 'suspendisse potenti nullam porttitor lacus at turpis donec posuere metus vitae ipsum', 6, 27);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (32, 'Desert Twinbugs', 'consectetuer adipiscing elit proin interdum mauris non ligula', 'ut', 4, 27);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (33, 'Paperbush', 'nullam porttitor', 'dignissim vestibulum vestibulum', 6, 28);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (34, 'Hiddenfruit Bladderwort', 'odio in hac habitasse platea dictumst maecenas', 'massa volutpat convallis morbi odio odio elementum eu interdum eu tincidunt in', 5, 28);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (35, 'Rinodina Lichen', 'eu massa donec dapibus duis at velit eu est', 'leo odio porttitor id consequat in consequat ut nulla sed accumsan felis ut at dolor', 10, 28);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (36, 'Bigelow''s Thoroughwort', 'morbi ut odio cras mi pede malesuada in imperdiet et', 'pharetra magna ac', 6,28);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (37, 'Broom-like Ragwort', 'tortor quis turpis sed ante vivamus tortor duis mattis egestas metus aenean fermentum donec ut mauris', 'ut nulla sed accumsan', 2, 28);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (38, 'Hispid Starbur', 'eget semper rutrum nulla nunc purus phasellus in felis donec semper sapien a libero nam dui proin leo odio', 'augue aliquam erat volutpat in congue etiam', 7, 28);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (39, 'Leonard''s Beardtongue', 'felis eu sapien cursus vestibulum proin', 'aliquam sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non', 9, 28);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (40, 'Pignut', 'integer ac leo pellentesque ultrices mattis odio donec vitae nisi nam ultrices libero non mattis', 'dolor sit amet consectetuer adipiscing elit proin interdum mauris non ligula', 2, 28);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (41, 'Triteleia', 'nulla tellus in sagittis dui vel nisl duis ac nibh', 'nullam molestie nibh in lectus pellentesque at nulla suspendisse potenti cras in purus eu magna', 9, 29);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (42, 'Emmons'' Sedge', 'ridiculus mus etiam', 'congue risus semper porta volutpat quam pede lobortis ligula sit amet eleifend pede', 5, 29);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (43, 'Siberian Crab Apple', 'lorem vitae', 'elit', 5, 30);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (44, 'Pineland Heliotrope', 'in faucibus orci luctus et ultrices posuere cubilia curae donec pharetra', 'sit amet sem fusce consequat nulla nisl nunc', 6, 31);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (45, 'Finger Millet', 'suscipit ligula in lacus curabitur at ipsum ac tellus', 'iaculis justo in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut', 1, 31);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (46, 'Shaggy Blackfoot', 'aliquam non mauris morbi non lectus aliquam sit', 'venenatis lacinia aenean sit amet justo morbi ut odio cras mi pede malesuada in imperdiet et commodo vulputate justo in', 8, 31);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (47, 'Carrizo Sands Woollywhite', 'aenean auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo morbi ut odio cras', 'volutpat convallis morbi odio odio elementum eu interdum eu', 2, 31);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (48, 'Beet', 'aliquam non', 'vestibulum sagittis sapien', 3, 31);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (49, 'Beet', 'in est risus auctor sed tristique in tempus sit amet sem fusce consequat nulla nisl', 'id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis in faucibus', 9, 32);
insert into INGREDIENTES (id, nombre, descrEsp, descring, grupo, restaurante) values (50, 'Maxon''s Goldback Fern', 'vivamus vel nulla eget eros elementum pellentesque quisque porta volutpat erat quisque erat eros viverra eget congue eget', 'erat curabitur gravida nisi at nibh in hac habitasse platea dictumst aliquam augue quam sollicitudin vitae consectetuer eget rutrum', 4, 32);


insert into MENUS (id) values (11);
insert into MENUS (id) values (12);
insert into MENUS (id) values (13);
insert into MENUS (id) values (14);
insert into MENUS (id) values (15);
insert into MENUS (id) values (16);
insert into MENUS (id) values (17);
insert into MENUS (id) values (18);
insert into MENUS (id) values (19);
insert into MENUS (id) values (20);

Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('320','Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('322','Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('32', 'Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('329','Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('321','Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('325', 'Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('329','Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('328','Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('324', 'Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('324','Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('323','Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('322', 'Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('323', 'Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('32', 'Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('326','Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('3223', 'Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('321','Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('327','Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('325','Colombia');
Insert into ZONAS_PREFERIDAS (USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE) values ('3223', 'Colombia');

Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (1,'4','30', TO_DATE('2017/05/17 13:45:34','yyyy/mm/dd hh24:mi:ss'));

Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (2,'1','19', TO_DATE('2017/06/12 13:45:35','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (3,'1','19', TO_DATE('2017/06/12 13:45:35','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (4,'1','19', TO_DATE('2017/06/12 13:45:35','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (5,'1','19', TO_DATE('2017/06/12 13:45:35','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (6,'1','19', TO_DATE('2017/06/12 13:45:35','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (7,'1','19', TO_DATE('2017/06/17 13:45:35','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (8,'1','19', TO_DATE('2017/06/18 13:45:35','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (10,'1','21', TO_DATE('2017/06/12 13:45:35','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (11,'1','21', TO_DATE('2017/06/12 13:45:35','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (12,'1','21', TO_DATE('2017/06/13 13:45:35','yyyy/mm/dd hh24:mi:ss'));

Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (13,'2','24', TO_DATE('2017/07/19 13:45:36','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (14,'2','24', TO_DATE('2017/07/19 13:45:37','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (15,'9','24', TO_DATE('2017/09/17 13:45:38','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (16,'14','29', TO_DATE('2017/10/17 13:45:39','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (17,'9' ,'29', TO_DATE('2017/11/17 13:45:40','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (18,'18','29', TO_DATE('2017/12/17 13:45:41','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (19,'20','29', TO_DATE('2017/05/18 13:45:42','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (20,'2', '29', TO_DATE('2017/06/18 13:45:43','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (21,'11','29', TO_DATE('2017/07/18 13:45:44','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (22,'17','29', TO_DATE('2017/08/18 13:45:45','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (23,'15','29', TO_DATE('2017/09/19 13:45:46','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (24,'17','29', TO_DATE('2017/10/18 13:45:47','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (25,'5', '29', TO_DATE('2017/11/18 13:45:48','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (26,'8', '29', TO_DATE('2017/12/19 13:45:49','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (27,'10','29', TO_DATE('2017/05/19 13:45:50','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (28,'7', '29', TO_DATE('2017/06/19 13:45:51','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (29,'12','29', TO_DATE('2017/07/19 13:45:52','yyyy/mm/dd hh24:mi:ss'));
Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values (30,'13','29', TO_DATE('2017/08/19 13:45:53','yyyy/mm/dd hh24:mi:ss'));

Insert into HISTORIAL (ID,ID_PRODUCTO, ID_USUARIO_REGISTRADO, FECHA) values

Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('1', '41');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('2', '42');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('3', '43');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('4', '410');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('5', '49');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('6', '49');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('7', '42');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('8', '41');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('9', '44');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('10','45');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('11','46');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('12','48');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('13','47');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('14','45');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('15','410');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('16','42');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('17','43');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('18','47');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('19','45');
Insert into INGREDIENTES_PRODUCTIND (ID_INGREDIENTE, ID_PRODINDIVIDUAL) values ('20','48');

Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('1','molestie hendrerit at vulputate vitae nisl aenean','21','40');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('2','est risus auctor sed tristique','28','39');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('3','ligula suspendisse ornare consequat lectus in','22','38');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('4','at vulputate vitae nisl aenean lectus','22','35');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('5','cras pellentesque volutpat dui maecenas tristique','28','33');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('6','primis in','26','30');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('7','est risus auctor sed tristique','26','27');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('8','molestie hendrerit at vulputate vitae nisl aenean','29','25');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('9','cras pellentesque volutpat dui maecenas tristique','26','22');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('10','at vulputate vitae nisl aenean lectus','27','18');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('11','est risus auctor sed tristique','22','16');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('12','ligula suspendisse ornare consequat lectus in','27','15');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('13','primis in','26','12');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('14','at vulputate vitae nisl aenean lectus','25','10');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('15','cras pellentesque volutpat dui maecenas tristique','22','9');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('16','molestie hendrerit at vulputate vitae nisl aenean','22','7');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('17','cras pellentesque volutpat dui maecenas tristique','22','6');
Insert into ITEMS (ID, PERSONALIZACION, ID_PRODUCTO, ID_CARRITO) values ('18','ligula suspendisse ornare consequat lectus in','24','4');
-- Insert into ITEMS (ID, ID_PRODUCTO, ID_CARRITO) values ('19','3','3');
-- Insert into ITEMS (ID, ID_PRODUCTO, ID_CARRITO) values ('20','20','1');

Insert into RESERVAS (ID, FECHAINICIO, FECHAFIN, NUMCOMENSALES, USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE, MENUS_PRODUCTOS_ID) values ('1',TO_DATE('2017/03/29 20:00','yyyy/mm/dd hh24:mi'),TO_DATE('2017/03/29 21:00','yyyy/mm/dd hh24:mi'),'40','31','Colombia','600544');
Insert into RESERVAS (ID, FECHAINICIO, FECHAFIN, NUMCOMENSALES, USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE, MENUS_PRODUCTOS_ID) values ('2',TO_DATE('2017/03/30 20:00','yyyy/mm/dd hh24:mi'),TO_DATE('2017/03/30 21:00','yyyy/mm/dd hh24:mi'),'120','32','Venezuela','600545');
Insert into RESERVAS (ID, FECHAINICIO, FECHAFIN, NUMCOMENSALES, USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE, MENUS_PRODUCTOS_ID) values ('3',TO_DATE('2017/03/31 20:00','yyyy/mm/dd hh24:mi'),TO_DATE('2017/03/31 21:00','yyyy/mm/dd hh24:mi'),'140','33','Colombia','600546');
Insert into RESERVAS (ID, FECHAINICIO, FECHAFIN, NUMCOMENSALES, USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE, MENUS_PRODUCTOS_ID) values ('4',TO_DATE('2017/04/10 20:00','yyyy/mm/dd hh24:mi'),TO_DATE('2017/04/10 21:00','yyyy/mm/dd hh24:mi'),'400','34','Venezuela','600547');
Insert into RESERVAS (ID, FECHAINICIO, FECHAFIN, NUMCOMENSALES, USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE, MENUS_PRODUCTOS_ID) values ('5',TO_DATE('2017/04/25 20:00','yyyy/mm/dd hh24:mi'),TO_DATE('2017/04/25 21:00','yyyy/mm/dd hh24:mi'),'340','35','Colombia','600548');
Insert into RESERVAS (ID, FECHAINICIO, FECHAFIN, NUMCOMENSALES, USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE, MENUS_PRODUCTOS_ID) values ('6',TO_DATE('2017/04/28 20:00','yyyy/mm/dd hh24:mi'),TO_DATE('2017/04/28 21:00','yyyy/mm/dd hh24:mi'),'40','36','Colombia','600549');
Insert into RESERVAS (ID, FECHAINICIO, FECHAFIN, NUMCOMENSALES, USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE, MENUS_PRODUCTOS_ID) values ('7',TO_DATE('2017/04/29 20:00','yyyy/mm/dd hh24:mi'),TO_DATE('2017/04/29 21:00','yyyy/mm/dd hh24:mi'),'60','36','Colombia','600550');
Insert into RESERVAS (ID, FECHAINICIO, FECHAFIN, NUMCOMENSALES, USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE, MENUS_PRODUCTOS_ID) values ('8',TO_DATE('2017/12/04 20:00','yyyy/mm/dd hh24:mi'),TO_DATE('2017/12/04 21:00','yyyy/mm/dd hh24:mi'),'100','37','Colombia','600551');
Insert into RESERVAS (ID, FECHAINICIO, FECHAFIN, NUMCOMENSALES, USUARIOSREGISTRADOS_ID, ZONAS_NOMBRE, MENUS_PRODUCTOS_ID) values ('9',TO_DATE('2017/12/12 20:00','yyyy/mm/dd hh24:mi'),TO_DATE('2017/12/12 21:00','yyyy/mm/dd hh24:mi'),'80','38','Colombia','600552');


insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('arochell0', 'semper porta volutpat quam pede lobortis ligula sit amet eleifend pede libero quis orci nullam molestie nibh in', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('athaim1', 'neque sapien placerat ante nulla justo aliquam quis turpis eget elit sodales scelerisque mauris sit amet', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('jbratchell2', 'ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae duis', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('adrillingcourt3', 'tincidunt in leo maecenas pulvinar lobortis est phasellus sit amet erat nulla tempus vivamus in felis eu sapien cursus vestibulum', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('psinson4', 'in quam fringilla rhoncus mauris enim leo rhoncus sed vestibulum sit amet cursus id turpis integer', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('hpennicard5', 'mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis congue vivamus metus', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('rbeagley6', 'vivamus vestibulum sagittis sapien cum sociis natoque penatibus et magnis dis parturient montes', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('jnisard7', 'rutrum at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed magna at nunc', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('bprobyn8', 'aliquam erat volutpat in congue etiam justo etiam pretium iaculis justo in hac habitasse platea dictumst etiam faucibus cursus', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('ableythin9', 'tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum sed magna', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('wlewsya', 'vestibulum sed magna at nunc commodo placerat praesent blandit nam nulla integer pede justo lacinia eget tincidunt eget', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('dchoffinb', 'ultricies eu nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis in', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('dgammonc', 'erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy maecenas tincidunt lacus', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('flansleyd', 'ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('ocalcute', 'sagittis nam congue risus semper porta volutpat quam pede lobortis ligula', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('pastletf', 'eget rutrum at lorem integer tincidunt ante vel ipsum praesent blandit lacinia erat vestibulum', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('cspencerg', 'ullamcorper purus sit amet nulla quisque arcu libero rutrum ac', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('scornewellh', 'amet eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor duis', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('cmcpecki', 'odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia nisi venenatis tristique fusce congue diam id', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('ehenkenj', 'dictumst morbi vestibulum velit id pretium iaculis diam erat fermentum justo nec', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('lpaveyk', 'ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae donec pharetra magna', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('mginsiel', 'in sagittis dui vel nisl duis ac nibh fusce lacus purus aliquet at feugiat non pretium quis', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('alangcastlem', 'etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('isemensn', 'auctor gravida sem praesent id massa id nisl venenatis lacinia aenean sit amet justo', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('kmarkovicho', 'vel accumsan tellus nisi eu orci mauris lacinia sapien quis libero', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('iridwoodp', 'eu est congue elementum in hac habitasse platea dictumst morbi vestibulum velit id', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('cgippsq', 'porttitor lorem id ligula suspendisse ornare consequat lectus in est risus auctor sed tristique in tempus sit amet sem fusce', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('kgoldsberryr', 'dictumst aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('aprantonis', 'lectus suspendisse potenti in eleifend quam a odio in hac habitasse platea dictumst maecenas ut massa quis augue luctus tincidunt', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('ecollofft', 'turpis adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam convallis nunc', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('bpyrahu', 'tristique est et tempus semper est quam pharetra magna ac consequat metus sapien ut nunc vestibulum ante ipsum', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('jbelchamv', 'turpis a pede posuere nonummy integer non velit donec diam neque', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('rdjurdjevicw', 'rhoncus aliquam lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('apalatinox', 'luctus et ultrices posuere cubilia curae mauris viverra diam vitae quam', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('rhanrattyy', 'tempus sit amet sem fusce consequat nulla nisl nunc nisl duis bibendum felis sed interdum venenatis', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('sklichz', 'interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('ddelort10', 'orci vehicula condimentum curabitur in libero ut massa volutpat convallis morbi odio odio elementum', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('dbolle11', 'in hac habitasse platea dictumst etiam faucibus cursus urna ut tellus nulla ut erat id mauris vulputate elementum', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('stuny12', 'massa id lobortis convallis tortor risus dapibus augue vel accumsan', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('jbortoloni13', 'orci luctus et ultrices posuere cubilia curae mauris viverra diam vitae quam suspendisse potenti nullam porttitor lacus at', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('cpendock14', 'tortor id nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('strill15', 'nulla pede ullamcorper augue a suscipit nulla elit ac nulla sed vel enim sit amet nunc viverra dapibus', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('fmorteo16', 'orci pede venenatis non sodales sed tincidunt eu felis fusce posuere felis sed lacus', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('mtosney17', 'condimentum id luctus nec molestie sed justo pellentesque viverra pede ac diam cras pellentesque', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('rlavalde18', 'interdum mauris non ligula pellentesque ultrices phasellus id sapien in sapien iaculis', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('vorknay19', 'justo eu massa donec dapibus duis at velit eu est congue elementum in', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('ahambly1a', 'at nulla suspendisse potenti cras in purus eu magna vulputate luctus cum sociis', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('sealden1b', 'amet consectetuer adipiscing elit proin risus praesent lectus vestibulum quam sapien varius ut blandit non interdum in ante vestibulum ante', 'Venezuela');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('mpietzke1c', 'lobortis convallis tortor risus dapibus augue vel accumsan tellus nisi eu orci mauris lacinia sapien quis libero nullam sit amet', 'Colombia');
insert into CAPACIDADES_TECNICAS (nombre, descripcion, zona) values ('ptomas1d', 'leo odio condimentum id luctus nec molestie sed justo pellentesque viverra pede', 'Venezuela');

Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('1','30');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('2','22');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('3','3');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('4','19');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('5','9');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('6','21');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('7','29');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('8','1');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('9','18');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('10','24');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('11','13');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('12','4');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('13','7');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('14','5');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('15','16');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('16','2');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('17','11');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('18','17');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('19','15');
Insert into PRODUCTOS_PREFERIDOS (ID_PRODUCTO, ID_USUARIO_REGISTRADO) values ('20','8');

Insert into MENU_PRODUCTO_INDIVIDUAL (ID_MENU, ID_PROD_INDIVIDUAL) values ('11','1');
Insert into MENU_PRODUCTO_INDIVIDUAL (ID_MENU, ID_PROD_INDIVIDUAL) values ('12','2');
Insert into MENU_PRODUCTO_INDIVIDUAL (ID_MENU, ID_PROD_INDIVIDUAL) values ('13','3');
Insert into MENU_PRODUCTO_INDIVIDUAL (ID_MENU, ID_PROD_INDIVIDUAL) values ('14','4');
Insert into MENU_PRODUCTO_INDIVIDUAL (ID_MENU, ID_PROD_INDIVIDUAL) values ('15','5');
Insert into MENU_PRODUCTO_INDIVIDUAL (ID_MENU, ID_PROD_INDIVIDUAL) values ('16','6');
Insert into MENU_PRODUCTO_INDIVIDUAL (ID_MENU, ID_PROD_INDIVIDUAL) values ('17','7');
Insert into MENU_PRODUCTO_INDIVIDUAL (ID_MENU, ID_PROD_INDIVIDUAL) values ('18','8');
Insert into MENU_PRODUCTO_INDIVIDUAL (ID_MENU, ID_PROD_INDIVIDUAL) values ('19','9');
Insert into MENU_PRODUCTO_INDIVIDUAL (ID_MENU, ID_PROD_INDIVIDUAL) values ('20','10');

COMMIT;

