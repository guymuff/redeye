package redeye

import java.math.MathContext
import java.math.RoundingMode

class HomeController {

    def recommendService

    def index() {
    }
    def tester() {

    }

    def populateData(){
        def model = [:]
        List<ProductBundle> productBundleList = []
        def userId = params.UserIdInput
        Author input_user = Author.findById(Integer.parseInt(userId))


        Map<RecommenderService.ProductRecommendation, List<Review> > res = recommendService.getProductAndReviews(input_user)


        res.each {
            RecommenderService.ProductRecommendation pr, List<Review> reviewList ->
                Review review = reviewList.pop()
                Product product = Product.findById(pr.productID)
                BigDecimal overall = product.overall_rating.setScale(1, RoundingMode.CEILING)
                BigDecimal range = product.rating_range.setScale(1, RoundingMode.CEILING)
                BigDecimal estimate =((BigDecimal) pr.starRatingEstimate).setScale(1, RoundingMode.CEILING)

                productBundleList.add(new ProductBundle(
                        overall_rating: overall,
                        rating_range: range,
                        personalized_rating: estimate,
                        author:review.author.nickName,
                        author_location:review.author.location,
                        author_gender:review.author.gender,
                        review: review.review,
                        product_name: product.name,
                        image_url: product.image_url,
                        ))

        }

        /**
        ProductBundle pb = new ProductBundle(overall_rating: 4.3,
        rating_range: 5.0,
        personalized_rating: 4.2,
        author: 'Sung Ma',
        author_location: 'Texas',
        author_gender: 'Male',
        review: 'This deodorant smells great and lasts all day long.',
        product_name: 'Deodorant',
        image_url: 'http://www.dove.us/TD/Images/WhiteHouse_Medium_118x214_0008_Deo_Revive71-715974.png')
        productBundleList.add(pb)
        **/
        model['productbundle'] = productBundleList
        model['input_user'] = input_user
        render(view:"recommended",model:model)
    }

    class ProductBundle {
        BigDecimal overall_rating
        BigDecimal rating_range
        BigDecimal personalized_rating

        String author
        String author_location
        String author_gender

        String product_name
        String review

        String image_url

    }

}
