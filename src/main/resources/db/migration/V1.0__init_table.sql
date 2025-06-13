CREATE TABLE order_pin (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_id BIGINT,
  pin INT
);


-- REVERT
-- DROP TABLE IF EXISTS order_pin;
-- DELETE FROM flyway_schema_history WHERE version = '1.0';
