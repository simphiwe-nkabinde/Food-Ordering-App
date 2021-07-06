
CREATE TABLE Customers
(CustomerID INT IDENTITY (1,1) CONSTRAINT pk_custid PRIMARY KEY,
Name varchar(20) NOT NULL,
Phone varchar(10) NOT NULL,
City varchar(20) NOT NULL,
Address varchar(40) NOT NULL,
Email varchar(40) NOT NULL
)


CREATE TABLE Drivers
(DriverID int IDENTITY (1,1) CONSTRAINT pk_driverid PRIMARY KEY,
Name varchar(20) NOT NULL,
City varchar(20) NOT NULL,
Load int NOT  NULL
)


CREATE TABLE Restaurants
(RestaurantID char(5) CONSTRAINT pk_restid PRIMARY KEY,
Name varchar(20) NOT NULL,
City varchar(20) NOT NULL,
Phone varchar(10)
)


CREATE TABLE Items
(ItemID int IDENTITY(1,1) CONSTRAINT pk_itemid PRIMARY KEY,
Name varchar(20) NOT NULL,
Price smallmoney NOT NULL,
RestaurantID char(5) NOT NULL REFERENCES Restaurants(RestaurantID)
)

CREATE TABLE [Order Header]
(OrderID int IDENTITY (1000,1) CONSTRAINT pk_orderid PRIMARY KEY,
CustomerID int NOT NULL REFERENCES Customers(CustomerID),
DateTime smalldatetime DEFAULT GETDATE(),
RestaurantID char(5) NOT NULL REFERENCES Restaurants(RestaurantID),
DriverID int NOT NULL REFERENCES Drivers(DriverID),
[Prep Instr] varchar(50) NOT NULL

)

CREATE TABLE [Order Item Details]
(OrderID int NOT NULL REFERENCES [Order Header](OrderID),
ItemID int NOT NULL REFERENCES Items(ItemID),
[Item Name] varchar(20) NULL,
[Unit Price] smallmoney NULL,
Qty smallint NOT NULL,
[Total Price] AS [Unit Price]*Qty,

)