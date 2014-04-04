package redeye

class HomeController {

    def index() {
    }
    def tester() {

    }

    def populateData(){
        def userId = params.UserIdInput
        def model = [:]
        List<ProductBundle> productBundleList = []


        Product p0 = Product.findById(20)


        Product p1 = Product.findById(21)
        Review r0 = new Review(review:"Hello, this is nice product.")
        Review r1 = new Review(review:"LuLu LaLa")
        Review r2 = new Review(review:"UNbelievable.")
        Review r3 = new Review(review:"LuLu LaLa")

        def returnMap = [:]
        returnMap[p0] = [r0, r1]
        returnMap[p1] = [r2, r3]

        returnMap.each {
            Product product, List<Review> reviewList ->
                Review review = reviewList.pop()

                productBundleList.add(new ProductBundle(
                        overall_rating: product.overall_rating,
                        rating_range: product.rating_range,
                        personalized_rating: 4.5,
                        author:'Yi-Ju Chung',
                        author_location:'CA',
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
