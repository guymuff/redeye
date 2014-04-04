<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title >Recommend Products</title>
    <r:require module="recommends"/>
</head>

<body>

<h1 style="padding-left: 1em; padding-top: 1em; font-size: 40px">Recommended Products</h1>

<div class="pr-contents-wrapper">

    <ul>
        <g:each var="recommendProduct" in="${productbundle}">


        %{--
    <% for (var review_index = 0; review_index < reviews.length; review_index++) {
    var review = reviews[review_index];
    var groupSet = {};
    var hasOtherAttributes = false;
    if (review.g) {
        for (var i = 0; i < review.g.length; i++) {
            var key = review.g[i].k;
            groupSet[key] = true;
            hasOtherAttributes = hasOtherAttributes || (key != 'pros' && key != 'cons' && key != 'bestuses' && key != 'describeyourself');
        }
    }%>
    --}%
            <div class="pr-review-wrap">
                <div class="pr-review-wrap">
                    <div class="pr-review-rating-wrapper">
                        <div class="everyone_review">
                            <div class="pr-review-author-date" style="max-width:20%"><span style="font-size: x-large;">${recommendProduct.overall_rating}/${recommendProduct.rating_range}</span> Over All</div>
                        </div>
                        <div class="targeted_review">
                            <div class="pr-review-author-date" style="max-width:20%"><span style="font-size: x-large;font-weight: bold">${recommendProduct.personalized_rating}/${recommendProduct.rating_range}</span> Focused</div>
                        </div>
                        <div class="pr-review-rating">
                            <div class="pr-stars pr-stars-small pr-stars-4-sm" style="background-position: 0px -144px;" title="That's good stuff">&nbsp;</div>
                            <p class="pr-review-rating-headline">${recommendProduct.product_name}</p>
                        </div>
                    </div>
                    <div class="pr-review-author">
                        <div class="pr-review-author-info-wrapper">
                            <r:img uri="${recommendProduct.image_url}" alt="${recommendProduct.product_name}" height="280"/>
                            <p class="pr-review-author-name">By&nbsp;<span>${recommendProduct.author}</span></p>
                            <p class="pr-review-author-location">from <span>${recommendProduct.author_location}</span></p>
                        </div>
                    </div>
                    <div class="pr-review-main-wrapper">
                        <div class="pr-review-text">
                            <p class="pr-comments-header"> ${recommendProduct.review} <em><span class="pr-product-name"></span></em></p>
                        </div>
                        <div class="pr-review-footer">
                            <div class="pr-review-tools">
                                <p class="pr-review-helpful-text">Was this review helpful?&nbsp;
                                    <a data-pr-event="review-helpful-yes-link" class="pr-review-helpful-text-link" href="#" onclick="POWERREVIEWS.display.submitHelpfulVote('42755281', 'helpful', 'en_US', POWERREVIEWS.common.getOptions('engine-1-en_US')); return false;">Yes</a>&nbsp;/&nbsp;
                                    <a data-pr-event="review-helpful-no-link" class="pr-review-helpful-text-link" href="#" onclick="POWERREVIEWS.display.submitHelpfulVote('42755281', 'unhelpful', 'en_US', POWERREVIEWS.common.getOptions('engine-1-en_US')); return false;">No</a></p>
                                <p class="pr-review-report-issue"><span class="pr-review-report-issue-hyphen">&nbsp;-&nbsp;</span>You may also
                                    <a href="#" data-pr-event="review-report-issue-link" class="pr-review-helpful-text-link" onclick="POWERREVIEWS.display.launchErrorDiv('42755281', 'en_US', 'engine-1-en_US', POWERREVIEWS.common.getOptions('engine-1-en_US')); return false;">flag this review</a>
                                </p>
                                <div class="pr-error" id="error_div42755281" style=""></div>
                                <p class="pr-review-helpful-response" id="review_feedback42755281"></p>
                            </div>
                        </div>
                    </div>
                    <div class="pr-clear"></div>
                </div>
            </div>
        </g:each>
    </ul>
</div>
</body>
</html>