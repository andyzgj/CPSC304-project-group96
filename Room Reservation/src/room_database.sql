-- create database RoomReservation_xxxx;
-- go


drop table Guest;
-- has no dependencies
drop table VIP;
-- cascade delete from Guest
drop table Make_Reservation;
-- cascade delete from Guest
drop table Room;
-- has no dependencies
drop table Booked_At;
-- cascade delete from Room and Make_Reservation
drop table Approve;
-- cascade delete from Employee and Make_Reservation
drop table Employee;
-- has no dependencies
drop table Includes_Meal;
-- cascade delete from Make_Reservation
drop table Provides;
-- cascade delete from Parking_Space and Make_Reservation
drop table Parking_Space;
-- has no dependencies


commit;

CREATE TABLE Guest(
	ID					INT,
	name 	 			VARCHAR(20),
	birthday 			DATE,
	phone_num 			INT,
	credit_card_num 	INT,
	PRIMARY KEY (ID)
);
grant select on Guest to public;

CREATE TABLE VIP(
	ID					INT,
	points 				INT,
	PRIMARY KEY (ID),
	FOREIGN KEY (ID) REFERENCES Guest(ID)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);
grant select on VIP to public;

CREATE TABLE Make_Reservation(
	reserve_num				INT,
	number_of_guest			INT,
	staying_period			VARCHAR(20),
	discount				FLOAT,
	ID						INT NOT NULL,
	PRIMARY KEY (reserve_num),
	FOREIGN KEY (ID) REFERENCES Guest(ID)
	ON DELETE CASCADE
	ON UPDATE NO ACTION
);
grant select on Make_Reservation to public;

CREATE TABLE Room(
	room_num		INT,
	type			VARCHAR(20),
	price			FLOAT,
	PRIMARY KEY (room_num)
);
grant select on Room to public;

CREATE TABLE Booked_At(
	room_num		INT,
	reserve_num   	INT,
	PRIMARY KEY (room_num, reserve_num),
	FOREIGN KEY (room_num) REFERENCES Room(room_num)
               ON DELETE NO ACTION
               ON UPDATE NO ACTION,
	FOREIGN KEY (reserve_num) REFERENCES Make_Reservation(reserve_num)
               ON DELETE NO ACTION
               ON UPDATE NO ACTION
);
grant select on Booked_At to public;

CREATE TABLE Approve(
	reserve_num		INT,
	employee_ID		INT,
	PRIMARY KEY (reserve_num,employee_ID),
	FOREIGN KEY (reserve_num) REFERENCES Make_Reservation(reserve_num)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	FOREIGN KEY (employee_ID) REFERENCES Employee(employee_ID)	
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);
grant select on Approve to public;

CREATE TABLE Employee(
	name			VARCHAR(20),
	employee_ID		INT,
	phone_num		INT,
	PRIMARY KEY (employee_ID)
);
grant select on Employee to public;

CREATE TABLE Includes_Meal(
	reserve_num		INT NOT NULL,
	price			FLOAT,
	name			VARCHAR(20),
	time			TIME,
	PRIMARY KEY (reserve_num, name, time),
	FOREIGN KEY(reserve_num) REFERENCES Make_Reservation(reserve_num)
	  ON DELETE CASCADE
  ON UPDATE CASCADE
);
grant select on Includes_Meal to public;

CREATE TABLE Provides(
	reserve_num		INT,
	stall_num		INT,
	PRIMARY KEY (reserve_num, stall_num),
	FOREIGN KEY(reserve_num) REFERENCES Make_Reservation(reserve_num)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	FOREIGN KEY(stall_num) REFERENCES Parking_Space(stall_num)
	  ON DELETE SET NULL
  ON UPDATE CASCADE
);
grant select on Provides to public;

CREATE TABLE Parking_Space(
           plate_num		CHAR(20),
           stall_num		INT,
           PRIMARY KEY (stall_num)
);
grant select on Parking_Space to public;
commit;





-- insert data into diffrent tables
INSERT
INTO		Guest(ID, name, birthday, phone_num, credit_card_num)
VALUES	(52033688,'Andy','1992-08-18',6205869176,1925827681016543);
INSERT
INTO		Guest(ID, name, birthday, phone_num, credit_card_num)
VALUES	(57483960,'Jingwei','1997-01-21',5847105827,1029756472102760);
INSERT
INTO		Guest(ID, name, birthday, phone_num, credit_card_num)
VALUES	(58395748,'Happy','1997-04-19',5748102958,1029584710295647);
INSERT
INTO		Guest(ID, name, birthday, phone_num, credit_card_num)
VALUES	(58492049,'Lynch','1996-10-24',5849307810,2857304918275940);
INSERT
INTO		Guest(ID, name, birthday, phone_num, credit_card_num)
VALUES	(67583920,'Ling','1987-05-04',1029584710,1928503948124123);


