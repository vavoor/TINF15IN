# Pipifax - Die Sprache

Pipifax ist eine einfache Programmiersprache, die die wesentlichen Elemente einer prozeduralen Sprache enthält. Es ist eine kleine Sprache, für die auch in relativ kurzer Zeit ein Übersetzer entwickelt werden kann.

## Die Eingabe
Ein Pipifax-Programm besteht aus einer Datei mit 8-Bit ASCII-Daten (z.B. ISO 8859-1). In dieser Datei sind alle Funktionen und globalen Variablen definiert. Die Reihenfolge darin spielt keine Rolle, d.h. Funktionen können andere Funktionen und globale Variablen nutzen, die in der Datei erst danach deklariert/definiert werden.

## Kommentare
Kommentare in der Eingabe werden mit dem \#-Zeichen markiert und reichen bis zum Zeilenende:

\# Das ist ein Kommentar


## Bezeichner
Bezeichner (Identifier) für Funktionen und Variablen beginnen mit einem Buchstaben oder Unterstrich und können danach beliebig viele Buchstaben, Ziffern und Unterstriche enthalten.

## Typen
In Pipifax existieren folgende Datentypen:

- Vorzeichenbehaftete, ganze Zahlen im Wertebereich -2^31bis +2^31-1 (int)
- Fließkommazahlen gemäß IEEE 754 mit 64 Bit (double)
- Zeichenketten beliebiger Länge (string)
- Felder/Arrays beliebiger Typen und beliebiger Länge
- Referenzen auf beliebige Typen (nur als Funktionsparameter, nicht als Rückgabetyp oder Typ einer globalen Variablen)
- Literale
- Ganze Zahlen (Integers) werden in Dezimalschreibweise dargestellt: 0, 1, 1234, -3456
- Fließkommazahlen werden mit Dezimalpunkt oder in Exponentialschreibweise dargestellt: 3.14, -0.2, -1.2e+10, 0.3E-20, 8E70
- Zeichenketten (Strings) werden in doppelten Anführungszeichen notiert: "Das ist ein String!"

Ein String darf auch Zeilenumbrüche enthalten.


# Variablen
Variablen - global oder lokal - werden wie folgt deklariert:

```
var a int  # a is a 32-bit integer
var b double  # b is a 64-bit double
var c string  # c is a string of ASCII characters
var d [30]int  # d is an array of 30 integers
var e [4][5] string  # e is an array of 4 arrays of 5 strings
```

## Funktionen
Funktionen können in beliebiger Reihenfolge definiert werden. Die Funktion main wird beim Starten des Programms automatisch aufgerufen.
```
func main(args *[]string) int {
  # Here's the code
}
```

Kommandozeilenargumente werden an die main-Funktion als Referenz auf ein Array von strings übergeben.

```
func square(v double) double {
  square = v*v
}
```

Falls eine Funktionen einen Wert zurückgibt (hier vom Typ double), dann ist innerhalb der Funktion der Funktionsname als lokale Variable vom Rückgabetyp verfügbar. Im obigen Beispiel würde die Funktion square das Quadrat des Funktionsarguments v zurückgeben.

Funktionen dürfen nicht innerhalb von Funktionen definiert werden.

## Funktionsparameter
Die Parameter einer Funktion werden in den Klammern ähnlich wie Variablen deklariert, allerdings wird das Schlüsselwort var weggelassen.

```
func print (i int, d double, s string) {
  # a function with no return value
}
```

Eine Funktion kann beliebig viele Argumente haben. Sie muss auch nicht unbedingt einen Rückgabewert besitzen.

```
func no_arguments() {}
```

Als Funktionsparameter ist auch der Typ Referenz und Referenz auf ein Array unbekannter Größe zulässig. Referenzen werden by reference übergeben, andere Argument werden by value übergeben:

```
func f0() {
  var v int
  v = 4

  var out int
  out = f1(v)
  # v = 6 since passed by reference into f1
  # out = 3
}
func f1(i *int) int {
  i = i+2
  f1 = i/2
}
```

f1 hat einen Parameter vom Typ Referenz auf int. Innerhalb der Funktion wird eine Referenz benutzt wie eine Variable.

```
func f2(a *[17]double)
```

f2 hat als Parameter eine Referenz auf ein Array mit 17 double Elementen.

```
func f3 (len int, a *[]string)
```

f3 hat zwei Parameter: ein integer len und eine Referenz auf ein Array unbekannter Länge

```
func f4(a [2]double)
```

f4 hat als Parameter ein Array mit zwei double-Werten, die by value übergeben (sprich: kopiert) werden.

