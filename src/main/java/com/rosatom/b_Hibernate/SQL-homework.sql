-- ///////////////////////////// CREATING /////////////////////////////

    CREATE TABLE public.competition (
        competition_id serial PRIMARY KEY,
        competition_name varchar UNIQUE NOT NULL,
        world_record decimal NOT NULL,
        set_date date NOT NULL
    );

    CREATE TABLE public.sportsman (
    	sportsman_id serial PRIMARY KEY,
    	sportsman_name varchar NOT NULL,
    	rank integer NOT NULL,
    	year_of_birth integer NOT NULL,
    	personal_record decimal NOT NULL,
    	county varchar NOT NULL
    );

    CREATE TABLE public.result (
    	competition_id serial,
    	sportsman_id serial,
    	result decimal NOT NULL,
    	city varchar NOT NULL,
    	hold_date date NOT NULL,
    	FOREIGN KEY (competition_id)
    		REFERENCES public.competition(competition_id),
    	FOREIGN KEY (sportsman_id)
    		REFERENCES public.sportsman(sportsman_id)
    );

-- ///////////////////////////// INSERTING /////////////////////////////

    INSERT INTO public.competition
    	(competition_name, world_record, set_date)
    VALUES
    	('Бег на 100 метров. Мужчины', 9.58, '2009-08-16'),
    	('Бег на 200 метров. Мужчины', 19.19, '2009-08-20'),
    	('Бег на 400 метров. Мужчины', 43.03, '2016-08-14'),
    	('Бег на 800 метров. Мужчины', 100.91, '2012-08-09'),
    	('Бег на 1000 метров. Мужчины', 131.96, '1999-09-05'),
    	('Бег на 1500 метров. Мужчины', 206.00, '1998-07-14'),
    	('Бег на 1 милю. Мужчины', 223.13, '1999-07-07'),
    	('Бег на 2000 метров. Мужчины', 284.79, '1999-09-07'),
    	('Бег на 3000 метров. Мужчины', 440.67, '1996-09-01'),
    	('Бег на 5000 метров. Мужчины', 755.36, '2020-08-14'),
    	('Бег на 10000 метров. Мужчины', 1577.53, '2005-08-26'),
    	('Бег на 20000 метров. Мужчины', 3385.98, '2007-06-27'),
    	('Бег на 25000 метров. Мужчины', 4345.4, '2011-06-03'),
    	('Бег на 30000 метров. Мужчины', 5207.4, '2011-06-13'),
    	('Бег. Марафон. Мужчины', 7299, '2018-09-16'),
    	('Бег 3000 метров с препятствиями. Мужчины', 473.63, '2004-09-03'),
    	('Бег на 110 метров c барьерами. Мужчины', 12.80, '2012-09-07'),
    	('Бег на 400 метров с барьерами. Мужчины', 46.78, '1992-08-06'),
    	('Ходьба на 20000 метров. Мужчины', 4645.6, '1994-05-07'),
    	('Ходьба на 30000 метров. Мужчины', 7304.1, '1992-10-03'),
    	('Ходьба на 50000 метров. Мужчины', 12927.2, '2011-03-12');

    INSERT INTO public.sportsman
    	(sportsman_name, rank, year_of_birth, personal_record, country)
    VALUES
    	('Petrov Stepan Olegovich', 1, 1990, 8.43, 'Russia'),
    	('Ivanov Igor Maratovich', 2, 1996, 8.08, 'Ukraine'),
    	('Bagrov Dmitriy Vasilievich', 4, 1989, 7.43, 'Russia'),
    	('Vezlov Alexander Valentinovich', 3, 1981, 8.33, 'Ukraine'),
    	('Abramov Oleg Grigorievich', 1, 1995, 7.93, 'Belarus'),
    	('Rubnov Vsevolod Stepanovich', 2, 1999, 4.33, 'Kazakhstan'),
    	('Savin Andrey Dmitrievich', 4, 1981, 10.94, 'Russia'),
    	('Danilin Alexander Petrovich', 2, 1980, 14.9, 'Russia'),
    	('Ryabkov Dmitriy Vladimirovich', 3, 2001, 4.51, 'Kazakhstan'),
    	('Gspoian Gregoriy Maratovich', 1, 2002, 8.4, 'Russia'),
    	('Pastuchov Maskim Viacheslavovich', 1, 1885, 15.91, 'Belarus'),
    	('Averin Michail Olegovich', 2, 1991, 11.19, 'Russia'),
    	('Volodin Vasiliy Ashotovich', 1, 1984, 12.13, 'Belarus'),
    	('Kruglov Vladimir Andreevich', 1, 1974, 13.41, 'Russia'),
    	('Krykov Oleg Olegovich', 3, 1979, 9.13, 'Russia');

    INSERT INTO public.result
    	(competition_id, sportsman_id, result, city, hold_date)
    VALUES
    	(1, 15, 8.1, 'Nizhniy Novgorod', '2001-03-29'),
    	(20, 1, 4.43, 'Sarov', '2001-04-29'),
    	(4, 14, 9.3, 'Arzamas', '2004-12-01'),
    	(8, 2, 12.01, 'Cheboksary', '2003-04-4'),
    	(7, 13, 9.04, 'Moskva', '2008-11-10'),
    	(2, 3, 12.90, 'Saint Petersburg', '2003-9-21'),
    	(3, 12, 13.3, 'Youshkar-Ola', '2001-03-20'),
    	(5, 4, 134, 'Velikiy Novgorod', '2001-1-19'),
    	(6, 11, 13.34, 'Tiymen', '2001-2-3'),
    	(19, 5, 43.93, 'Gorodets', '2001-3-6'),
    	(18, 10, 54.39, 'Vladimir', '2001-04-9'),
    	(17, 6, 44.17, 'Vyksa', '2001-05-13'),
    	(16, 9, 103.28, 'Adler', '2001-06-16'),
    	(21, 7, 81.36, 'Sochi', '2001-08-19'),
    	(9, 8, 94.44, 'Gorodets', '2001-07-21');

