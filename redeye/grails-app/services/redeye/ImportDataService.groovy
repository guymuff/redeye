package redeye

import groovy.json.JsonSlurper
import org.codehaus.groovy.grails.web.json.JSONObject

import java.text.DateFormat
import java.text.SimpleDateFormat

class ImportDataService {

    static String AUTHOR_TEXT = 'dove/authors.txt'

    def importAuthor() {
        if (Author.findAll().size() == 0) {
            JsonSlurper slurper = new JsonSlurper()
            Reader r = new FileReader(AUTHOR_TEXT)
            String readline = r.readLine()

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            while(readline) {
                JSONObject root = slurper.parseText(readline)

                Date lastModified = format.parse(root.LastModeratedTime)
                Date submissionTime = format.parse(root.SubmissionTime)
                Author author = new Author(nickName: root['UserNickname'], idString: root['Id'],
                        gender: root?.ContextDataValues?.Gender?.Value, location: root['Location'],
                        lastModeratedTime: lastModified, submissionTime: submissionTime
                ).save()

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

    def addAuthor() {
        Author newAuthor = new Author(idString: 'test')
        newAuthor.save(flush: true)
    }
}
