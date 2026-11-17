# Programming in Java – Assignment 1 (CO1)
## Campus Smart-Parking System

**Student:** A. Cassandra Maya Neelali  
**Register number:** 192521073  
**Repository:** https://github.com/192521073simats-a11y/CSA0914-192521073-Assignment1  
**Language:** Java  
**Recommended JDK:** Java 17 or later

## Assignment coverage
This repository contains the answers and implementation for all 12 tasks in the supplied 20-page assignment brief. Each task has its own folder. The report covers the analysis, assumptions, data representation, algorithms, constructors, static members, inheritance, overriding, polymorphism, interfaces, packages, encapsulation, testing, complexity comparisons and the Day-4 design challenge.

## Folder structure
```
Task01/  Problem analysis, candidate classes and object/array comparison
Task02/  Data types, arrays, input, casting and representation comparison
Task03/  Allocation/fee pseudocode and completed ParkingFacility exercise
Task04/  Constructors, this, overloading and constructor alternatives
Task05/  Static members and object life cycle
Task06/  Vehicle/user inheritance hierarchy and UML
Task07/  Method overriding, final and Object methods
Task08/  Compile-time/runtime polymorphism and completed FeeDemo
Task09/  Abstract class, interfaces and capability design
Task10/  Packages, access modifiers, wrappers and encapsulation
Task11/  Complete executable system and test evidence
Task12/  Day-4 changed requirements and redesigned implementation
README.md
```

## Requirements
- JDK 17 or later
- A terminal/command prompt

Check Java:
```bash
java -version
javac -version
```

## Compile and run the completed-code exercises
From the repository root:

**Task 3**
```bash
javac Task03/ParkingFacility.java Task03/ParkingFacilityDemo.java
java -cp Task03 ParkingFacilityDemo
```

**Task 8**
```bash
javac Task08/FeeDemo.java
java -cp Task08 FeeDemo
```

**Task 11 – complete system and tests**
```bash
javac Task11/SmartParkingSystem.java Task11/SmartParkingTests.java
java -cp Task11 SmartParkingSystem
java -cp Task11 SmartParkingTests
```

**Task 12 – extended system**
```bash
javac Task12/ExtendedParkingSystem.java
java -cp Task12 ExtendedParkingSystem
```

## Task result summary
| Task | Result |
|---|---|
| 1 | Candidate classes, responsibilities, rules, assumptions and object/parallel-array comparison documented |
| 2 | Field data types, jagged arrays, Scanner input, casting risk and array alternatives documented |
| 3 | Allocation and fee pseudocode supplied; TODOs completed and expected output checked |
| 4 | Constructor design and alternatives compared |
| 5 | Static counter/occupancy and garbage-collection life cycle explained |
| 6 | Inheritance hierarchy, constructor trace and UML supplied |
| 7 | Overriding, final, equals, hashCode and toString explained |
| 8 | Compile-time and runtime polymorphism demonstrated; FeeDemo completed |
| 9 | Abstract class and interface choices justified and compared |
| 10 | Package design, access control, wrappers and encapsulation explained |
| 11 | Executable parking system and normal/boundary/invalid tests supplied |
| 12 | E-bike, delivery van, one-day pass and unequal five-floor layout implemented and evaluated |

## Important implementation notes
- Parking storage uses a jagged array so each floor can have a different slot count.
- Registration numbers and active tickets are indexed with a HashMap to prevent duplicate active parking and support average O(1) ticket lookup.
- Parking tickets receive monotonically increasing identifiers; identifiers are not reused during a program run.
- The sample hourly rates in the FeeDemo are selected to reproduce the exact expected output in the assignment brief: two-wheeler Rs 30, car Rs 90, and electric car Rs 210 (including the sample charging fee).
- The complete system in Task 11 uses clearly stated sample rates and rules. These are configurable examples because the assignment does not prescribe a complete rate card.

## Evidence and verification
The Task 3 and Task 8 programs were compiled and run locally; their outputs are saved in each task folder. Task 11 was compiled and its repeatable test program passed 9 checks covering last-slot allocation, zero minutes, 60/61-minute boundaries, duplicate registration, unknown category, full facility, invalid release and monthly-pass fee. Task 12 was compiled and run; its output is saved in Task12/OUTPUT.txt. These are text execution records, not screenshots. No screenshot is claimed unless an actual screenshot file is present.

## GitHub submission
Repository URL: https://github.com/192521073simats-a11y/CSA0914-192521073-Assignment1
