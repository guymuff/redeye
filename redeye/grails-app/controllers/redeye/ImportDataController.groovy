package redeye

class ImportDataController {

    def importDataService

    def index() {
    }

    def importAuthor() {
        importDataService.importAuthor()
    }

    def listAuthor() {
        importDataService.listAllAuthorNameAndId()
    }
}
