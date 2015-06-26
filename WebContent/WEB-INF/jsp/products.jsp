<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
  <title>www.tech-freaks.com - Product List</title>

  
  
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head><body>
<div><center>${errorMessage}</center></div>
<p><font face="Verdana, Arial, Helvetica, sans-serif" size="3"><strong>Product
List </strong></font></p>

<a href="${pageContext.servletContext.contextPath}/cart/view.html">View Cart</a>
<p>&nbsp;&nbsp;&nbsp;
</p>

<table style="width: 827px; height: 358px;" border="1">

  <tbody>
  <c:forEach items="${productList}" var="product">
  <form:form method="post" action="${pageContext.servletContext.contextPath}/cart/add.html" >
    <tr>
      <td>
      <table style="text-align: left; width: 817px; height: 144px;" border="1" cellpadding="2" cellspacing="2">
        <tbody>
          <tr>
            <td style="vertical-align: top; width: 483px;"><span style="font-weight: bold;">Part Number: </span><c:out value="${product.partnumber}"/> <input name="productId" value="${product.productId}" type="hidden"><br>
            </td>
            <td colspan="1" rowspan="5" style="vertical-align: top; width: 314px;">
            	<c:choose>
            		<c:when test="${not empty product.thumbnailUrl}">
            			<img alt="${product.name}" height="200px" width="200px" src="<c:out value='${pageContext.request.contextPath}${product.thumbnailUrl}'/>"/><br>
            		</c:when>
            		<c:when test="${empty product.thumbnailUrl}">
            			<img alt="${product.name}" height="200px" width="200px" src="<c:out value='${pageContext.request.contextPath}'/>/images/noimage.png"/><br>
            		</c:when>
            	</c:choose>
            </td>
          </tr>
          <tr>
            <td style="vertical-align: top; width: 483px;"><span style="font-weight: bold;">Name</span>: <c:out value="${product.name}"/><br>
            </td>
          </tr>
          <tr>
            <td style="vertical-align: top; width: 483px;"><c:out value="${product.description}" escapeXml="true"/><br>
            </td>
          </tr>
          <tr>
            <td style="vertical-align: top; width: 483px;"><br>
Quantity:<input size="2" value="1" name="quantity" type="text"> </td>
          </tr>
          <tr>
            <td style="vertical-align: top; width: 483px;"><span style="font-weight: bold;">Price:</span> $<c:out value="${product.unitprice}"/><br>
            <input name="price" value="${product.unitprice}" type="hidden">
            </td>
          </tr>
          <tr>
            <td style="vertical-align: top;"><br>
            <c:choose>
            		<c:when test="${product.buyable.toString() eq 'Y' }">
            			<input name="action" value="add" type="hidden"><input name="addToCart" value="Add To Cart" type="submit">
            		</c:when>
            		<c:when test="${product.buyable.toString() eq 'N'}">
            			<div style="color: red">Sold Out</div>
            		</c:when>
            	</c:choose>
            	 
            </td>
            <td style="vertical-align: top;"><br>
            </td>
          </tr>
        </tbody>
      </table>
      <br>
      </td>
    </tr>
    </form:form>
    </c:forEach>
  </tbody>
</table>

<p>&nbsp;</p>

</body></html>