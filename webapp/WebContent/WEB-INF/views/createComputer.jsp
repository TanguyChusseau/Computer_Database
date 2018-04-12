<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard?page=1"> Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Create a Computer</h1>
                    <form action="createComputer" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="computerName" min="1970-01-01" placeholder="Computer name" required>
                                <span style="color: #900">${createComputerErrors['computerName']}</span>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduction date</label>
                                <input type="date" class="form-control" id="introductionDate" name="introductionDate" min="1970-01-01" placeholder="Introduction date" required>
                                <span style="color: #900">${createComputerErrors['introductionDate']}</span>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinuation date</label>
                                <input type="date" class="form-control" id="discontinuationDate" name="discontinuationDate" placeholder="Discontinuation date">
                                <span style="color: #900">${createComputerErrors['discontinuationDate']}</span>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId">
                                <option value="0">--</option>
	                                <c:forEach items="${companiesList}" var="company">
	                                    <option value="${company.companyId}">${company.companyName}</option>
	                                </c:forEach>
                                </select>
                                <span style="color: #900">${createComputerErrors['companyId']}</span>
                            </div>                    
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Create" id="createButton" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    <script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/createComputer.js"></script>

	 <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
</body>
</html>