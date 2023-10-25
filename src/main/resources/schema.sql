DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id 						INT AUTO_INCREMENT  PRIMARY KEY,
  created 	                DATE NOT NULL,
  lastLogin 				DATE,
  token 	                VARCHAR(255),
  isActive 				    BOOLEAN NOT NULL,
  name                      VARCHAR(255),
  email                     VARCHAR(255),
  password                  VARCHAR(255),
  phones                    VARCHAR(255)
);