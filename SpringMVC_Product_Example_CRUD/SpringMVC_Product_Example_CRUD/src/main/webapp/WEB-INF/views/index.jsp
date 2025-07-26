<html>
<head>
<%@include file="./base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <h1 class="text-center mb-3">Welcome to the Product Management System</h1>
            <table class="table">
                <thead class="table-dark">
                    <tr>
                        <th scope="col">Sr.No.</th>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Price</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>

                <c:forEach var="product" items="${products}">
                    <tr>
                        <th scope="row">${product.id}</th>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td style="font-weight: bold;">&#8377; ${product.price}</td>
                        <td>
                            <a href="edit-product/${product.id}" class="btn btn-primary"><i class="fa-solid fa-pen-to-square"></i></a>
                            <a href="delete-product/${product.id}" class="btn btn-danger"><i class="fa-solid fa-trash"></i></a>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="text-center">
                <a href="add-product" class="btn btn-success">Add New Product</a>
            </div>
        </div>
    </div>
</body>
</html>
