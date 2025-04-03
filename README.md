# Vehicle Maintenance Management System

**Author:** Rick Hayes  
**Version:** 1.1  
**License:** BSD 3-Clause License  
**Date:** April 03, 2025  

## Description

The Vehicle Maintenance Management System (VMMS) is a Java-based application designed for automotive repair shops to streamline vehicle repair tracking, parts inventory management, and customer service requests. Inspired by forklift repair management concepts, this system provides a comprehensive solution for modern repair facilities with enhanced features in Version 1.1.

### Key Features
- **OBD-II Diagnostics**: Integrates with vehicle OBD-II systems using the jOBD2 library for real-time diagnostic data, now with detailed trouble code analysis.
- **Inventory Management**: Tracks parts inventory with barcode scanning support via the ZXing library.
- **Work Order Tracking**: Manages repair jobs with status updates and technician assignments, enhanced with scheduling.
- **Customer Portal**: Web-based interface (built with Spring MVC) for customers to check repair status and view invoices, now with payment processing.
- **Predictive Maintenance**: Uses machine learning (Weka or Deeplearning4j) to predict maintenance needs based on diagnostic data.
- **Technician Scheduling**: Assigns and manages technician availability and workload.
- **Payment Processing**: Integrates with a payment gateway (e.g., Stripe) for invoice payments.
- **Mobile App Integration**: Provides API endpoints for a mobile app companion.
- **Detailed Reporting**: Generates comprehensive reports on repairs, inventory, and financials.

## Version History
- **1.1 (April 03, 2025)**: Added detailed diagnostics, technician scheduling, payment processing, mobile app integration, and detailed reporting.
- **1.0 (April 03, 2025)**: Initial release with core functionality including OBD-II integration, inventory management, work order system, customer portal, and predictive maintenance.

## Prerequisites
- Java 17 or higher
- Maven 3.8.x or higher
- H2 Database (for demo) or any supported SQL database
- OBD-II hardware (optional, for real diagnostics)
- Barcode scanner (optional, for inventory)
- Stripe account (for payment processing)
- Mobile development environment (e.g., Android Studio or Xcode, optional)

## Dependencies
- Spring Boot 3.2.4
- jOBD2 (OBD-II library, version TBD based on availability)
- ZXing Core 3.5.3 (barcode scanning)
- Weka 3.8.6 (machine learning)
- H2 Database 2.2.224 (embedded database)
- Stripe Java 20.124.0 (payment processing)

Add these to your `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>3.2.4</version>
    </dependency>
    <dependency>
        <groupId>com.google.zxing</groupId>
        <artifactId>core</artifactId>
        <version>3.5.3</version>
    </dependency>
    <dependency>
        <groupId>nz.ac.waikato.cms.weka</groupId>
        <artifactId>weka-stable</artifactId>
        <version>3.8.6</version>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>2.2.224</version>
    </dependency>
    <dependency>
        <groupId>com.stripe</groupId>
        <artifactId>stripe-java</artifactId>
        <version>20.124.0</version>
    </dependency>
    <!-- Add jOBD2 dependency when available -->
</dependencies>
```

## Installation
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/encrypter15/vmms.git
   cd vmms
   ```

2. **Build the Project**:
   ```bash
   mvn clean install
   ```

3. **Configure Database**:
   - For H2 (default):
     - Update `application.properties`:
       ```properties
       spring.datasource.url=jdbc:h2:mem:vmms
       spring.datasource.driverClassName=org.h2.Driver
       spring.datasource.username=sa
       spring.datasource.password=
       spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
       ```
   - For other databases (e.g., MySQL), adjust the properties accordingly.

4. **Configure Payment Processing**:
   - Add Stripe API keys to `application.properties`:
     ```properties
     stripe.api.key=sk_test_yourkey
     ```

5. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

## Usage
1. **Start the Application**:
   - Access the customer portal at `http://localhost:8080/api/customer`.
   - API endpoints are available under `/api`.

