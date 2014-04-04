package redeye

import groovy.json.JsonSlurper
import org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin
import org.codehaus.groovy.grails.support.SoftThreadLocalMap
import org.codehaus.groovy.grails.web.json.JSONObject
import org.hibernate.Session

import java.text.DateFormat
import java.text.SimpleDateFormat

class ImportDataService {

    static String AUTHOR_TEXT = 'dove/authors.txt'
    static String PRODUCT_TEXT = 'dove/products.txt'
    static String REVIEW_TEXT = 'dove/reviews.txt'

    def sessionFactory

    def importAuthor() {
        if (Author.findAll().size() == 0) {
            JsonSlurper slurper = new JsonSlurper()
            Reader r = new FileReader(AUTHOR_TEXT)
            String readline = r.readLine()

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            int i = 0
            while(readline) {
                JSONObject root = slurper.parseText(readline)

                Date lastModified = format.parse(root.LastModeratedTime)
                Date submissionTime = format.parse(root.SubmissionTime)
                Author author = new Author(nickName: root['UserNickname'], idString: root['Id'],
                        gender: root?.ContextDataValues?.Gender?.Value, location: root['Location'],
                        lastModeratedTime: lastModified, submissionTime: submissionTime
                ).save()

                i++

                if(i%100 == 0) {
                    Session session = sessionFactory.getCurrentSession()
                    session.flush()
                    session.clear()
                    SoftThreadLocalMap propertyInstanceMap = DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP
                    propertyInstanceMap.get().clear()
                }

                readline = r.readLine()
            }

            println Author.getAll().size()+" authors imported !!"
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
            int i = 0
            while(readline) {
                JSONObject root = slurper.parseText(readline)

                Author author = Author.findByIdString(root.AuthorId)
                Product product = Product.findByProduct_id(root.ProductId)

                if(!author) {
                    Date submissionTime = format.parse(root['SubmissionTime'])   // TODO this not 100% accurate in some cases
                    Date moderatedTime = format.parse(root['LastModeratedTime'])
                    author = new Author(idString: root['AuthorId'], nickName: root['AuthorId'],
                            lastModeratedTime: moderatedTime, submissionTime: submissionTime)
                            // TODO this may be later than the last submission time on the existing author
                    author.save()
                }

                if (author && product) {
                    Date submissionTime = format.parse(root.SubmissionTime)
                    String text = root.ReviewText
                    text.trim()
                    Review review = new Review( author: author, product: product,review: text,
                            rating: root.Rating, ratingRange: root.RatingRange,
                            submissionTime: submissionTime
                    ).save()
                    i++

                    if(i%100 == 0) {
                        Session session = sessionFactory.getCurrentSession()
                        session.flush()
                        session.clear()
                        SoftThreadLocalMap propertyInstanceMap = DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP
                        propertyInstanceMap.get().clear()
                    }
                }

                readline = r.readLine()
            }

            println Review.getAll().size()+" reviews imported !!"
        }
    }
}
