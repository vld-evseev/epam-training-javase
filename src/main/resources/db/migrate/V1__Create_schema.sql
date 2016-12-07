CREATE SCHEMA unit8db;

CREATE SEQUENCE unit8db.artist_seq;

CREATE TABLE unit8db.artist (
  id   INTEGER PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE SEQUENCE unit8db.album_seq;

CREATE TABLE unit8db.album (
  id        INTEGER PRIMARY KEY,
  name      VARCHAR(255) NOT NULL,
  artist_id INTEGER      NOT NULL,
  FOREIGN KEY (artist_id) REFERENCES unit8db.artist (id)
);

CREATE SEQUENCE unit8db.track_seq;

CREATE TABLE unit8db.track (
  id       INTEGER PRIMARY KEY,
  position INTEGER      NOT NULL,
  name     VARCHAR(255) NOT NULL,
  album_id INTEGER      NOT NULL,
  FOREIGN KEY (album_id) REFERENCES unit8db.album (id)
);









