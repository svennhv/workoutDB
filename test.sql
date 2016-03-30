
INSERT INTO EXERCISE
VALUES( "bench press", "breast exercise", "120kg x 3", "100kg x3", 70, 5, 3, NULL, NULL);

INSERT INTO EXERCISE
VALUES( "squats", "leg exercise", "160kg x 3", "120kg x3", 90, 5, 3, NULL, NULL);

INSERT INTO EXERCISE
VALUES("jogging", "endurence training", "10km", "12 km", NULL,  NULL, NULL, 10, 65);

INSERT INTO WORKOUT
VALUES(NULL, "Strength exercise", TRUE, "16.03.2017", 60, 6, 8, "upper body strength exercises", NULL, "good", 0);

INSERT INTO WORKOUT
VALUES(NULL, "Endurance ecercise", TRUE, "17.03.2017", 70, 6, 6, "endurance exercise", "sunny, warm temperature", NULL,NULL);

INSERT INTO WORKOUTTEMPLATE
VALUES(1,1);

INSERT INTO EXERCISECATEGORY
VALUES("Endurance training");

INSERT INTO LOGENTRY
VALUES(NULL,"jogging");

INSERT INTO CANREPLACE
VALUES("jogging","intervall training");


INSERT INTO EXERCISEINWORKOUT
VALUES("jogging",1);

INSERT INTO EXERCISEINCATEGORY
VALUES("jogging","endurance training");

INSERT INTO CATEGORYINCATEGORY
VALUES("training","endurance training");