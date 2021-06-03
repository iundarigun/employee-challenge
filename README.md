# Napptilus challenge

Version API Rest:
- [ ] checkstyle
- [X] Database MySql + docker compose
- [X] flyway to create schema
- Restful
  - [ ] Adding job field
  - [X] create - ok
  - [X] update - ok
  - [ ] patch  - Pending
  - [X] Delete
  - [X] findById
  - [X] findByParams - Paginat
  - [ ] findResume for sector
- [X] OpenAPI
- [X] MapStruct to map between DTO and entity
- [X] Use datetime for updateat and createat  
- [X] Use jbacon? Testcontainers?
- [X] Use API to implement resilient4j (circuit breaker) and feign (retry)
---
- Add Cache caffeine or Redis (eviction policy)
  - [X] Add Cache to department
  - [X] Add Cache to employee
 
### MySQL
```
version: '3.6'
services:
database:
 image: mysql:8.0.22
 environment:
   - MYSQL_DATABASE=employee_management
   - MYSQL_ROOT_PASSWORD=root
```   
 docker run -d --name local-mysql -p 3306:3306 -e MYSQL_DATABASE=employee_management -e MYSQL_ROOT_PASSWORD=root mysql