use [SWP_2024_let_us_book ];

DROP TABLE IF EXISTS Employee;


CREATE TABLE Employee (
    MID INT PRIMARY KEY IDENTITY(1,1), 
    Name VARCHAR(64),               
    Email VARCHAR(64),
    Password VARCHAR(64),
	Role VARCHAR(64)
);

SELECT * FROM Employee;
