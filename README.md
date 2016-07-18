# hsReisePlugin
Ein Plugin für die [HeldenSoftware](http://www.helden-software.de/), welches die Erschöpfung und Überanstrengung der Helden berechnet.

Die JavaDocs sind [hier](https://targodan.github.io/hsReisePlugin/) zu finden.

## Lizenz
Dieses Plugin unterliegt der GNU General Public License Version 3. Genaueres könnt ihr [hier](http://www.gnu.org/licenses/) nachlesen, oder in der [LICENSE](https://github.com/targodan/hsReisePlugin/blob/master/LICENSE) Datei.

## Benutzung
Unter [Releases](https://github.com/targodan/hsReisePlugin/releases) die neueste Version herunterladen und mit der HeldenSoftware über "Datei" -> "Importieren" -> "Importieren aus Datei" installieren.
Anschließend die HeldenSoftware neu starten und über das "Erweiterungen" Menü auf "Reisen" klicken. Der Rest sollte hoffentlich selbsterklärend sein.

## Compilieren
Das Repo beinhaltet das XML-Schema ([xml/native.xsd](https://github.com/targodan/hsReisePlugin/blob/master/xml/native.xsd)), welches mit Hilfe von [JAXB](https://jaxb.java.net/) genutzt werden kann um die XML-Daten aus der HeldenSoftware in Java-Klassen umzuwandeln.
Das enthaltene NetBeans-Projekt ist so konfiguriert, dass JAXB vor jedem
Compilieren mit den nötigen Parametern aufgerufen wird. Hierzu muss die
JAXB executable `xjc` im `PATH` enthalten sein.

Außerdem muss die jar-Datei der HeldenSoftware in das NetBeans-Projekt
eingebunden werden.

## Beitragen
Ihr wollt zu diesem Projekt beitragen? Super! :grinning::thumbsup:

### Bugs und fehlende Features
Immer her mit den Bugs, aber auch erwünschte Features sind gerne gesehen.
Einfach ein [Issue auf machen](https://github.com/targodan/hsReisePlugin/issues/new) und ich werde mein bestes geben, das Issue so schnell wie möglich zu bearbeiten.

### Selber schreiben
Wer selbst Code schreiben will, hier die Schritte zum beitragen.

1. Forken
2. Macht eure Änderungen bitte auf dem `development` Branch. Pull-requests auf den `master` Branch werde ich einfach löschen. Bitte schaut auch in die [Coding Richtlinien](#coding-richtlinien) unten.
3. Sendet einen Pull-request auf den `development` Branch.
4. Ich schaue mir das an und wenn alles gut ist werde ich euren Code mergen.

### Coding Richtlinien
Hier sind ein paar Richtlinien, an die ihr euch bitte halten sollt, wenn ihr zu diesem Projekt beitragen wollt.

- **Sprache:**
Programmiert und Kommentiert wird bitte auf Englisch, vorzugsweise auf britischem Englisch.
Einzige ausnahme bilden feststehende Begriffe aus der DSA-Welt bzw. aus der HeldenSoftware (z. B. Konstitution, Held oder Erschöpfung).

- **Umlaute:**
Ein hoch auf UTF-8!
Die Benutzung von Umlauten ist nicht nur gestattet sondern erwünscht.
Wenn ein "ü" in dem Wort vorkommt, dann schreibt auch bitte ein "ü" und kein "ue".
Java kommt damit poblemlos klar und ich finde es macht das ganze etwas leserlicher.

- **Commits:**
Die commit messages bitte auch möglichst auf Englisch halten.
Es muss mindestens drin stehen, was ihr getan/verändert habt.
Sollte es sich um die bearbeitung eines Issues handeln, dann verweist in der message auch bitte auf das entsprechende Issue.
Solltet ihr im Laufe der Arbeit unsaubere commits gemacht haben, kein Problem aber bitte bearbeitet diese bevor ihr den Pull-request macht.
[Hier](https://git-scm.com/book/en/v2/Git-Tools-Rewriting-History) ein nützlicher Artikel zu diesem Thema.

- **Bibliotheken:**
Ihr könnt alles verwenden, was in der Java-API enthalten ist.
[Java-Streams](http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html) sind gerne gesehen.
Von dritt-Bibliotheken bitte absehen.
Wenn es garnicht ohne geht tretet bitte bevor ihr euch die Arbeit macht mit mir in Kontakt z. B. über ein Issue oder über E-Mail etc.

- **Kommentare:**
JavaDocs sind Pflicht, der Rest ist Kür.
Wenn ich den Code jedoch nicht verstehe werde ich euch beim Pull-request wahrscheinlich darum bitten ein paar Kommentare einzufügen.

- **Lizenz-Header:**
Wenn ihr neue Dateien bzw. Klassen anlegt, dann fügt als erstes bitte oben den Lizenz-Header ein.
Wenn ihr NetBeans benutzt, sollte es das automatisch für euch machen.

- ***Credit where credit is due:***
Wer eine Klasse erweitert oder neu erstellt, der trägt sich bitte in den JavaDocs als (zusätzlicher) `@author` ein.
Hier könnt ihr selbstverständlich euren echten Namen angeben, ein Pseudonym ist aber auch in Ordnung.
Wer will kann auch seine E-Mail Adresse angeben, das ist allerdings keinesfalls Pflicht.

## Aufbau dieses Projekts
Hier noch ein paar Worte zum Aufbau dieses Projekts.

### Die Branches
Die `master` Branch soll ab dem ersten Release sauber bleiben und nur stabilen Code enthalten.
Entwickelt wird auf dem `development` Branch.
Je nachdem wie groß das Projekt wird werden wenn nötig auch `feature` und evtl. `release` Branches angelegt.
In Anlenung an den [git-flow Workflow](http://danielkummer.github.io/git-flow-cheatsheet/).

### Versionierung
Die Versionsnummern werden nach dem [Semantic-Versioning](http://semver.org/) vergeben wobei ich um ehrlich zu sein zu faul bin zu diesem Zeitpunkt eine saubere Definition der API zu geben.
Daher werde ich die Backwards-Compatibility hier eher intuitiv angehen.
Wenn eine Änderung gefahr Läuft alten Code oder bereits aktive Pull-request kaputt zu machen, dann ist das für mich ein Bruch der Backwards-Compatibility.
