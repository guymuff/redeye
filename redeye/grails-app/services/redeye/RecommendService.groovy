package redeye

class RecommendService {
    def recommenderService

    static int SIMILIAR_AUTHOR = 50
    static int SIMILIAR_PRODUCT = 20

    Map<RecommenderService.ProductRecommendation, List<Review> > getProductAndReviews(Author me) {
        Map<RecommenderService.ProductRecommendation, List<Review> > results = [:]

        List<RecommenderService.ProductRecommendation> productIds = recommenderService.recommendProducts(me.id, SIMILIAR_PRODUCT)
        List<Long> authorIds = recommenderService.recommendSimilarAuthors(me.id, SIMILIAR_AUTHOR)

        productIds.each {
            RecommenderService.ProductRecommendation pr ->
                Product product = Product.findById(pr.productID)
                List<Review> reviews = new LinkedList<Review>()

                authorIds.each {
                    Long aid ->
                        Author author = Author.findById aid
                        if(author){
                            reviews.addAll(Review.findAllByAuthorAndProduct(author, product))
                        }
                }

                if(reviews){
                    results.put(pr,reviews)
                }
        }

        results
    }
}
