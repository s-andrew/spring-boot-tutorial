INSERT INTO PERSONS (ID, FIRST_NAME, SECOND_NAME, BIRTHDAY)
    VALUES
    (1, 'Stupid', 'Student', '2018-01-20'),
    (2, 'Nikita', 'Zubetch', '1997-01-10'),
    (3, 'Stas', 'Pochipov', '1996-12-10');





INSERT INTO PHONES(ID, NUMBER, PERSON_ID)
    VALUES
    (1, '00000000000', 1),
    (2, '11111111111', 2),
    (3, '22222222222', 3);



INSERT INTO PROFESSIONS(ID, TITLE)
    VALUES
    (1, 'Po professii'),
    (2, 'Ne po professii');


INSERT INTO PERSONS_PROFESSIONS(PERSON_ID, PROFESSION_ID)
    VALUES
    (1, 1),
    (2, 2),
    (3, 1);
