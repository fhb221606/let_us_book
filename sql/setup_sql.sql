use [SWP_2024_let_us_book ];

CREATE TABLE Mitarbeiter (
    ID INT PRIMARY KEY IDENTITY(1,1), 
    Name VARCHAR(64),               
    Email VARCHAR(64),
    Passwort VARCHAR(64)           
);

INSERT INTO Mitarbeiter (Name, Email, Passwort) VALUES
('Max Mustermann', 'max.mustermann@example.com', 'passwort123'),
('Julia Schmitt', 'julia.schmitt@example.com', 'juliaSecure123'),
('Franz Kafka', 'franz.kafka@example.com', 'kafka2024'),
('Lisa Müller', 'lisa.mueller@example.com', 'lisaPass99'),
('John Doe', 'john.doe@example.com', 'doeJohn456');