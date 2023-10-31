DROP TABLE IF EXISTS users;

CREATE TABLE Users (
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

CREATE TABLE Phone (
  id 						INT AUTO_INCREMENT  PRIMARY KEY,
  number                    VARCHAR(255),
  cityCode                  VARCHAR(255),
  countryCode               VARCHAR(255),
  user_id                   INT,
  CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES Users(id)
);
