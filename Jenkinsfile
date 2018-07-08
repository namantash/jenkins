#!/usr/bin/env groovy

node {
    def awsResult = sh (
        script: 'ls -lah',
        returnStdout: true).trim()

    def deployUtils = load("${workspace}@script/scripts/DeploymentUtils.groovy")

    deployUtils.test()
}