import com.phil.jenkins.lib.PipelineMessages

def call(final Closure body) {
    body.resolveStrategy = Closure.DELEGATE_FIRST

    final String x = System.getProperty("user.dir")
    final String y = WORKSPACE

    pipeline {
        agent any

        options {
            buildDiscarder (
                    logRotator (
                            daysToKeepStr: '60'
                    )
            )
            disableConcurrentBuilds()
            timeout (
                    time: 1,
                    unit: 'HOURS'
            )
        }

        stages {
            stage ('this is a test 1') {
                steps {
                    println PipelineMessages.DN
                    println "This is x: ${x}"
                    println "This is pwd(): ${pwd()}"
                    println "This is workspace: ${WORKSPACE}"

                    sh """
                        
                    """
                }
            }
        }
    }
}