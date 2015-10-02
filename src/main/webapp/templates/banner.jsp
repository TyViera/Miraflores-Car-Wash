<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="min-height: 50px;">
    <!-- Jssor Slider Begin -->
    <!-- To move inline styles to css file/block, please specify a class name for each element. --> 
    <!-- ================================================== -->
    <div id="slider1_container" style="display: none; position: relative; margin: 0 auto;
         top: 0px; left: 0px; width: 1300px; height: 300px; overflow: hidden;">
        <!-- Loading Screen -->
        <div u="loading" style="position: absolute; top: 0px; left: 0px;">
            <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block;
                 top: 0px; left: 0px; width: 100%; height: 100%;">
            </div>
            <div style="position: absolute; display: block; //background: url(../resources/img/loading.gif) no-repeat center center;
                 top: 0px; left: 0px; width: 100%; height: 100%;">
            </div>
        </div>
        <!-- Slides Container -->
        <div u="slides" style="cursor: move; position: absolute; left: 0px; top: 0px; width: 1300px; height: 400px; overflow: hidden;">
            <div>
                <img u="image" src2="<c:url value="/resources/img/banner01.jpg"/>" />
            </div>
            <div>
                <img u="image" src2="<c:url value="/resources/img/banner02.jpg"/>" />
            </div>
            <div>
                <img u="image" src2="<c:url value="/resources/img/banner03.jpg"/>" />
            </div>
        </div>

        <!--#region Bullet Navigator Skin Begin -->
        <!-- Help: http://www.jssor.com/development/slider-with-bullet-navigator-jquery.html -->
        <link rel="stylesheet" href="<c:url value="/resources/css/stylebanner.css"/>" />
        <!-- bullet navigator container -->
        <div u="navigator" class="jssorb21" style="bottom: 26px; right: 6px;">
            <!-- bullet navigator item prototype -->
            <div u="prototype"></div>
        </div>
        <!-- Arrow Left -->
        <span u="arrowleft" class="jssora21l" style="top: 123px; left: 8px;">
        </span>
        <!-- Arrow Right -->
        <span u="arrowright" class="jssora21r" style="top: 123px; right: 8px;">
        </span>
        <!--#endregion Arrow Navigator Skin End -->
    </div>
    <!-- Jssor Slider End -->
</div>
<script type="text/javascript" src="<c:url value="/resources/js/jssor.slider.mini.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/docs.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/ie10-viewport-bug-workaround.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/slider.js"/>"></script>
