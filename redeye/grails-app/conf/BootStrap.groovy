import redeye.RecommenderService

class BootStrap {

    RecommenderService recommenderService

    def init = { servletContext ->
        recommenderService.init()
    }
    def destroy = {
    }
}
