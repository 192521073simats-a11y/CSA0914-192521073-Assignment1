# TASK 6 – Design the Inheritance Hierarchy (4 Marks)

## 1. Vehicle hierarchy
```text
                 Vehicle (abstract)
                /       |          \
       TwoWheeler      Car      ElectricCar
                                  implements Chargeable
```
This is hierarchical inheritance: several subclasses inherit from one superclass. `Vehicle` stores common registration number and defines common fee behaviour. `TwoWheeler`, `Car` and `ElectricCar` provide their own hourly rates. `ElectricCar` adds charging behaviour.

A user hierarchy may similarly be `User (abstract) → Student, Staff, Visitor`. Student and Staff may hold monthly passes; Visitor pays the visitor rate.

## 2. Inherited and added members
All vehicle subclasses inherit the registration number and `getRegNo()`. Each subclass supplies its own `ratePerHour()`. ElectricCar additionally implements `chargingFee(int kWh)`.

## 3. Use of super
- `super(regNo)` calls the parent constructor and initializes common state.
- `super.someMethod()` invokes an accessible parent implementation.
- Java does not permit direct access to a private parent field using `super.field`; use a protected method or public getter.

## 4. ElectricCar constructor trace
For `new ElectricCar("TN03C3")`:
1. Memory is allocated and fields receive default values.
2. ElectricCar constructor begins and invokes `super("TN03C3")`.
3. Vehicle constructor validates and assigns the registration number.
4. Vehicle constructor returns.
5. ElectricCar initializes its own fields.
6. ElectricCar constructor returns; the object is ready.

## 5. UML (text form)
```text
<<abstract>> Vehicle
- regNo: String
+ getRegNo(): String
+ calculateFee(minutes: int): double
+ ratePerHour(): double {abstract}
       ^                 ^                 ^
       |                 |                 |
 TwoWheeler             Car           ElectricCar
                                          ..|> Chargeable
Chargeable <<interface>>
+ chargingFee(kWh: int): double
```

## 6. Alternative: composition
Instead of subclasses, one `Vehicle` could contain a `VehicleType` or `PricingStrategy` object. This uses has-a composition rather than is-a inheritance. Adding a new category can mean adding a new strategy without changing Vehicle. Composition is more flexible when rates vary independently of vehicle identity; inheritance is simple when categories have genuinely different behaviour. Both designs have O(1) fee dispatch for a fixed strategy reference and O(1) per-object state; composition adds a strategy reference and strategy object (which may be shared).