-- ///////////////////////////// QUERYING /////////////////////////////

-- Выдайте всю информацию о спортсменах из таблицы sportsman.
SELECT *
  FROM public.sportsman;

-- Выдайте наименование и мировые результаты по всем соревнованиям.
SELECT competition_name, world_record
  FROM public.competition;

-- Выберите имена всех спортсменов, которые родились в 1990 году.
SELECT sportsman_name
  FROM public.sportsman
 WHERE year_of_birth = 1990;

-- Выберите наименование и мировые результаты по всем соревнованиям, установленные 12-05-2010 или 15-05-2010.
SELECT competition_name
  FROM public.competition
 WHERE set_date IN ('2010-05-12', '2010-05-15');

-- Выберите дату проведения всех соревнований, которые проводились в Москве и полученные на них результаты равны 10 секунд.
SELECT hold_date
  FROM public.result
 WHERE city = 'Moskva'
   AND result = 10;

-- Выберите названия всех соревнований, у которых мировой рекорд равен 15 с и дата установки рекорда не равна 12-02-2015.
SELECT competition_name
  FROM competition
 WHERE world_record = 15
   AND set_date != '2015-02-12';

-- Выберите имена всех спортсменов, у которых персональный рекорд не равен 25 с.
SELECT sportsman_name
  FROM public.sportsman
 WHERE personal_record != 25;

-- Выберите города проведения соревнований, где результаты принадлежат множеству {13, 25, 17, 9}.
SELECT city
  FROM public.result
 WHERE result IN (13, 25, 17, 9);

-- Выберите наименования всех соревнований, у которых в названии есть слово "международные".
SELECT competition_name
  FROM public.competition
 WHERE competition_name LIKE '%международные%';

-- Выберите дату проведения всех соревнований, у которых город проведения начинается с буквы "М".
SELECT hold_date
  FROM public.result
 WHERE city LIKE 'M%';

-- Выберите имена всех спортсменов, у которых год рождения 2000 и разряд не принадлежит множеству {3, 7, 9}.
SELECT sportsman_name
  FROM public.sportsman
 WHERE year_of_birth = 2000
   AND rank NOT IN (3, 7, 9);

-- Выберите имена всех спортсменов, у которых имена начинаются с буквы "М" и год рождения не заканчивается на "6".
SELECT sportsman_name
  FROM public.sportsman
 WHERE sportsman_name LIKE '% M% %'
   AND CAST(year_of_birth AS varchar) NOT LIKE '%6';

-- Вычислите максимальный результат, полученный в Москве.
SELECT MAX(result)
  FROM public.result
 WHERE city = 'Moskva';

-- Выберите годы рождения всех спортсменов без повторений.
SELECT DISTINCT(year_of_birth)
  FROM public.sportsman;

-- Найдите количество результатов, полученных 12-05-2014.
SELECT COUNT(*)
  FROM public.result
 WHERE hold_date = '2014-05-12';

-- Вычислите минимальный год рождения спортсменов, которые имеют 1 разряд.
SELECT MIN(year_of_birth)
  FROM public.sportsman
 WHERE rank = 1;

-- Вычислите средний результат каждого из спортсменов.
SELECT AVG(result)
  FROM public.result
 GROUP BY sportsman_id;

-- Выведите годы рождения спортсменов, у которых результат, показанный в Москве выше среднего по всем спортсменам.
SELECT AVG(result)
  FROM public.result
 GROUP BY sportsman_id;

-- Выведите имена всех спортсменов, у которых год рождения больше, чем год установления мирового рекорда, равного 12 с.
SELECT sportsman_name
  FROM public.sportsman
 WHERE year_of_birth >
	   EXTRACT (YEAR FROM
					(SELECT set_date
					   FROM competition
			   	      WHERE world_record = 12));

-- Выведите имена всех спортсменов, у которых разряд ниже среднего разряда всех спортсменов, родившихся в 2000 году.
SELECT sportsman_name
  FROM public.sportsman
 WHERE rank <
	  (SELECT AVG(rank)
	     FROM public.sportsman
	    WHERE year_of_birth = 2000);

