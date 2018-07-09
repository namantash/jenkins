#!/usr/bin/env groovy

node {
    def deployUtils = load("${workspace}@script/scripts/DeploymentUtils.groovy")

    def echo = sh(script:./test.sh, returnStdout: true)

    println "this is from sh: ${echo}"

    //deployUtils.getEnvironmentByCNAME("sdfsfsdf")
}