- [Materials](#materials)
- [Install](#install)
  - [Install on Windows 10](#install-on-windows-10)
  - [Install on OSX](#install-on-osx)
- [REPL](#repl)
- [DSL (Domain Specific Language)](#dsl-domain-specific-language)
- [Basic](#basic)
  - [Compile, Run](#compile-run)
  - [Hello World](#hello-world)
  - [Reserved Words](#reserved-words)
  - [Data Types](#data-types)
  - [Print Formatted Text](#print-formatted-text)
  - [Control Flows](#control-flows)
  - [Loops](#loops)
  - [Operators](#operators)
  - [Collections compared to c++ containers](#collections-compared-to-c-containers)
  - [Collections](#collections)
  - [Functions](#functions)
  - [Struct, Class, Interface, AbstractClass](#struct-class-interface-abstractclass)
  - [Closure](#closure)
  - [Lambda](#lambda)
  - [Exception](#exception)
  - [Structure of Project](#structure-of-project)
- [Advanced](#advanced)
  - [Introspection](#introspection)
  - [Traits](#traits)
  - [owner, delgate, this](#owner-delgate-this)

----

# Materials

* [groovy  Documentation](http://groovy-lang.org/documentation.html)
  * [Differences with Java](http://groovy-lang.org/differences.html)
  * [Domain-Specific Languages](https://groovy-lang.org/dsls.html)
* [learn groovy in minutes](https://learnxinyminutes.com/docs/groovy/)
* [Groovy](https://code-maven.com/groovy)
  * Groovy Tutotial for Jenkins Pipeline.
* [GROOVY TUTORIAL](http://www.newthinktank.com/2016/04/groovy-tutorial/)
  * 한 페이지로 배우는 groovy
* [Groovy Tutorial @ tutorial point](https://www.tutorialspoint.com/groovy/index.htm)
  * groovy tutorial
* [Groovy for Java developers @ youtube](https://www.youtube.com/watch?v=BXRDTiJfrSE)
  * java 개발자를 위한 groovy

# Install

## Install on Windows 10

```
$ choco install groovy
```

## Install on OSX

```bash
$ brew install groovy
```

# REPL

* [GroovyShell](http://groovy-lang.org/groovysh.html)
* [How you can benefit from Groovy Shell](https://www.mscharhag.com/groovy/groovy-shell)

----

program counter 가 0 보다 큰 상황에서 buffer 를 취소하고 싶다면 `:clear` 로 program counter, buffer 를 초기화한다.

variables, classes, imports, preferences 를 초기화 하고 싶다면 `:purge all` 를 사용한다.

```groovy
$ groovysh
groovy:000> def a = 3
// show help
groovy:000> :help
// display current buffer
groovy:000> :display
// clear current buffer
groovy:000> :clear
// show variables, classes, interfaces, preferences
groovy:000> :show all
// Purge variables, classes, imports or preferences
groovy:000> :purge all
// set preferences (:=)
groovy:000> :set
```

`def` 는 local variable 을 선언한다. 따라서 `def a = 1` 을 실행하고 `a` 를 참조하면 unknown property error 가 발생한다. `a = 1` 을 실행하면 shell variable 을 선언한다. 이후 `a` 를 사용할 수 있다. [참고](https://stackoverflow.com/questions/26716995/groovy-console-cant-remember-any-variables-always-says-unknown-property)

```groovy
Groovy Shell (2.5.7, JVM: 1.8.0_31)
Type ':help' or ':h' for help.
-------------------------------------------------------------------------------
groovy:000> def a = 1
groovy:000> a
Unknown property: a
groovy:000> b = 2
===> 2
groovy:000> b
===> 2
```

`:= interpreterMode` 를 실행하면 unknown property error 를 피할 수 있다.

```groovy
Groovy Shell (2.5.7, JVM: 1.8.0_31)
Type ':help' or ':h' for help.
-------------------------------------------------------------------------------
groovy:000> := interpreterMode
groovy:000> def x = 1
===> 1
groovy:000> x
===> 1
```

# DSL (Domain Specific Language)

* [Domain-Specific Languages](https://groovy-lang.org/dsls.html)
* [Writing Build Scripts](https://docs.gradle.org/current/userguide/writing_build_scripts.html)

----

groovy 의 top level statement 는 argument 주변의 "`()`" 와 function call 사이의 "`.`" 를 생략할 수 있다. 이것을 `command chain` 이라고 한다.

```groovy
// equivalent to: turn(left).then(right)
turn left then right
// equivalent to: take(2.pills).of(chloroquinine).after(6.hours)
take 2.pills of chloroquinine after 6.hours
// equivalent to: paint(wall).with(red, green).and(yellow)
paint wall with red, green and yellow
// with named parameters too
// equivalent to: check(that: margarita).tastes(good)
check that: margarita tastes good
// with closures as parameters
// equivalent to: given({}).when({}).then({})
given { } when { } then { }
// equivalent to: select(all).unique().from(names)
select all unique() from names
// equivalent to: take(3).cookies
// and also this: take(3).getCookies()
take 3 cookies

// Closures as the last parameter in a method
// There are all same.
repositories {
   println "in a closure"
}
repositories() { println "in a closure" }
repositories({ println "in a closure" })
```

다음은 `EmailDs1` 을 DSL 에서 사용하기 위한 구현이다. [Groovy - DSLS @ tutorialpoint](https://www.tutorialspoint.com/groovy/groovy_dsls.htm)

```groovy
class EmailDsl {  
   String toText 
   String fromText 
   String body 
	
   /** 
   * This method accepts a closure which is essentially the DSL. Delegate the 
   * closure methods to 
   * the DSL class so the calls can be processed 
   */ 
   
   def static make(closure) { 
      EmailDsl emailDsl = new EmailDsl() 
      // any method called in closure will be delegated to the EmailDsl class 
      closure.delegate = emailDsl
      closure() 
   }
   
   /** 
   * Store the parameter as a variable and use it later to output a memo 
   */ 
	
   def to(String toText) { 
      this.toText = toText 
   }
   
   def from(String fromText) { 
      this.fromText = fromText 
   }
   
   def body(String bodyText) { 
      this.body = bodyText 
   } 
}

EmailDsl.make { 
   to "Nirav Assar" 
   from "Barack Obama" 
   body "How are things? We are doing well. Take care" 
}
```

closure 를 argument 로 static function `EmailDs1.make` 을 호출한다. `EmailDs1.make` 는 closure 의 delegate 를 EmailDs1 instance 로 assign 한다. 그리고 `closure()` 를 호출한다.

이후 `closure` 에 포함된 function 들이 호출된다. 이것은 미리 assign 된 EmailDs1 instance 의 function 들이 호출되는 것과 같다.

# Basic 

## Compile, Run

```bash
$ groovy a.groovy
```

## Hello World

* a.groovy

```groovy
class A {
    static void main(String[] args){
    // Print to the screen
    println("Hello World");
    }
}
```

## Reserved Words

```groovy
as     assert  break      case
catch  class   const      continue
def    default do         else  
enum   extends false      finally
for    goto    if         implements 
import in      instanceof interface
new    null    package    return
super  switch  this       throw
throws trait   true       try
while
```

## Data Types

* [Built-in Data Types @ tutorialpoints](https://www.tutorialspoint.com/groovy/groovy_data_types.htm)

----

```groovy
byte
short
int
long
float
double
char
Boolean
String
```

## Print Formatted Text

```groovy
println(String.format("%s : %s", "name", "pass"))
```

## Control Flows

```groovy
//// if-else
if (a < 100) {
   println("The value is less than 100")
} else if (a < 50) {
   println("The value is less than 50")
} else {
   println("The value is too small")
}

//// switch
switch(a) {
   case 1:
     println("The value is one")
     break;
   case 2:
     println("The value is two")
     break;
   default:
     println("The value is unknown")
     break;
}
```

## Loops

```groovy
//// while
while (cnt < 10) {
   println(cnt)
   cnt++
}

//// for
for (int i = 0; i < 5; i++) {
   println(i)
}

//// for-in
for (int i in 1..5) {
   println(i)
}
```

## Operators

```groovy
//// Arithmetic operators
1 + 2 // 3
2 - 1 // 1
2 * 2 // 4
3 / 2 // 1.5
3 % 2 // 1

//// Relational operators
2 == 2
3 != 2
2 < 3
2 <= 3
3 > 2
3 >= 2

//// Logical operators
true && true
ture || true
!false

//// Bitwise operators
1 & 1
1 | 0
1 ^ 1
~1

//// Assignment operators
def A = 5; 
A += 3
A -= 3
A *= 3
A /= 3
A %= 3

//// Elvis Operators
// http://www.groovy-lang.org/operators.html#_elvis_operator
// These three code snippets mean the same thing. 
// If x is true according to groovy truth return x else return y
x ?: y

x ? x : y  // Standard ternary operator.

if (x) {
  return x
} else {
  return y
}
```

## Collections compared to c++ containers


| c++                  | groovy                            |
|:---------------------|:--------------------------------|
| `if, else`           | `if, else`                      |
| `for, while`         | `for, while`                    |
| `array`              | `Collections.unmodifiableList`  |
| `vector`             | `Vector, ArrayList`             |
| `deque`              | `Deque, ArrayDeque`             |
| `forward_list`       | ``                              |
| `list`               | `List, LinkedList`              |
| `stack`              | `Stack, Deque`                  |
| `queue`              | `Queue, LinkedList`             |
| `priority_queue`     | `Queue, PriorityQueue`          |
| `set`                | `SortedSet, TreeSet`       |
| `multiset`           | ``                              |
| `map`                | `SortedMap, TreeMap`       |
| `multimap`           | ``                              |
| `unordered_set`      | `Set, HashSet`                  |
| `unordered_multiset` | ``                              |
| `unordered_map`      | `Map, HashMap`                  |
| `unordered_multimap` | ``                              |

## Collections

Lists, Maps, Ranges

* Lists

```groovy
def list = [5, 6, 7, 8]
assert list.get(2) == 7
assert list[2] == 7
assert list instanceof java.util.List

def emptyList = []
assert emptyList.size() == 0
emptyList.add(5)
assert emptyList.size() == 1
```

* Maps

```groovy
def map = [name: 'Gromit', likes: 'cheese', id: 1234]
assert map.get('name') == 'Gromit'
assert map.get('id') == 1234
assert map['name'] == 'Gromit'
assert map['id'] == 1234
assert map instanceof java.util.Map

def emptyMap = [:]
assert emptyMap.size() == 0
emptyMap.put("foo", 5)
assert emptyMap.size() == 1
assert emptyMap.get("foo") == 5
```

* Ranges
  * Range extends java.util.List.

```groovy
// an inclusive range
def range = 5..8
assert range.size() == 4
assert range.get(2) == 7
assert range[2] == 7
assert range instanceof java.util.List
assert range.contains(5)
assert range.contains(8)

// lets use a half-open range
range = 5..<8
assert range.size() == 3
assert range.get(2) == 7
assert range[2] == 7
assert range instanceof java.util.List
assert range.contains(5)
assert !range.contains(8)

//get the end points of the range without using indexes
range = 1..10
assert range.from == 1
assert range.to == 10
```

## Functions

```groovy
// basic function
static def DisplayName() {
  println("This is how methods work in groovy");
  println("This is an example of a simple method");
}
// parameter
static void sum(int a, int b) {
  int c = a + b;
  println(c);
}
// default parameter
static void sum(int a, int b = 5) { 
  int c = a + b; 
  println(c); 
} 
// return value
static int sum(int a, int b = 5) {
  int c = a + b;
  return c;
} 
// instance methods
class Example { 
  int x; 
  public int getX() { 
    return x; 
  } 
  public void setX(int pX) { 
    x = pX; 
  } 
  static void main(String[] args) { 
    Example ex = new Example(); 
    ex.setX(100); 
    println(ex.getX()); 
  } 
}
```

## Struct, Class, Interface, AbstractClass

TODO

## Closure

A closure is a short anonymous block of code. whose syntax is like this.

```groovy
{ [closureParameters -> ] statements }
```

These are valid examples of closure.

```groovy

{ item++ }                                          
{ -> item++ }                                      
//  A closure using an implicit parameter (it)
{ println it }                                      
{ it -> println it }                                
{ name -> println name }                            
// A closure accepting two typed parameters
{ String x, int y ->                                
    println "hey ${x} the value is ${y}"
}
// A closure can contain multiple statements
{ reader ->                                         
    def line = reader.readLine()
    line.trim()
}

//// basic closure
class Example {
  static void main(String[] args) {
    def clos = { println "Hello World" };
    clos.call();
  } 
}
//// formal parameters in closures
class Example {
   static void main(String[] args) {
      def clos = { param -> println "Hello ${param}" };
      clos.call("World");
   } 
}
class Example {
   static void main(String[] args) {
      def clos = { println "Hello ${it}" };
      clos.call("World");
   } 
}
//// Closures and Variables
class Example {     
   static void main(String[] args) {
      def str1 = "Hello";
      def clos = { param -> println "${str1} ${param}" }
      clos.call("World");		
      // We are now changing the value of the String str1 which is referenced in the closure
      str1 = "Welcome";
      clos.call("World");
   } 
}
// Hello World 
// Welcome World
//// using closures in methods
class Example { 
   def static Display(clo) {
      // This time the $param parameter gets replaced by the string "Inner"         
      clo.call("Inner");
   } 
	
   static void main(String[] args) {
      def str1 = "Hello";
      def clos = { param -> println "${str1} ${param}" }
      clos.call("World");
		
      // We are now changing the value of the String str1 which is referenced in the closure
      str1 = "Welcome";
      clos.call("World");
		
      // Passing our closure to a method
      Example.Display(clos);
   } 
}
// Hello World 
// Welcome World 
// Welcome Inner

//// Closures in Collections and String
class Example {
   static void main(String[] args) {
      def lst = [11, 12, 13, 14];
      lst.each { println it }
   } 
}
class Example {
   static void main(String[] args) {
      def mp = ["TopicName" : "Maps", "TopicDescription" : "Methods in Maps"]             
      mp.each({ println it })
      mp.each({ println "${it.key} maps to: ${it.value}"})
   } 
}
```

## Lambda

There is no lambda but closure.

```groovy
def str1 = "Hello";
def clos = { param -> println "${str1} ${param}" }
clos.call("World");		
```

## Exception

TODO

## Structure of Project

TODO

# Advanced

## Introspection

* [What's the Groovy equivalent to Python's dir()? @ stackoverflow](https://stackoverflow.com/questions/10882469/whats-the-groovy-equivalent-to-pythons-dir)

----

```groovy
// Introspection, know all the details about classes :
// List all constructors of a class
String.constructors.each{ println it }

// List all interfaces implemented by a class
String.interfaces.each{ println it }

// List all methods offered by a class
String.methods.each{ println it }

// Just list the methods names
String.methods.name

// Get the fields of an object (with their values)
d = new Date()
d.properties.each{ println(it) }
```

## Traits

TODO

## owner, delgate, this

```groovy
class SpecialMeanings{
  String prop1 = "prop1"
  def closure = {
    String prop1 = "inner_prop1"  
    // this refers to SpecialMeanings instance
    println this.class.name // SpecialMeanings
    println this.prop1 // prop1
    // owner indicates Owner of the surrounding closure which is SpecialMeaning
    println owner.prop1 // prop1
    // delegate indicates the object on which the closure is invoked 
    // here Delegate of closure is SpecialMeaning
    println delegate.prop1 // prop1
    // This is where prop1 from the closure itself in referred
    println prop1 // inner_prop1
  }
}

def closure = new SpecialMeanings().closure
closure()
// SpecialMeanings
// prop1
// prop1
// prop1
// inner_prop1
println "----------------"
// Example of modifying the delegate to the script itself
prop1 = "PROPERTY FROM SCRIPT"
closure.delegate = this
closure()
// SpecialMeanings
// prop1
// prop1
// PROPERTY FROM SCRIPT
// inner_prop1
```
