drop table genres;

drop sequence dept_seq3;
CREATE TABLE genres (
  name varchar2(30),
  id int primary key
  
  );



CREATE SEQUENCE dept_seq3 START WITH 1;

CREATE OR REPLACE TRIGGER dept_bir3 
BEFORE INSERT ON genres
FOR EACH ROW

BEGIN
  SELECT dept_seq3.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/



drop table artists;

drop sequence dept_seq2;
CREATE TABLE artists (
  name varchar2(30),
  id int primary key
  
  );



CREATE SEQUENCE dept_seq2 START WITH 1;

CREATE OR REPLACE TRIGGER dept_bir2 
BEFORE INSERT ON artists 
FOR EACH ROW

BEGIN
  SELECT dept_seq2.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/


drop table albums;

drop sequence dept_seq;
CREATE TABLE albums (
  ID int not null primary key,
  release_year varchar(30),
  title varchar(30) ,
  artist_id int,
  CONSTRAINT FK_PersonOrder FOREIGN KEY (artist_id)
    REFERENCES artists(id)
  
  );



CREATE SEQUENCE dept_seq START WITH 1;

CREATE OR REPLACE TRIGGER dept_bir 
BEFORE INSERT ON albums 
FOR EACH ROW

BEGIN
  SELECT dept_seq.NEXTVAL
  INTO   :new.id
  FROM   dual;
END;
/




drop table album_genre;

drop sequence dept_seq4;
CREATE TABLE album_genre (
  album_id int,
  genre_id int ,
  CONSTRAINT FK_PersonOrde2 FOREIGN KEY (album_id)
    REFERENCES albums(id),
    CONSTRAINT FK_PersonOrder3 FOREIGN KEY (genre_id)
    REFERENCES genres(id)
  );
