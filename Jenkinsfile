pipeline {
    agent any

    tools {
        maven 'Maven3'   // Make sure Maven is configured in Jenkins
        jdk 'JDK21'     // Match your Spring Boot version
    }

    environment {
        DOCKER_IMAGE = "9708156765/springboot-cloud:latest"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Shaurabh-Suman/springboot-cloud.git'
            }
        }

        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t $DOCKER_IMAGE ."
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    '''
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh "docker push $DOCKER_IMAGE"
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker rm -f springboot-app || true
                    docker run -d --restart unless-stopped -p 9096:8088 --name springboot-app $DOCKER_IMAGE
                '''
            }
        }

        stage('Smoke Test') {
            steps {
                sh '''
                    echo "Waiting for Spring Boot container..."

                    for i in {1..30}
                    do
                      echo "Attempt $i..."

                      # Direct container check (NO NETWORK DEPENDENCY)
                      CONTAINER=$(docker inspect -f '{{.State.Running}}' springboot-app 2>/dev/null || echo "false")

                      echo "Container Running: $CONTAINER"

                      if [ "$CONTAINER" = "true" ]; then

                        echo "Container is running ✔"

                        # Run curl INSIDE container (THIS IS THE FIX)
                        docker exec springboot-app curl -f http://localhost:8088/actuator/health && exit 0
                      fi

                      sleep 3
                    done

                    echo "Application failed"
                    docker logs springboot-app --tail 50
                    exit 1
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline SUCCESS 🚀'
        }
        failure {
            echo 'Pipeline FAILED ❌'
        }
    }
}