-- Database: lab2_db

-- DROP DATABASE lab2_db;

CREATE DATABASE lab2_db
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Ukrainian_Ukraine.1251'
    LC_CTYPE = 'Ukrainian_Ukraine.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

    -- Table: public.student

-- DROP TABLE public.student;

CREATE TABLE public.student
(
    first_name character(128) COLLATE pg_catalog."default" NOT NULL,
    id bigint NOT NULL DEFAULT nextval('student_id_seq'::regclass),
    last_name character(128) COLLATE pg_catalog."default" NOT NULL,
    group_number character(128) COLLATE pg_catalog."default" NOT NULL,
    math_mark integer NOT NULL,
    history_mark integer NOT NULL,
    music_mark integer NOT NULL,
    CONSTRAINT student_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.student
    OWNER to postgres;

INSERT INTO public.student (first_name, id, last_name, group_number, math_mark, history_mark, music_mark) VALUES ('Ania   ', 2, 'Perun  ', '4      ', 5, 5, 5);
INSERT INTO public.student (first_name, id, last_name, group_number, math_mark, history_mark, music_mark) VALUES ('Chloe  ', 3, 'Branch ', '65     ', 5, 3, 2);
INSERT INTO public.student (first_name, id, last_name, group_number, math_mark, history_mark, music_mark) VALUES ('Pearson', 1, 'Charles', '545    ', 4, 5, 4);
INSERT INTO public.student (first_name, id, last_name, group_number, math_mark, history_mark, music_mark) VALUES ('Roman  ', 4, 'Hilis  ', '5      ', 3, 4, 4);
INSERT INTO public.student (first_name, id, last_name, group_number, math_mark, history_mark, music_mark) VALUES ('Irina  ', 5, 'Mitchel', '22     ', 4, 3, 2);
