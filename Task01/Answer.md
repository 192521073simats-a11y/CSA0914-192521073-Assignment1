# TASK 1 – Understand the Problem and Identify the Objects (4 Marks)

## 1. Problem analysis
The university operates a multi-floor parking facility for students, staff and visitors. Vehicles may be two-wheelers, cars or electric vehicles. On entry, the system must find a suitable free slot, issue a unique ticket and record entry time. On exit, it must find the active ticket, calculate the fee, release the slot and close the ticket. Students and staff with a valid monthly pass park free; visitors pay the normal fee. EV charging is charged separately.

## 2. Candidate classes and responsibilities
| Class | Responsibilities | Why it is needed |
|---|---|---|
| Vehicle (abstract) | Registration number, common identity and fee operation | Keeps shared vehicle state and behaviour in one place |
| TwoWheeler, Car, ElectricCar | Vehicle-specific rates and EV charging capability | Models different vehicle categories without repeated type checks |
| User, Student, Staff, Visitor | User identity and eligibility | Separates user/pass rules from vehicle rules |
| ParkingSlot | Floor/slot number, occupancy and suitability | Prevents double allocation and validates release |
| ParkingFloor | Owns the slots on one floor | Allows each floor to have its own capacity |
| ParkingTicket | Ticket ID, vehicle, slot, entry time and pass status | Records one parking transaction |
| ParkingFacility | Floors, active tickets, entry/exit coordination | Coordinates the complete facility |
| MonthlyPass | Holder, validity dates and validity check | Applies free parking only to eligible users |
| PricingPolicy | Calculates parking and additional charging charges | Keeps pricing rules central and extendable |

## 3. Business rules
1. A slot can contain at most one vehicle.
2. A vehicle may have at most one active ticket.
3. Ticket IDs are unique and are never reused during the system's lifetime.
4. Only a free, suitable slot may be allocated.
5. A release is valid only for an occupied slot and a valid floor/slot index.
6. An exit must close the active ticket and free the same slot exactly once.
7. Fees depend on vehicle category and billable parking duration.
8. Every started hour is charged as a full hour where the hourly policy applies.
9. A valid monthly pass makes an eligible student/staff parking charge zero; it does not automatically waive EV charging unless the policy explicitly says so.
10. Visitors pay the full applicable fee.
11. EV charging is available only in a charging-capable slot and is billed separately.
12. Duplicate active registration numbers and unknown vehicle categories are rejected.

## 4. Assumptions
- Floor and slot indexes are zero-based in the Java implementation; displayed labels may be one-based.
- Registration numbers are stored uppercase after trimming whitespace.
- A vehicle's registration number identifies it uniquely while parked.
- Time is represented by Java `Instant`/epoch milliseconds.
- The assignment does not prescribe a complete rate card. The implementation uses clearly labelled sample rates and makes them configurable.
- A valid monthly pass is checked at entry and its status is recorded on the ticket.
- A charging slot is a special slot; an EV may use a regular slot only if charging is not requested and the policy permits it.

## 5. Object-oriented model
A `ParkingFacility` contains `ParkingFloor` objects; each floor contains `ParkingSlot` objects. An active `ParkingTicket` refers to one `Vehicle` and one `ParkingSlot`. Vehicle subclasses inherit common registration information from `Vehicle`; `ElectricCar` implements `Chargeable`. A `User` may hold a `MonthlyPass`.

## 6. Alternative: Vehicle objects vs parallel arrays
Object approach:
```java
class VehicleRecord {
    String registrationNo;
    String type;
    long entryTime;
}
VehicleRecord[] vehicles = new VehicleRecord[100];
```
Parallel-array approach:
```java
String[] regNo = new String[100];
String[] type = new String[100];
long[] entryTime = new long[100];
// all details for a vehicle must use the same index
```

| Comparison | Vehicle objects | Parallel arrays |
|---|---|---|
| Find registration | Linear scan O(n) | Linear scan O(n) |
| Add an attribute | Add a field to the class | Add another array and keep indexes aligned |
| Data integrity | Related values stay together | Index mismatch can associate wrong values |
| Space | O(n) records plus object overhead | O(n) values across arrays |

Both approaches use O(n) space and O(n) worst-case search without an index. Objects are preferable because attributes and behaviour remain together and the design is easier to extend. A HashMap index can later provide average O(1) lookup.
