# Spring Boot Task Manager

A REST API for task management built with Spring Boot and deployed on AWS EC2 using Docker, PostgreSQL, Nginx, GitHub Actions, GHCR and AWS Systems Manager.

## Features

- User registration and login
- JWT authentication
- Password hashing with BCrypt
- Create, read, update and delete tasks
- Input validation
- Global exception handling
- PostgreSQL persistence
- Dockerized application
- Docker Compose deployment
- PostgreSQL healthcheck
- Nginx reverse proxy
- Automated CI/CD deployment to AWS EC2

## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Maven

### Database
- PostgreSQL 18

### Testing
- JUnit
- Mockito
- H2

### DevOps / Cloud
- Docker
- Docker Compose
- GitHub Actions
- GitHub Container Registry (GHCR)
- AWS EC2
- AWS Systems Manager
- IAM
- Nginx

## Architecture

```text
Client
  |
  v
AWS Security Group
  |
  v
Nginx :80
  |
  v
Spring Boot :8080
  |
  v
PostgreSQL :5432
```

The Spring Boot container is bound only to:

```text
127.0.0.1:8080
```

PostgreSQL is accessible only through the internal Docker network.

## Main API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/register` | Register a user |
| POST | `/login` | Authenticate and receive a JWT |
| GET | `/tasks` | Get all tasks |
| GET | `/tasks/{id}` | Get a task |
| POST | `/tasks` | Create a task |
| PUT | `/tasks/{id}` | Update a task |
| DELETE | `/tasks/{id}` | Delete a task |
| GET | `/health` | Deployment health test |

Protected endpoints require:

```text
Authorization: Bearer <JWT>
```

## Docker

The application consists of two services:

```text
taskmanager-app
taskmanager-db
```

PostgreSQL uses a persistent Docker volume.

A healthcheck ensures that PostgreSQL is ready before the Spring Boot application starts.

Useful commands:

```bash
docker compose ps
docker compose logs -f app
docker compose logs -f db
docker compose pull
docker compose up -d
```

## CI/CD Pipeline

Every push to the `main` branch triggers GitHub Actions.

Pipeline:

```text
git push
   |
   v
Run tests
   |
   v
Build Spring Boot application
   |
   v
Build Docker image
   |
   v
Push image to GHCR
   |
   v
AWS Systems Manager
   |
   v
EC2
   |
   v
docker compose pull
docker compose up -d
```

This automatically deploys the latest application version to the EC2 server.

## Security

The deployment includes several security measures:

- JWT authentication
- BCrypt password hashing
- Secrets stored outside the source code
- GitHub Actions secrets
- Restricted IAM permissions for the deployment user
- SSH restricted through the EC2 Security Group
- Port `8080` not publicly exposed
- PostgreSQL port `5432` not publicly exposed
- Nginx used as reverse proxy
- AWS Systems Manager used for automated deployment

## Running Locally

Configure the required environment variables:

```env
DB_PASSWORD=your_postgresql_password
JWT_SECRET=your_jwt_secret
```

Then run:

```bash
./mvnw spring-boot:run
```

Or with Docker Compose:

```bash
docker compose up -d
```

## Deployment

The application is currently deployed on AWS EC2.

Infrastructure:

```text
Internet
   |
   v
AWS EC2 Security Group
   |
   v
Nginx
   |
   v
Docker Compose
   |
   +--> Spring Boot
   |
   +--> PostgreSQL
```

## What I Learned

Through this project I practiced:

- REST API development with Spring Boot
- Spring Security and JWT authentication
- Relational database persistence with PostgreSQL
- Unit testing
- Docker images and containers
- Docker networking and volumes
- Docker Compose
- Reverse proxy configuration with Nginx
- AWS EC2 deployment
- IAM permissions
- AWS Systems Manager
- GitHub Actions CI/CD
- GHCR image publishing
- Basic production deployment and troubleshooting

## Author

Taha Lazar

GitHub: `tahaLazar`