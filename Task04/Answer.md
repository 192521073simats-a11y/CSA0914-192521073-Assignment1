# TASK 4 – Design Classes and Constructors

## 1. Class and constructor design
A `ParkingTicket` must be valid as soon as it is created. Its ticket ID, vehicle, slot and entry time are required, so they are initialized in a constructor rather than left temporarily invalid.

```java
class ParkingTicket {
    private final long ticketId;
    private final String registrationNo;
    private final int floor;
    private final int slot;
    private final long entryTimeMillis;

    ParkingTicket(long id, String regNo, int floor, int slot) {
        this(id, regNo, floor, slot, System.currentTimeMillis());
    }

    ParkingTicket(long id, String regNo, int floor, int slot, long entryTime) {
        if (id <= 0 || regNo == null || regNo.isBlank()
                || floor < 0 || slot < 0 || entryTime < 0) {
            throw new IllegalArgumentException("Invalid ticket details");
        }
        this.ticketId = id;
        this.registrationNo = regNo.trim().toUpperCase();
        this.floor = floor;
        this.slot = slot;
        this.entryTimeMillis = entryTime;
    }
}
```

The first constructor calls the second constructor using `this(...)`. This is constructor chaining and avoids repeating validation and assignment logic. `this.field` refers to the current object's field when a parameter has the same name.

## 2. Alternatives
- Default constructor followed by setters: object can exist in an incomplete or invalid state between construction and setter calls. Every setter must validate. It is flexible but less safe.
- Overloaded constructors: required fields are supplied immediately; constructor validation protects invariants. Optional fields can use additional overloads.
- Static factory methods: methods such as `ParkingTicket.create(...)` can have descriptive names, return cached/specialized instances if appropriate, or choose a subtype. They still call a validating constructor.

## 3. Time and space
Creating n tickets requires O(n) total time and O(n) total object storage in all three approaches. Each individual construction is O(1) when it validates a fixed number of fields. Constructor chaining does not change asymptotic complexity. Setter-based construction adds a fixed number of method calls, still O(n), but creates a larger opportunity for invalid intermediate state. A factory adds one O(1) method call per object and O(1) additional space, usually negligible.
