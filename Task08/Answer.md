# TASK 8 – Apply Polymorphism (4 Marks)

## 1. Compile-time polymorphism
Method overloading provides compile-time polymorphism. For example:
```java
double calculateFee(int minutes) { ... }
double calculateFee(int minutes, boolean monthlyPass) { ... }
```
The compiler chooses the overload from the argument list.

## 2. Runtime polymorphism
A parent reference can hold a child object:
```java
Vehicle v = new Car("TN02B2");
double fee = v.calculateFee(130);
```
Declared type: `Vehicle`. Actual object type: `Car`. Executed implementation: the inherited calculateFee method calls the overridden `Car.ratePerHour()` at runtime. This is dynamic method dispatch.

## 3. Why it avoids long if-else chains
The calling code uses the common Vehicle interface and does not need to test every vehicle category. Each subtype owns its own rate/behaviour. Adding a new subtype does not require changing every caller.

## 4. Completed code exercise
See `FeeDemo.java`. It completes integer ceiling division as `(minutes + 59) / 60` for non-negative minutes and adds charging fees only when the vehicle implements Chargeable. It is written to produce the assignment's expected lines:
```text
TwoWheeler TN01A1 -> Rs 30.0
Car TN02B2 -> Rs 90.0
ElectricCar TN03C3 -> Rs 210.0
true
```
The example's 130-minute stay is billed as 3 started hours. Rates are chosen to match the PDF's expected output.

## 5. Alternative: instanceof checks
An alternative loops through type checks and casts:
```java
if (v instanceof Car) { ... }
else if (v instanceof TwoWheeler) { ... }
else if (v instanceof ElectricCar) { ... }
```
A chain may perform up to k checks for k categories (O(k)); it also grows in code size and requires edits when a new type is added. Dynamic dispatch is normally O(1) for method selection and keeps the caller independent of concrete types.
