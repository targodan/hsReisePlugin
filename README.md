# ReisePlugin

Ein kleines Plugin für die HeldenSoftware, welche die Erschöpfung und
die Überanstrengung der Helden berechnen kann.

## Compilieren

Das Repo beinhaltet das XML-Schema (xml/native.xsd), welches mit Hilfe von
[JAXB](https://jaxb.java.net/) genutzt werden kann um die XML-Daten aus
der HeldenSoftware in Java-Klassen umzuwandeln.

Das enthaltene NetBeans-Projekt ist so konfiguriert, dass JAXB vor jedem
Compilieren mit den nötigen Parametern aufgerufen wird. Hierzu muss die
JAXB executable `xjc` im `PATH` enthalten sein.

Außerdem muss die jar-Datei der HeldenSoftware in das NetBeans-Projekt
eingebunden werden.
