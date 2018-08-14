<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <base href="/mavenproject2/">
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>Urban Gear Shop</title>
        <link rel="stylesheet" href="resources/style.css" type="text/css" media="all" />
	

</head>
<body>
<!-- Top -->
<div id="top">
	
	<div class="shell">
		
		<!-- Header -->
		<div id="header">
			<h1 id="logo"><a href="#">Urgan Gear</a></h1>
			<div id="navigation">
				<ul>
                                        <li><a href="admin/login">Admin login</a></li>
					<li class="last"><a href="#">Contact</a></li>
				</ul>
			</div>
		</div>
		<!-- End Header -->
		
		<!-- Slider -->
		<div id="slider">
			<div id="slider-holder">
				<ul>
				    <li><a href="#"><img src="resources/images/slide1.jpg" alt="" /></a></li>
				    <li><a href="#"><img src="resources/images/slide2.jpg" alt="" /></a></li>
				    <li><a href="#"><img src="resources/images/slide1.jpg" alt="" /></a></li>
				    <li><a href="#"><img src="resources/images/slide2.jpg" alt="" /></a></li>
				    <li><a href="#"><img src="resources/images/slide1.jpg" alt="" /></a></li>
				    <li><a href="#"><img src="resources/images/slide2.jpg" alt="" /></a></li>
				</ul>
			</div>
			<div id="slider-nav">
				<a href="#" class="prev">Previous</a>
				<a href="#" class="next">Next</a>
			</div>
		</div>
		<!-- End Slider -->
		
	</div>
</div>
<!-- Top -->

<!-- Main -->
<div id="main">
	<div class="shell">
		
		<!-- Search, etc -->
		<div class="options">
			
			<div class="right">
				<span class="cart">
					<a href="cart" class="cart-ico">&nbsp;</a>
					<strong>${bill}RSD</strong>
				</span>
				<span class="left more-links">
                                 <a href="confirm">Checkout</a>
				</span>
			</div>
		</div>
		<!-- End Search, etc -->
		
		<!-- Content -->
		<div id="content">
			
			<!-- Tabs -->
			<div class="tabs">
                         <c:forEach items="${categories}" var="category">
                         
				<ul>
				    <li><a  class="active" href="./${category.id}"><span>${category.name}</span></a></li>
				</ul>
                           </c:forEach> 
			</div>
			<!-- Tabs -->
			
			<!-- Container -->
			<div id="container">
				
				<div class="tabbed">
					
					<!-- First Tab Content -->
					<div class="tab-content" style="display:block;">
						<div class="items">
							<div class="cl">&nbsp;</div>
							<ul>
							    <li>
							    	<div class="image">
							    		<a href="#"><img src="resources/images/${shoe.photo}" alt="" /></a>
							    	</div>
                                                                <p>CONFIRM ORDER </p>
                                                                <form method="post" action="confirm">
                                                                    <br>Enter your details:</br>
                                                                    <textarea name="userdata"> </textarea>
                                                                <input type="submit" value="Confirm order" />
                                                                 </form>
							    </li>
							   
						    </ul>
							<div class="cl">&nbsp;</div>
                                                        </div>
<!--						</div>-->
                                                

					<!-- End Third Tab Content -->
					
				</div>
				
				<!-- Brands -->
				<div class="brands">
					<h3>Brands</h3>
					<div class="logos">
						<a href="#"><img src="resources/images/logo1.gif" alt="" /></a>
						<a href="#"><img src="resources/images/logo2.gif" alt="" /></a>
						<a href="#"><img src="resources/images/logo3.gif" alt="" /></a>
						<a href="#"><img src="resources/images/logo4.gif" alt="" /></a>
						<a href="#"><img src="resources/images/logo5.gif" alt="" /></a>
					</div>
				</div>
				<!-- End Brands -->
				
				<!-- Footer -->
				<div id="footer">

					<div class="right">
						&copy; Sitename.com. Design by <a href="http://chocotemplates.com" target="_blank" title="CSS Templates">ChocoTemplates.com</a>
					</div>
				</div>
				<!-- End Footer -->
				
			</div>
			<!-- End Container -->
			
		</div>
		<!-- End Content -->
		
	</div>
</div>
<!-- End Main -->
	
</body>
</html>