def printConfig() {
    echo 'Configuration:'
    echo "   dxWPSCredentials: ${config.dxWPSCredentials}"
    echo "   dxWASCredentials: ${config.dxWASCredentials}"
    echo "   dxProtocol: ${config.dxProtocol}"
    echo "   dxConnectProtocol: ${config.dxConnectProtocol}"
    echo "   hostname: ${config.hostname}"
    echo "   dxPort: ${config.dxPort}"
    echo "   dxSoapPort: ${config.dxSoapPort}"
    echo "   dxConnectPort: ${config.dxConnectPort}"
    echo "   dxContextRoot: ${config.dxContextRoot}"
    echo "   contenthandlerPath: ${config.contenthandlerPath}"
}

def call(Map config = [:]) {
    Map config = [:]

    configFileProvider([configFile(fileId: 'dx-targets.yaml', variable: 'DXCLIENT_SETTINGS')]) {
        def fileConfig = readYaml(file: $DXCLIENT_SETTINGS)

        fileConfig.environments.each { envName, envConfig ->
            // Print the branch value

            echo "Environment: ${envName}, Branch: ${envConfig.branch} GIT_BRANCH: ${env.GIT_BRANCH}"

            if ('origin/' + envConfig.branch == env.GIT_BRANCH) {
                echo "EnvConfig: ${envConfig}"
                echo "Loading configuration for ${envName}"
                config['dxWPSCredentials'] = envConfig.dxWPSCredentials
                config['dxWASCredentials'] = envConfig.dxWASCredentials
                config['dxProtocol'] = envConfig.dxProtocol
                config['dxConnectProtocol'] = envConfig.dxConnectProtocol
                config['hostname'] = envConfig.hostname
                config['dxPort'] = envConfig.dxPort
                config['dxSoapPort'] = envConfig.dxSoapPort
                config['dxConnectPort'] = envConfig.dxConnectPort
                config['dxContextRoot'] = envConfig.dxContextRoot
                config['contenthandlerPath'] = envConfig.contenthandlerPath

                printConfig()
            }
        }
    }

    return config
}
