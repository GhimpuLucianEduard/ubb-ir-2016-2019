use Lab5DB

CREATE TABLE Ta
(
	coda int NOT NULL PRIMARY KEY,
	a2 int UNIQUE	
);

CREATE TABLE Tb
(
	codb int NOT NULL PRIMARY KEY,
	b2 int 
);

CREATE TABLE Tc
(
    coda int NOT NULL,
    codb int NOT NULL,
    FOREIGN KEY (coda) REFERENCES Ta(coda),
    FOREIGN KEY (codb) REFERENCES Tb(codb),
	codc int primary key
)

drop table Ta
drop Table Tb
drop Table Tc

select * from Ta
select * from Tb
select * from Tc


go
create procedure populare
as
begin
	declare @index1 int
	set @index1 = 0

	while @index1 < 10000
	begin
		insert into Ta
		values (@index1,@index1)
		if @index1 < 3000
		begin
			insert into Tb
			values (@index1,@index1)
		end
		set @index1 = @index1 + 1
	end

	set @index1 = 0
	while @index1 <= 30000
	begin
		insert into Tc(codc,coda,codb)
		values (@index1,@index1%10000,@index1%3000)
		set @index1 = @index1 + 1
	end
end
go

exec populare
drop procedure populare

select * from Ta
select * from Tb
select * from Tc

declare @index int
set @index = 3
select @index % 2
go

create procedure  addTa(@a1 int, @a2 int)
as
begin
	begin try
		insert into Ta
		values (@a1, @a2)
	end try
	begin catch
		select ERROR_MESSAGE() as Eroare
	end catch

end
go

create procedure  addTb(@b1 int, @b2 int)
as
begin
	begin try
		insert into Tb
		values (@b1, @b2)
	end try
	begin catch
		select ERROR_MESSAGE() as Eroare
	end catch
end
go


select * from Ta
exec addTa 123456, 13124
exec addTa 9999,32131

select * from Tb
exec addTb 3000,3000
exec addTb 1000,1000
go


create procedure  deleteTa(@a1 int)
as
begin
	begin try
		delete from Ta
		where coda=@a1
	end try
	begin catch
		select ERROR_MESSAGE() as Eroare
	end catch
end
go

exec deleteTa 123456

create procedure deleteTb(@b1 int)
as
begin
	begin try
		delete from Tb
		where codb=@b1
	end try
	begin catch
		select ERROR_MESSAGE() as Eroare
	end catch
end
go

create procedure getTa(@a1 int)
as
begin
	begin try
		select * 
		from Ta
		where coda=@a1
	end try
	begin catch
		select ERROR_MESSAGE() as Eroare
	end catch
end
go


--A
--CISeek
select *
from Ta
where coda>5000

--NCISeek
select *
from Ta
where a2=5

--CIScan
select *
from Ta
order by coda

--NCIScan
select *
from Ta
order by a2

--B
--trebuie sa mai adaug o coloana pentur key lookup
alter table Ta
add  a3 int

--key look up
select coda
from Ta
where a2 = 4000


--C
select *
from Tb
where b2=100 --->0.003 clustered index scan (citeste toate cele 3000 de lini ca sa se asigure ca nu exista duplicate)

-- ca sa optimizam facem in index non clust. pe b2
create index nci_b2
on dbo.Tb(b2);select *
from Tb
where b2=100 --->non clustered index seek (sare direct la cel il intereseza)--D--dupa ce pun indexul e de 2 ori mai putin costisitorselect ta.coda, tc.codc, tc.codb
from Tc tc
INNER JOIN Ta ta ON tc.coda=ta.coda
where Ta.coda = 3000

select tb.codb, tc.codc
from Tc tc
INNER JOIN Tb tb ON tc.codb=tb.codb


----inainte de index face seek + scan, dupa index face seek+seek, devine de 3-4 ori mai putin costisitor
--indexurile facute
create index nci_coda
on dbo.Tc(coda);

create index nci_codb
on dbo.Tc(codb);


select ta.coda, tc.codc
from Tc tc
INNER JOIN Ta ta ON tc.coda=ta.coda
where ta.coda > 3000

select tb.codb, tc.codc
from Tc tc
INNER JOIN Tb tb ON tc.codb=tb.codb
where tb.codb > 100 and tb.codb<1000


--E
use Lab5DB
alter table Tb
add b3 int
select * from Tb

create nonclustered index index_cerita_e
on dbo.Tb (codb)
include (b3)
go

select codb, b3
from Tb
where codb>300 and b3>10


--F
create view view_test_f
as
select tb.codb, tc.codc
from Tc tc
INNER JOIN Tb tb ON tc.codb=tb.codb

select * from view_test_f -- scan + scan

create 

---------------------

select * from sys.indexes
where object_id = object_id('Ta')

SELECT * 
FROM sys.dm_db_index_physical_stats (null,null,null,null,null)
where database_id = DB_ID() and OBJECT_ID('Tc') = OBJECT_ID