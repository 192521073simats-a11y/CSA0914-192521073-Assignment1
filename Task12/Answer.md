# TASK 12 – Re-evaluate and Adapt the Design (8 Marks)

## Changed requirements
The system adds e-bikes with a flat fee, delivery vans charged at twice the car rate between 9 AM and 5 PM, one-day visitor passes, and a five-floor facility whose floor capacities differ.

## A. Impact analysis (2 marks)
1. Unequal floor capacities cannot safely be represented by assuming one common slots-per-floor value in a flat array. A jagged array such as `ParkingSlot[][]` or a list of floor objects supports each floor's individual length. The implementation uses a jagged array.
2. Vehicle-specific changes affect the vehicle hierarchy and pricing policy. Visitor one-day passes affect User/Pass/Ticket and entry validation. Facility expansion affects configuration and slot initialization, not allocation logic if it already iterates each floor's actual length.

## B. Evaluation of original design (2 marks)
The original abstract Vehicle plus subclasses and Chargeable interface is suitable for adding e-bikes and delivery vans. New subclasses can be added without changing the common Vehicle contract. However, time-dependent van pricing and one-day passes should not be scattered through vehicle subclasses or entry/exit code. Keep these rules in a PricingPolicy and Pass validation service. With that separation, tested slot allocation and ticket classes can remain unchanged; the new category classes and policy rules are additive.

## C. Adaptation / redesign (2 marks)
Decision: **MODIFY**, not completely redesign.
- Add `EBike` with a flat parking fee strategy.
- Add `DeliveryVan` with a rate policy that checks local entry/parking time; between 09:00 inclusive and 17:00 exclusive, the hourly rate is twice the configured car rate.
- Add `OneDayPass` with start/end validity and validate it at entry.
- Configure five floor capacities independently, for example `{20, 35, 40, 50, 60}`.
- Keep the existing Vehicle abstraction, Chargeable capability, ticket model and jagged slot storage.

Trade-off: a strategy/policy adds a small number of classes and indirection but prevents pricing rules from spreading across the code. Inheritance remains useful for category identity; composition is useful for pricing that changes independently.

## New-category tests
| Test | Scenario | Expected |
|---|---|---|
| D01 | E-bike parks for any duration | Flat parking fee |
| D02 | Delivery van charged at 10:00 | Twice car hourly rate |
| D03 | Delivery van charged at 18:00 | Normal configured van/car-derived rate |
| D04 | Visitor has valid one-day pass | Pass benefit applied according to policy |
| D05 | Visitor one-day pass expired | Full visitor fee |
| D06 | Floors have capacities 20, 35, 40, 50, 60 | All five floor arrays have those lengths; allocation visits all floors |

The executable extension is in `ExtendedParkingSystem.java`. The sample policy treats the daytime window as [09:00, 17:00) local time and charges started hours. The exact daily-pass fee/benefit is not specified by the assignment, so it is represented as a configurable pass benefit rather than an invented mandatory amount.

## Final conclusion
The original design should be modified. The core object-oriented structure remains appropriate, while new vehicle categories, pass types and pricing strategies are added. Jagged arrays preserve correctness for unequal floor sizes, and centralized pricing keeps future changes localized.
