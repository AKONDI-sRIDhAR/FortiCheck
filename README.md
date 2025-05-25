# FortiCheck
Forticheck is a lightweight, console-based user authentication and password management tool written in Java. It provides a simple yet effective way to securely manage user credentials with AES encryption, password strength checking, and basic administrative user controls.

Features
User Authentication: Secure login system validating usernames and AES-encrypted passwords.

User Management: Add and remove users (admin-only operations).

Password Strength Checker: Evaluates password complexity based on length, uppercase, lowercase, numbers, and special characters.

Encryption & Decryption: AES-256 encryption (CBC mode with PKCS5 padding) for storing passwords and protecting sensitive data.

Simple Console Interface: Intuitive menu-driven interface for easy use.

Credential Storage: Usernames and encrypted passwords stored in a local file (credentials.txt).

How It Works
Users register with a username and password; passwords are encrypted with AES and stored securely.

On login, user input passwords are encrypted and matched against stored ciphertext.

Admins can add or remove users by verifying an admin password.

Password strength can be checked interactively.

Provides utilities for encrypting and decrypting arbitrary text inputs.

Tech Stack
Java (Standard Edition)

AES Encryption with javax.crypto

File I/O for credential management

Console-based user interface

Usage
Clone the repo and run the Password class. The application will prompt for username and password, then provide a menu for user management, password checking, encryption, and decryption.

Future Improvements
Implement salted password hashing (e.g., bcrypt) instead of reversible encryption for better security.

Improve admin authentication mechanism.

Add user session management and timeout.

Implement a GUI for better usability.
