import groovy.json.JsonSlurper
import org.codehaus.groovy.grails.web.json.JSONObject

import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * Created with IntelliJ IDEA.
 * User: guy.muff
 * Date: 4/3/14
 * Time: 7:30 AM
 */

def REVIEWS_PATH = '/Users/guy.muff/Downloads/dove/reviews.txt'


JsonSlurper slurper = new JsonSlurper()
Reader r = new FileReader(REVIEWS_PATH)
String readline = r.readLine()

FileWriter w = new FileWriter('ratings.csv')

//LastModificationTime=2012-05-31T05:19:56.000+00:00
DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")

while (readline) {
    JSONObject root = slurper.parseText(readline)

    Date lastModified = format.parse(root.LastModificationTime)
    String line = "${root['AuthorId']},${root['ProductId']},${root['Rating']},${lastModified.time}\n"
    w.write(line)

    readline = r.readLine()

}



w.close()



