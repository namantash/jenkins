#!/usr/bin/env groovy

node {
    sh "apt-get updater"
    sh "apt-get install python"

    def deployUtils = load("${workspace}@script/scripts/DeploymentUtils.groovy")

    deployUtils.waitForGreen("app-${env.BUILD_NUMBER}")

    println deployUtils.getEnvironmentByCNAME("hello world")
}