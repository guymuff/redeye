package redeye

class ImportDataController {

    def importDataService

    def index() {
    }

    def importAuthor() {
        importDataService.importAuthor()
        render text: 'OK'
    }

    def listAuthor() {
        importDataService.listAllAuthorNameAndId()
        render text: 'OK'
    }
}