INSERT
INTO		VIP(ID, points)
VALUES	(52033688,1000);
INSERT
INTO		VIP(ID, points)
VALUES	(57483960,2000);
INSERT
INTO		VIP(ID, points)
VALUES	(23847112, 500);
INSERT
INTO		VIP(ID, points)
VALUES	(19387981,0);
INSERT
INTO		VIP(ID, points)
VALUES	(13424149,5000);


INSERT
INTO		Make_Reservation(reserve_num, number_of_guest, staying_period, discount, ID)
VALUES	(12345,2,'1 day',100,52033688);
INSERT
INTO		Make_Reservation(reserve_num, number_of_guest, staying_period, discount, ID)
VALUES	(54321,2,'2 days',90,57483960);
INSERT
INTO		Make_Reservation(reserve_num, number_of_guest, staying_period, discount, ID)
VALUES	(99873,2,'3 days',0,58395748);
INSERT
INTO		Make_Reservation(reserve_num, number_of_guest, staying_period, discount, ID)
VALUES	(19283,3,'3 days',0,49302948);
INSERT
INTO		Make_Reservation(reserve_num, number_of_guest, staying_period, discount, ID)
VALUES	(28493,2,'5 days',0,58473921);


INSERT
INTO		Room(room_num, type, price)
VALUES	(103,'Single Room',150);
INSERT
INTO		Room(room_num, type, price)
VALUES	(204,'Double Room',250);
INSERT
INTO		Room(room_num, type, price)
VALUES	(304,'Executive Room',400);
INSERT
INTO		Room(room_num, type, price)
VALUES	(507,'Deluxe Suite',500);
INSERT
INTO		Room(room_num, type, price)
VALUES	(909,'Presidential Suite',800);


INSERT
INTO		Booked_At(room_num, reserve_num)
VALUES	(306,58473);
INSERT
INTO		Booked_At(room_num, reserve_num)
VALUES	(404,83723);
INSERT
INTO		Booked_At(room_num, reserve_num)
VALUES	(518,61236);
INSERT
INTO		Booked_At(room_num, reserve_num)
VALUES	(709,84732);
INSERT
INTO		Booked_At(room_num, reserve_num)
VALUES	(819,43271);


INSERT
INTO		Approve(reserve_num, employee_ID)
VALUES	(56271,9481);
INSERT
INTO		Approve(reserve_num, employee_ID)
VALUES	(47362,4637);
INSERT
INTO		Approve(reserve_num, employee_ID)
VALUES	(91827,7635);
INSERT
INTO		Approve(reserve_num, employee_ID)
VALUES	(56742,6354);
INSERT
INTO		Approve(reserve_num, employee_ID)
VALUES	(29473,2736);


INSERT
INTO		Employee(name, employee_ID,phone_num)
VALUES	('Tony',1176,8376293810);
INSERT
INTO		Employee(name, employee_ID,phone_num)
VALUES	('Amber',5748,5749102758);
INSERT
INTO		Employee(name, employee_ID, phone_num)
VALUES	('Jack',2736,36472937820);
INSERT
INTO		Employee(name, employee_ID,phone_num)
VALUES	('Tom',8746,83729475031);
INSERT
INTO		Employee(name, employee_ID,phone_num)
VALUES	('Leon',6666,73628563911);


INSERT
INTO		Includes_Meal(reserve_num, price, name, time)
VALUES	(37282,10,'dumplings','16:00');
INSERT
INTO		Includes_Meal(reserve_num, price, name, time)
VALUES	(37283,23,'pizza','12:00');
INSERT
INTO		Includes_Meal(reserve_num, price, name, time)
VALUES	(37283,31,'steak','18:00');
INSERT
INTO		Includes_Meal(reserve_num, price, name, time)
VALUES	(68695,450,'King Crab','19:00');
INSERT
INTO		Includes_Meal(reserve_num, price, name, time)
VALUES	(19283,15,'mashed potatoes','21:00');


INSERT
INTO		Provides(reserve_num, stall_num)
VALUES	(39283,10);
INSERT
INTO		Provides(reserve_num, stall_num)
VALUES	(58493,20);
INSERT
INTO		Provides(reserve_num, stall_num)
VALUES	(58493,33);
INSERT
INTO		Provides(reserve_num, stall_num)
VALUES	(58493,102);
INSERT
INTO		Provides(reserve_num, stall_num)
VALUES	(44494,78);


INSERT
INTO		Parking_Space(plate_num, stall_num)
VALUES	('ABC123',9);
INSERT
INTO		Parking_Space(plate_num, stall_num)
VALUES	('E596CV',45);
INSERT
INTO		Parking_Space(plate_num, stall_num)
VALUES	('F963LD',66);
INSERT
INTO		Parking_Space(plate_num, stall_num)
VALUES	('OP35W3',54);
INSERT
INTO		Parking_Space(plate_num, stall_num)
VALUES	('JG7F39',76);



