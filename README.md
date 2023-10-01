# eppTec
Algo written in Java can be found in Algo/src folder
Below you can find link to data model diagram and SQL query for the tables and exercises

https://app.creately.com/d/TLClNv2DgfD/edit

-----------------------------------------------------------------------------------------

CREATE DATABASE IF NOT EXISTS Bank;

USE Bank;

CREATE TABLE IF NOT EXISTS Klient (
	klient_id INT AUTO_INCREMENT PRIMARY KEY,
	jmeno VARCHAR(255) NOT NULL,
	prijmeni VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS Typy_transakci(
transakce_id INT AUTO_INCREMENT PRIMARY KEY,
nazev_transakce VARCHAR(255) NOT NULL,
popis_transakce TEXT

);
u
CREATE TABLE IF NOT EXISTS Ucet(
	ucet_id INT AUTO_INCREMENT PRIMARY KEY,
	cislo_uctu VARCHAR(20) NOT NULL,
	klient_id INT NOT NULL,
	FOREIGN KEY (klient_id) REFERENCES Klient(klient_id)
);

CREATE TABLE IF NOT EXISTS Transakce(
	transaction_id INT AUTO_INCREMENT PRIMARY KEY,
	ucet_id INT NOT NULL,
	typ_transakce INT NOT NULL,
	castka DECIMAL(10, 2) NOT NULL,
	datum_transakce DATE NOT NULL,
	FOREIGN KEY (ucet_id) REFERENCES Ucet(ucet_id),
	FOREIGN KEY (typ_transakce) REFERENCES Typy_transakci(transakce_id)
);

CREATE TABLE IF NOT EXISTS Balance(
	balance_id INT AUTO_INCREMENT PRIMARY KEY,
	ucet_id INT NOT NULL,
	datum DATE NOT NULL,
	jistina DECIMAL(10, 2),
	urok DECIMAL(10, 2),
	poplatky DECIMAL(10, 2),
	FOREIGN KEY (ucet_id) REFERENCES Ucet(ucet_id)
);


SELECT * FROM Klient
LEFT JOIN Ucet 
ON Klient.klient_id = Ucet.klient_id
JOIN (
	SELECT ucet_id, SUM(jistina) as soucet_jistin
    FROM Balance
    WHERE MONTH(datum) = MONTH(current_date())
    GROUP BY ucet_id
) Balance 
ON Ucet.ucet_id = Balance.ucet_id
WHERE soucet_jistin > c;

SELECT Klient.klient_id, jmeno, prijmeni, MAX(soucet_pohledavek) AS maximalni_vyska_pohledavek FROM Klient
JOIN Ucet
ON Klient.klient_id = Ucet.klient_id
JOIN( 
	SELECT ucet_id, SUM(castka) AS soucet_pohledavek
    FROM Transakce
    WHERE MONTH(datum_transakce) = MONTH(CURRENT_DATE())
    GROUP BY ucet_id
) Transakce 
ON Ucet.ucet_id = Transakce.ucet_id
GROUP BY Klient.klient_id, jmeno, prijmeni
ORDER BY maximalni_vyska_pohledavek DESC
LIMIT 10;
