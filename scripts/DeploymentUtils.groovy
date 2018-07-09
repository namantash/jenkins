import groovy.json.JsonSlurperClassic
import jenkins.model.Jenkins
/**
 * @author: Askhat Salikhov
 */

/**
 * should be same with .elasticbeanstalk/config.xml/application_name
 */
//APPLICATION_NAME = 'core-server'

/**
 * Retrieves environment name by its cname using AWS ElasticBeanstalk CLI
 * @param cname environment cname
 * @return environment name
 */

void approveScript() {
    def extension = Jenkins.instance
            .getExtensionList(org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval.class)[0];
    extension.approveScript("scripts/DeploymentUtils.groovy")
    extension.approveScript("scripts/DeploymentUtils")
}

String getEnvironmentByCNAME(String cname) {
    final String responseStr = sh (
            script: "aws elasticbeanstalk describe-environments --application-name core-server",
            returnStdout: true).trim()

    def response = parse(responseStr)

    Optional<String> envName = ((List<Map<String, Object>>)((Map) response).get("Environments")).stream()
            .filter({it.get("CNAME").toString().startsWith(cname + ".")})
            .map({it -> it.get("EnvironmentName").toString()})
            .findAny()

    if (envName.isPresent()) {
        return envName.get();
    } else {
        assert false : "Environment not found"
    }
}

/**
 * Runs periodical checks against specified environment until its deployment status is green
 * @param envName environment name
 */

void waitForGreen(String envName) {
    final int secToSleep = 10

    final timeoutMillis = System.currentTimeMillis() + (120 * 1000)

    while (true) {
        final String responseStr = sh (
                script: "aws elasticbeanstalk describe-environments --application-name core-server",
                returnStdout: true).trim()

        def response = parse(responseStr)

        Optional<String> envHealth = ((List<Map<String, Object>>)((Map) response).get("Environments")).stream()
                .filter({it.get("EnvironmentName").toString().equalsIgnoreCase(envName)})
                .map({it -> it.get("Health").toString()})
                .findAny()

        if (envHealth.isPresent()) {
            if (envHealth.get().equalsIgnoreCase("green")) {
                println "Environment is up and running"
                return;
            } else {
                println "Current health color ${envHealth.get()} -- will check back in ${secToSleep} ..."
            }
        } else {
            assert false : "Environment not found"
        }

        assert System.currentTimeMillis() < timeoutMillis :  "Application launch timeout"

        sleep(secToSleep * 1000)
    }
}

/**
 * Jenkins requires objects to be serializable -- workaround
 * https://issues.apache.org/jira/browse/GROOVY-6934
 *
 * @param json
 * @return
 */
@NonCPS
static def parse(String json) {
    new JsonSlurperClassic().parseText(json)
}

return this