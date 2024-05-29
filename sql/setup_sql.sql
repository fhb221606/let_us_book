use [SWP_2024_let_us_book ];

DROP TABLE IF EXISTS Transactional;
DROP TABLE IF EXISTS Hotel;
DROP TABLE IF EXISTS Employee;


CREATE TABLE Employee (
    MID INT PRIMARY KEY IDENTITY(1,1), 
    Name VARCHAR(64),               
    Email VARCHAR(64) UNIQUE,
    Passwort VARCHAR(64)           
);

CREATE TABLE Hotel (
	HID INT PRIMARY KEY IDENTITY(1,1), 
    Name VARCHAR(255) NOT NULL,
    Category VARCHAR(255) NOT NULL,
    Rooms INT NOT NULL,
	Beds INT NOT NULL,
	City VARCHAR(255) NOT NULL,
	Street VARCHAR(255) NOT NULL
);

CREATE TABLE Transactional (
	TID INT PRIMARY KEY IDENTITY(1,1), 
    HID INT NOT NULL,
    Rooms_Occupied INT NOT NULL,
	Beds_Occupied INT NOT NULL,
	CONSTRAINT fk_Hotel_HID FOREIGN KEY (HID) REFERENCES Hotel(HID)
);

INSERT INTO Employee(Name, Email, Passwort) VALUES
('John Doe', 'john.doe@example.com', 'hashed_password_1'),
('Jane Smith', 'jane.smith@example.com', 'hashed_password_2'),
('Alice Johnson', 'alice.johnson@example.com', 'hashed_password_3'),
('Bob Brown', 'bob.brown@example.com', 'hashed_password_4'),
('Charlie Davis', 'charlie.davis@example.com', 'hashed_password_5'),
('Diana Evans', 'diana.evans@example.com', 'hashed_password_6'),
('Frank White', 'frank.white@example.com', 'hashed_password_7'),
('Gina Harris', 'gina.harris@example.com', 'hashed_password_8'),
('Henry Martin', 'henry.martin@example.com', 'hashed_password_9'),
('Ivy Wilson', 'ivy.wilson@example.com', 'hashed_password_10');


INSERT INTO Hotel (Name, Category, Rooms, Beds, City, Street) VALUES
('Grand Plaza', '*****', 200, 400, 'New York', '123 Park Ave'),
('Sea View Resort', '****', 150, 300, 'Miami', '47 Ocean Drive'),
('Mountain Lodge', '***', 100, 200, 'Denver', '95 Mountain Rd'),
('Urban Hotel', '**', 50, 100, 'San Francisco', '388 Market St'),
('Budget Inn', '*', 30, 60, 'Las Vegas', '14 Main St'),
('Skyline Modern', '*****', 180, 360, 'Chicago', '88 High St'),
('Coastline Breeze', '****', 130, 260, 'Los Angeles', '44 Sunset Blvd'),
('Forest Retreat', '***', 75, 150, 'Portland', '331 Forest Ln'),
('City Center Flat', '**', 45, 90, 'Austin', '220 Sixth St'),
('Travelers Motel', '*', 25, 50, 'Phoenix', '18 Route Rd');


INSERT INTO Transactional (HID, Rooms_Occupied, Beds_Occupied) VALUES
(1, 150, 300), -- Grand Plaza
(2, 100, 200), -- Sea View Resort
(3, 70, 140),  -- Mountain Lodge
(4, 30, 60),   -- Urban Hotel
(5, 20, 40),   -- Budget Inn
(6, 160, 320), -- Skyline Modern
(7, 90, 180),  -- Coastline Breeze
(8, 50, 100),  -- Forest Retreat
(9, 25, 50),   -- City Center Flat
(10, 15, 30);  -- Travelers Motel



SELECT COUNT(*) AS 'rowcount' FROM (
SELECT 
  Category,
  COUNT(*) AS Establishments,
  SUM(Rooms) as Rooms,
  SUM(Beds) as Beds
FROM Hotel
WHERE Category IS NOT NULL
GROUP BY Category
) as Sub_Select;


SELECT COUNT(*) AS 'rowcount' FROM (
SELECT 
  COUNT(*) AS Total_Hotels,
  SUM(Rooms) as Rooms,
  SUM(Beds) as Beds
FROM Hotel
) as Sub_Select;


SELECT * 
FROM Employee;

SELECT * 
FROM Hotel;

SELECT * 
FROM Transactional;