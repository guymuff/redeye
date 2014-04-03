package redeye

class Review {
    Author author
    Product product

    int rating
    int ratingRange

    String review
    Date submissionTime

    String response
    Date responseTime
    String responserName

    static constraints = {
        response nullable: true
        responseTime nullable: true
        responserName nullable: true
    }
}
