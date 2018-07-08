#!/usr/bin/env groovy

node {
    def instanceType = 't2.large'

    sh "eb create paktor-${env.BUILD_NUMBER} --instance_type ${instanceType} --instance_profile core-server --cname paktor-test-${env.BUILD_NUMBER}"

    deployUtils.waitForGreen("paktor-${env.BUILD_NUMBER}")
}