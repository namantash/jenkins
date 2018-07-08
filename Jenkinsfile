#!/usr/bin/env groovy

node {
    def deployUtils = load("${workspace}@script/scripts/DeploymentUtils.groovy")

    deployUtils.waitForGreen("app-${env.BUILD_NUMBER}")
}