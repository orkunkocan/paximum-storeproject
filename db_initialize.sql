create database paximum_project;

use paximum_project;

CREATE TABLE `products` (
  `product_type` varchar(31) NOT NULL,
  `product_id` int(11) NOT NULL,
  `base_price` float NOT NULL,
  `genre` varchar(255),
  `name` varchar(255),
  `release_date` date,
  `isbn` varchar(255),
  `writer_name` varchar(255),
  `imdbscore` float,
  `director_name` varchar(255),
  `number_of_songs` int(11),
  `singer_name` varchar(255)
);

insert into products (product_type, product_id, base_price, genre, name, release_date, isbn, writer_name) values
("book", 1, 18.94, "Fiction", "The Great Gatsby", "1925-04-10", "9780743273565", "Francis Scott Fitzgerald"),
("book", 2, 22.18, "Romance", "Pride and Prejudice", "1813-01-28", "9780679783268", "Jane Austen"),
("book", 3, 19.96, "Cultural", "Crime and Punishment", "1866-12-12", "9780743273565", "Fyodor Dostoevsky");

insert into products (product_type, product_id, base_price, genre, name, release_date, imdbscore, director_name) values
("film", 4, 24.98, "Superhero", "Watchmen", "2009-03-06", 7.6, "Zack Snyder"),
("film", 5, 22.44, "Science-Fi", "Interstellar", "2010-07-16", 8.8, "Christopher Nolan"),
("film", 6, 28.16, "Psychology", "Shutter Island", "2010-02-19", 8.2, "Martin Scorsese");

insert into products (product_type, product_id, base_price, genre, name, release_date, number_of_songs, singer_name) values
("musicAlbum", 7, 30.75, "Rock", "A Day at the Races", "1976-08-30", 12, "Queen"),
("musicAlbum", 8, 34.55, "Rock", "Escape", "1981-07-17", 10, "Journey"),
("musicAlbum", 9, 32.20, "Rock", "Ghost In the Machine", "1981-10-02", 11, "The Police");


ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20)
);

insert into hibernate_sequence values(10);