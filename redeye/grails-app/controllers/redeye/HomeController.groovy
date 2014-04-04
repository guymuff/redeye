package redeye

class HomeController {

    def index() {
    }
    def tester() {

    }

    def populateData(){
        def userId = params.UserIdInput
        List<Review> reviewList1 = []
        List<Review> reviewList2 = []
        def model = [:]
        def productList = [:]

        Product p0 = Product.findById(1)
        Product p1 = Product.findById(2)
        Review r0 = new Review(review:"Hello, this is nice product.")
        Review r1 = new Review(review:"LuLu LaLa")
        Review r2 = new Review(review:"UNbelievable.")
        Review r3 = new Review(review:"LuLu LaLa")

        reviewList1.add(r0)
        reviewList1.add(r1)
        reviewList2.add(r2)
        reviewList2.add(r3)

        productList[p0] = reviewList1
        productList[p1] = reviewList2

        render(view:"recommended",model:model)
    }

    class ProductBundle {
        ProductBundle

    }

}
