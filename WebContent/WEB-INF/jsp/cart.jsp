<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>www.tech-freaks.Com - DB Shopping Cart</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>

<p><font face="Verdana, Arial, Helvetica, sans-serif"><strong>Shopping Cart</strong></font></p>
<p><a href="${pageContext.servletContext.contextPath}/products.html">Model List</a> </p>
<table width="75%" border="1">
  <tr bgcolor="#CCCCCC"> 
    <td><strong><font size="2" face="Verdana, Arial, Helvetica, sans-serif">Model 
      Description</font></strong></td>
    <td><strong><font size="2" face="Verdana, Arial, Helvetica, sans-serif">Quantity</font></strong></td>
    <td><strong><font size="2" face="Verdana, Arial, Helvetica, sans-serif">Unit 
      Price</font></strong></td>
    <td><strong><font size="2" face="Verdana, Arial, Helvetica, sans-serif">Total</font></strong></td>
  </tr>
  <c:if test="${empty cart || empty cart.cartitems}">
  <tr>
  <td colspan="4"><font size="2" face="Verdana, Arial, Helvetica, sans-serif">- Cart is currently empty -<br/>
  </tr>
  </c:if>
  <c:forEach var="cartItem" items="${cart.cartitems}" varStatus="counter"> 
  <form name="item" method="POST" action="${pageContext.servletContext.contextPath}/cart/change.html">
  <tr> 
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><c:out value="${cartItem.product.partnumber}"/></b><br/>
      <c:out value="${cartItem.product.name}"/></font></td>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif"><input type='hidden' name='cartItemId' value='<c:out value="${cartItem.cartitemId}"/>'><input type='text' name="quantity" value='<c:out value="${cartItem.quantity}"/>' size='2'>&nbsp;<input type="submit" name="action" value="Update">
	<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="action" value="Delete"></font></td>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif">$<c:out value="${cartItem.unitprice}"/></font></td>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif">$<c:out value="${cartItem.totalprice}"/></font></td>
  </tr>
  </form>
  </c:forEach> 
  <tr> 
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
    <td><font size="2" face="Verdana, Arial, Helvetica, sans-serif">Subtotal: $<c:out value="${cart.totalPrice}"/></font></td>
  </tr>
</table>
</body>
</html>