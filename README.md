
# 🛒 E-Commerce Website – Electronics Shopping

This is an E-Commerce website built for selling electronic products online.

## 📌 About the Project

This project allows users to:

* Browse all available products
* Register & log in
* Filter and search products
* Add items to cart, update quantity, and proceed to checkout
* Use a demo credit-card page (NOT connected to any real payment gateway)
* Place orders and view order details & shipping status

Admins can:

* Add products
* Update products
* Remove products
* Manage inventory
* View all orders and update shipping/delivery status


## 🛠️ Tech Stack

### **Frontend**

* HTML
* CSS
* JavaScript
* Bootstrap

### **Backend**

* Java (JDK 8+)
* Servlets
* JSP
* JDBC

### **Database**

* MySQL

---

## ⚙️ Software / Tools Required

* Git
* Java JDK 8+
* Eclipse Enterprise Edition
* Apache Maven
* Tomcat v8+
* MySQL Server
* MySQL Workbench

---

## 🗄️ Dummy Database Initialization

1. Open MySQL Command Prompt or MySQL Workbench
2. Login:

```
mysql -u <username> -p
```

3. Run SQL script located in:

```
databases/mysql_query.sql
```

---



## 🚀 Import & Run Project Using Eclipse EE

### **1. Clone Project**

```
https://github.com/Praveenchoudary/E-Commerce-Application.git
```

### **2. Update `application.properties`**

* Update MySQL `db.username` & `db.password`
* Update `mailer.email` & `mailer.password`
  
```
mvn clean package
```

### **7. Access the Website**

Default:

```
http://localhost:8080/shopping-cart/
```

To change port:

```
Server tab → double click Tomcat → Ports → Change HTTP/1.1 → Save
```

---

## 🔑 Default Credentials

### **Admin**

* Email: `admin@gmail.com`
* Password: `admin`

### **User**

* Email: `guest@gmail.com`
* Password: `guest`


