
package redeye

class Product {

    String name
    String image_url
    String product_url
    String category_id
    BigDecimal overall_rating
    BigDecimal rating_range
    String product_id


    static constraints = {
        image_url nullable: true
        product_url nullable: true
        category_id nullable: true
        overall_rating nullable: true
        rating_range nullable: true
    }
}
