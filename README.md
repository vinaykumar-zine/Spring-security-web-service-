# 🔐 Spring Boot Security Full-Feature Demo

This Spring Boot project demonstrates a full-fledged security setup using **Spring Security**. It features:

- In-Memory and Database Authentication
- Role-Based Access Control (RBAC)
- Form Login and Basic Auth
- JWT Token-Based Authentication (for REST APIs)
- REST API endpoints secured by roles
- Secure password hashing using `BCryptPasswordEncoder`

---

## 🚀 Project Setup

### 📦 Prerequisites

- Java 17+
- Maven
- MySQL or PostgreSQL (if using database auth)
- Any IDE (IntelliJ, Eclipse, VS Code)

### 🛠️ Getting Started

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/demo-security-springboot.git
   cd demo-security-springboot
   ```

2. **Configure Database (if using DB auth)**
   In `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/yourdb
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```

3. **Build the Project**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

---

## 🔍 Application Features

### ✅ Authentication Types

- 🔐 **In-Memory Authentication**  
  Useful for quick testing and admin fallback accounts.

- 🗃️ **Database Authentication**  
  Users are loaded from a relational database (e.g., MySQL). Passwords are encrypted using BCrypt.

- 🪪 **JWT Token Authentication**  
  Stateless authentication for APIs using signed JWTs. After login, the client receives a token to be passed in `Authorization: Bearer <token>`.

---

### 🔐 HTTP Security Configuration

Defined in `DemoSecuruty1Application.java`:

```java
http.authorizeHttpRequests(auth -> auth
    .requestMatchers("/").permitAll()
    .requestMatchers("/admin").hasRole("ADMIN")
    .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
    .anyRequest().authenticated()
)
.formLogin()
.httpBasic();
```

### 🔓 Access Rules

| Endpoint         | Access Level       | Required Role |
|------------------|--------------------|---------------|
| `/`              | Public             | None          |
| `/admin`         | Protected          | `ADMIN`       |
| `/user/**`       | Protected          | `USER`, `ADMIN` |
| Any other path   | Protected          | Authenticated |

---

## 👥 Users & Roles

### In-Memory Users

```java
User.withUsername("vinay")
    .password("123456")
    .roles("USER");

User.withUsername("shengdana")
    .password("789456")
    .roles("ADMIN");
```

### Database Users (Example Schema)

```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
);

CREATE TABLE authorities (
    user_id BIGINT,
    authority VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

> Passwords are encrypted using `BCryptPasswordEncoder`.

---

## 📡 REST API (Secured with JWT)

### 🔑 Login Endpoint

```
POST /api/auth/login
```

**Request:**
```json
{
  "username": "vinay",
  "password": "123456"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```

Use the token for all subsequent API calls:
```http
Authorization: Bearer <JWT_TOKEN>
```

---

## 🛡️ Password Encoding

```java
@Bean
public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
}
```

All passwords are hashed with BCrypt before storage to ensure security.

---

## 🧪 Testing Credentials

### 👤 User

- Username: `vinay`
- Password: `123456`

Access:
- `/`
- `/user/**`

---

### 👮 Admin

- Username: `shengdana`
- Password: `789456`

Access:
- `/`
- `/admin`
- `/user/**`

---

## 🧱 Technologies Used

- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Spring Data JPA
- MySQL (or PostgreSQL)
- Maven

---

## 📚 References

- 🔗 [Spring Security Docs](https://docs.spring.io/spring-security/reference/)
- 🔗 [JWT Guide](https://jwt.io/introduction/)
- 🔗 [Spring Boot Guide](https://spring.io/guides/gs/securing-web/)

---

## 🧊 Additional Features

- [x] Role-Based Access Control (RBAC)
- [x] Secure REST APIs
- [x] JWT authentication
- [x] Password encryption with BCrypt
- [x] Support for both form login and HTTP Basic
- [x] Modular and extensible configuration

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

Made with ❤️ by Vinaykumar Zine