pipeline {
    agent any

    stages {
        stage('Clone the project') {
            steps {
                git branch: 'main', url: 'https://github.com/Arthur488/MyFlow'
            }
        }

        stage('Build') {
            steps {
                withMaven(maven: 'apache-maven-3.8.1') {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Run Tests') {
            steps {
                // Здесь можно добавить шаги для запуска тестов (если есть)
                bat 'mvn test'
            }
        }

        stage('Deployment') {
            steps {
                // Здесь можно добавить шаги для развертывания приложения (если есть)
                bat 'java -jar target/WorkFlow-0.0.1-SNAPSHOT.jar'
            }
        }
    }
}
