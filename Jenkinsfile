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
                sh 'mvn clean test'
            }
        }

        stage('JaCoCo Coverage Check') {
            steps {
                jacoco(
                    execPattern: 'target/jacoco.exec',
                    classPattern: 'target/classes',
                    sourcePattern: 'src/main/java',
                    minimumLineCoverage: '90',
                    minimumBranchCoverage: '90',
                    minimumInstructionCoverage: '90'
                )
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'docker-sonar-token', variable: 'SONAR_TOKEN')]) {
                    withSonarQubeEnv('sonar-server') {
                        sh '''
                            mvn clean verify sonar:sonar \
                            -Dsonar.token=$SONAR_TOKEN \
                            -Dsonar.host.url=http://sonarqube:9000
                        '''
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
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

        stage('K8s Deployment') {
            steps {
                sh '''
                    alias k="kubectl --insecure-skip-tls-verify=true"

                    k apply -f k8s/deployment.yaml
                    k apply -f k8s/service.yaml
                    k rollout restart deployment/spring-cloud-app

                    k get pods
                    k get svc
                    k get deployment
                '''
            }
        }

        stage('Smoke Test') {
            steps {
                sh '''
                    echo "Waiting for Spring Boot..."

                    for i in $(seq 1 30)
                    do
                      echo "Attempt $i"

                      STATUS=$(docker exec springboot-app curl -s -o /dev/null -w "%{http_code}" http://localhost:8088/actuator/health || echo "000")

                      echo "HTTP Status: $STATUS"

                      if [ "$STATUS" = "200" ]; then
                        echo "Application is UP ✔"
                        exit 0
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
        always {
            cleanWs()
        }
    }
}