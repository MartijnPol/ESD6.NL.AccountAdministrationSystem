node("docker") {
    docker.withRegistry('localhost:5000') {

        stage "build"
        def app = docker.build "AccountAdministrationSystem"

        stage "publish"
        app.push 'release'
        app.push "${commit_id}"
    }
}