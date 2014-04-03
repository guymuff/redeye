import groovy.json.JsonSlurper
import org.codehaus.groovy.grails.web.json.JSONObject

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

while(readline) {
    JSONObject root = slurper.parseText(readline)
        String line = "${root['AuthorId']},${root['ProductId']},${root['Rating']}\n"
        w.write(line)

        readline = r.readLine()

}



w.close()



