use [SWP_2024_let_us_book ];

DROP TABLE IF EXISTS Transactional;
DROP TABLE IF EXISTS Hotel;

SET DATEFORMAT ymd;





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
    Index_Date Date,
    CONSTRAINT fk_Hotel_HID FOREIGN KEY (HID) REFERENCES Hotel(HID)
);

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



INSERT INTO Transactional (HID, Rooms_Occupied, Beds_Occupied, Index_Date) VALUES 
(1, 150, 300, '2023-01-01'),  -- Grand Plaza
(1, 150, 300, '2023-02-02'),  -- Grand Plaza
(1, 150, 300, '2023-03-03'),  -- Grand Plaza
(1, 150, 300, '2023-04-04'),  -- Grand Plaza
(2, 100, 200, '2023-01-05'),  -- Sea View Resort
(3, 70, 140, '2023-01-06'),   -- Mountain Lodge
(4, 30, 60, '2023-01-07'),    -- Urban Hotel
(5, 20, 40, '2023-01-08'),    -- Budget Inn
(6, 160, 320, '2023-01-09'),  -- Skyline Modern
(7, 90, 180, '2023-01-10'),   -- Coastline Breeze
(8, 50, 100, '2023-01-11'),   -- Forest Retreat
(9, 25, 50, '2023-01-12'),    -- City Center Flat
(10, 15, 30, '2023-01-13');   -- Travelers Motel


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
FROM Hotel;

SELECT * 
FROM Transactional;

SELECT t.TID, h.name
FROM Transactional t
JOIN Hotel h ON t.HID = h.HID;

/*
SELECT h.Category,
    SUM(h.Rooms) AS Total_Rooms,
    ROUND(CAST(SUM(t.Rooms_Occupied) AS FLOAT) / SUM(h.Rooms) * 100, 2) AS Percentage_Rooms_Occupied,
    SUM(h.Beds) AS Total_Beds,
    ROUND(CAST(SUM(t.Beds_Occupied) AS FLOAT) / SUM(h.Beds) * 100, 2) AS Percentage_Beds_Occupied
FROM Hotel h
JOIN Transactional t ON h.HID = t.HID
GROUP BY h.Category
ORDER BY h.Category DESC; 

SELECT SUM(h.Rooms) AS Total_Rooms,
    ROUND(CAST(SUM(t.Rooms_Occupied) AS FLOAT) / SUM(h.Rooms) * 100, 2) AS Percentage_Rooms_Occupied,
    SUM(h.Beds) AS Total_Beds,
    ROUND(CAST(SUM(t.Beds_Occupied) AS FLOAT) / SUM(h.Beds) * 100, 2) AS Percentage_Beds_Occupied
FROM Hotel h
JOIN Transactional t ON h.HID = t.HID;
*/
