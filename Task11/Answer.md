# TASK 11 – Implement, Test and Defend the Design (5 Marks)

The complete executable sample is in `SmartParkingSystem.java`. It demonstrates a jagged floor layout, vehicle categories, unique tickets, duplicate-registration prevention, entry/exit, started-hour billing, monthly-pass eligibility and invalid-slot handling.

## 1. Test cases
The following table describes the test scenarios the implementation is designed to handle. Run the program to observe the actual console output.

| ID | Scenario | Input / condition | Expected result |
|---|---|---|---|
| T01 | Normal car entry | Free suitable slot | Ticket issued and slot occupied |
| T02 | Two-wheeler entry | Free suitable slot | Ticket issued with two-wheeler category |
| T03 | Normal exit | Existing active ticket | Fee returned, ticket closed, slot released |
| T04 | Last free slot | Only one slot remains | Vehicle is allocated to that slot |
| T05 | Full facility | No free suitable slot | Entry rejected; no ticket created |
| T06 | Zero-minute stay | Entry and exit at same minute | Parking component is Rs 0 |
| T07 | Exact one-hour boundary | 60 minutes, hourly billing | One billable hour |
| T08 | Just over one hour | 61 minutes | Two billable hours |
| T09 | Unknown category | Unsupported type string | Rejected as invalid input |
| T10 | Duplicate registration | Same registration already active | Second entry rejected |
| T11 | Invalid floor/slot | Negative or out-of-range index | Release returns false |
| T12 | Valid monthly pass | Student/staff pass valid | Parking fee is zero under the stated rule |

## 2. Data structure comparison for active tickets
- Array: finding a ticket by registration requires O(n); storage O(n).
- ArrayList: lookup by scan is O(n); append is amortized O(1); storage O(n).
- HashMap keyed by registration: average lookup/insert/remove O(1), worst-case O(n); storage O(n). This is selected for the implementation because exit processing frequently looks up a ticket by registration.

## 3. Evaluation
A successful run alone is not sufficient. The tests above cover normal use, capacity boundaries, fee boundaries and invalid inputs. Rates are sample configurable values because the PDF does not define an exact full rate card. A production deployment would persist tickets, use a transaction-safe store and use BigDecimal for money.
