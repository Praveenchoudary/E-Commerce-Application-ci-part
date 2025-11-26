pipeline {
    agent any

    environment {
        COMPOSE_DIR = "Docker-deployment"
    }

    options {
        disableConcurrentBuilds()    // Prevent parallel pipeline runs
        timestamps()                 // Better logs
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo " Checking out repository..."
                git branch: 'main', url: 'https://github.com/Praveenchoudary/E-Commerce-Application.git'
            }
        }

        stage('Build WAR with Maven') {
            steps {
                echo "🏗 Building WAR file..."
                sh '''
                    mvn clean package -DskipTests
                    cp target/*.war Docker-deployment/app/shopping-app.war
                '''
            }
        }

        stage('Build Docker Images') {
            steps {
                echo "🐳 Building Docker images..."
                sh '''
                    cd Docker-deployment/app
                    docker build -t ecommerce-app .

                    cd ../mysql
                    docker build -t ecommerce-mysql .
                '''
            }
        }

        stage('Deploy using Docker Compose') {
            steps {
                echo " Deploying containers with Docker Compose..."
                sh '''
                    cd Docker-deployment

                    docker-compose down || true
                    docker-compose up -d --build
                '''
            }
        }
    }

    post {
        success {
            echo " Deployment complete!"
            echo "Application available at: http://<SERVER-IP>:8081/shopping-app/"
        }
        failure {
            echo " Deployment failed. Check Jenkins logs."
        }
    }
}
