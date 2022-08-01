import com.phil.jenkins.lib.PipelineMessages

def call(final Closure body) {
    pipeline {
        agent any
        options {
            buildDiscarder(
                    logRotator(
                            daysToKeepStr: '60'
                    )
            )
            disableConcurrentBuilds()
            timeout(
                    time: 30,
                    unit: 'MINUTES'
            )
        }

        stages {
            stage('deploying') {
                environment {
                    UNUSED = 'aws-dev-creds'
                }
                steps {
                    // Terraform files need to be in a directory named 'terraform'
                    dir('terraform') {
                        script {
                            println "USING TERRAFORM TO DEPLOY..."

                            sh """
                                terraform plan -out plan
                                terraform apply plan
                            """
                        }
                    }
                }
            }

            stage('cleanuo') {
                steps {
                    println PipelineMessages.DN
                }
            }
        }
    }
}