2. **Detailed Diagnostics**:
   - Connect OBD-II hardware to the specified COM port.
   - Use the `OBDReader` class to fetch detailed trouble code descriptions and sensor data.

3. **Inventory Management**:
   - Scan parts using a barcode scanner or manually add via the `InventoryManager`.

4. **Work Orders and Technician Scheduling**:
   - Create new work orders through the `VehicleMaintenanceService`.
   - Assign technicians based on availability using the `TechnicianScheduler`.

5. **Customer Portal**:
   - Check repair status: `GET /api/customer/status/{orderId}`
   - View invoices: `GET /api/customer/invoice/{orderId}`
   - Process payments: `POST /api/customer/payment/{orderId}`

6. **Predictive Maintenance**:
   - The system automatically generates alerts based on OBD data and historical patterns.

7. **Mobile App Integration**:
   - Use API endpoints like `/api/mobile/status` for mobile app connectivity.

8. **Detailed Reporting**:
   - Generate reports: `GET /api/reports/{type}` (e.g., `repairs`, `inventory`, `financials`).

## Project Structure
```
vmms/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── vmms/
│   │   │           ├── model/       # Entity classes (Vehicle, Part, Technician, etc.)
│   │   │           ├── service/     # Business logic (VehicleMaintenanceService, PaymentService)
│   │   │           ├── controller/  # REST controllers (CustomerPortalController, ReportController)
│   │   │           └── util/        # Utility classes (OBDReader, MaintenancePredictor, TechnicianScheduler)
│   │   └── resources/
│   │       └── application.properties  # Configuration
├── pom.xml  # Maven configuration
└── README.md  # This file
```

## Extended Features (Version 1.1)

### More Detailed Diagnostics
- Enhanced `OBDReader` to include detailed trouble code explanations and additional sensor readings (e.g., coolant temperature, fuel pressure).

### Technician Scheduling
- Added `TechnicianScheduler` class:
  ```java
  public class TechnicianScheduler {
      private Map<String, Technician> technicians;
      public void assignTechnician(WorkOrder order, LocalDateTime scheduleTime) { ... }
      public List<Technician> getAvailableTechnicians(LocalDateTime time) { ... }
  }
  ```

### Payment Processing
- Integrated Stripe for payments:
  ```java
  @Service
  public class PaymentService {
      public Charge processPayment(String orderId, String token, double amount) { ... }
  }
  ```

### Mobile App Integration
- Added mobile-specific API endpoints:
  ```java
  @RestController
  @RequestMapping("/api/mobile")
  public class MobileController {
      @GetMapping("/status")
      public ResponseEntity<List<WorkOrderDTO>> getCustomerOrders(@RequestParam String customerId) { ... }
  }
  ```

### Detailed Reporting
- Added reporting service:
  ```java
  @Service
  public class ReportService {
      public Report generateReport(String type, LocalDateTime start, LocalDateTime end) { ... }
  }
  ```

## Development
- **Add Features**: Extend classes like `WorkOrder` or `ReportService` for new functionality.
- **Testing**: Use JUnit and Spring Boot Test for unit and integration tests.
- **Version Tracking**: Update the `Version History` section with each release.

## Contributing
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/new-feature`).
3. Commit changes (`git commit -m "Add new feature"`).
4. Push to the branch (`git push origin feature/new-feature`).
5. Create a Pull Request.

## License
BSD 3-Clause License

Copyright (c) 2025, Rick Hayes  
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.

## Contact
For questions or support, contact Rick Hayes at [encrypter15@gmail.com](mailto:encrypter15@gmail.com).
```

### Updates:
- **GitHub URL**: Changed to `https://github.com/encrypter15/vmms.git` in the "Installation" section.
- The Stripe API key (`sk_test_yourkey`) remains a placeholder; replace it with your actual key when configuring payment processing.

