package redeye

import groovy.json.JsonSlurper
import org.codehaus.groovy.grails.web.json.JSONObject

class ImportDataService {

    static String AUTHOR_TEXT = '/Users/yi-ju.chung/Downloads/dove/authors.txt'

    def importAuthor() {
        if (Author.findAll().size() == 0) {
            JsonSlurper slurper = new JsonSlurper()
            Reader r = new FileReader(AUTHOR_TEXT)
            String readline = r.readLine()

            while(readline) {
                JSONObject root = slurper.parseText(readline)

                Author author = new Author(nickName: root['nickName'], idString: root['Id'],
                        gender: root?.ContextDataValues?.Gender?.Value, location: root['Location'],
                        lastModeratedTime: root['LastModeratedTime'], submissionTime: root['SubmissionTime']
                ).save()

                //String line = "${root['Id']},${root?.ContextDataValues?.Gender?.Value},${root['Location']}"
                //println line

                readline = r.readLine()

            }
        }
    }

    def listAllAuthorNameAndId() {

        List<Author> authors = Author.getAll()
        println authors.size()
        authors.each {
            println it.id + ',' + it.nickName
        }
    }
}