-- Выведите данные о спортсменах, у которых персональный рекорд совпадает с мировым.
SELECT sportsman_name
  FROM public.sportsman
  JOIN public.competition ON personal_record = world_record; -- INNER JOIN == JOIN

-- Определите количество участников с фамилией Иванов, которые участвовали в соревнованиях с названием, содержащим слово 'Региональные'.
SELECT COUNT(*)
  FROM result
  JOIN competition ON result.competition_id = competition.competition_id
  JOIN sportsman ON result.sportsman_id = sportsman.sportsman_id
 WHERE competition.competition_name LIKE '%Региональные%'
   AND sportsman.sportsman_name LIKE '% Ivanov %';

-- Выведите города, в которых были установлены мировые рекорды.
SELECT result.city
  FROM result
  JOIN competition ON competition.competition_id = result.competition_id;

-- Выведите наименования соревнований, у которых дата установления мирового рекорда совпадает с датой проведения соревнований в - Москве 20-04-2015.
SELECT competition.competition_name
  FROM competition
  JOIN result ON competition.competition_id = result.competition_id
 WHERE result.city = 'Moskva'
   AND result.hold_date = '2015-04-20';

-- Найдите минимальный разряд спортсменов, которые установили мировой рекорд.
SELECT MIN(sportsman.rank)
  FROM sportsman
  JOIN result ON sportsman.sportsman_id = result.sportsman_id;

-- Определите имена спортсменов, у которых личные рекорды совпадают с результатами, установленными 12-04-2014.
SELECT sportsman.sportsman_name
  FROM sportsman
  JOIN result ON sportsman.personal_record = result.result
 WHERE result.hold_date = '2014-04-12';

-- Вычислите возраст спортсменов, которые участвовали в соревнованиях в Москве.
SELECT year_of_birth
  FROM result
  JOIN sportsman ON result.sportsman_id = sportsman.sportsman_id
 WHERE result.city = 'Moskva';

-- Определите, спортсмены какой страны участвовали в соревнованиях больше всего.
SELECT sportsman.country
  FROM sportsman
 INNER JOIN result ON sportsman.sportsman_id = result.sportsman_id
 GROUP BY sportsman.country
 ORDER BY COUNT(sportsman.sportsman_id) DESC
 LIMIT 1;

-- Выведите названия соревнований, на которых было установлено максимальное количество мировых рекордов.
SELECT competition_name
  FROM competition
 GROUP BY competition_name
 ORDER BY COUNT(competition_name) DESC
 LIMIT 1;

-- Выведите список спортсменов в виде 'Спортсмен ' ['имя спортсмена'] 'показал результат' ['результат'] 'в городе' ['город'].
SELECT FORMAT('Спортсмен %s показал результат %s в городе %s',
			 SUBSTRING(sportsman.sportsman_name, ' .+ '), result.result, result.city)
  FROM sportsman
  JOIN result ON sportsman.sportsman_id = result.sportsman_id;

-- Измените страну у спортсменов, у которых разряд равен 1 или 2, с Италии на Россию.
UPDATE sportsman
   SET country = 'Russia'
 WHERE country = 'Italy'
   AND rank IN (1, 2);

-- Измените разряд на 1 тех спортсменов, у которых личный рекорд совпадает с мировым.
UPDATE sportsman
   SET rank = 1
  FROM sportsman s
 INNER JOIN result ON s.personal_record = result.result;

-- Измените дату проведения всех соревнований, проходящих в Москве на 4 дня вперед.
UPDATE result
   SET hold_date = hold_date + INTERVAL '4 days'
 WHERE city = 'Moskva';

-- Измените название соревнований с 'Бег' на 'Бег с препятствиями'.
UPDATE competition
   SET competition_name = 'Бег с препятствиями'
 WHERE competition_name = 'Бег';

-- Увеличьте мировой результат на 2 с для соревнований ранее 20-03-2005.
UPDATE competition
   SET world_record = world_record + 2
 WHERE set_date < '2005-03-20';

-- Уменьшите результаты на 2 с соревнований, которые проводились 20-05-2012 и показанный результат не менее 45 с.
UPDATE result
   SET result = result - 2
 WHERE hold_date = '2012-05-20'
   AND result >= 45;

-- Удалите все соревнования, у которых результат равен 20 с.
DELETE FROM result
 WHERE result = 20;

-- Удалите все результаты спортсменов, которые родились в 2001 году.
DELETE FROM result
 WHERE sportsman_id =
 	  (SELECT sportsman_id
     	 FROM sportsman
 		WHERE year_of_birth = 2001);

-- Удалите все результаты соревнований в Москве, участники которых родились не позже 1980 г.
DELETE FROM result
 WHERE city = 'Moskva'
   AND sportsman_id IN
   	  (SELECT sportsman_id
     	 FROM sportsman
 		WHERE year_of_birth <= 1980);