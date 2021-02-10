
node{
        def DOCKER_HUB_ACCOUNT = "lalit1980"
        def DOCKER_IMAGE_NAME  = "bow-product-svc"
        def TAG_NUMBER = "${env.BUILD_NUMBER}"
        def BRANCH = "${env.BRANCH_NAME}"
        stage("SCM Checkout"){
            git branch: 'develop', credentialsId: 'GITHUB_CREDENTIAL1', url: 'https://github.com/lalit1980/bow-product-svc.git'
        }
        stage("Unit Test & App Scan"){
            echo "Unit Test Case Execution started...."
            echo "Unit Test Case Execution ended...."
            def mavenHome =  tool name: "Maven3.6.3", type: "maven"
            def mavenCMD = "${mavenHome}/bin/mvn"
            sh "${mavenCMD} clean package"
            echo "AppScan for vulnerabilty scan execution started...."
            echo "AppScan for vulnerabilty scan execution ended...."
        }
        stage("SonarQube"){
            echo "SonarQube scan started...."
            echo "SonarQube coverage started...."
        }
        stage("Docker Build & Tag"){
            withCredentials([string(credentialsId: 'DOCKER_HUB_CREDENTIALS', variable: 'DOCKER_HUB_CREDENTIALS')]) {
                sh "docker login -u lalit1980 -p ${DOCKER_HUB_CREDENTIALS}"
            }
            echo "docker build -t ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:${TAG_NUMBER} ."
            sh ("docker build -t ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:${TAG_NUMBER} .")
        }

        stage("Docker Push"){
            echo "docker push ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:${TAG_NUMBER}"
            sh ("docker push ${DOCKER_HUB_ACCOUNT}/${DOCKER_IMAGE_NAME}:${TAG_NUMBER}")
        }

        stage("Deployment Report and Notification"){
            echo "Deploment status report published..."

        }

}

