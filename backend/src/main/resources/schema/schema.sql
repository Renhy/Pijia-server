CREATE TABLE backend.device_data (
  id          BIGSERIAL   NOT NULL PRIMARY KEY,
  device      VARCHAR(32) NOT NULL,
  type        VARCHAR(32) NOT NULL,
  timestamp   TIMESTAMP   NOT NULL,
  content     VARCHAR(32) NOT NULL,
);