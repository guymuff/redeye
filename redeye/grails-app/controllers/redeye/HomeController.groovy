package redeye

class HomeController {

    def recommendService

    def index() {
    }
    def tester() {

    }

    def populateData(){
        def userId = params.UserIdInput
        Author author = Author.findById((int)userId)
        Map<RecommenderService.ProductRecommendation, List<Review> > res = recommendService.getProductAndReviews(author)




        def model = [:]
        List<ProductBundle> productBundleList = []

        /**
        Product p0 = Product.findById(20)
        Product p1 = Product.findById(21)
        Review r0 = new Review(review:"Hello, this is nice product.")
        Review r1 = new Review(review:"I saw this soap on the Dove website and thought hmmm maybe that soap will not make my skin so itchy. Every winter I get horribly dry skin, in bed I would just itch no matter what lotion I used. And if anyone who has skin like me, it's highly irritating, well not anymore, I bought and used this soap and for the first time I did not itch like a mad man! My skin was calm and smooth, usually I get red blotchy type rashes on my forearms, and it was gone. HEAVENLY! I recommend for anyone with extremely dry skin or eczema, or psoriasis, to buy this soap.")
        Review r2 = new Review(review:"UNbelievable.")
        Review r3 = new Review(review:"I have been using the Dove Aerosol Sprays for years by stocking up on them every time I go abroad and having my cousin's boyfriend ship it to me from France. It is amazing, 24 and 48 hours protection, no white marks, and a variety of nice light scent. I was so excited about it coming to America, but this is a HUGE DISAPPOINTMENT! Not only is the packaging ugly (the contoured can that fits nicely in your hand from Europe is perfect) but the smell is bad (no variety). I also think the formula may have been changed; I don't think it has the same 24- 48 hour protection as the stuff I get abroad (but maybe that is just because I was so turned off by the smell). Why ruin a great product? All you had to do was bring the same can/ product that already sells well abroad and put it on selves in America.")
        returnMap[p0] = [r0, r1]
        returnMap[p1] = [r2, r3]
        **/
        res.each {
            RecommenderService.ProductRecommendation pr, List<Review> reviewList ->
                Review review = reviewList.pop()
                Product product = Product.findById(pr.productID)
                productBundleList.add(new ProductBundle(
                        overall_rating: product.overall_rating,
                        rating_range: product.rating_range,
                        personalized_rating: pr.starRatingEstimate,
                        author:review.author.nickName,
                        author_location:review.author.location,
                        review: review.review,
                        product_name: product.name,
                        image_url: product.image_url))

        }
        model['productbundle'] = productBundleList
        render(view:"recommended",model:model)
    }

    class ProductBundle {
        int overall_rating
        int rating_range
        int personalized_rating

        String author
        String author_location

        String product_name
        String review

        String image_url
    }

}
