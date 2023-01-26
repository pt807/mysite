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
-- login
select * from user where email = 'ssss@dddd' and password = password('1234');

select name, password, gender from user where no = 1;

update user set name ='마이콜', password = password('1234'), gender = 'female' where no = 1;

desc board;
select a.no, title, b.name, hit, reg_date, a.user_no from board a, user b where a.user_no = b.no  order by g_no desc, o_no asc;
select a.no, title, contents, a.user_no from board a, user b where a.user_no = b.no and a.no = 4;

insert into board values(null, '저녁 머먹지?', '저녁 머먹지~~~~', 1, now(), 1, 1, 0, 2);
delete from board where user_no = ? and no = ?;
update board set title = 'test33', contents = 'test44' where no = 4;