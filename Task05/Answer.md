# TASK 5 – Apply Static Members and the Object Life Cycle (3 Marks)

## 1. Static data
A ticket counter belongs to the class because it must be shared by all tickets. If it were an instance field, each ticket could start with its own counter and duplicate IDs could be generated.

```java
class TicketNumber {
    private static long nextId = 1;
    static synchronized long next() { return nextId++; }
}
```
A total occupancy count can also be static only when the application has exactly one shared facility. If multiple facilities exist, occupancy should be an instance field of each `ParkingFacility`.

## 2. Static methods and blocks
A static method is appropriate for operations that do not need a particular object, such as validating a registration number or returning a fixed default rate. A static block can initialize shared configuration once when the class is loaded. Business data such as an individual ticket's entry time must not be static.

## 3. Object life cycle
A ticket is created when a vehicle enters. The facility stores it in the active-ticket collection. When the vehicle exits, the system calculates the fee, releases the slot and removes the ticket from the active collection. If no other reachable object refers to the ticket, it becomes eligible for garbage collection. Java does not guarantee the exact time at which garbage collection runs.

## 4. Passing and returning objects
Java passes object references by value. A method can receive a `Vehicle` reference, read or invoke its methods, and return a `ParkingTicket` reference. Reassigning the local parameter does not change the caller's variable, but mutating the referenced object can be observed by the caller. Prefer immutable ticket fields to avoid accidental changes.

## 5. Complexity
Generating an ID is O(1) time and O(1) shared storage. Creating each ticket is O(1); n active tickets occupy O(n) space. Removing a ticket from a HashMap is average O(1).
