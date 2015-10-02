<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/resources/css/stylenavbar.css"/>" />
<div class="navbar-wrapper">
    <div class="container">
        <nav class="navbar navbar-inverse navbar-static-top" role="navigation" style="margin-bottom: 0px;">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href='${pageContext.servletContext.contextPath}/index.html'>Miraflores Car Wash</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href='${pageContext.servletContext.contextPath}/index.html'>Inicio</a></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" 
                               href='${pageContext.servletContext.contextPath}/Cliente/index.html'>
                                Clientes<b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href='${pageContext.servletContext.contextPath}/Cliente/index.html'>Inicio</a></li>
                                <li><a href='${pageContext.servletContext.contextPath}/Cliente/add.html'>Agregar</a></li>
                                <li><a href="${pageContext.servletContext.contextPath}/Reporte/cliente.html?tipo=cre">Crédito Disponible</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" 
                               href='${pageContext.servletContext.contextPath}/Carro/index.html'>
                                Carros<b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href='${pageContext.servletContext.contextPath}/Carro/index.html'>Inicio</a></li>
                                <li><a href='${pageContext.servletContext.contextPath}/Carro/add.html'>Agregar</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" 
                               href='${pageContext.servletContext.contextPath}/Combo/index.html'>
                                Combos<b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href='${pageContext.servletContext.contextPath}/Combo/index.html'>Inicio</a></li>
                                <li><a href='${pageContext.servletContext.contextPath}/Combo/add.html'>Agregar</a></li>
                            </ul>
                        </li>
                        <li><a href='${pageContext.servletContext.contextPath}/Usuario/index.html'>Usuarios</a></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" 
                               href='${pageContext.servletContext.contextPath}/Lavada/index.html'>
                                Lavadas<b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href='${pageContext.servletContext.contextPath}/Lavada/index.html'>Inicio</a></li>
                                <li><a href='${pageContext.servletContext.contextPath}/Lavada/add.html'>Agregar</a></li>
                                <li><a href='${pageContext.servletContext.contextPath}/Lavada/pendientes.html'>Ver Pendientes</a></li>
                                <li><a href='${pageContext.servletContext.contextPath}/Lavada/recargar.html'>Recargar</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" 
                               href="${pageContext.servletContext.contextPath}/Reporte/index.html">
                                Reportes <b class="caret"></b>
                            </a> 
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.servletContext.contextPath}/Reporte/index.html">Inicio</a></li>
                                <li class="dropdown-submenu">
                                    <a tabindex="-1" href="#" data-toggle="dropdown">Ventas</a>
                                    <ul class="dropdown-menu sub-menu">
                                        <li><a tabindex="-1" href="${pageContext.servletContext.contextPath}/Reporte/ventas.html?tiempo=dia">Diarias</a></li> 
                                        <li><a href="${pageContext.servletContext.contextPath}/Reporte/ventas.html?tiempo=sem">Semanales</a></li> 
                                        <li><a href="${pageContext.servletContext.contextPath}/Reporte/ventas.html?tiempo=mes">Mensuales</a></li> 
                                    </ul>
                                </li>
                                <li class="dropdown-submenu">
                                    <a tabindex="-1" href="#" data-toggle="dropdown">Clientes</a>
                                    <ul class="dropdown-menu sub-menu">
                                        <li><a href="${pageContext.servletContext.contextPath}/Reporte/cliente.html?tipo=fre">Frecuentes</a></li>
                                        <li><a href="${pageContext.servletContext.contextPath}/Reporte/cliente.html?tipo=prm">Gastos en el Mes</a></li>
                                        <li><a href="${pageContext.servletContext.contextPath}/Reporte/cliente.html?tipo=evo">Evolución Mensual</a></li>
                                        <li><a href="${pageContext.servletContext.contextPath}/Reporte/cliente.html?tipo=cre">Crédito Disponible</a></li>
                                    </ul>
                                </li>
                                <li><a href="${pageContext.servletContext.contextPath}/Reporte/combos.html">Combos</a></li>
                            </ul> 
                        </li>
                        <li><a href='${pageContext.servletContext.contextPath}/logout.html'>Cerrar Sesión</a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>