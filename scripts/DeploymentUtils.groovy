/**
 * @author: Askhat Salikhov
 */


String getEnvironmentByCNAME(String cname) {
    println "getEnvironmentByCNAME: ${cname}"
    return cname
}

/**
 * Runs periodical checks against specified environment until its deployment status is green
 * @param envName environment name
 */

void waitForGreen(String envName) {
    println "waitForGreen: ${envName}"
    sleep(10)
}

return this