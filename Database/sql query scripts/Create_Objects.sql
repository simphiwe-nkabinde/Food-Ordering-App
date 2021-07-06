--Remember to grant Execution permision to the user for all objects

use QuickFoodMS
go

--User Defined Functions-------------------------------
--1
CREATE FUNCTION fn_get_custid
(@name varchar(20), @phone char(10), @address varchar(40))
RETURNS INT
AS
BEGIN
RETURN (SELECT TOP 1 CustomerID FROM Customers
			WHERE Name = @name AND
			Phone = @phone AND
			Address = @address)
END
go

--2
CREATE FUNCTION fn_get_orderid
(@custid int)
RETURNS int
AS
BEGIN
DECLARE @orderid int
SET @orderid = (SELECT TOP 1 OrderID FROM [Order Header]
				WHERE CustomerID = @custid
				ORDER BY DateTime DESC)

RETURN @orderid
END
go


--Stored Procedures-----------------------------------

--1

CREATE PROC sp_insert_cust
@name varchar(20),
@phone char(10),
@address char(40),
@city char(20),
@email varchar(40)
AS
INSERT INTO Customers (Name, Phone, Address, City, Email) VALUES
(@name, @phone, @address, @city, @email)
go

--2

CREATE PROC sp_insert_orderheader
@custid int,
@restid char(5),
@driverid int,
@prep_instr varchar(50)
AS
INSERT INTO [Order Header] (CustomerID, RestaurantID, DriverID, [Prep Instr]) VALUES
(@custid, @restid, @driverid, @prep_instr)
go

--3

CREATE PROC sp_insert_orderitem
@orderid int,
@itemid int,
@qty int
AS
INSERT INTO [Order Item Details] (OrderID, ItemID,Qty) VALUES
(@orderid, @itemid, @qty)
go

--4

CREATE PROC sp_incr_driverload
@driverid int
AS
UPDATE Drivers
SET Load = Load + 1
WHERE DriverID = @driverid
go

--Trigger-------------------------------------------

CREATE TRIGGER tr_set_itemNamePrice
ON [Order Item Details]
	AFTER INSERT
AS
DECLARE @itemid int, @itemname varchar(20), @unitprice smallmoney
SELECT @itemid = ItemID from inserted
SELECT @itemname = Name, @unitprice = Price FROM Items
WHERE ItemID = @itemid
BEGIN
UPDATE [Order Item Details]
SET [Item Name] = @itemname,
[Unit Price] = @unitprice
WHERE ItemID = @itemid
END
go