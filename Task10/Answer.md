# TASK 10 – Enforce Encapsulation through Packages and Access Modifiers (3 Marks)

## 1. Package structure
A suitable package structure is:
```text
src/
  parking/model/       Vehicle, ParkingSlot, ParkingTicket, User
  parking/vehicle/     TwoWheeler, Car, ElectricCar, Chargeable
  parking/service/     ParkingFacility, PricingPolicy
  parking/app/         Main
```
Package names are lowercase and classes are grouped by responsibility.

## 2. Access modifiers
- `private`: fields such as registration number, ticket ID, entry time and slot occupancy.
- `public`: operations needed by other packages, such as `enterVehicle` and `exitVehicle`.
- `protected`: carefully selected methods intended for subclasses, such as `ratePerHour()`.
- package-private: helper methods used only within the same package.

Getters expose read-only information. Setters should validate input; immutable identity fields should have no setter.

## 3. Wrapper classes and autoboxing
Collections such as `ArrayList<Integer>` store wrapper objects, not primitive int values. Autoboxing converts int to Integer; unboxing converts Integer to int. Comparing wrapper objects with `==` may compare references rather than numeric values. Use `.equals()` or compare primitive values. Unboxing null causes NullPointerException.

## 4. Alternative: public fields
If fields were public, any caller could set a negative slot number, blank registration number or invalid fee, bypassing validation. Every calling location would need to repeat the same checks, creating inconsistent behaviour. Validation itself is O(1) per update, but repeated across m call sites it creates O(m) duplicated maintenance points. Encapsulation centralizes the check in one setter/constructor, also O(1) per update, and protects the invariant.
