sourcefiles = \
Parsing.java \
Depart.java

classfiles  = $(sourcefiles:.java=.class)
classfiles = Depart.class Parsing.class

default: build run

build:
	javac -d . -classpath . Depart.java
	javac -d . -classpath . Parsing.java

clean:
	rm -f *.class