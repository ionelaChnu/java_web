connect 'jdbc:mysql://localhost:3306//mydb;create=true;user=root;password=root';
--drop table students;
create table students
(
    id      int auto_increment,
    name    varchar(45) null,
    surname varchar(45) null,
    age     int         null,
    mark1   int         null,
    mark2   int         null,
    mark3   int         null,
    constraint id_UNIQUE
        unique (id)
);

-- дані
insert into students values (1,'Пастула','Михайло', 18, 4, 2, 5);
insert into students values (2,'Лупашку','Іонела', 18, 5, 5, 3);
insert into students values (3,'Ільчук','Таня', 18, 3, 5, 5);
insert into students values (4,'Придолоб','Сашка', 18, 4, 5, 5);
insert into students values (5,'Придолоб','Олександр', 18, 5, 5, 5);
insert into students values (6,'Придолоб','Саньок', 18, 4, 4, 5);

-- вибрати все із таблиці для перевірки
select * from students;
-- відключення і вихід
disconnect;
exit;
