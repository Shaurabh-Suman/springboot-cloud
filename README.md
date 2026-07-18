# Spring Boot CI/CD Pipeline with Jenkins, SonarQube, Docker, Kubernetes & Helm

## Overview

This project demonstrates an end-to-end CI/CD pipeline for a Spring Boot
application using modern DevOps tools.

### Pipeline

1.  Checkout source from GitHub
2.  Build with Maven
3.  Run unit tests
4.  Analyze code with SonarQube
5.  Build Docker image
6.  Push image to Docker Hub
7.  Deploy to Kubernetes using Helm

------------------------------------------------------------------------

## Tech Stack

  Technology                    Purpose
  ----------------------------- ----------------------------
  Java 21                       Application
  Spring Boot                   Backend
  Maven                         Build Tool
  Git & GitHub                  Source Control
  Jenkins                       CI/CD
  SonarQube                     Code Quality
  Docker                        Containerization
  Docker Hub                    Image Registry
  Kubernetes (Docker Desktop)   Orchestration
  Helm                          Kubernetes Package Manager

------------------------------------------------------------------------

## Architecture

``` text
Developer
    |
git push origin main
    |
    v
GitHub Repository
    |
 Poll SCM / Webhook
    |
    v
Jenkins Pipeline
    |
+-------------------------------+
| Checkout                      |
| Maven Build                   |
| Unit Tests                    |
| SonarQube Analysis            |
| Docker Build                  |
| Docker Push                   |
| Helm Upgrade                  |
+-------------------------------+
    |
    v
Kubernetes Cluster
    |
    v
Spring Boot Application
```

------------------------------------------------------------------------

## Project Structure

``` text
spring-cloud-app
├── src/
├── helm/
├── k8s/
├── target/
├── Dockerfile
├── Dockerfile.jenkins
├── Jenkinsfile
├── pom.xml
└── README.md
```

------------------------------------------------------------------------

## Prerequisites

-   Java 21
-   Maven
-   Docker Desktop (Kubernetes enabled)
-   Jenkins
-   SonarQube
-   Helm
-   kubectl
-   Git

------------------------------------------------------------------------

## Running the Application

``` bash
mvn clean install
docker build -t 9708156765/springboot-cloud:latest .
docker run -p 8088:8080 9708156765/springboot-cloud:latest
```

------------------------------------------------------------------------

## Jenkins Container

``` bash
docker run -d \
--name jenkins \
--restart unless-stopped \
--add-host=host.docker.internal:host-gateway \
-p 8081:8080 \
-p 50000:50000 \
-u jenkins \
--group-add 0 \
-v jenkins_home:/var/jenkins_home \
-v /var/run/docker.sock:/var/run/docker.sock \
-v ${HOME}/.kube/config:/var/jenkins_home/.kube/config \
my-jenkins:jdk21
```

------------------------------------------------------------------------

## Dockerfile

``` dockerfile
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/spring-cloud-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
```

------------------------------------------------------------------------

## CI/CD Stages

-   Source Checkout
-   Maven Build
-   Unit Testing
-   SonarQube Scan
-   Quality Gate
-   Docker Build
-   Docker Push
-   Helm Deployment
-   Kubernetes Verification

------------------------------------------------------------------------

## Kubernetes

``` bash
kubectl get nodes
kubectl get pods
kubectl get svc
```

------------------------------------------------------------------------

## Helm

``` bash
helm upgrade --install springboot-app ./helm
```

------------------------------------------------------------------------

## Jenkins Auto Trigger

Current setup uses **Poll SCM**.

Schedule:

``` text
H/5 * * * *
```

For production, GitHub Webhooks are recommended.

------------------------------------------------------------------------

## Useful Commands

``` bash
git add .
git commit -m "Update"
git push origin main
```

``` bash
docker ps
docker images
kubectl get pods
helm list
```

------------------------------------------------------------------------

## Screenshots

Create an `images/` folder and add:

``` text
images/
├── jenkins-pipeline.png
├── sonarqube-dashboard.png
├── kubernetes-pods.png
├── docker-images.png
└── application.png
```

Reference them:

``` markdown
![Pipeline](images/jenkins-pipeline.png)
![SonarQube](images/sonarqube-dashboard.png)
```

------------------------------------------------------------------------

## Future Enhancements

-   GitHub Webhooks
-   Argo CD
-   Prometheus
-   Grafana
-   Trivy
-   OWASP Dependency Check
-   Slack Notifications

------------------------------------------------------------------------

## Troubleshooting

-   Ensure Docker Desktop is running.
-   Enable Kubernetes in Docker Desktop.
-   Verify `kubectl get nodes`.
-   Ensure Jenkins has access to `/var/run/docker.sock`.
-   Verify Helm release with `helm list`.

------------------------------------------------------------------------

## Author

**Shaurabh Suman**

GitHub: https://github.com/Shaurabh-Suman
