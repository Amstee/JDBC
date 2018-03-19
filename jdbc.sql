/* Create the schema for our tables */
CREATE TABLE writinggroups(
    groupName varchar(50) NOT NULL, 
    headWriter varchar(50) NOT NULL,
    yearFormed int DEFAULT NULL,
    subject varchar(50) DEFAULT NULL,
    constraint groupName_pk primary key (groupName)
);

CREATE TABLE publishers(
    pubName varchar(50) NOT NULL,
    pubAddress varchar(100) NOT NULL,
    pubPhone varchar(50) NOT NULL,
    pubEmail varchar(100) DEFAULT NULL,
    constraint publishers_pk primary key (pubName)
);

CREATE TABLE books(
    bookTitle varchar(200) NOT NULL, 
    yearPublished int NOT NULL, 
    numberPages int NOT NULL,
    pubName varchar(50) NOT NULL,
    groupName varchar(50) NOT NULL,
    constraint books_pk primary key (bookTitle, groupName),
    constraint books_fk_1 foreign key (pubName) references publishers (pubName),
    constraint books_fk_2 foreign key (groupName) references writinggroups (groupName)
);


/* Populate the tables with our data */
INSERT INTO writinggroups(groupName, headWriter, yearFormed, subject) values
('Bibliophiles', 'Jane Doe', 1997, 'Young Adult'),
('Book Club', 'William Kay', 2000, null),
('Baby Steps', 'Martha Steward', 2005, 'Children'),
('Bookworms', 'India Jonas', 1980, 'Fantasy'),
('Lunch Bunch', 'Ann Ikeda', 1993, null),
('Hello World', 'Amy Tan', 1975, 'Sci-Fi');

INSERT INTO publishers(pubName, pubAddress, pubPhone, pubEmail) values
('Number One', '45631 Reading Way, Long Beach, CA', '310-689-9741', 'numberOne@n1books.org'),
('Bloom', '6583 Bloomsburg Ave, Carson City, NV', '789-965-7896', null),
('Great Books', '68453 Great Blvd, New York City, New York', '424-362-5686', 'greatBooks@books.org'),
('Collins Harper', '789 Beach Drive, Santa Monica, CA', '818-569-7895', 'collins.harper@gmail.com');

INSERT INTO books(bookTitle, yearPublished, numberPages, pubName, groupName) values
('The First One', 1999, 156, 'Number One', 'Bibliophiles'),
('Curious Tim', 2001, 54, 'Bloom', 'Baby Steps'),
('Henrietta Pots', 1997, 123, 'Collins Harper', 'Bookworms'),
('Old Dusk', 2007, 397, 'Great Books', 'Bibliophiles'),
('Moonlight', 2002, 322, 'Great Books', 'Bibliophiles'),
('Gold Mind', 2013, 564, 'Collins Harper', 'Book Club'),
('The Fault in Our Worlds', 2002, 282, 'Number One', 'Hello World'),
('The Brotherhood of the Resting Shoes', 2008, 226, 'Bloom', 'Lunch Bunch'),
('Slay the Spire', 2007, 489, 'Collins Harper', 'Book Club'),
('Palindromes', 1989, 780, 'Collins Harper', 'Bookworms'),
('RSA', 1993, 682, 'Number One', 'Hello World'),
('Asmuth-Bloom', 1983, 450, 'Great Books', 'Hello World'),
('New Year', 2001, 460, 'Bloom', 'Lunch Bunch'),
('Diffie-Hellman', 1976, 356, 'Number One', 'Hello World'),
('Tikitacotimba', 2007, 15, 'Bloom', 'Baby Steps');

/* Test Queries */
--SELECT * FROM books;
--SELECT * FROM writinggroups;
--SELECT * FROM publishers;