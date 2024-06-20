# SuperMarket System - Testing & User Guide

## 🧪 Testing the Application

### Step 1: Start the Application

```bash
cd /home/ashwin/SuperMarket
mvn clean install
mvn spring-boot:run
```

Wait for startup message:
```
Started SuperMarketApplication in X.XXX seconds
```

### Step 2: Access the Application

Open browser: `http://localhost:8080`

You'll be redirected to login page.

---

## 🔐 Test User Accounts

### Admin Account (Full Access)
```
Username: admin
Password: admin123
```
**Permissions:** All features, create/delete users, view all reports

### Cashier Account
```
Username: cashier1
Password: cashier123
```
**Permissions:** Create receipts, view products, process sales

### Storekeeper Account
```
Username: storekeeper1
Password: store123
```
**Permissions:** Manage inventory, update stock levels

---

## 📋 Testing Scenarios

### Scenario 1: First Login
1. Navigate to `http://localhost:8080`
2. Enter: `admin` / `admin123`
3. Expected: Redirected to dashboard
4. Verify: Dashboard shows statistics

### Scenario 2: Dashboard Overview
1. Check statistics cards
2. Verify:
   - Total Products (should show default products)
   - Sections (should show 8)
   - Employees (should show 3)
   - Total Sales (should show $0 initially)

### Scenario 3: Create New Product
1. Click "Products" in sidebar
2. Click "Add Product" button
3. Fill form:
   - Name: "Test Product"
   - Price: 19.99
   - Quantity: 50
   - Section: Select any section
   - Barcode: "TEST001"
4. Click "Save Product"
5. Expected: Product added to table

### Scenario 4: Create New Section
1. Click "Sections" in sidebar
2. Click "Add Section" button
3. Fill form:
   - Name: "Electronics"
   - Description: "Electronics section"
4. Click "Save Section"
5. Expected: Section appears in grid

### Scenario 5: Add New Employee
1. Click "Employees" in sidebar
2. Click "Add Employee" button
3. Fill form:
   - First Name: "John"
   - Last Name: "Doe"
   - Username: "johndoe"
   - Password: "pass123"
   - Email: "john@example.com"
   - Phone: "555-1234"
   - Address: "123 Street"
   - Salary: 3000
   - Role: "CASHIER"
4. Click "Save Employee"
5. Expected: Employee added to table

### Scenario 6: Create Receipt
1. Click "Receipts" in sidebar
2. Click "New Receipt" button
3. Select Cashier
4. Click "Add Item"
5. Select Product, Quantity
6. Click "Complete Receipt"
7. Expected: Receipt number generated

### Scenario 7: View Analytics
1. Click "Analytics" in sidebar
2. Expected to see:
   - Total Sales
   - Total Tax
   - Total Discount
   - Transaction count
   - Payment method chart
   - Sales trend chart

### Scenario 8: Low Stock Alert
1. Click "Products"
2. Create a product with:
   - Quantity: 5
   - Reorder Level: 10
3. Go to Dashboard
4. Expected: Product appears in "Low Stock Products"

---

## ✅ Feature Checklist

### Authentication
- [ ] Login works
- [ ] Logout clears session
- [ ] JWT token stored
- [ ] Invalid credentials rejected
- [ ] Session persists on page refresh

### Products Module
- [ ] List all products
- [ ] Add new product
- [ ] Edit existing product
- [ ] Delete product
- [ ] View by section
- [ ] Low stock detection
- [ ] Barcode search
- [ ] Status toggle (Active/Inactive)

### Sections Module
- [ ] List all sections
- [ ] Add new section
- [ ] Edit section
- [ ] Delete section
- [ ] Product count shown

### Employees Module
- [ ] List employees
- [ ] Add employee
- [ ] Edit employee
- [ ] Delete employee
- [ ] Activate/Deactivate
- [ ] Filter by role

### Receipts Module
- [ ] Create receipt
- [ ] Add items to receipt
- [ ] Calculate totals
- [ ] Tax calculation
- [ ] Payment method selection
- [ ] View receipt history
- [ ] Delete receipt

### Analytics Module
- [ ] Display total sales
- [ ] Display total tax
- [ ] Display total discount
- [ ] Show transaction count
- [ ] Chart visualization
- [ ] Sales trends

