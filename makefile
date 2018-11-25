# basic format taken from https://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html

JFLAGS = -g -d . -cp .
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	src/main/Node.java \
	src/main/BST.java \
	src/main/Assignment.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
