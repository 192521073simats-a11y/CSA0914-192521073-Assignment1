# TASK 2 – Choose the Data Representation (3 Marks)

## 1. Data types
| Field | Java type | Reason |
|---|---|---|
| Floor number / slot number | `int` | Whole-number indexes |
| Registration number | `String` | Contains letters and digits |
| Ticket number | `long` | Supports a large increasing sequence |
| Entry and exit time | `Instant` or `long` | Represents a precise point in time |
| Duration | `long` minutes | Avoids small-range overflow |
| Hourly rate / fee | `double` for this teaching exercise; `BigDecimal` for production currency | Decimal currency values; BigDecimal avoids binary floating-point rounding |
| Occupancy | `boolean` or null vehicle reference | Represents free/occupied state |
| Floor capacities | `int[]` | Stores the slot count for each floor |

For real financial software, use `BigDecimal` with an explicit currency scale and rounding rule. The assignment's supplied FeeDemo expects double values, so that exercise uses `double`.

## 2. Array representation
Use a jagged two-dimensional array: `String[][] slots`. The first dimension is the floor; each row has exactly that floor's capacity. This supports floors with different slot counts.

```java
int[] slotsPerFloor = {20, 35, 50};
String[][] slots = new String[slotsPerFloor.length][];
for (int f = 0; f < slotsPerFloor.length; f++) {
    slots[f] = new String[slotsPerFloor[f]];
}
```

## 3. Input
Use `Scanner` for an interactive console application. Read registration numbers with `nextLine()`, validate non-empty input, normalize with `trim().toUpperCase()`, and parse numeric input with guarded parsing. Command-line arguments are suitable for automated execution but are less friendly for interactive entry.

## 4. Integer division and casting risk
If a vehicle parks for 90 minutes, `90 / 60` using integer arithmetic gives 1, not 1.5. If the rule charges every started hour, the correct billable hours are 2. For an hourly rate of Rs 30, the fee must be Rs 60.

Correct ceiling using integer arithmetic:
```java
long billableHours = (minutes + 59L) / 60L; // for minutes >= 0
double fee = billableHours * ratePerHour;
```
Do not cast after division: `(double)(minutes / 60)` still loses the fraction because integer division happened first. If a fractional duration is required, cast before division: `(double) minutes / 60.0`.

## 5. Alternative representations
- Jagged array `String[floor][slot]`: supports unequal floor sizes directly. Locating a slot by scanning is O(F × S) for approximately S slots per floor; memory is O(total slots).
- One-dimensional array: index formula `floor * slotsPerFloor + slot` works only when every floor has the same capacity. With unequal capacities, use prefix offsets: `offset[f] = sum of capacities before floor f`; index is `offset[f] + slot`. Locating a known floor/slot is O(1); finding a free slot by scanning remains O(total slots).
- `ArrayList<String[]>`: naturally stores rows of different lengths. Access by floor and slot is O(1); scanning for a free slot is O(total slots). It has small list/object overhead.

A jagged array is the clearest choice for the assignment because the number of slots can differ by floor.
