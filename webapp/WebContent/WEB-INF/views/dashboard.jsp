<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="custom" uri="/WEB-INF/pagination.tld"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> <spring:message code="App.Title" /> </a>
            <div class="pull-right">
            	<a href="dashboard?locale=fr">FR</a>
            	<a href="dashboard?locale=en">EN</a>
			</div>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">${pageData.numberOfComputers} <spring:message code="label.numberOfComputersFound" /> </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">
                        <input type="search" id="searchbox" name="computerFilteredByName" class="form-control" placeholder="<spring:message
                            code="label.placeholderSearch" />" />
                        <input type="submit" id="searchsubmit" value="<spring:message
                            code="label.searchButton" />"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="createComputer" href="createComputer"><spring:message
                            code="label.addComputerButton" /></a>
                    <a class="btn btn-default" id="deleteComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message
                            code="label.editButton" /></a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="dashboard" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
						<th><a
							href="dashboard?limit=${pageData.limit}&search=${pageData.search}&page=${pageData.currentPage}"><spring:message
                                code="label.ComputerName" /></a></th>
						<th><a
							href="dashboard?limit=${pageData.limit}&search=${pageData.search}&page=${pageData.currentPage}"><spring:message
                                code="label.IntroductionDate" /></a></th>
						<th><a
							href="dashboard?limit=${pageData.limit}&search=${pageData.search}&page=${pageData.currentPage}"><spring:message
                                code="label.DiscontinuationDate" /></a></th>
						<th><a
							href="dashboard?limit=${pageData.limit}&search=${pageData.search}&page=${pageData.currentPage}"><spring:message
                                code="label.Company" /></a></th>
                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                    <c:forEach items="${pageData.dataList}" var="computer">
                        <tr>
                            <td class="editMode"><input type="checkbox" name="cb" class="cb" value="${computer.computerId}"></td>
                            <td><a href="updateComputer?computerId=${computer.computerId}" onclick="">${computer.computerName}</a></td>
                            <td>${computer.computerIntroductionDate}</td>
                            <td>${computer.computerDiscontinuationDate}</td>
                            <td>${computer.companyName}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
    
    <footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<custom:pagination maxPage="${pageData.maxPage}"
					currentPage="${pageData.currentPage}" limit="${pageData.limit}"
					search="${pageData.search}" />
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<input type="button" class="btn btn-default" onclick="location.href='dashboard?limit=10&search=${pageData.search}&page=1'" value="10" />
				<input type="button" class="btn btn-default" onclick="location.href='dashboard?limit=50&search=${pageData.search}&page=1'" value="50" />
				<input type="button" class="btn btn-default" onclick="location.href='dashboard?limit=100&search=${pageData.search}&page=1'" value="100" />
			</div>
		</div>
    </footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>