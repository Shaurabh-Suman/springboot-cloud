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
                          -Dsonar.login=$SONAR_TOKEN
                      '''
                  }
              }
          }
      }

        stage('Quality Gate') {
            steps {
                script {
                    timeout(time: 5, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        echo "Quality Gate Status: ${qg.status}"

                        if (qg.status != 'OK') {
                            error "Pipeline failed due to Quality Gate: ${qg.status}"
                        }
                    }
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
    }
}