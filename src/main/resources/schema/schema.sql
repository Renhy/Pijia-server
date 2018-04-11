CREATE TABLE pijia.device_data (
  id          BIGSERIAL   NOT NULL PRIMARY KEY,
  device      VARCHAR(32) NOT NULL,
  type        VARCHAR(32) NOT NULL,
  ts          TIMESTAMP   NOT NULL,
  content     TEXT        NOT NULL
);

CREATE TABLE pijia.aqo_data (
  id          BIGSERIAL   NOT NULL PRIMARY KEY,
  device      VARCHAR(32) NOT NULL,
  ts          TIMESTAMP   NOT NULL,
  tem         FLOAT,
  hum         FLOAT,
  ch2o        FLOAT,
  tvoc        FLOAT,
  co2         FLOAT,
  pm10        FLOAT,
  pm25        FLOAT
);