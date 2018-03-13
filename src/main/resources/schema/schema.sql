CREATE TABLE backend.device_data (
  id          BIGSERIAL   NOT NULL PRIMARY KEY,
  device      VARCHAR(32) NOT NULL,
  type        VARCHAR(32) NOT NULL,
  ts          TIMESTAMP   NOT NULL,
  content     TEXT        NOT NULL
);