<div align="center">

# ☕ Java Developer Journey: Zero to Industry-Ready

**A hands-on monorepo documenting my path from core Java to professional Spring Boot development.**

![Java](https://img.shields.io/badge/Java-17+-orange?logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=springboot&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-59666C?logo=hibernate&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?logo=mysql&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5&logoColor=white)
![Status](https://img.shields.io/badge/Status-Learning_in_progress-blue)

</div>

---

## 📖 About This Repository

This repository is my **public learning log**. Each folder is an independent Maven project that
focuses on one concept, so the commit history shows how my skills build up step by step:
build tools, then testing, then JDBC, then web basics, then REST, and finally Spring Boot with Hibernate.

I am still learning, and this repo will keep growing as I move toward industry-level skills such as
security, containerization, and microservices.

> 💡 **Goal:** Become a job-ready Java backend developer who can design, build, test, and deploy
> production-style applications with the Spring ecosystem.

---

## 🧰 Tech Stack

| Area | Technologies |
|---|---|
| **Language** | Java 17+ |
| **Build tool** | Maven |
| **Web / API** | Servlets, JSP, Jersey (JAX-RS), Spring Boot, Spring Web (REST) |
| **Persistence** | JDBC, DAO pattern, Hibernate ORM, Spring Data JPA |
| **Database** | MySQL |
| **Testing** | JUnit 5 |
| **Tools** | VS Code, Postman, Git & GitHub |

---

## 🗺️ Learning Roadmap

Legend: ✅ done · 🚧 in progress · ⬜ planned

### Phase 1: Foundations
- ✅ Maven project structure and lifecycle
- ✅ Unit testing with JUnit 5 (lifecycle annotations, assertions)

### Phase 2: Database Connectivity
- ✅ JDBC connections, statements, result sets
- ✅ DAO design pattern (Student Management)

### Phase 3: Web Fundamentals
- ✅ Servlets and JSP (user registration)
- ✅ REST APIs with Jersey (JAX-RS)

### Phase 4: Spring Ecosystem
- ✅ Spring Boot REST API (full CRUD: GET, POST, PUT, PATCH, DELETE)
- ✅ Hibernate ORM and JPA (`EntityManager`, `JpaRepository`)
- 🚧 Derived queries, custom queries, pagination and sorting
- ⬜ Entity relationships (`@OneToMany`, `@ManyToOne`, `@ManyToMany`)
- ⬜ DTOs, validation (`@Valid`), global exception handling (`@ControllerAdvice`)
- ⬜ Layered architecture (Controller → Service → Repository)

### Phase 5: Industry-Level Skills
- ⬜ Spring Security and JWT authentication
- ⬜ API documentation with Swagger / OpenAPI
- ⬜ Unit and integration tests with Mockito and Spring Boot Test
- ⬜ Profiles and externalized configuration
- ⬜ Docker and Docker Compose
- ⬜ CI/CD with GitHub Actions
- ⬜ Microservices (Spring Cloud, API Gateway)
- ⬜ Messaging (Kafka / RabbitMQ) and caching (Redis)
- ⬜ Cloud deployment (AWS / Render / Railway)

---

## 📂 Project Structure

| # | Project | Focus | Key Concepts |
|---|---|---|---|
| 0 | `Project_0_demoProject` | Maven basics | Project layout, `pom.xml`, build lifecycle |
| 1 | `Project_1_JUnit5_01` | Unit testing intro | `@Test`, `@BeforeEach`, `@AfterEach`, assertions |
| 2 | `Project_2_JUnit5_02` | Practical testing | Testing string manipulation logic |
| 3 | `Project_3_JDBC_01` | JDBC core | Driver, `Connection`, `Statement`, `ResultSet` |
| 4 | `Project_4_JDBC_02` | Student Management | JDBC with DAO pattern |
| 5 | `Project_5_servletApp` | Servlets and JSP | User registration flow, request/response cycle |
| 6 | `Project_6_RestAPIJersey` | REST with JAX-RS | Resources, annotations, deployment on Tomcat |
| 7 | `Project_7_RestAPISpringBoot` | Spring Boot REST | Controllers, JPA repository, full CRUD |
| 8 | `Project_8_ORMHibernate` | Hibernate ORM | Entities, `EntityManager`, HQL/Criteria, derived queries |

---

## 🚀 Getting Started

### Prerequisites

- JDK 17 or higher
- Apache Maven 3.8+
- MySQL Server running locally
- Postman (or any API client) for testing REST endpoints

### Run a project

```bash
# 1. Clone the repository
git clone https://github.com/shashank-1-2/MAVEN.git

# 2. Move into the project you want to run
cd Project_7_RestAPISpringBoot

# 3. Build and download dependencies
mvn clean install

# 4. Start the embedded server (Spring Boot projects)
mvn spring-boot:run
```

> **Servlet and Jersey projects:** package with `mvn clean package`, then deploy the generated
> `.war` file from `/target` to Apache Tomcat.

### Database setup

```sql
CREATE DATABASE university;
```

Then edit `src/main/resources/application.properties` in the Spring Boot projects:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/university?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Hibernate creates the `students` table automatically on first run.

> 🔐 **Never commit real passwords.** Keep placeholders in the repo, or load credentials from
> environment variables, e.g. `spring.datasource.password=${DB_PASSWORD}`.

---

## 🔌 API Reference (Projects 7 and 8)

Base URL: `http://localhost:8080/api/student`

| Method | Endpoint | Description | Body |
|---|---|---|---|
| `GET` | `/api/student` | Get all students | none |
| `GET` | `/api/student/{id}` | Get one student | none |
| `GET` | `/api/student/filter?minGrade=85` | Students with grade ≥ value | none |
| `GET` | `/api/student/course?name=Java` | Students by course | none |
| `GET` | `/api/student/search?text=ra` | Students whose name contains text | none |
| `POST` | `/api/student` | Create a student | JSON |
| `PUT` | `/api/student/{id}` | Replace a student | JSON |
| `PATCH` | `/api/student/{id}` | Update selected fields | JSON |
| `DELETE` | `/api/student/{id}` | Delete a student | none |

**Sample request body (POST / PUT):**

```json
{
  "name": "Rahul",
  "course": "Java",
  "grade": 88.5
}
```

---

## 🧠 Key Lessons Learned

- **Spring Boot manages dependency versions.** Overriding Hibernate or driver versions by hand
  can break auto-configuration, so I let the starter manage them.
- **Raw types break Spring Data.** `JpaRepository<Student, Integer>` needs its generics so Spring
  knows the entity and ID type.
- **405 vs 500 vs 404 errors:** 405 means the route exists but not for that HTTP method, 404 means
  the route or resource wasn't found, and 500 means a server-side exception (read the terminal stack trace).
- **DAO vs Repository:** a DAO is hand-written `EntityManager` code, while a repository interface lets
  Spring generate the implementation. Both run on Hibernate underneath.
- **`@PathVariable("id")`** with explicit names is safer if parameter names aren't preserved at compile time.

---

## 🎯 Next Steps

- [ ] Add a Service layer between controller and repository
- [ ] Add DTOs, input validation and global exception handling
- [ ] Write unit and integration tests for the Spring Boot projects
- [ ] Add Swagger UI for live API documentation
- [ ] Secure the API with Spring Security and JWT
- [ ] Dockerize the application and its MySQL database

---

## 🤝 Feedback

I'm still learning, so suggestions, code reviews and corrections are welcome. Open an issue
or a pull request if you spot something I can improve.

## 👤 Author

**Shashank Kumar**
GitHub: [@shashank-1-2](https://github.com/shashank-1-2)

---

<div align="center">

⭐ If this repo helps you on your own Java journey, consider giving it a star.

*"Consistency beats intensity. One concept, one project, every week."*

</div>
