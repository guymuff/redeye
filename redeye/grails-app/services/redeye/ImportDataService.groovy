package redeye

import groovy.json.JsonSlurper
import org.codehaus.groovy.grails.web.json.JSONObject

import java.text.DateFormat
import java.text.SimpleDateFormat

class ImportDataService {

    static String AUTHOR_TEXT = 'dove/authors.txt'
    static String PRODUCT_TEXT = 'dove/products.txt'
    static String REVIEW_TEXT = 'dove/reviews.txt'

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

            log.info Author.getAll().size()+" authors imported !!"
        }
    }

    def importProduct() {
        if (Product.findAll().size() == 0) {
            JsonSlurper slurper = new JsonSlurper()
            Reader r = new FileReader(PRODUCT_TEXT)
            String readline = r.readLine()

            while(readline) {
                JSONObject root = (JSONObject) slurper.parseText(readline)
                JSONObject ratings = (JSONObject) root['ReviewStatistics']
                if(root['Name'] && root['Id']){
                    Product product = new Product(
                            name: root['Name'],
                            image_url:root['ImageUrl'],
                            product_url:root['ProductPageUrl'],
                            category_id: root['CategoryId'],
                            product_id: root['Id'],
                            overall_rating: ratings['AverageOverallRating'],
                            rating_range: ratings['OverallRatingRange'])
                    product.save()
                }
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

    def importReview() {
        if (Review.findAll().size() == 0) {
            JsonSlurper slurper = new JsonSlurper()
            Reader r = new FileReader(REVIEW_TEXT)
            String readline = r.readLine()

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            while(readline) {
                JSONObject root = slurper.parseText(readline)

                Author author = Author.findByIdString(root.AuthorId)
                Product product = Product.findByProduct_id(root.ProductId)
                if (author && product) {
                    Date responseTime = null
                    String response = null
                    String responseName = null
                    if (root.ClientResponses?.Date)
                        responseTime = format.parse(root.ClientResponses?.Date)
                    if (root.ClientResponses?.Response)
                        response = root.ClientResponses?.Response
                    if (root.ClientResponses?.Name)
                        responseName = root.ClientResponses?.Name
                    Date submissionTime = format.parse(root.SubmissionTime)
                    Review review = new Review( author: author, product: product,review: root.ReviewText,
                            rating: root.Rating, ratingRange: root.RatingRange,
                            submissionTime: submissionTime, responseTime: responseTime,
                            response: response, responserName: responseName
                    ).save()
                }

                readline = r.readLine()
            }

            log.info Review.getAll().size()+" reviews imported !!"
        }
    }
}
