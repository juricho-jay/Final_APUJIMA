<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<style>
html,body { 
   margin:0;
   padding:0; 
   width:100%; 
   height:100% 
   }

#body-wrapper {
	 min-height: 80%; 
	 position: relative; 
 } 

#body-content { 
	 margin-top: 100px; 
	 padding-bottom: 100px; 
	/* footer의 높이 */ 
	} 
footer {
  width: 100%;
  height: 205px; /* footer의 높이 */
  position: absolute;
  bottom: 0;
  left: 0; 
  }


.footer-basic {
  padding:40px 0;
  background-color:#F4F1EA;
  color:#4b4c4d;
}

.footer-basic ul {
  padding:0;
  list-style:none;
  text-align:center;
  font-size:18px;
  line-height:1.6;
  margin-bottom:0;
}

.footer-basic li {
  padding:0 10px;
}

.footer-basic ul a {
  color:inherit;
  text-decoration:none;
  opacity:0.8;
}

.footer-basic ul a:hover {
  opacity:1;
}

.footer-basic .social {
  text-align:center;
  padding-bottom:25px;
}

.footer-basic .social > a {
  font-size:24px;
  width:40px;
  height:40px;
  line-height:40px;
  display:inline-block;
  text-align:center;
  border-radius:50%;
  border:1px solid #ccc;
  margin:0 8px;
  color:inherit;
  opacity:0.75;
}

.footer-basic .social > a:hover {
  opacity:0.9;
}

.footer-basic .copyright {
  margin-top:15px;
  text-align:center;
  font-size:13px;
  color:#aaa;
  margin-bottom:0;
}

</style>
</head>
<body>

<div id="body-wrapper">
<div id="body-content">
</div>
<footer>
  <div class="footer-basic">
        <div class="social">
        <a href="#"><i class="bi bi-instagram"></i></a>
        <a href="#"><i class="bi bi-facebook"></i></a>
        <a href="#"><i class="bi bi-twitter"></i></a>
        <a href="#"><i class="bi bi-github"></i></a>
        </div>
            <ul class="list-inline">
                <li class="list-inline-item"><a href="#">Home</a></li>
                <li class="list-inline-item"><a href="#">About</a></li>
                <li class="list-inline-item"><a href="#">Healer</a></li>
                <li class="list-inline-item"><a href="#">Terms</a></li>
                <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
            </ul>
            <p class="copyright">PROJECT APUJIMA © 2021</p>
  </div>
</footer>
</div>
</body>
