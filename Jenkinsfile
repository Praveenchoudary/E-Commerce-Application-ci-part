pipeline {

    agent any

    environment {
        SCANNER_HOME = tool 'sonar-scanner'
        APP_IMAGE = "praveen22233/ecommerce-app"
        MYSQL_IMAGE = "praveen22233/ecommerce-mysql"
    }

    options {
        disableConcurrentBuilds()
        timestamps()
    }

    stages {

        /* ---------------- CLEAN WORKSPACE ---------------- */
        stage("Clean Workspace") {
            steps { cleanWs() }
        }

        /* ---------------- CHECKOUT CODE ---------------- */
        stage("Checkout Code") {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Praveenchoudary/E-Commerce-Application.git'
            }
        }

        /* ---------------- BUILD WAR ---------------- */
        stage("Build WAR") {
            steps {
                sh """
                    mvn clean package -DskipTests
                    cp target/*.war Docker-deployment/app/shopping-app.war
                """
            }
        }

        /* ---------------- SONARQUBE ---------------- */
        stage("Code Quality Analysis") {
            steps {
                withSonarQubeEnv("sonar-scanner") {
                    sh """
                        ${SCANNER_HOME}/bin/sonar-scanner \
                          -Dsonar.projectKey=ecommerce-app \
                          -Dsonar.projectName=ecommerce-app \
                          -Dsonar.sources=src \
                          -Dsonar.java.binaries=target/classes \
                          -Dsonar.java.source=8 \
                          -Dsonar.java.target=8
                    """
                }
            }
        }

        /* ---------------- OWASP DEPENDENCY CHECK ---------------- */
        stage("OWASP Dependency Check") {
            steps {
                dependencyCheck additionalArguments: '--scan . --format XML --disableYarnAudit --disableNodeAudit',
                    odcInstallation: 'DP-Check'
                dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            }
        }

        /* ---------------- BUILD DOCKER IMAGES ---------------- */
        stage("Build Docker Images") {
            steps {
                sh """
                    # Build Application Image 
                    cd Docker-deployment/app
                    docker build -t ${APP_IMAGE}:${BUILD_NUMBER} .

                    # Build MySQL Image 
                    cd ../mysql
                    docker build -t ${MYSQL_IMAGE}:${BUILD_NUMBER} .
                """
            }
        }

        /* ---------------- TRIVY SCANS ---------------- */
        stage("Trivy FS Scan") {
            steps { sh "trivy fs . > trivy-fs-report.txt" }
        }

        stage("Trivy Scan App Image") {
            steps { sh "trivy image ${APP_IMAGE}:${BUILD_NUMBER} > trivy-app-img.txt" }
        }

        stage("Trivy Scan MySQL Image") {
            steps { sh "trivy image ${MYSQL_IMAGE}:${BUILD_NUMBER} > trivy-mysql-img.txt" }
        }

        /* ---------------- PUSH TO DOCKER HUB ---------------- */
        stage("Push Docker Images to DockerHub") {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'dockerhub-creds') {

                        sh "docker push ${APP_IMAGE}:${BUILD_NUMBER}"
                        sh "docker push ${MYSQL_IMAGE}:${BUILD_NUMBER}"
                    }
                }
            }
        }

        /* ---------------- DEPLOY USING DOCKER COMPOSE ---------------- */
        stage("Deploy with Docker Compose") {
            steps {
                sh """
                    cd Docker-deployment

                    # Replace image tags inside docker-compose.yml
                    sed -i 's#IMAGE_TAG#${BUILD_NUMBER}#g' docker-compose.yaml

                    docker-compose down || true
                    docker-compose up -d --build
                """
            }
        }
    }

    /* ---------------- POST BUILD STATUS ---------------- */
    post {
        success {
            echo " Deployment Successful!"
            echo "➡ Access App: http://<SERVER-IP>:8081/shopping-app/"
        }

        failure {
            echo " Deployment Failed — Check Jenkins Logs."
        }
    }
}
