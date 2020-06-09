pipeline {
    agent any
    environment {
        PATH = "$PATH:/usr/local/bin"
    }
    tools {
        maven 'apache-maven-3.6.1'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                git credentialsId: 'github', url: 'https://github.com/NathalieSong/FSD-Stachathon-Backend-User-Service.git'
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage('Docker Build') {
            steps {
                echo 'Docker Building...'
                sh 'docker-compose down'
                sh 'docker image rm emart-user-service'
                sh 'docker build . -t emart-user-service'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                sh 'docker-compose up -d'
            }
        }
    }
}