modules = {
    application {
        resource url:'js/application.js'
    }
    recommends {
        dependsOn 'application'
        resource url: 'css/recommends.css'
    }
}