### Dashboard
- [ ] Display statistics
- [ ] Show low stock products
- [ ] Display charts
- [ ] Real-time updates

---

## 🔧 API Testing (with cURL or Postman)

### Get Auth Token
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

Response:
```json
{
  "success": true,
  "message": "Login successful",
  "token": "eyJhbGc...",
  "employee": { ... }
}
```

### Get All Products
```bash
curl -X GET http://localhost:8080/api/products \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### Create Product
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{
    "name": "Test Product",
    "price": 29.99,
    "quantity": 100,
    "sectionId": 1,
    "barcode": "TEST123"
  }'
```

### Get Analytics
```bash
curl -X GET http://localhost:8080/api/receipts/analytics \
  -H "Authorization: Bearer YOUR_TOKEN"
```

---

## 📊 Expected Default Data

### Sections (8 total)
1. Fruits & Vegetables
2. Dairy & Eggs
3. Meat & Seafood
4. Bakery
5. Beverages
6. Snacks
7. Frozen Foods
8. Personal Care

### Sample Products (5)
1. Fresh Apples - $1.99 (Fruits & Vegetables)
2. Bananas - $0.59 (Fruits & Vegetables)
3. Carrots - $0.79 (Fruits & Vegetables)
4. Whole Milk 1L - $2.99 (Dairy & Eggs)
5. Potato Chips - $1.49 (Snacks)

### Employees (3)
1. System Admin (admin/admin123)
2. John Cashier (cashier1/cashier123)
3. Jane Storekeeper (storekeeper1/store123)

---

## 🐛 Debugging Tips

### Check Logs
Logs are saved to `logs/supermarket.log`

```bash
tail -f logs/supermarket.log
```

### Database Issues
Database file: `supermarket.db`

To reset database:
1. Stop application
2. Delete `supermarket.db`
3. Restart application
4. Data will be recreated

### Port Already in Use
```bash
# Find process using port 8080
lsof -i :8080

# Kill process
kill -9 <PID>
```

### Clear Cache
1. Clear browser localStorage
2. Clear cookies
3. Hard refresh (Ctrl+Shift+R)

---

## 🚀 Performance Testing

### Load Test Endpoints
```bash
# Create 100 products
for i in {1..100}; do
  curl -X POST http://localhost:8080/api/products \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer TOKEN" \
    -d "{\"name\":\"Product $i\",\"price\":9.99,\"quantity\":100,\"sectionId\":1,\"barcode\":\"TEST$i\"}"
done
```

### Stress Test
```bash
# 1000 requests
ab -n 1000 -c 10 http://localhost:8080/api/products
```

---

## 📝 Common Issues & Solutions

### Issue: Blank Page After Login
**Solution:**
1. Check browser console for errors (F12)
2. Check application logs
3. Verify JWT token format
4. Clear localStorage and cache

### Issue: API Returns 401 Unauthorized
**Solution:**
1. Token may have expired (24 hours)
2. Login again to get new token
3. Verify token format in Authorization header

### Issue: Can't Create Product
**Solution:**
1. Section must exist first
2. Verify all required fields filled
3. Check for duplicate barcode
4. Check application logs

### Issue: Receipt Calculation Wrong
**Solution:**
1. Verify tax percentage
2. Check product prices
3. Verify quantity values
4. Recalculate manually to verify

---

## ✨ User Experience Tips

1. **Dashboard First** - Always start with dashboard to see overview
2. **Sections First** - Create sections before products
3. **Bulk Products** - Add frequently used products upfront
4. **Regular Backups** - Backup database.db regularly
5. **Monitor Alerts** - Check low-stock alerts daily
6. **Review Analytics** - Weekly review of sales reports

---

## 📞 Support

If you encounter issues:

1. Check the logs: `logs/supermarket.log`
2. Verify all dependencies installed
3. Ensure Java 17+ is available
4. Check port 8080 is not in use
5. Review this testing guide

---

## 🎉 You're All Set!

The system is now ready for:
- ✅ Testing
- ✅ Development
- ✅ Production use
- ✅ Integration

**Happy Testing! 🚀**
