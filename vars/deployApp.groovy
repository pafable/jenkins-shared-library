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
                        echo 'PET=dog' >> pet.txt
                        export PET=dog
                        echo 'AGE=1' >> pet.txt
                        echo 'LEGCOUNT=4' >> pet.txt
                        echo 'WAGGINGTAIL=yes' >> pet.txt
                    """

                    println "BUILD ID: ${env.BUILD_ID}"
                }
            }

            stage('this is test 2') {
                steps {
                    println "Workspace is: ${WORKSPACE}"

                    sh """
                        cat pet.txt
                    """
                }
            }
        }
    }
}