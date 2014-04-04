<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title >Recommend Products</title>
    <r:require module="recommends"/>
</head>

<body>

<h1 style="padding-left: 1em; padding-top: 1em; font-size: 40px">Recommended Products</h1>
<div style="padding-left: 2.3em; padding-bottom: 1em; font-size: 18px">
    <p class="pr-review-author-name">Recommendation for User:&nbsp;<span>${input_user.nickName}</span></p>
    <g:if test="${input_user.location}">
         <p class="pr-review-author-location">from <span>${input_user.location}</span></p>
    </g:if>
</div>

<div class="pr-contents-wrapper">

    <ul>
        <g:each var="recommendProduct" in="${productbundle}">
            <div class="pr-review-wrap">
                <div class="pr-review-wrap">
                    <div class="pr-review-rating-wrapper" style="width:75%">
                        <div class="everyone_review">
                            <div class="pr-review-author-date" style="max-width:18%"><span style="font-size: x-large;">${recommendProduct.overall_rating} / ${recommendProduct.rating_range}</span> Over All</div>
                        </div>
                        <div class="targeted_review">
                            <div class="pr-review-author-date" style="max-width:18%"><span style="font-size: x-large;font-weight: bold">${recommendProduct.personalized_rating} / ${recommendProduct.rating_range}</span> Focused</div>
                        </div>
                        <div>
                            <span class="pr-product-name" style=" font-size:26px ">${recommendProduct.product_name}</span>
                            <div class="pr-stars pr-stars-small pr-stars-4-sm" style="background-position: 0px -144px;" title="That's good stuff">&nbsp;</div>

                        </div>
                    </div>
                    <div class="pr-review-author" style="width:25%">
                        <div class="pr-review-author-info-wrapper" style="padding-left: 15%">
                            <r:img uri="${recommendProduct.image_url}" alt="${recommendProduct.product_name}" height="200"/>
                            <p class="pr-review-author-name">By&nbsp;<span>${recommendProduct.author}</span></p>
                            <g:if test="${recommendProduct.author_location}">
                                <p class="pr-review-author-location">from <span>${recommendProduct.author_location}</span></p>
                            </g:if>
                            <g:if test="${recommendProduct.author_gender}">
                                <p class="pr-review-author-location">gender <span>${recommendProduct.author_gender}</span></p>
                            </g:if>
                        </div>
                    </div>
                    <div class="pr-review-main-wrapper" style="width:75%">
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