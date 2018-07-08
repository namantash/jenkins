import groovy.json.JsonSlurper

/**
 * @author: Askhat Salikhov
 */

/**
 * should be same with .elasticbeanstalk/config.xml/application_name
 */
APPLICATION_NAME = 'core-server'

/**
 * Retrieves environment name by its cname using AWS ElasticBeanstalk CLI
 * @param cname environment cname                                          x
 * @return environment name
 */

String getEnvironmentByCNAME(String cname) {
    final def jsonSlurper = new JsonSlurper()
    

    println cname
    
/*
    Optional<String> envName = ((List<Map<String, Object>>)((Map) response).get("Environments")).stream()
            .filter({it.get("CNAME").toString().startsWith(cname + ".")})
            .map({it -> it.get("EnvironmentName").toString()})
            .findAny()

    if (envName.isPresent()) {
        return envName.get();
    } else {
        //assert false : "Environment not found"
    }
*/
    return null
}

/**
 * Runs periodical checks against specified environment until its deployment status is green
 * @param envName environment name
 */

void waitForGreen(String envName) {
    final def jsonSlurper = new JsonSlurper()

    println envName
}

return this