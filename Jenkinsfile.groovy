pipeline {
    agent any

    environment {
        JAVA_TOOL_OPTIONS = "-Dfile.encoding=UTF-8"
    }

    stages {
        stage('Clone the project') {
            steps {
                git branch: 'master', url: 'https://github.com/Arthur488/MyFlow'
            }
        }

        stage('Build') {
            steps {
                withMaven(maven: 'apache-maven-3.9.2') {
                    bat 'mvn clean package -DskipTests'
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
//                bat 'java -version'
                bat 'cd ..'

                bat 'java -jar C:\\ProgramData\\Jenkins\\.jenkins\\workspace\\MySpringBootApp\\target\\WorkFlow-0.0.1-SNAPSHOT.jar\n'
            }
        }
    }
}
