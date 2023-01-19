select* from emaillist;

insert into emaillist values
(null, '김', '둘리', 'qwe@qwe.com'),
(null, '이', '또치', 'asd@ads.com');

select * from guestbook;

select no, name, reg_date, message from guestbook order by no asc;

insert into guestbook values(null, '둘리', '1234', '안녕하세요', now());

delete from guestbook where password = 1234 and no = 1;

-- user
desc user;
select * from user;
-- join
insert into user values(null, 'name', 'email', password('pass'), 'male', now());