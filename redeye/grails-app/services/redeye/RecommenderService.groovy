package redeye

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity
import org.apache.mahout.cf.taste.model.DataModel
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood
import org.apache.mahout.cf.taste.recommender.RecommendedItem
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender
import org.apache.mahout.cf.taste.similarity.UserSimilarity
import org.apache.tools.ant.taskdefs.Classloader

class RecommenderService {

    UserBasedRecommender recommender

    public void init() {
        URL url = Classloader.getResource('/data.csv')
        File f = new File(url.toURI())
        DataModel model = new FileDataModel(f)
        UserSimilarity similarity = new EuclideanDistanceSimilarity(model)
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.5, similarity, model);
        recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
    }

    long[] recommendSimilarAuthors(long authorId, int numOfSimilarAuthors) {
        return recommender.mostSimilarUserIDs(authorId, numOfSimilarAuthors)
    }

    float starRatingEstimate(long authorID, long productID) {
        return recommender.estimatePreference(authorID, productID)
    }

    ProductRecommendation[] recommendProducts(long authorID, int howMany) {
        List<RecommendedItem> ris = recommender.recommend(authorID, howMany)

        ProductRecommendation[] ret = new ProductRecommendation[ris.size()]
        int i = 0
        for(RecommendedItem ri : ris) {
            ret[i++] = new ProductRecommendation(productID: ri.itemID, starRatingEstimate: ri.value)
        }

        return ret
    }

    class ProductRecommendation {
        long productID
        float starRatingEstimate
    }
}
