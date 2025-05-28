# 🔐 FortiCheck - Java Security Utility

**FortiCheck** is a beginner-friendly command-line application built in Java that provides basic user authentication, encryption, password strength checking, and file integrity features. Ideal for learning cryptography, authentication, and secure file operations in Java.

---

## 📌 Features

### 🧑‍💼 User Management
- Login authentication using AES-encrypted passwords
- Default user: `root:root`
- Add or remove users (admin password: `admin`)

### 🔐 Encryption & Decryption
- AES encryption/decryption using CBC mode
- Predefined secret key and IV

### 💪 Password Strength Checker
Evaluates a password based on:
- Minimum length (8+ characters)
- Use of uppercase and lowercase letters
- Numeric digits
- Special characters

### 🧾 Hash Generator
Generate cryptographic hash of files using:
- SHA-256
- SHA-1
- MD5

### 🧪 File Integrity Checker
Compare existing file hash with current one to detect file corruption or tampering.

---

## 📁 File Structure

FortiCheck/
│
├── Password.java # Main application source code
├── credentials.txt # Stores user credentials (username:encrypted_password)
└── README.md # This file

yaml
Copy
Edit

---

## 🚀 How to Run

### ✅ Prerequisites
- Java JDK 8 or later installed

### 🔧 Compile the Program

```bash
javac Password.java
▶️ Run the Program
bash
Copy
Edit
java Password
🧪 First Login
Use the default credentials:

Username: root

Password: root

⚠️ Security Disclaimer
This application is not production ready and is intended for educational purposes only.
It uses static AES keys, lacks proper password hashing (e.g., bcrypt/scrypt), and stores credentials insecurely.
