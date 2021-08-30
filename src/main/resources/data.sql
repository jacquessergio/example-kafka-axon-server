DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS BILLIONAIRES;

CREATE TABLE clients (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  uuid VARCHAR(250) NOT NULL,
  name VARCHAR(250) NOT NULL
);

INSERT INTO clients (uuid, name) VALUES ('1e009ea6-2f19-4fce-bcf5-08183b37e93d', 'Goku');
INSERT INTO clients (uuid, name) VALUES ('d105d10e-2ceb-4062-9991-e2e9acecc41d', 'Vegeta');
INSERT INTO clients (uuid, name) VALUES ('05f86996-08e4-4fc4-8e7a-a62f84763b94', 'Bulma');