```
func f5(illegal []int)
```

Arrays unbekannter Größe dürfen nicht by value übergeben werden.

## Lokale Variablen

Syntaktisch werden lokale Variablen wie globale Variablen deklariert. Allerdings befindet sich die Deklaration in einem Statement-Block (d.h. innerhalb von geschweiften Klammern). Der Gültigkeitsbereich (Scope) beginnt mit der Deklaration und endet mit dem umschließenden Block.

```
func f(v1 int) double {   # scope of f and v1 starts here
  var s string  # scope of s starts here
  if v1!=0 {
    s = "Not null"
    var v2 int  # scope of v2 starts here
    print(v2)
  }  # scope if v2 ends here
}
# scope of f, v1 and s ends here
```

Variablen im selben Scope müssen verschieden heißen. Allerdings dürfen sich Variablen in verschiedenen Scopes verdecken:

```
var a int
var x [5]int
func f(x int) {  # scope if x/int starts here
  var x double  # scope of x/double starts here
  a = 10
  while a>0 {
    var x string  # scope of x/string starts here
    a = a-1
  }  # scope of x/string ends here
} # scope of x/int and x/double ends here
# x/array is again visible here
```

## Statements
Statements (Programmbefehle) können nur innerhalb von Funktionen vorkommen. Es gibt folgende Arten von Statements:

- Zuweisungen
- Verzweigungen
- Schleifen
- Funktionsaufrufe

Statements werden nicht mit Semikolon terminiert. Es dürfen mehrere Statements in einer Zeile stehen, und Statements dürfen auch über mehrere Zeilen verstreut sein.

Innerhalb von Statement-Blöcken können Befehle und Variablendeklarationen in beliebiger Reihenfolge vorkommen.

### Zuweisungen:

```
a = 3.14  # ok
a[4] = "Hello World!"  # ok
b[1][2][3] = a[3]  # ok
a()[x] = 0  # Not allowed!
```

Dabei muss der Typ des Ausdrucks auf der rechten Seite mit dem Typ der Variablen auf der linken Seite übereinstimmen. Eine implizite Typkonvertierung findet nicht statt.

### Verzweigungen:

```
if a {
  # Weitere Statements oder Variablen-Deklarationen
}
```

```
if b!=5 {
}
else {
}
```

Eine Bedingung gilt als erfüllt, wenn der Ausdruck nach dem Schlüsselwort if vom Typ int und ungleich 0 ist.

### Schleifen:

```
while i<length(s) {
}
```

Eine Bedingung gilt als erfüllt, wenn der Ausdruck nach dem Schlüsselwort while vom Typ int und ungleich 0 ist.

### Funktionsaufrufe:

```
sqrt(17.3)
print(1+2, sqrt(17))
```

### Ausdrücke
Ausdrücke kommen auf der rechten Seite von Zuweisungen, als Funktionsargumente oder als Bedingungen in Schleifen oder Verzweigungen vor. Ausdrücke bestehen aus Literalen, Funktionsaufrufen, Variablen- oder Arrayzugriffen oder Verknüpfungen von Ausdrücken. Operatoren sind: +, -, *, /, <, <=, >, >=, ==, !=, &&, || und !

   * +, -, *, / und das Vorzeichen (-) sind für Ganzzahlen und Fließkommazahlen definiert
   * <, <=, >, >=, == und != sind für Ganzzahlen und Fließkommazahlen definiert und geben eine Ganzzahl (0 oder 1) zurück
   * <=> ist für Zeichenketten definiert und gibt für a<=>b Folgendes zurück
      * -1 wenn a lexikalisch kleiner als b ist
      * 0 wenn a und b gleich sind
      * 1 wenn a lexikalisch größer als b ist
  * &&, || und ! (Not, Inversion) sind für Ganzzahlen definiert und geben eine 0 oder 1 zurück

## Typumwandlungen
In Pipifax gibt es keine impliziten Typkonvertierungen. a op b (wobei op ein beliebiger Operator ist) führt zu einem Fehler, wenn a und b nicht vom selben Typ sind.

Der Cast-Operatoren `(int)` wandelt den direkt folgenden double-Ausdruck in einen Integer-Ausdruck um, indem die Nachkommastellen abgeschnitten werden. Das Verhalten ist undefiniert, wenn der Wert zu groß oder zu klein ist.

Der Cast-Operator `(double)` wandeln den direkt folgenden Integer-Ausdruck in eine Fließkommazahl um.

Der Cast-Operator hat die selbe Präzedenz wie ein Vorzeichen.

