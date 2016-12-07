INSERT INTO unit8db.artist (id, name)
  SELECT nextval('unit8db.artist_seq'), 'Bob Dylan'
  UNION ALL SELECT nextval('unit8db.artist_seq'), 'Pink Floyd'
  UNION ALL SELECT nextval('unit8db.artist_seq'), 'Ramones';

INSERT INTO unit8db.album (id, name, artist_id)
  SELECT nextval('unit8db.album_seq'), 'Freewheeling Bob Dylan',
    (SELECT id FROM unit8db.artist WHERE name = 'Bob Dylan')
  UNION ALL SELECT nextval('unit8db.album_seq'), 'The Times They Are a-Changin',
              (SELECT id FROM unit8db.artist WHERE name = 'Bob Dylan')
  UNION ALL SELECT nextval('unit8db.album_seq'), 'Bringing It All Back Home',
              (SELECT id FROM unit8db.artist WHERE name = 'Bob Dylan')
  UNION ALL SELECT nextval('unit8db.album_seq'), 'The Piper at the Gates of Dawn',
              (SELECT id FROM unit8db.artist WHERE name = 'Pink Floyd')
  UNION ALL SELECT nextval('unit8db.album_seq'), 'A Saucerful of Secrets',
              (SELECT id FROM unit8db.artist WHERE name = 'Pink Floyd');

INSERT INTO unit8db.track (id, position, name, album_id)
  SELECT nextval('unit8db.track_seq'), 1, 'Blowin'' in the Wind',
    (SELECT id FROM unit8db.album WHERE name = 'Freewheeling Bob Dylan')
  UNION ALL SELECT nextval('unit8db.track_seq'), 2, 'Girl from the North Country',
              (SELECT id FROM unit8db.album WHERE name = 'Freewheeling Bob Dylan')
  UNION ALL SELECT nextval('unit8db.track_seq'), 3, 'Masters of War',
              (SELECT id FROM unit8db.album WHERE name = 'Freewheeling Bob Dylan')
  UNION ALL SELECT nextval('unit8db.track_seq'), 4, 'Down the Highway',
              (SELECT id FROM unit8db.album WHERE name = 'Freewheeling Bob Dylan')
  UNION ALL SELECT nextval('unit8db.track_seq'), 5, 'Bob Dylan’s Blues',
              (SELECT id FROM unit8db.album WHERE name = 'Freewheeling Bob Dylan')
  UNION ALL SELECT nextval('unit8db.track_seq'), 6, 'A Hard Rain’s a-Gonna Fall',
              (SELECT id FROM unit8db.album WHERE name = 'Freewheeling Bob Dylan')
  UNION ALL SELECT nextval('unit8db.track_seq'), 1, 'The Times They Are a-Changin’',
              (SELECT id FROM unit8db.album WHERE name = 'The Times They Are a-Changin')
  UNION ALL SELECT nextval('unit8db.track_seq'), 2, 'Ballad of Hollis Brown',
              (SELECT id FROM unit8db.album WHERE name = 'The Times They Are a-Changin')
  UNION ALL SELECT nextval('unit8db.track_seq'), 3, 'With God on Our Side',
              (SELECT id FROM unit8db.album WHERE name = 'The Times They Are a-Changin')
  UNION ALL SELECT nextval('unit8db.track_seq'), 4, 'One Too Many Mornings',
              (SELECT id FROM unit8db.album WHERE name = 'The Times They Are a-Changin')
  UNION ALL SELECT nextval('unit8db.track_seq'), 5, 'North Country Blues',
              (SELECT id FROM unit8db.album WHERE name = 'The Times They Are a-Changin')
  UNION ALL SELECT nextval('unit8db.track_seq'), 1, 'Subterranean Homesick Blues',
              (SELECT id FROM unit8db.album WHERE name = 'Bringing It All Back Home')
  UNION ALL SELECT nextval('unit8db.track_seq'), 2, 'She Belongs to Me',
              (SELECT id FROM unit8db.album WHERE name = 'Bringing It All Back Home')
  UNION ALL SELECT nextval('unit8db.track_seq'), 3, 'Maggie’s Farm',
              (SELECT id FROM unit8db.album WHERE name = 'Bringing It All Back Home')
  UNION ALL SELECT nextval('unit8db.track_seq'), 4, 'Love Minus Zero/No Limit',
              (SELECT id FROM unit8db.album WHERE name = 'Bringing It All Back Home')
  UNION ALL SELECT nextval('unit8db.track_seq'), 5, 'Outlaw Blues',
              (SELECT id FROM unit8db.album WHERE name = 'Bringing It All Back Home')
  UNION ALL SELECT nextval('unit8db.track_seq'), 6, 'On the Road Again',
              (SELECT id FROM unit8db.album WHERE name = 'Bringing It All Back Home')
  UNION ALL SELECT nextval('unit8db.track_seq'), 1, 'Astronomy Domine',
              (SELECT id FROM unit8db.album WHERE name = 'The Piper at the Gates of Dawn')
  UNION ALL SELECT nextval('unit8db.track_seq'), 2, 'Lucifer Sam',
              (SELECT id FROM unit8db.album WHERE name = 'The Piper at the Gates of Dawn')
  UNION ALL SELECT nextval('unit8db.track_seq'), 3, 'Matilda Mother',
              (SELECT id FROM unit8db.album WHERE name = 'The Piper at the Gates of Dawn')
  UNION ALL SELECT nextval('unit8db.track_seq'), 4, 'Flaming',
              (SELECT id FROM unit8db.album WHERE name = 'The Piper at the Gates of Dawn')
  UNION ALL SELECT nextval('unit8db.track_seq'), 5, 'Pow R. Toc H.',
              (SELECT id FROM unit8db.album WHERE name = 'The Piper at the Gates of Dawn')
  UNION ALL SELECT nextval('unit8db.track_seq'), 1, 'Let There Be More Light',
              (SELECT id FROM unit8db.album WHERE name = 'A Saucerful of Secrets')
  UNION ALL SELECT nextval('unit8db.track_seq'), 2, 'Remember a Day',
              (SELECT id FROM unit8db.album WHERE name = 'A Saucerful of Secrets')
  UNION ALL SELECT nextval('unit8db.track_seq'), 3, 'Set the Controls for the Heart of the Sun',
              (SELECT id FROM unit8db.album WHERE name = 'A Saucerful of Secrets')
  UNION ALL SELECT nextval('unit8db.track_seq'), 4, 'Corporal Clegg',
              (SELECT id FROM unit8db.album WHERE name = 'A Saucerful of Secrets')
  UNION ALL SELECT nextval('unit8db.track_seq'), 5, 'A Saucerful of Secrets',
              (SELECT id FROM unit8db.album WHERE name = 'A Saucerful of Secrets')



