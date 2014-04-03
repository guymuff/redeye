package redeye

class ImportDataController {

    def importDataService

    def index() {
        render text: 'OK'
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
