#!/usr/bin/env groovy

node {
    sh "ls -a > temp"

    def deployUtils = load("${workspace}@script/scripts/DeploymentUtils.groovy")

    deployUtils.waitForGreen("app-${env.BUILD_NUMBER}")

    println deployUtils.getEnvironmentByCNAME("hello world")
}