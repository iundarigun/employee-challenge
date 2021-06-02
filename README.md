# Napptilus challenge

Version API Rest:
- Database MySql + docker compose
- flyway to create schema
- Restful
  - create
  - update
  - patch
  - findById
  - findByParams - Paginat
  - findResume for sector
- OpenAPI
- MapStruct to map between DTO and entity
- Use datetime for updateat and createat  
- Use jbacon? Testcontainers?
- Use API to implement resilient4j (circuit breaker) and feign (retry)
---
- Add Cache caffeine or Redis (eviction policy)

### MySQL
```
version: '3.6'
services:
database:
 image: mysql:8.0.22
 environment:
   - MYSQL_DATABASE=studentdb
   - MYSQL_USER=root
   - MYSQL_ROOT_PASSWORD=
```   