package com.phil.jenkins.lib

enum DeploymentSettings {
    PROD(3, 'prod', true),
    QA(2, 'qa', false),
    DEV(1, 'dev', false)

    final int numberOfInstances
    final String name
    final boolean isPriority

    DeploymentSettings (int numberOfInstances, String name, boolean isPriority) {
        this.numberOfInstances = numberOfInstances
        this.name = name
        this.isPriority = isPriority
    }
}