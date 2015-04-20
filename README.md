-----------------------------------------------------------
/*Jak to ruszyć*/ 
maven run as build... goals: clean install profile development
clean install tomcat7:run 	profile development
 -----------------------------------------------------------------
moduly:
#gigs-parent - informacje o innych modulach
#gigs-i18n - modul do internacjonalizacji, komunikaty logi 
	resources:
		log4j-propertires   logi itd.
		messages  - slowniki 
	main.java
		package formatter
		package resolver
		funckjonalonosci do logowania
		
	

	
#model
		BaseEntity// wszystkie klasy modelu po niej dziedzicza
		hibernate core
		mapowanie do bazy
		
		Create:wywali stata zrobi nowa
		update : dopisze nowa
		(ustawiamy w pom w parencie)
		@ManyToOne - joinColumn

#repository musi podciagnac model  nie moze być ifów (nasze DAO) DAO to 4 klasy? (2(int+imp))

	evict to usuniecie z cache
	(coop repository config)
	findAll(); //zamist pisć  kla kazdego
	Hibernate criteria? <-poczytac
	nie pod id to piszemy zmiennna (serch token (tekst)) 
	do kazdej piszemy metode
	merge(!) te ktore podamy zostana uzyte do budowy zapytania
	apectJ //do translacji
	
		dependency injection 

#service// service to logika 
	
#util- statyczne klasy?

#webapp - widok i kontroler
	HomeControler
		adnotacja @Controller
		zwraca Stringa - nazwa strony do ktorej ma przejsc
		

#worker - backend
	PortalWorker  pobierze context z config.xml,który jest w resources
	pom  
	nasze parsery