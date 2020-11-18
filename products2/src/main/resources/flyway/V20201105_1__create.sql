
CREATE TABLE  category (
                            id serial,
                            name varchar(255),
                            description TEXT
    );



CREATE TABLE product (
                           id serial ,
                           category_id int8,
                           name varchar(255),
                           price float8,
                           description TEXT
    );



CREATE TABLE order_item (
                              id serial,
                              order_id int8,
                              product_id int8,
                              quantity int,
                              price float8

    );



CREATE TABLE orderr (
                         id serial,
                         shop_id int8,
                         date DATE,
                         total_price float8,
                         status int8
    );

CREATE TABLE status (
                                        id serial,
                                        name VARCHAR(255)

);


CREATE TABLE login (
                         id serial,
                         shop_id int8,
                         login varchar(255),
                         password varchar(255),
                         token varchar(255)
    );



CREATE TABLE shop (
                        id serial,
                        name varchar(255),
                        address varchar(255),
                        phone varchar(11)
    );






