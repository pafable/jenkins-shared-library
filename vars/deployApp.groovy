import com.phil.jenkins.lib.PipelineMessages

def call(final Closure body) {
    body.resolveStrategy = Closure.DELEGATE_FIRST

    final String x = System.getProperty("user.dir")

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
                    time: 1,
                    unit: 'HOURS'
            )
        }

        stages {
            stage('this is a test 1') {
                steps {
                    sh 'printenv'
                    println PipelineMessages.DN
                    println "This is x: ${x}"
                    println "This is pwd(): ${pwd()}"
                    println "This is workspace: ${WORKSPACE}"

                    sh """
                        echo 'SAMPLE KEY PAIRS'
                        echo 'env.PET="dog"' >> pet.groovy
                        echo 'env.AGE="1"' >> pet.groovy
                        echo 'env.LEGCOUNT="4"' >> pet.groovy
                        echo 'env.WAGGINGTAIL="yes"' >> pet.groovy
                    """

                    println "BUILD ID: ${env.BUILD_ID}"

                    // Injecting environment variables
                    load "pet.groovy"
                    chuckNorris()
                }
            }

            stage('this is test 2') {
                steps {
                    sh 'ls -ahlrt'
                    println "Workspace is: ${WORKSPACE}"
                    println "Build ID: ${env.BUILD_ID}"

                    sh """
                        printenv
                        echo Tail wagging: ${env.WAGGINGTAIL}
                    """
                }
            }
        }
    }
}