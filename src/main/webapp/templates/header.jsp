<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header class="container">
    <div class="jumbotron">
        <h1>Miraflores Car Wash</h1>
        <h2>Usuario: <sec:authentication property="principal.username"/></h2>
    </div>
</header>