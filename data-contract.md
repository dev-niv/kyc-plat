# Data Contract – Synthetic KYC Sources

## Source 1: Fenergo.csv

Represents customer KYC master data.

| Field        | Type    | Nullable | Description                          | Example                         |
|-------------|---------|----------|--------------------------------------|---------------------------------|
| customerId  | UUID    | No       | Unique customer identifier           | 550e8400-e29b-41d4-a716-446655440000 |
| fullName    | String  | No       | Customer legal name                  | Customer_12                    |
| countryCode | String  | No       | ISO-2 country code                   | IN                              |
| riskRating  | String  | No       | Risk classification (LOW/MEDIUM/HIGH)| HIGH                            |
| pepFlag     | Boolean | No       | Politically Exposed Person indicator | true                            |

Primary Key: customerId

---

## Source 2: gears.csv

Represents financial accounts linked to customers.

| Field       | Type      | Nullable | Description                         | Example |
|------------|-----------|----------|-------------------------------------|---------|
| accountId  | UUID      | No       | Unique account identifier           | 98aa... |
| customerId | UUID      | No       | Foreign key to Fenergo.customerId   | 550e... |
| accountType| String    | No       | SAVINGS/CURRENT/BROKERAGE           | SAVINGS |
| balance    | Decimal   | No       | Current account balance             | 10500.45 |
| openedDate | ISO Date  | No       | Account opening date (yyyy-MM-dd)   | 2021-06-12 |

Foreign Key: customerId → Fenergo.customerId
