<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title >Insert UserID for Recommendation</title>
    <r:require module="recommends"/>
</head>

<body>

<h1 style="padding-left: 1em; padding-top: 1em; font-size: 40px">Insert UserID for Recommendation</h1>

<div class="pr-contents-wrapper">

    <ul>
        <g:form name="testForm" controller="home" action="index">
            <g:textField name="UserIdInput" value="${UserIdInput}">  </g:textField>
            <g:actionSubmit value="Accio Recommendation"  action="populateData"/>
        </g:form>
    </ul>
</div>
</body>
</html>
