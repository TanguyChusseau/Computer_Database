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
            <a class="navbar-brand" href="dashboard?page=1"> Home Page </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${selectedComputer.computerId}
                    </div>
                    <h1>Update Computer</h1>

                    <form action="updateComputer" method="POST">
                        <input type="hidden" id="computerId" name="computerId" value="${selectedComputer.computerId}" /> 
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer's name</label>
                                <input type="text" class="form-control" id="computerName" name="computerName" value="${selectedComputer.computerName}" required >
                                <span style="color: #900">${updateComputerErrors['selectedComputer.computerName']} </span>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduction date</label>
                                <input type="date" class="form-control" id="introductionDate" name="introductionDate" min="1970-01-01" value="${selectedComputer.computerIntroductionDate}" required >
                                <span style="color: #900">${updateComputerErrors['selectedComputer.introductionDate']} </span>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinuation date</label>
                                <input type="date" class="form-control" id="discontinuationDate" name="discontinuationDate" min="1970-01-01" value="${selectedComputer.computerDiscontinuationDate}" > 
                                <span style="color: #900">${updateComputerErrors['selectedComputer.discontinuationDate']} </span>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId" >
                                	<option selected="selected" value="${selectedComputer.companyId}">${selectedComputer.companyName} </option>
	                                <c:forEach items="${companiesList}" var="company" >
	                                    <option value="${company.companyId}">${company.companyName} </option>
	                                </c:forEach>
                                </select>
                                <span style="color: #900">${updateComputerErrors['selectedComputer.companyId']} </span>
                            </div>           
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Update" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>