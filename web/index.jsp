<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 08.01.2016
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Welcome page</title>
</head>
<body>
<h2>It's a XML-parser.</h2>


<h2>Choose the way of parsing.</h2>

<hr/>

<FORM ACTION="/parseaction" METHOD="get" align="center">
    <INPUT TYPE="radio" NAME="radios" VALUE="dom">
    DOM-parser
    <BR>
    <INPUT TYPE="radio" NAME="radios" VALUE="sax">
    SAX-parser
    <BR>
    <INPUT TYPE="radio" NAME="radios" VALUE="stax">
    StAX-parser
    <BR>
    <INPUT TYPE="submit" VALUE="Submit">
    <button type="button" name="back" onclick="history.back()">Back</button>
</FORM>
<hr/>
<br>
<table style="width:100%" align="center" border="5px">
    <tr>
        <th>COUNTRY OF VISIT</th>
        <th>VOUCHER TYPE</th>
        <th>CITY</th>
        <th>ACCOMMODATION</th>
        <th>TRANSFER</th>
        <th>COST</th>
        <th>BELARUS PROFESSIONAL UNION</th>
    </tr>
    <tr>
        <td colspan="7" align="center">
            <c:set var="voucherType" value="${voucher}"/>
            <c:if test="${ voucherType['class'].simpleName eq 'VoucherDOMBuilder' }">
                DOM-parser
            </c:if>
            <c:if test="${ voucherType['class'].simpleName eq 'VoucherSAXBuilder' }">
                SAX-parser
            </c:if>
            <c:if test="${ voucherType['class'].simpleName eq 'VoucherStAXBuilder' }">
                StAX-parser
            </c:if>

        </td>
    </tr>
    <c:forEach var="voucherObj" items="${voucher.getVouchers()}" varStatus="status">
        <tr>
            <td>
                <c:if test="${ voucherObj['class'].simpleName eq 'InternationalVoucher' }">
                    <c:out value="${ voucherObj.country }"/>
                </c:if>
                <c:if test="${ voucherObj['class'].simpleName eq 'NationalVoucher' }">
                    Belarus
                </c:if>
            </td>
            <td><c:out value="${ voucherObj.voucherType }"/>
            <td><c:out value="${ voucherObj.city }"/></td>
            <td><c:out value="${ voucherObj.hotel }"/></td>
            <td><c:out value="${ voucherObj.transfer }"/></td>
            <td><c:out value="${ voucherObj.cost }"/></td>
            <td>
                <c:choose>
                    <c:when test="${ voucherObj['class'].simpleName eq 'NationalVoucher'}">
                        ${voucherObj.professionalUnion ? "is member" : "not member"}
                    </c:when>
                    <c:otherwise>
                        not provided
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>

</table>


</body>
</html>


