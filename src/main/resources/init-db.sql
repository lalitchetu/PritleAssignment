CREATE TABLE UserProfile (
	userID INTEGER NOT NULL,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    dateOfBirth VARCHAR(100),
    profilePicPath VARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(100),
   PRIMARY KEY (userID)
);


CREATE TABLE Alert (
	alertID INTEGER NOT NULL,
    alert VARCHAR(100),
   PRIMARY KEY (alertID)
);


CREATE TABLE UserAlert (
    UserAlert INTEGER NOT NULL,
	userID INTEGER,
	alertID INTEGER,
   PRIMARY KEY (UserAlert)
);

INSERT INTO UserProfile VALUES(1, 'firstName', 'lastName', '28/05/1990', '/root', 'test@gmail.com', '9873835219');

INSERT INTO Alert VALUES(1, 'PUSH');
INSERT INTO Alert VALUES(2, 'SMS');
INSERT INTO Alert VALUES(3, 'EMAIL');

INSERT INTO UserAlert VALUES(1, 1, 1);
INSERT INTO UserAlert VALUES(2, 1, 2);
INSERT INTO UserAlert VALUES(3, 1, 3);
