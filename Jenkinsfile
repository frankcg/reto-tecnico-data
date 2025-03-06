pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = '73191639'
        IMAGE_NAME = 'migraciones_poc'
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Java Project') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}", '.')
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                        docker.image("${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}").push()
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'kubeconfig-secret', variable: 'KUBECONFIG')]) {
                        sh "kubectl version --client --kubeconfig=$KUBECONFIG"
                        sh "kubectl apply -f k8s.yaml --kubeconfig=$KUBECONFIG"
                        sh "kubectl set image deployment/migraciones-poc-deployment migraciones-poc-container=${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG} --kubeconfig=$KUBECONFIG -n default"
                    }
                }
            }
        }
    }
}
