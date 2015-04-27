maven run as build... goals: clean install profile development

clean install tomcat7:run 	profile development
 
moduly:
###gigs-parent
informacje o innych modulach, spina wszystko w calosc

###gigs-i18n
modul do internacjonalizacji, komunikaty logi 
resources:
log4j-propertires   logi itd.
messages  - slowniki 
main.java
package formatter
package resolver
funckjonalonosci do logowania
	
###gigs-model
jest to podejdynczy obiekt (klasa) w naszym swiecie, jeden koncert, jeden uzytkownik
BaseEntity// wszystkie klasy modelu po niej dziedzicza
hibernate core
mapowanie do bazy

Create:wywali stata zrobi nowa
update : dopisze nowa
(ustawiamy w pom w parencie)
@ManyToOne - joinColumn

###gigs-repository
musi podciagnac model  nie moze być ifów (nasze DAO) DAO to 4 klasy? (2(int+imp))
najprosciej mówic repozytorium to jest zbiór modeli na których operujemy, grupa, kilka
evict to usuniecie z cache
(coop repository config)
findAll(); //zamist pisć  dla kazdego
Hibernate criteria? <-poczytac
nie pod id to piszemy zmiennna (serch token (tekst)) 
do kazdej piszemy metode
merge(!) te ktore podamy zostana uzyte do budowy zapytania
apectJ //do translacji
dependency injection 

###gigs-service
service to logika, sprawdzanie czy nie brakuje jakis danych wpisywanych przez uzytkownika albo np dodaje date rejestracji do danych ktore uzytkownik wprowadza podczas rejestracji 
	
###gigs-util
statyczne klasy, jakies metody ktore nie wymagaja tworzenia obiektow, np funkcje ktore robia jakies okreslone smieci typu normalizuja stringi czy cos, taka piwnica jesli chodzi o kod 

###gigs-webapp
widok i kontroler
HomeControler
adnotacja @Controller
zwraca Stringa - nazwa strony do ktorej ma przejsc
		

###gigs-worker-backend
równiez kontroler
PortalWorker  pobierze context z config.xml,który jest w resources
nasze parsery
