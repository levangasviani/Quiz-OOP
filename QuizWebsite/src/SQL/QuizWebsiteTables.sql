create schema QuizWebsiteDatabase

default character set utf8;




/**
	მომხმარებლების ტიპების ცხრილი
*/

CREATE TABLE `QuizWebsiteDatabase`.`USER_TYPES` (
    `ID` INT NOT NULL,
    `NAME` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`ID`)
);




/**
	მომხმარებლების ცხრილი
*/

CREATE TABLE `QuizWebsiteDatabase`.`USERS` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    `USERNAME` VARCHAR(45) NOT NULL,
    `PASSWORD` VARCHAR(100) NOT NULL,
    `FIRSTNAME` VARCHAR(45) NOT NULL,
    `LASTNAME` VARCHAR(45) NOT NULL,
    `EMAIL` VARCHAR(45) NOT NULL,
    `TYPE_ID` INT NOT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE INDEX `USERNAME_UNIQUE` (`USERNAME` ASC),
    INDEX `fk_USERS_TO_TYPES_idx` (`TYPE_ID` ASC),
    CONSTRAINT `fk_USERS_TO_TYPES` FOREIGN KEY (`TYPE_ID`)
        REFERENCES `QuizWebsiteDatabase`.`USER_TYPES` (`ID`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);



/**
	მეგობრების ცხრილი
*/

CREATE TABLE `QuizWebsiteDatabase`.`FRIENDS` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    `USER_ONE` INT NOT NULL,
    `USER_TWO` INT NOT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE KEY (`USER_ONE`, `USER_TWO`),
    INDEX `fk_FRIENDS_1_idx` (`USER_ONE` ASC),
    INDEX `fk_FRIENDS_2_idx` (`USER_TWO` ASC),
    CONSTRAINT `fk_FRIENDS_1` FOREIGN KEY (`USER_ONE`)
        REFERENCES `QuizWebsiteDatabase`.`USERS` (`ID`),
    CONSTRAINT `fk_FRIENDS_2` FOREIGN KEY (`USER_TWO`)
        REFERENCES `QuizWebsiteDatabase`.`USERS` (`ID`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);




/**
	ქვიზის სახელების ცხრილი
*/

CREATE TABLE `QuizWebsiteDatabase`.`QUIZZES` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    `NAME` VARCHAR(45) NOT NULL,
    `DESCRIPTION` VARCHAR(100),
    `RANDOM` ENUM('TRUE', 'FALSE') NOT NULL,
    `PRACTICE_MODE` ENUM('TRUE', 'FALSE') NOT NULL,
    `FREQUENCY` INT NOT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC)
);




/**
	მომხმარებლის მიერ შევსებული ქვიზების ცხრილი
*/

CREATE TABLE `QuizWebsiteDatabase`.`COMPLETED_QUIZES` (
	`ID` INT NOT NULL AUTO_INCREMENT,
	`USER_ID` INT NOT NULL,
    `QUIZ_ID` INT NOT NULL,
    `SCORE` INT NOT NULL,
    `SPENT_TIME` INT NOT NULL,
    PRIMARY KEY (`ID`),
     INDEX `fk_COMPLETED_QUIZES_1_idx` (`USER_ID` ASC),
     INDEX `fk_COMPLETED_QUIZES_2_idx` (`QUIZ_ID` ASC),
    CONSTRAINT `fk_COMPLETED_QUIZES_TO_USERS` FOREIGN KEY (`USER_ID`)
        REFERENCES `QuizWebsiteDatabase`.`USERS` (`ID`),
    CONSTRAINT `fk_COMPLETED_QUIZES_TO_QUIZES` FOREIGN KEY (`QUIZ_ID`)
        REFERENCES `QuizWebsiteDatabase`.`QUIZZES` (`ID`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);



/**
	მომხმარებლის მიერ გაკეთებული ქვიზებსი ცხრილი
*/


CREATE TABLE `QuizWebsiteDatabase`.`CREATED_QUIZZES` (
  `ID` INT NOT NULL,
  `USER_ID` INT NOT NULL,
  `QUIZ_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_CREATED_TO_USERS_idx` (`USER_ID` ASC),
  INDEX `fk_CREATED_TO_QUIZZES_idx` (`QUIZ_ID` ASC),
  CONSTRAINT `fk_CREATED_TO_USERS`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `QuizWebsiteDatabase`.`USERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CREATED_TO_QUIZZES`
    FOREIGN KEY (`QUIZ_ID`)
    REFERENCES `QuizWebsiteDatabase`.`QUIZZES` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);





/**
	კითხვის ტიპების ცხრილი
*/

CREATE TABLE `QuizWebsiteDatabase`.`QUESTION_TYPES` (
    `ID` INT NOT NULL,
    `NAME` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`ID`)
);




/**
	კითხვების ცხრილი
*/

