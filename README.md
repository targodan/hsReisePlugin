# hsReisePlugin [![Build Status](https://travis-ci.org/targodan/hsReisePlugin.svg?branch=develop)](https://travis-ci.org/targodan/hsReisePlugin) [![Coverage Status](https://coveralls.io/repos/github/targodan/hsReisePlugin/badge.svg?branch=develop)](https://coveralls.io/github/targodan/hsReisePlugin?branch=develop)[\*](#code-coverage)

Ein Plugin für die [HeldenSoftware](http://www.helden-software.de/), welches die Erschöpfung und Überanstrengung der Helden berechnet.

Die JavaDocs sind [hier](https://targodan.github.io/hsReisePlugin/) zu finden.

## Lizenz
Dieses Plugin unterliegt der GNU General Public License Version 3. Genaueres könnt ihr [hier](http://www.gnu.org/licenses/) nachlesen, oder in der [LICENSE](https://github.com/targodan/hsReisePlugin/blob/master/LICENSE) Datei.

## Benutzung
Unter [Releases](https://github.com/targodan/hsReisePlugin/releases) die neueste Version herunterladen und mit der HeldenSoftware über "Datei" -> "Importieren" -> "Importieren aus Datei" installieren.
Anschließend die HeldenSoftware neu starten und über das "Erweiterungen" Menü auf "Reisen" klicken. Der Rest sollte hoffentlich selbsterklärend sein.

## Compilieren
Das hsReisePlugin benutzt [gradle](https://gradle.org/) zum Compilieren und Testen.
Zum Compilieren einfach den mitgelieferten Wrapper mit den Parametern `xjc build` ausführen.

Linux:
```bash
./gradlew xjc build
```

Windows:
```dos
gradlew.bat xjc build
```

Anschließend findet ihr unter `build/lib` die gerade erstellte jar-Datei.

Wer zum Testen oft kompilieren und dann die jar-Datei kopieren muss, kann auch den `deploy` Task verwenden. Einfach die Datei `heldenSoftware.path.example` nach `heldenSoftware.path` kopieren und den Pfad zum Plugin-Ordner eintragen. Schon wird gradle euch die jar-Datei beim Aufruf des `deploy` Tasks dort hin kopieren.

Linux:
```bash
cp heldenSoftware.path.example heldenSoftware.path
$EDITOR heldenSoftware.path # Pfad mit Hilfe des lieblins-editor eintragen.
./gradlew xjc build deploy
```

Windows:
```dos
copy heldenSoftware.path.example heldenSoftware.path
:: Pfad mit Hilfe des lieblins-editors eintragen.
gradlew.bat xjc build deploy
```

## Beitragen
Ihr wollt zu diesem Projekt beitragen? Super! :grinning::thumbsup:

### Bugs und fehlende Features
Immer her mit den Bugs, aber auch erwünschte Features sind gerne gesehen.
Einfach ein [Issue auf machen](https://github.com/targodan/hsReisePlugin/issues/new) und ich werde mein bestes geben, das Issue so schnell wie möglich zu bearbeiten.

### Selber schreiben
Wer selbst Code schreiben will, hier die Schritte zum beitragen.

1. Forken
2. Euren Fork clonen.
3. Neue Branch erstellen. `git checkout -b feature/<euerFeature>` oder `git checkout -b bugfix/<euerFix>`.
4. Eure änderungen vornehmen und abschicken `git commit` und `git push`. Bitte schaut auch in die [Coding Richtlinien](#coding-richtlinien) unten.
5. Einen Pull-request auf den `develop` Branch senden. Pull-requests auf andere Branches werden verworfen.
6. Ich schaue mir das an und wenn alles gut ist werde ich euren Code mergen.

### Coding Richtlinien
Hier sind ein paar Richtlinien, an die ihr euch bitte halten sollt, wenn ihr zu diesem Projekt beitragen wollt.

- **Sprache**

    Programmiert und Kommentiert wird bitte auf Englisch, vorzugsweise auf britischem Englisch.
    Einzige ausnahme bilden feststehende Begriffe aus der DSA-Welt bzw. aus der HeldenSoftware (z. B. Konstitution, Held oder Erschöpfung).

- **Umlaute**

    Ein hoch auf UTF-8!
    Die Benutzung von Umlauten ist nicht nur gestattet sondern erwünscht.
    Wenn ein "ü" in dem Wort vorkommt, dann schreibt auch bitte ein "ü" und kein "ue".
    Java kommt damit poblemlos klar und ich finde es macht das ganze etwas leserlicher.

- **Einrückung**

    Eingerückt wird ausschließlich mit Leerzeichen.
    Pull-requests mit Tabulatorschritten werden zurückgewiesen!
    Die standard Einrückungstiefe ist 4 Leerzeichen.

- **Einzeilige blocks**

    Einzeilige Blocks wie z. B. `if` Zweige oder `for` Schleifen mit nur einer Zeile im body haben *IMMER* in geschweiften Klammern zu sein.
    Die Erfahrung hat gezeigt, dass es vor allem beim "Ich kommentiere mal eben diese Zeile aus um was zu testen." nicht nur sehr schnell, sondern fast immer zu Nebeneffekten kommt, die einem den Tag verderben.

    **Nicht** so:
<div class="highlight highlight-source-java">
<pre><s>if(a == b)</s>
<s>    return b;</s></pre>
</div>

    Sondern so:
    ```java
    if(a == b) {
        return b;
    }
    ```

- **Commits**

    Die commit messages bitte auch möglichst auf Englisch halten.
    Der böse shortcut ~~`git commit -m "<message>"`~~ darf nicht benutzt werden.
    Macht eure commits bitte über `git commit`. Dort seht ihr direkt wenn eure zusammenfassung zu lang ist und ihr könnt noch einen ausführlichen Text hinzufügen.
    Es muss mindestens drin stehen, was ihr getan/verändert habt. Idealerweise verweist ihr in eurer commit message noch auf das entsprechende Issue, wenn ein solches existiert. [Hier ein paar Tips dazu.](https://help.github.com/articles/closing-issues-via-commit-messages/)

    Sollte es sich um die bearbeitung eines Issues handeln, dann verweist in der message auch bitte auf das entsprechende Issue.
    Solltet ihr im Laufe der Arbeit unsaubere commits gemacht haben, kein Problem aber bitte bearbeitet diese bevor ihr den Pull-request macht.
    [Hier](https://git-scm.com/book/en/v2/Git-Tools-Rewriting-History) ein nützlicher Artikel zu diesem Thema.

- **Bibliotheken**

    Ihr könnt alles verwenden, was in der Java-API enthalten ist.
    [Java-Streams](http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html) sind gerne gesehen.
    Von dritt-Bibliotheken bitte absehen.
    Wenn es garnicht ohne geht tretet bitte bevor ihr euch die Arbeit macht mit mir in Kontakt z. B. über ein Issue oder über E-Mail etc.

- **Kommentare**

    JavaDocs sind Pflicht, der Rest ist Kür.
    Wenn ich den Code jedoch nicht verstehe werde ich euch beim Pull-request wahrscheinlich darum bitten ein paar Kommentare einzufügen.

- **Lizenz-Header**

    Wenn ihr neue Dateien bzw. Klassen anlegt, dann fügt als erstes bitte oben den Lizenz-Header ein.
    Wenn ihr NetBeans benutzt, sollte es das automatisch für euch machen.

- ***Credit where credit is due***

    Wer eine Klasse erweitert oder neu erstellt, der trägt sich bitte in den JavaDocs als (zusätzlicher) `@author` ein.
    Wer will kann hier auch seine E-Mail Adresse angeben, das ist allerdings keinesfalls Pflicht.

    Zusätzlich zum `@author` Eintrag fügt ihr euch bitte auch in den Copyright Bereich des Lizenz-Headers der bearbeiteten Datei ein. Die Reihenfolge der Namen geht nach dem Wer-zuerst-kommt-malt-zuerst-Prinzip, angefügt wird also immer Hinten. Wenn die Liste zu lang wird, bitte einen Zeilenumbruch machen und den nächsten Namen einrücken. Der Header sollte dann in etwa so aussehen.
    ```java
/*
 * Copyright (C) 2016 Luca Corbatto, Melanie Musterfrau, Max Mustermann,
 *                    Noch ein Name, ..., Euer Name
 * ...
*/
    ```

## Aufbau dieses Projekts
Hier noch ein paar Worte zum Aufbau dieses Projekts.

### Die Branches
Die `master` Branch soll ab dem ersten Release sauber bleiben und nur stabilen Code enthalten.
Entwickelt wird auf dem `develop` Branch.
Je nachdem wie groß das Projekt wird werden wenn nötig auch `feature` und evtl. `release` Branches angelegt.
In Anlenung an den [git-flow Workflow](http://danielkummer.github.io/git-flow-cheatsheet/).

### Versionierung
Die Versionsnummern werden nach dem [Semantic-Versioning](http://semver.org/) vergeben.
Die aktuelle API ist definiert durch die [JavaDocs](https://targodan.github.io/hsReisePlugin).

## Code Coverage
Hier ein paar Worte zu der Code Coverage dieses Projekts.
Ich halte es nicht für Sinnvoll unbedingt 100% coverage erzielen zu wollen, allerdings sehen z. B. 50% recht schlecht aus.
Daher steht die oben angegebene Prozentzahl in Relation zu den von mir als wichtig erachteten Codezeilen.
Um dies zu erreichen habe ich folgende Anpassungen an die Berechnung vorgenommen.

-   Klassen, bei denen es in meinen Augen keinen Sinn ergibt sie zu Testen, sind von der Berechnung ausgenommen.
    Folgende Klassen sind davon betroffen.
    (Für die immer aktuelle Liste siehe `jacocoTestReport` Abschnitt in [build.grade](https://github.com/targodan/hsReisePlugin/blob/develop/build.gradle))

    - `reiseplugin.HeldenStart`

    - `reiseplugin.Main`

    - `reiseplugin.WTFException`

    - `reiseplugin.gui.*`

    Hier bin ich noch unentschlossen. Ich denke ich werde zumindest das Observable verhalten der Models Testen.

    - `reiseplugin.data.DummyService`

    - `reiseplugin.data.helden.entities.Daten`

    - `reiseplugin.data.helden.entities.ObjectFactory`

    - `reiseplugin.data.helden.entities.jaxb.*`

-   Simple Getter und Setter, sowie `hashCode` und `toString` methoden werden bei nicht Ausgeschlossenen Klassen in dem `dummyForCoverage` Test aufgerufen, ohne dass sie wirklich getestet werden.

    Als "simple" Getter bzw. Setter sehe ich solche an, die nichts anderes tun als auf einen Member zuzugreifen.
    Getter und Setter, welche Parameter auf ungültige Werte prüfen (z. B. nur positive Zahlen) sehe ich hingegen nicht als "simpel" an und solche werden entsprechend auch getestet.
