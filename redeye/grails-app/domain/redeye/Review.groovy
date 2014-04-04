package redeye

class Review {
    Author author
    Product product

    int rating
    int ratingRange

    String review
    Date submissionTime

    static mapping = {
        review type: 'text', sqlType: 'text'
    }
}
