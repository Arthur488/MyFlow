pipeline {
    agent any

    stages {
        stage('Clone the project') {
            steps {
                git branch: 'main', url: 'https://github.com/Arthur488/WorkFlow.git'
            }
        }

        stage('Build') {
            steps {
                withMaven(maven: 'apache-maven-3.6.1') {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Run Tests') {
            steps {
                // Здесь можно добавить шаги для запуска тестов (если есть)
                // Например: bat 'mvn test'
            }
        }

        stage('Deployment') {
            steps {
                // Здесь можно добавить шаги для развертывания приложения (если есть)
                // Например: bat 'java -jar target/WorkFlow-0.0.1-SNAPSHOT.jar'
            }
        }
    }
}
