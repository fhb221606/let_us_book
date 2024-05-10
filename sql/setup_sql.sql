use [SWP_2024_let_us_book ];

DROP TABLE Mitarbeiter;
DROP TABLE Hotel;


CREATE TABLE Mitarbeiter (
    ID INT PRIMARY KEY IDENTITY(1,1), 
    Name VARCHAR(64),               
    Email VARCHAR(64),
    Passwort VARCHAR(64)           
);

CREATE TABLE Hotel (
	ID INT PRIMARY KEY IDENTITY(1,1), 
    Name VARCHAR(255) NOT NULL,
    Category VARCHAR(255) NOT NULL,
    Rooms INT NOT NULL,
	Occupied_rooms INT NOT NULL,
	Beds INT NOT NULL,
    Occupied_beds INT NOT NULL
);

INSERT INTO Mitarbeiter (Name, Email, Passwort) VALUES
('Max Mustermann', 'max.mustermann@example.com', 'passwort123'),
('Julia Schmitt', 'julia.schmitt@example.com', 'juliaSecure123'),
('Franz Kafka', 'franz.kafka@example.com', 'kafka2024'),
('Lisa Müller', 'lisa.mueller@example.com', 'lisaPass99'),
('John Doe', 'john.doe@example.com', 'doeJohn456');


INSERT INTO Hotel (Name, Category, Rooms, Occupied_rooms, Beds, Occupied_beds) VALUES
('Grand Royal Hotel', '*****', 200, 150, 400, 300),
('Seaside Resort', '****', 120, 80, 240, 160),
('Mountain View Retreat', '***', 80, 60, 160, 120),
('City Lights Hotel', '**', 50, 20, 100, 40),
('Budget Inn', '*', 30, 10, 60, 20),
('Lakeside Villa', '*****', 100, 70, 200, 140),
('Coastline Hotel', '****', 150, 100, 300, 200),
('Sunset Motel', '**', 40, 30, 80, 60),
('Highland Lodge', '***', 70, 50, 140, 100),
('Urban Hotel', '*', 60, 45, 120, 90);

SELECT COUNT(*) AS 'rowcount' FROM (
SELECT 
  Category,
  COUNT(*) AS Establishments,
  SUM(Rooms) - SUM(Occupied_rooms) as Rooms,
  SUM(Beds) - SUM(Occupied_beds) as Beds
FROM Hotel
WHERE Category IS NOT NULL
GROUP BY Category
) as Sub_Select;

SELECT 
  COUNT(*) AS Total_Hotels,
  SUM(Rooms) - SUM(Occupied_rooms) as Rooms,
  SUM(Beds) - SUM(Occupied_beds) as Beds
FROM Hotel;

SELECT COUNT(*) AS 'rowcount' FROM (
SELECT COUNT(*) AS Total_Hotels, SUM(Rooms) - SUM(Occupied_rooms) as Rooms, SUM(Beds) - SUM(Occupied_beds) as Beds
FROM Hotel
) as Sub_Select;