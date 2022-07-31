def call(final Closure body) {
    pipeline {
        agent any

        options {
            buildDiscarder(
                    logRotator(
                            daysToKeepStr: '60'
                    )
            )
            disableConcurrentBuild()
            timeout(
                    timeout: 30,
                    unit: 'MINUTES'
            )
        }

        stages {
            stage('STAGE 1') {
                steps {
                    println "Deploying with terraform"
                    sh """
                        terraform plan -out plan
                
                    """
                }
            }

            stage('STAGE 2') {
                steps {

                }
            }
        }
    }
}
