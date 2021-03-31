package com.minestom.gradle

import groovy.json.JsonSlurper

class HttpUtil {
    private static final def GITHUB_ENDPOINT = "https://api.github.com/repos/Minestom/Minestom/commits/master"

    static String getLatestCommitId() {
        def json = new JsonSlurper().parse(new URL(GITHUB_ENDPOINT))
        return json.sha.substring(0, 10)
    }
}
