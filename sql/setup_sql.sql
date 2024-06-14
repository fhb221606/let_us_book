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
(1, 120, 240, '2023-01-01'),  -- Grand Plaza
(1, 130, 260, '2023-02-01'),  -- Grand Plaza
(1, 140, 280, '2023-03-01'),  -- Grand Plaza
(1, 150, 300, '2023-04-01'),  -- Grand Plaza
(1, 160, 320, '2023-05-01'),  -- Grand Plaza
(1, 170, 340, '2023-06-01'),  -- Grand Plaza
(1, 180, 360, '2023-07-01'),  -- Grand Plaza
(1, 190, 380, '2023-08-01'),  -- Grand Plaza
(1, 150, 300, '2023-09-01'),  -- Grand Plaza
(1, 160, 320, '2023-10-01'),  -- Grand Plaza
(1, 170, 340, '2023-11-01'),  -- Grand Plaza
(1, 180, 360, '2023-12-01'),  -- Grand Plaza
(2, 100, 200, '2023-01-05'),  -- Sea View Resort
(2, 110, 220, '2023-02-05'),  -- Sea View Resort
(2, 120, 240, '2023-03-05'),  -- Sea View Resort
(2, 130, 260, '2023-04-05'),  -- Sea View Resort
(2, 90, 180, '2023-05-05'),   -- Sea View Resort
(2, 100, 200, '2023-06-05'),  -- Sea View Resort
(2, 110, 220, '2023-07-05'),  -- Sea View Resort
(2, 120, 240, '2023-08-05'),  -- Sea View Resort
(2, 130, 260, '2023-09-05'),  -- Sea View Resort
(2, 100, 200, '2023-10-05'),  -- Sea View Resort
(2, 110, 220, '2023-11-05'),  -- Sea View Resort
(2, 120, 240, '2023-12-05'),  -- Sea View Resort
(3, 50, 100, '2023-01-06'),   -- Mountain Lodge
(3, 60, 120, '2023-02-06'),   -- Mountain Lodge
(3, 70, 140, '2023-03-06'),   -- Mountain Lodge
(3, 80, 160, '2023-04-06'),   -- Mountain Lodge
(3, 90, 180, '2023-05-06'),   -- Mountain Lodge
(3, 50, 100, '2023-06-06'),   -- Mountain Lodge
(3, 60, 120, '2023-07-06'),   -- Mountain Lodge
(3, 70, 140, '2023-08-06'),   -- Mountain Lodge
(3, 80, 160, '2023-09-06'),   -- Mountain Lodge
(3, 90, 180, '2023-10-06'),   -- Mountain Lodge
(3, 50, 100, '2023-11-06'),   -- Mountain Lodge
(3, 60, 120, '2023-12-06'),   -- Mountain Lodge
(4, 30, 60, '2023-01-07'),    -- Urban Hotel
(4, 25, 50, '2023-02-07'),    -- Urban Hotel
(4, 35, 70, '2023-03-07'),    -- Urban Hotel
(4, 40, 80, '2023-04-07'),    -- Urban Hotel
(4, 45, 90, '2023-05-07'),    -- Urban Hotel
(4, 30, 60, '2023-06-07'),    -- Urban Hotel
(4, 25, 50, '2023-07-07'),    -- Urban Hotel
(4, 35, 70, '2023-08-07'),    -- Urban Hotel
(4, 40, 80, '2023-09-07'),    -- Urban Hotel
(4, 45, 90, '2023-10-07'),    -- Urban Hotel
(4, 30, 60, '2023-11-07'),    -- Urban Hotel
(4, 25, 50, '2023-12-07'),    -- Urban Hotel
(5, 20, 40, '2023-01-08'),    -- Budget Inn
(5, 25, 50, '2023-02-08'),    -- Budget Inn
(5, 30, 60, '2023-03-08'),    -- Budget Inn
(5, 15, 30, '2023-04-08'),    -- Budget Inn
(5, 20, 40, '2023-05-08'),    -- Budget Inn
(5, 25, 50, '2023-06-08'),    -- Budget Inn
(5, 30, 60, '2023-07-08'),    -- Budget Inn
(5, 15, 30, '2023-08-08'),    -- Budget Inn
(5, 20, 40, '2023-09-08'),    -- Budget Inn
(5, 25, 50, '2023-10-08'),    -- Budget Inn
(5, 30, 60, '2023-11-08'),    -- Budget Inn
(5, 15, 30, '2023-12-08'),    -- Budget Inn
(6, 150, 300, '2023-01-09'),  -- Skyline Modern
(6, 160, 320, '2023-02-09'),  -- Skyline Modern
(6, 170, 340, '2023-03-09'),  -- Skyline Modern
(6, 180, 360, '2023-04-09'),  -- Skyline Modern
(6, 140, 280, '2023-05-09'),  -- Skyline Modern
(6, 150, 300, '2023-06-09'),  -- Skyline Modern
(6, 160, 320, '2023-07-09'),  -- Skyline Modern
(6, 170, 340, '2023-08-09'),  -- Skyline Modern
(6, 180, 360, '2023-09-09'),  -- Skyline Modern
(6, 140, 280, '2023-10-09'),  -- Skyline Modern
(6, 150, 300, '2023-11-09'),  -- Skyline Modern
(6, 160, 320, '2023-12-09'),  -- Skyline Modern
(7, 100, 200, '2023-01-10'),  -- Coastline Breeze
(7, 110, 220, '2023-02-10'),  -- Coastline Breeze
(7, 120, 240, '2023-03-10'),  -- Coastline Breeze
(7, 130, 260, '2023-04-10'),  -- Coastline Breeze
(7, 90, 180, '2023-05-10'),   -- Coastline Breeze
(7, 100, 200, '2023-06-10'),  -- Coastline Breeze
(7, 110, 220, '2023-07-10'),  -- Coastline Breeze
(7, 120, 240, '2023-08-10'),  -- Coastline Breeze
(7, 130, 260, '2023-09-10'),  -- Coastline Breeze
(7, 90, 180, '2023-10-10'),   -- Coastline Breeze
(7, 100, 200, '2023-11-10'),  -- Coastline Breeze
(7, 110, 220, '2023-12-10'),  -- Coastline Breeze
(8, 60, 120, '2023-01-11'),   -- Forest Retreat
(8, 50, 100, '2023-02-11'),   -- Forest Retreat
(8, 55, 110, '2023-03-11'),   -- Forest Retreat
(8, 65, 130, '2023-04-11'),   -- Forest Retreat
(8, 70, 140, '2023-05-11'),   -- Forest Retreat
(8, 60, 120, '2023-06-11'),   -- Forest Retreat
(8, 50, 100, '2023-07-11'),   -- Forest Retreat
(8, 55, 110, '2023-08-11'),   -- Forest Retreat
(8, 65, 130, '2023-09-11'),   -- Forest Retreat
(8, 70, 140, '2023-10-11'),   -- Forest Retreat
(8, 60, 120, '2023-11-11'),   -- Forest Retreat
(8, 50, 100, '2023-12-11'),   -- Forest Retreat
(9, 30, 60, '2023-01-12'),    -- City Center Flat
(9, 35, 70, '2023-02-12'),    -- City Center Flat
(9, 40, 80, '2023-03-12'),    -- City Center Flat
(9, 20, 40, '2023-04-12'),    -- City Center Flat
(9, 25, 50, '2023-05-12'),    -- City Center Flat
(9, 30, 60, '2023-06-12'),    -- City Center Flat
(9, 35, 70, '2023-07-12'),    -- City Center Flat
(9, 40, 80, '2023-08-12'),    -- City Center Flat
(9, 20, 40, '2023-09-12'),    -- City Center Flat
(9, 25, 50, '2023-10-12'),    -- City Center Flat
(9, 30, 60, '2023-11-12'),    -- City Center Flat
(9, 35, 70, '2023-12-12'),    -- City Center Flat
(10, 10, 20, '2023-01-13'),   -- Travelers Motel
(10, 15, 30, '2023-02-13'),   -- Travelers Motel
(10, 20, 40, '2023-03-13'),   -- Travelers Motel
(10, 5, 10, '2023-04-13'),    -- Travelers Motel
(10, 10, 20, '2023-05-13'),   -- Travelers Motel
(10, 15, 30, '2023-06-13'),   -- Travelers Motel
(10, 20, 40, '2023-07-13'),   -- Travelers Motel
(10, 5, 10, '2023-08-13'),    -- Travelers Motel
(10, 10, 20, '2023-09-13'),   -- Travelers Motel
(10, 15, 30, '2023-10-13'),   -- Travelers Motel
(10, 20, 40, '2023-11-13'),   -- Travelers Motel
(10, 5, 10, '2023-12-13'),    -- Travelers Motel
(1, 140, 280, '2024-01-01'),  -- Grand Plaza
(2, 90, 180, '2024-01-02'),   -- Sea View Resort
(3, 60, 120, '2024-01-03'),   -- Mountain Lodge
(4, 25, 50, '2024-01-04'),    -- Urban Hotel
(5, 15, 30, '2024-01-05'),    -- Budget Inn
(6, 150, 300, '2024-01-06'),  -- Skyline Modern
(7, 100, 200, '2024-01-07'),  -- Coastline Breeze
(8, 50, 100, '2024-01-08'),   -- Forest Retreat
(9, 25, 50, '2024-01-09'),    -- City Center Flat
(10, 10, 20, '2024-01-10');   -- Travelers Motel


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
JOIN Transactional t ON h.HID = t.HID
WHERE YEAR(t.Index_Date) = 2023 AND MONTH(t.Index_Date) = 1;
*/
SELECT 
    h.Category,
    SUM(h.Rooms) AS Total_Rooms,
    ROUND(CAST(SUM(t.Rooms_Occupied) AS FLOAT) / SUM(h.Rooms) * 100, 2) AS Percentage_Rooms_Occupied,
    SUM(h.Beds) AS Total_Beds,
    ROUND(CAST(SUM(t.Beds_Occupied) AS FLOAT) / SUM(h.Beds) * 100, 2) AS Percentage_Beds_Occupied
FROM 
    Hotel h
JOIN 
    Transactional t ON h.HID = t.HID
WHERE YEAR(t.Index_Date) = 2023 AND MONTH(t.Index_Date) = 1
GROUP BY 
    h.Category
ORDER BY 
    h.Category DESC;