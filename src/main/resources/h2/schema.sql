CREATE TABLE IF NOT EXISTS prices(
    id bigint NOT NULL AUTO_INCREMENT,
    brand_id bigint NOT NULL,
    name VARCHAR(100) NOT NULL,
    price_list bigint NOT NULL,
    product_id bigint NOT NULL,
    priority bigint NOT NULL,
    price decimal(10,2) NOT NULL,
    curr VARCHAR(100) NOT NULL,
    start_date timestamp,
    end_date timestamp,
    primary key (id)
);