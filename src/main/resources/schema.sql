/**
 * Author:  Pedro
*/
DROP SEQUENCE IF EXISTS seq_movie_list;
CREATE SEQUENCE seq_movie_list;


DROP TABLE IF EXISTS movie_list;
CREATE TABLE IF NOT EXISTS movie_list(
id          INT NOT NULL auto_increment,
year        VARCHAR(4),
title       VARCHAR(255),
studios     VARCHAR(255),
producers   VARCHAR(255),
winner      VARCHAR(3)
) as select seq_movie_list.nextval, * from CSVREAD('classpath:movielist.csv', null, 'charset=UTF-8 fieldSeparator=;');