
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:useBean id="usuario" scope="session" class="com.miraflorescarwash.model.Usuario"  />
<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/templates/head.jsp"/>
        <title>Miraflores Car Wash</title>
    </head>
    <body>
        <nav> 
            <jsp:include page="/templates/menu.jsp"/>
            <jsp:include page="/templates/banner.jsp"/>
        </nav>
        <section>
            <jsp:include page="/templates/header.jsp"/>
            <div class="container">
                <hr/>
                <div class="row">
                    <div class="col-lg-6" >
                        <h1>¿Realizar una lavada?</h1>
                        <h3>Realizala <a href="${pageContext.servletContext.contextPath}/Lavada/add.html">Aquí</a></h3>
                        <a href="${pageContext.servletContext.contextPath}/Lavada/add.html">
                            <img src="<c:url value="/resources/img/lavada.gif"/>" alt="Lavada"/>
                        </a>
                    </div>
                    <div class="col-lg-6">
                        <h1>¿Necesitas Crédito?</h1>
                        <h3>Recargalo <a href="${pageContext.servletContext.contextPath}/Lavada/recargar.html">Aquí</a></h3>
                        <a href="${pageContext.servletContext.contextPath}/Lavada/recargar.html">
                            <img src="<c:url value="/resources/img/credito.jpg"/>" alt="credito"/>
                        </a>
                        
                    </div>
                </div>
                <hr/>
            </div>
        </section>
        <footer>
            <jsp:include page="/templates/footer.jsp"/>
        </footer>
    </body>
</html>
