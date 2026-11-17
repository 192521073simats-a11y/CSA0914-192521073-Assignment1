# TASK 7 – Apply Method Overriding and Object Methods

## 1. Overriding
Each concrete vehicle subclass overrides `ratePerHour()` (or a vehicle-specific fee method) to supply its rate. The base class can then calculate a fee using dynamic dispatch.

```java
@Override
protected double ratePerHour() {
    return 30.0;
}
```
The `@Override` annotation asks the compiler to verify that the method really overrides a parent method. The method name and parameter list must match; the return type must be identical or covariant; access cannot be more restrictive; checked exceptions cannot be broader.

## 2. final keyword
- `private final String regNo`: registration number is assigned once.
- A `final` method is suitable when subclasses must not change a security or validation operation.
- A `final` class is suitable when inheritance would break its invariant, such as an immutable value class. Do not mark Vehicle final because it is designed for extension.

## 3. equals, hashCode and toString
If registration number defines vehicle identity, equality can compare normalized registration numbers. Whenever equals is overridden, hashCode must be overridden so equal objects have equal hash codes. toString should return a readable diagnostic summary without exposing sensitive data.

The supplied FeeDemo's Vehicle implementation compares only registration number. Therefore `new Car("TN02B2").equals(new ElectricCar("TN02B2"))` returns true. This is acceptable only if registration number is the identity of a vehicle independent of its Java subtype. If category is part of identity in the domain, equality should also compare category/class.

## 4. Alternative: switch statement
A single method could inspect a type field and use a switch to choose the rate. Fee calculation itself remains O(1) for a fixed finite switch (or O(k) if implemented as a sequential chain over k categories). However, adding a category requires editing the central switch and potentially tests/callers. With overriding, the method call is O(1) dispatch and the new subclass can be added without editing the existing fee method. The larger benefit is maintainability and reduced coupling rather than a dramatic runtime difference.
