# SuperMarket Management System

A modern, fully functional supermarket management system built with Spring Boot, SQLite, and a beautiful responsive UI.

## Features

✨ **Core Features:**
- 🏪 Complete Product Management (CRUD operations)
- 📁 Section/Category Management
- 👥 Employee Management (Admin, Cashier, Storekeeper roles)
- 💳 Receipt & Billing System
- 📊 Sales Analytics & Reports
- 📈 Inventory Tracking
- 🔐 Secure Authentication with JWT
- 🎨 Modern, Responsive UI with Bootstrap 5

## Tech Stack

- **Backend:** Spring Boot 3.1.5 with Spring Security
- **Database:** SQLite with Hibernate ORM/JPA
- **Frontend:** Bootstrap 5, HTML5, CSS3, JavaScript (Vanilla)
- **API:** RESTful API with CORS support
- **Authentication:** JWT (JSON Web Tokens)
- **Password Encoding:** BCrypt

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- Modern web browser

### Installation

1. **Clone/Extract the project**
   ```bash
   cd SuperMarket
   ```

2. **Build with Maven**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   Open browser and navigate to `http://localhost:8080`

## Default Admin Credentials

- **Username:** admin
- **Password:** admin123

## Database

SQLite database (`supermarket.db`) is automatically created in the root directory on first run. All tables and schema are created automatically.

## Project Structure

```
SuperMarket/
├── src/main/java/com/supermarket/
│   ├── SuperMarketApplication.java
│   ├── controller/          # REST API Controllers
│   ├── service/             # Business Logic
│   ├── entity/              # JPA Entities
│   ├── repository/          # Data Access
│   ├── dto/                 # Data Transfer Objects
│   ├── security/            # Security & JWT
│   ├── exception/           # Exception Handling
│   └── handler/             # Global Exception Handler
├── src/main/resources/
│   ├── templates/           # HTML Templates
│   │   ├── login.html
│   │   ├── dashboard.html
│   │   ├── products.html
│   │   ├── sections.html
│   │   ├── employees.html
│   │   ├── receipts.html
│   │   └── analytics.html
│   └── application.properties
├── pom.xml
└── README.md
```

## Available Pages & Features

### 1. **Login Page**
   - User authentication
   - Secure JWT token generation
   - Session management

### 2. **Dashboard**
   - Real-time statistics
   - Low stock alerts
   - Sales trends visualization
   - Quick navigation

### 3. **Products Management**
   - CRUD operations
   - Barcode tracking
   - Inventory levels
   - Reorder level management

### 4. **Sections Management**
   - Category organization
   - Product grouping
   - Section analytics

### 5. **Employees Management**
   - Employee CRUD
   - Role-based assignments
   - Salary tracking
   - Status management

### 6. **Receipts & Billing**
   - Receipt creation
   - Multiple payment methods
   - Real-time calculations
   - Receipt history

### 7. **Analytics & Reports**
   - Sales analytics
   - Payment method breakdown
   - Weekly/Monthly trends
   - Top products reports

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `POST /api/auth/change-password` - Change password

### Products
- `GET /api/products` - List all products
- `POST /api/products` - Create product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product
- `GET /api/products/low-stock` - Get low stock items

### Sections
- `GET /api/sections` - List all sections
- `POST /api/sections` - Create section
- `PUT /api/sections/{id}` - Update section
- `DELETE /api/sections/{id}` - Delete section

### Employees
- `GET /api/employees` - List all employees
- `POST /api/employees` - Create employee
- `PUT /api/employees/{id}` - Update employee
- `DELETE /api/employees/{id}` - Delete employee

### Receipts
- `GET /api/receipts` - List all receipts
- `POST /api/receipts` - Create receipt
- `GET /api/receipts/analytics` - Get analytics

## Configuration

Edit `application.properties` to customize:

```properties
server.port=8080
spring.jpa.hibernate.ddl-auto=update
jwt.secret=your_secret_key_here
jwt.expiration=86400000
```

## Building for Production

```bash
mvn clean package -DskipTests
java -jar target/supermarket-system-1.0.0.jar
```

## Troubleshooting

### Reset Database
Delete `supermarket.db` file - it will be recreated on next startup

### Port Already in Use
Change `server.port` in `application.properties`

### Clear Session
Clear browser localStorage and login again

## Future Enhancements

- 📱 Mobile Application
- 📧 Email Notifications
- 🗂️ Advanced Reporting
- 📦 Supplier Management
- 🎯 Demand Forecasting
- 📊 ML-based Analytics
- 🌐 Multi-language Support
- 🔔 Real-time Notifications

## License

MIT License - Feel free to use this project!

---

**Built with ❤️ for supermarket management**