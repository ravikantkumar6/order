CREATE DATABASE IF NOT EXISTS orders;
use orders;
CREATE TABLE IF NOT EXISTS OrderHeader (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id VARCHAR(255) NOT NULL,
    order_detail VARCHAR(255) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)  ENGINE=INNODB;