CREATE TABLE `QuizWebsiteDatabase`.`QUESTIONS` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    `QUESTION` VARCHAR(400) NOT NULL,
    `SCORE` INT NOT NULL,
    `CHECK_TYPE` ENUM('USER', 'COMPUTER') NOT NULL,
    `TIME` INT NOT NULL,
    `QUIZ_ID` INT NOT NULL,
    `TYPE_ID` INT NOT NULL,
    PRIMARY KEY (`ID`),
    INDEX `fk_QUESTIONS_1_idx` (`QUIZ_ID` ASC),
    INDEX `fk_QUESTIONS_2_idx` (`TYPE_ID` ASC),
    CONSTRAINT `fk_QUESTIONS_TO_QUIZZES` FOREIGN KEY (`QUIZ_ID`)
        REFERENCES `QuizWebsiteDatabase`.`QUIZZES` (`ID`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_QUESTIONS_TO_TYPES` FOREIGN KEY (`TYPE_ID`)
        REFERENCES `QuizWebsiteDatabase`.`QUESTION_TYPES` (`ID`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);



/**
	პასუხების ცხრილი
*/

CREATE TABLE `QuizWebsiteDatabase`.`ANSWERS` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    `ANSWER` VARCHAR(200) NOT NULL,
    `TF` ENUM('TRUE', 'FALSE') NOT NULL,
    `ORDER` ENUM('TRUE', 'FALSE') NOT NULL,
    `QUESTION_ID` INT NOT NULL,
    PRIMARY KEY (`ID`),
    INDEX `fk_ANSWERS_TO_QUESTIONS_idx` (`QUESTION_ID` ASC),
    CONSTRAINT `fk_ANSWERS_TO_QUESTIONS` FOREIGN KEY (`QUESTION_ID`)
        REFERENCES `QuizWebsiteDatabase`.`QUESTIONS` (`ID`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);




/**
	მიღწევების ტიპების ცხრილი
*/

CREATE TABLE `QuizWebsiteDatabase`.`ACHIEVEMENT_TYPES` (
    `ID` INT NOT NULL,
    `NAME` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`ID`)
);



/**
	მომხმარებლების ცხრილი
*/

CREATE TABLE `QuizWebsiteDatabase`.`ACHIEVEMENTS` (
    `ID` INT NOT NULL AUTO_INCREMENT,
    `USER_ID` INT NOT NULL,
    `TYPE_ID` INT NOT NULL,
    PRIMARY KEY (`ID`),
    INDEX `fk_ACHIEVEMENTS_1_idx` (`USER_ID` ASC),
    INDEX `fk_ACHIEVEMENTS_2_idx` (`TYPE_ID` ASC),
    CONSTRAINT `fk_ACHIEVEMENTS_TO_USERS` FOREIGN KEY (`USER_ID`)
        REFERENCES `QuizWebsiteDatabase`.`USERS` (`ID`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_ACHIEVEMENTS_TO_TYPES` FOREIGN KEY (`TYPE_ID`)
        REFERENCES `QuizWebsiteDatabase`.`ACHIEVEMENT_TYPES` (`ID`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);


CREATE TABLE `quizwebsitedatabase`.`notification_types` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`));

CREATE TABLE `quizwebsitedatabase`.`notifications` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `SENDER_ID` INT NOT NULL,
  `RECEIVER_ID` INT NOT NULL,
  `MESSAGE` VARCHAR(500) NULL,
  `QUIZ_ID` INT NULL,
  `QUESTION_ID` INT NULL,
  `TYPE_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_NOTIFICATIONS_TO_USERS1_idx` (`SENDER_ID` ASC),
  INDEX `fk_NOTIFICATIONS_TO_USERS2_idx` (`RECEIVER_ID` ASC),
  INDEX `fk_NOTIFICATIONS_TO_QUIZZES_idx` (`QUIZ_ID` ASC),
  INDEX `fk_NOTIFICATIONS_TO_QUESTIONS_idx` (`QUESTION_ID` ASC),
  INDEX `fk_NOTIFICATIONS_TO_NOTIFICATION_TYPES_idx` (`TYPE_ID` ASC),
  CONSTRAINT `fk_NOTIFICATIONS_TO_USERS1`
    FOREIGN KEY (`SENDER_ID`)
    REFERENCES `quizwebsitedatabase`.`users` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NOTIFICATIONS_TO_USERS2`
    FOREIGN KEY (`RECEIVER_ID`)
    REFERENCES `quizwebsitedatabase`.`users` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NOTIFICATIONS_TO_QUIZZES`
    FOREIGN KEY (`QUIZ_ID`)
    REFERENCES `quizwebsitedatabase`.`quizzes` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NOTIFICATIONS_TO_QUESTIONS`
    FOREIGN KEY (`QUESTION_ID`)
    REFERENCES `quizwebsitedatabase`.`questions` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NOTIFICATIONS_TO_NOTIFICATION_TYPES`
    FOREIGN KEY (`TYPE_ID`)
    REFERENCES `quizwebsitedatabase`.`notification_types` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `quizwebsitedatabase`.`notification_count` (
  `ID` INT NOT NULL,
  `USER_ID` INT NOT NULL,
  `COUNT` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_NITIFICATION_COUNT_TO_USERS_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_NITIFICATION_COUNT_TO_USERS`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `quizwebsitedatabase`.`users` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);