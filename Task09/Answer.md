# TASK 9 – Apply Abstraction and Interfaces

## 1. Abstract class
`Vehicle` is abstract because a generic vehicle should not be instantiated without a concrete category/rate. It can hold instance state such as registration number, have a constructor, provide common implemented methods, and declare abstract operations such as `ratePerHour()`.

## 2. Interface
`Chargeable` represents a capability rather than a family identity:
```java
interface Chargeable {
    double chargingFee(int kWh);
    default boolean supportsFastCharging() { return false; }
    static boolean validUnits(int kWh) { return kWh >= 0; }
}
```
ElectricCar implements Chargeable. A class may implement multiple interfaces, for example `ElectricCar implements Chargeable, Insurable`, allowing multiple capabilities without multiple class inheritance.

## 3. Default and static methods
A default method provides a common optional behaviour to implementers and can be overridden. A static interface method is a utility associated with the interface and is called using the interface name.

## 4. If Vehicle became an interface and Chargeable an abstract class
An interface cannot have ordinary instance constructors or per-instance mutable state in the same way an abstract class can; it can declare constants, abstract methods, default methods and static methods. A class can implement multiple interfaces, but can extend only one class. Making Chargeable an abstract class would prevent a vehicle from extending another unrelated base class as well. Method dispatch remains effectively O(1) at the source-level model; object memory depends on fields held by concrete classes, not merely on whether a parent is an interface or abstract class. The current design is better because all vehicles share identity/state, while charging is an optional capability.
