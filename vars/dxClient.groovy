def call(Map config = [:]) {

// should pass config file in
// could also use a library specific resource?
    configFileProvider(
        [configFile(fileId: 'dx-targets.yaml', variable: 'DXCLIENT_SETTINGS')]) {
        echo "${DXCLIENT_SETTINGS}"
    }

}