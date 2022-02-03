sourcefiles = \
Main.java \
Depart.java

classfiles  = $(sourcefiles:.java=.class)
classfiles = Depart.class Main.class

default: build run

build:
	javac -d . -classpath . Depart.java
	javac -d . -classpath . Main.java

clean:
	rm -f *.class