#!/usr/bin/env groovy

node {
    def deployUtils = load('scripts/DeploymentUtils.groovy')

    deployUtils.waitForGreen("app-${env.BUILD_NUMBER}")
}