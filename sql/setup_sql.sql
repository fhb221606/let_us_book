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
('Travelers Motel', '*', 25, 50, 'Phoenix', '18 Route Rd'),
('Lakeside Inn', '****', 120, 240, 'Seattle', '12 Lakeview Dr'),
('Desert Oasis', '*****', 160, 320, 'Tucson', '21 Desert Way'),
('Riverside Hotel', '***', 80, 160, 'Memphis', '77 River Rd'),
('Downtown Lodge', '**', 60, 120, 'Dallas', '456 Elm St'),
('Sunset Motel', '*', 40, 80, 'San Diego', '33 Beach Blvd'),
('Hilltop Hotel', '****', 140, 280, 'Atlanta', '11 Peachtree St'),
('Oceanfront Inn', '*****', 170, 340, 'Honolulu', '8 Oceanfront Ave'),
('Countryside Hotel', '***', 90, 180, 'Nashville', '22 Country Rd'),
('Suburban Suites', '**', 55, 110, 'Columbus', '99 Suburb Ln'),
('Mountain Peak', '*****', 190, 380, 'Salt Lake City', '7 Peak Rd');

INSERT INTO Transactional (HID, Rooms_Occupied, Beds_Occupied, Index_Date) VALUES
(1, 120, 240, '2023-01-01'),  -- Grand Plaza
(1, 130, 260, '2023-02-01'),  -- Grand Plaza
(1, 140, 280, '2023-03-01'),  -- Grand Plaza
(1, 150, 300, '2023-04-01'),  -- Grand Plaza
(1, 160, 320, '2023-05-01'),  -- Grand Plaza
(2, 90, 180, '2023-01-02'),  -- Sea View Resort
(2, 100, 200, '2023-02-02'),  -- Sea View Resort
(2, 110, 220, '2023-03-02'),  -- Sea View Resort
(2, 120, 240, '2023-04-02'),  -- Sea View Resort
(2, 130, 260, '2023-05-02'),  -- Sea View Resort
(3, 50, 100, '2023-01-03'),   -- Mountain Lodge
(3, 60, 120, '2023-02-03'),   -- Mountain Lodge
(3, 70, 140, '2023-03-03'),   -- Mountain Lodge
(3, 80, 160, '2023-04-03'),   -- Mountain Lodge
(3, 90, 180, '2023-05-03'),   -- Mountain Lodge
(4, 30, 60, '2023-01-04'),    -- Urban Hotel
(4, 25, 50, '2023-02-04'),    -- Urban Hotel
(4, 35, 70, '2023-03-04'),    -- Urban Hotel
(4, 40, 80, '2023-04-04'),    -- Urban Hotel
(4, 45, 90, '2023-05-04'),    -- Urban Hotel
(5, 20, 40, '2023-01-05'),    -- Budget Inn
(5, 25, 50, '2023-02-05'),    -- Budget Inn
(5, 30, 60, '2023-03-05'),    -- Budget Inn
(5, 15, 30, '2023-04-05'),    -- Budget Inn
(5, 20, 40, '2023-05-05'),    -- Budget Inn
(6, 140, 280, '2023-01-06'),  -- Skyline Modern
(6, 150, 300, '2023-02-06'),  -- Skyline Modern
(6, 160, 320, '2023-03-06'),  -- Skyline Modern
(6, 170, 340, '2023-04-06'),  -- Skyline Modern
(6, 180, 360, '2023-05-06'),  -- Skyline Modern
(7, 90, 180, '2023-01-07'),   -- Coastline Breeze
(7, 100, 200, '2023-02-07'),  -- Coastline Breeze
(7, 110, 220, '2023-03-07'),  -- Coastline Breeze
(7, 120, 240, '2023-04-07'),  -- Coastline Breeze
(7, 130, 260, '2023-05-07'),  -- Coastline Breeze
(8, 60, 120, '2023-01-08'),   -- Forest Retreat
(8, 50, 100, '2023-02-08'),   -- Forest Retreat
(8, 55, 110, '2023-03-08'),   -- Forest Retreat
(8, 65, 130, '2023-04-08'),   -- Forest Retreat
(8, 70, 140, '2023-05-08'),   -- Forest Retreat
(9, 30, 60, '2023-01-09'),    -- City Center Flat
(9, 35, 70, '2023-02-09'),    -- City Center Flat
(9, 40, 80, '2023-03-09'),    -- City Center Flat
(9, 20, 40, '2023-04-09'),    -- City Center Flat
(9, 25, 50, '2023-05-09'),    -- City Center Flat
(10, 10, 20, '2023-01-10'),   -- Travelers Motel
(10, 15, 30, '2023-02-10'),   -- Travelers Motel
(10, 20, 40, '2023-03-10'),   -- Travelers Motel
(10, 5, 10, '2023-04-10'),    -- Travelers Motel
(10, 10, 20, '2023-05-10'),   -- Travelers Motel
(11, 80, 160, '2023-01-11'),   -- Lakeside Inn
(11, 90, 180, '2023-02-11'),   -- Lakeside Inn
(11, 100, 200, '2023-03-11'),  -- Lakeside Inn
(11, 110, 220, '2023-04-11'),  -- Lakeside Inn
(11, 120, 240, '2023-05-11'),  -- Lakeside Inn
(12, 120, 240, '2023-01-12'),  -- Desert Oasis
(12, 130, 260, '2023-02-12'),  -- Desert Oasis
(12, 140, 280, '2023-03-12'),  -- Desert Oasis
(12, 150, 300, '2023-04-12'),  -- Desert Oasis
(12, 160, 320, '2023-05-12'),  -- Desert Oasis
(13, 60, 120, '2023-01-13'),   -- Riverside Hotel
(13, 65, 130, '2023-02-13'),   -- Riverside Hotel
(13, 70, 140, '2023-03-13'),   -- Riverside Hotel
(13, 75, 150, '2023-04-13'),   -- Riverside Hotel
(13, 80, 160, '2023-05-13'),   -- Riverside Hotel
(14, 40, 80, '2023-01-14'),    -- Downtown Lodge
(14, 45, 90, '2023-02-14'),    -- Downtown Lodge
(14, 50, 100, '2023-03-14'),   -- Downtown Lodge
(14, 55, 110, '2023-04-14'),   -- Downtown Lodge
(14, 60, 120, '2023-05-14'),   -- Downtown Lodge
(15, 25, 50, '2023-01-15'),    -- Sunset Motel
(15, 30, 60, '2023-02-15'),    -- Sunset Motel
(15, 35, 70, '2023-03-15'),    -- Sunset Motel
(15, 20, 40, '2023-04-15'),    -- Sunset Motel
(15, 25, 50, '2023-05-15'),    -- Sunset Motel
(16, 100, 200, '2023-01-16'),  -- Hilltop Hotel
(16, 110, 220, '2023-02-16'),  -- Hilltop Hotel
(16, 120, 240, '2023-03-16'),  -- Hilltop Hotel
(16, 130, 260, '2023-04-16'),  -- Hilltop Hotel
(16, 140, 280, '2023-05-16'),  -- Hilltop Hotel
(17, 110, 220, '2023-01-17'),  -- Oceanfront Inn
(17, 120, 240, '2023-02-17'),  -- Oceanfront Inn
(17, 130, 260, '2023-03-17'),  -- Oceanfront Inn
(17, 140, 280, '2023-04-17'),  -- Oceanfront Inn
(17, 150, 300, '2023-05-17'),  -- Oceanfront Inn
(18, 50, 100, '2023-01-18'),   -- Countryside Hotel
(18, 55, 110, '2023-02-18'),   -- Countryside Hotel
(18, 60, 120, '2023-03-18'),   -- Countryside Hotel
(18, 65, 130, '2023-04-18'),   -- Countryside Hotel
(18, 70, 140, '2023-05-18'),   -- Countryside Hotel
(19, 30, 60, '2023-01-19'),    -- Suburban Suites
(19, 35, 70, '2023-02-19'),    -- Suburban Suites
(19, 40, 80, '2023-03-19'),    -- Suburban Suites
(19, 20, 40, '2023-04-19'),    -- Suburban Suites
(19, 25, 50, '2023-05-19'),    -- Suburban Suites
(20, 150, 300, '2023-01-20'),  -- Mountain Peak
(20, 160, 320, '2023-02-20'),  -- Mountain Peak
(20, 170, 340, '2023-03-20'),  -- Mountain Peak
(20, 180, 360, '2023-04-20'),  -- Mountain Peak
(20, 190, 380, '2023-05-20');  -- Mountain Peak


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

