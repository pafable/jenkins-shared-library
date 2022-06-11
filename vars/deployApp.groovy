import com.phil.jenkins.lib.PipelineMessages

def call(final Closure body) {
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
                println deez()
            }
        }
    }
}