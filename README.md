PDS – mikroservisi (Eureka, API Gateway, Student, Enrollment)

Mali demo-sistem od 4 servisa:

discovery-service – Eureka server 
api-gateway – Spring Cloud Gateway 
student-service – CRUD nad studentima + H2 baza
enrollment-service – CRUD nad upisima, Feign poziv ka student-servisu + Resilience4J + H2 baza

Testirano sa Java 17+, Spring Boot 3.5.x, Spring Cloud 2024.0.x (Kilburn) i Maven wrapperom (mvnw).

Servisi se pokreću sledećim redosledom:
-Eureka
-Student-service
-Enrollment-service
-Api-gateway

Kada se pokrenu svi servisi Eureka konzola: http://localhost:8761 ovde će biti izlistani svi registrovani servisi na eureci.

Servis                    Port

discovery-service         8761
api-gateway               8080
student-service           8081
enrollment-service        8082


