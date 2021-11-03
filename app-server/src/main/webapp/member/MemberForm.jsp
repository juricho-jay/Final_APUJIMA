<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>회원가입</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">

  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../bootstrap/dist/bootstrap.css"></script>
  
  <style>
  .container {
    xborder: 1px solid red;
    width: 640px;
  }
  </style>
</head>
<body>
<div class="container">
<div class=""></div>
<h1>회원가입</h1>
<form action='add'>
<div class="col-md-3">
  <label for="validationServer04" class="form-label">-일반회원 가입 페이지-</label>
  <!-- <select class="form-select is-invalid" name="userType" id="validationServer04" aria-describedby="validationServer04Feedback" required>
>>>>>>> b6e15b3759b79dde3a02669aa19a1f3809a9b2c7
    <option selected disabled value="0">선택</option>
    <option value="1">일반 회원</option>
    <option value="2">의사</option>
  </select> -->
 
</div>
  <div class="mb-3 row">
    <label for='f-name' class="col-sm-2 col-form-label">이름</label>
    <div class="col-sm-6">
      <input id='f-name' type='text' name='name' class="form-control">
    </div>
</div>
<div class="mb-3 row">
    <label for='f-id' class="col-sm-2 col-form-label">아이디</label>
    <div class="col-sm-6">
      <input id='f-id' type='text' name='id' class="form-control">
    </div>
</div>
<div class="mb-3 row">
  <label for='f-password' class="col-sm-2 col-form-label">암호</label>
  <div class="col-sm-6">
    <input id='f-password' type='password' name='password' class="form-control">
  </div>
</div>
<div class="mb-3 row">
    <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
    <div class="col-sm-7">
      <input id='f-email' type='email' name='email' class="form-control">
    </div>
</div>
<div class="mb-3 row">
  <label for='f-birthDay' class="col-sm-2 col-form-label">생일</label>
  <!-- <div class="col-sm-10"> -->
  <div class="col-sm-3">
    <!-- <input id='f-birthDay' type='text' name='birthDay' class="form-control"> -->
    <select class="form-select" id="signupYear" name="signupYear" value="년도">
<option value="">년도</option>
<option value="2021">2021</option>
<option value="2020">2020</option>
<option value="2019">2019</option>
<option value="2018">2018</option>
<option value="2017">2017</option>
<option value="2016">2016</option>
<option value="2015">2015</option>
<option value="2014">2014</option>
<option value="2013">2013</option>
<option value="2012">2012</option>
<option value="2011">2011</option>
<option value="2010">2010</option>
<option value="2009">2009</option>
<option value="2008">2008</option>
<option value="2007">2007</option>
<option value="2006">2006</option>
<option value="2005">2005</option>
<option value="2004">2004</option>
<option value="2003">2003</option>
<option value="2002">2002</option>
<option value="2001">2001</option>
<option value="2000">2000</option>
<option value="1999">1999</option>
<option value="1998">1998</option>
<option value="1997">1997</option>
<option value="1996">1996</option>
<option value="1995">1995</option>
<option value="1994">1994</option>
<option value="1993">1993</option>
<option value="1992">1992</option>
<option value="1991">1991</option>
<option value="1990">1990</option>
<option value="1989">1989</option>
<option value="1988">1988</option>
<option value="1987">1987</option>
<option value="1986">1986</option>
<option value="1985">1985</option>
<option value="1984">1984</option>
<option value="1983">1983</option>
<option value="1982">1982</option>
<option value="1981">1981</option>
<option value="1980">1980</option>
<option value="1979">1979</option>
<option value="1978">1978</option>
<option value="1977">1977</option>
<option value="1976">1976</option>
<option value="1975">1975</option>
<option value="1974">1974</option>
<option value="1973">1973</option>
<option value="1972">1972</option>
<option value="1971">1971</option>
<option value="1970">1970</option>
<option value="1969">1969</option>
<option value="1968">1968</option>
<option value="1967">1967</option>
<option value="1966">1966</option>
<option value="1965">1965</option>
<option value="1964">1964</option>
<option value="1963">1963</option>
<option value="1962">1962</option>
<option value="1961">1961</option>
<option value="1960">1960</option>
<option value="1959">1959</option>
<option value="1958">1958</option>
<option value="1957">1957</option>
<option value="1956">1956</option>
<option value="1955">1955</option>
<option value="1954">1954</option>
<option value="1953">1953</option>
<option value="1952">1952</option>
<option value="1951">1951</option>
<option value="1950">1950</option>
<option value="1949">1949</option>
<option value="1948">1948</option>
<option value="1947">1947</option>
<option value="1946">1946</option>
<option value="1945">1945</option>
<option value="1944">1944</option>
<option value="1943">1943</option>
<option value="1942">1942</option>
<option value="1941">1941</option>
<option value="1940">1940</option>
<option value="1939">1939</option>
<option value="1938">1938</option>
<option value="1937">1937</option>
<option value="1936">1936</option>
<option value="1935">1935</option>
<option value="1934">1934</option>
<option value="1933">1933</option>
<option value="1932">1932</option>
<option value="1931">1931</option>
<option value="1930">1930</option>
<option value="1929">1929</option>
<option value="1928">1928</option>
<option value="1927">1927</option>
<option value="1926">1926</option>
<option value="1925">1925</option>
<option value="1924">1924</option>
<option value="1923">1923</option>
<option value="1922">1922</option>
<option value="1921">1921</option>
</select>
</div>
<div class="col-sm-3">
<select class="form-select" id="signupMonth" name="signupMonth" value="월">
<option value="">월</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
</select>
</div>
<div class="col-sm-3">
<select class="form-select" id="signupDate" name="signupDate" value="일">
<option value="">일</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>
<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
<option value="31">31</option>
</select>
</div>
  </div>
<!-- </div> -->
<div class="mb-3 row">
  <label for='f-phoneNum' class="col-sm-2 col-form-label">휴대전화</label>
  <div class="col-sm-6">
    <input id='f-phoneNum' type='text' name='phoneNum' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
  <div class="col-sm-6">
    <input id='f-photo' type='text' name='photo' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='f-sex' class="col-sm-2 col-form-label">성별</label>
  <div class="col-sm-6">
	<div class="form-check form-check-inline">
	  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
	  <label class="form-check-label" for="inlineRadio1">여성</label>
	</div>
	<div class="form-check form-check-inline">
	  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">
	  <label class="form-check-label" for="inlineRadio2">남성</label>
	</div>
  </div>
</div>


  
  <!-- <div class="col-md-4">
    <label for="validationServerUsername" class="form-label">Username</label>
    <div class="input-group has-validation">
      <span class="input-group-text" id="inputGroupPrepend3">@</span>
      <input type="text" class="form-control is-invalid" id="validationServerUsername" aria-describedby="inputGroupPrepend3 validationServerUsernameFeedback" required>
      <div id="validationServerUsernameFeedback" class="invalid-feedback">
        이름은 필수 입력입니다.
      </div>
    </div>
  </div>
  <div class="col-md-6">
    <label for="validationServer03" class="form-label">City</label>
    <script>
    
    <input type="text" class="form-control is-invalid" id="validationServer03" aria-describedby="validationServer03Feedback" required>

    </script>
    <input type="text" class="form-control is-invalid" id="validationServer03" aria-describedby="validationServer03Feedback" required>
    <div id="validationServer03Feedback" class="invalid-feedback">
      필수 **입니다.
    </div>
  </div>
 
  <div class="col-md-3">
    <label for="validationServer05" class="form-label">Zip</label>
    <input type="text" class="form-control is-invalid" id="validationServer05" aria-describedby="validationServer05Feedback" required>
    <div id="validationServer05Feedback" class="invalid-feedback">
      Please provide a valid zip.
    </div>
  </div>
  <div class="col-12">
    <div class="form-check">
      <input class="form-check-input is-invalid" type="checkbox" value="" id="invalidCheck3" aria-describedby="invalidCheck3Feedback" required>
      <label class="form-check-label" for="invalidCheck3">
        Agree to terms and conditions
      </label>
      <div id="invalidCheck3Feedback" class="invalid-feedback">
        You must agree before submitting.
      </div>
    </div>
  </div> -->
  <div class="col-12">
    <button class="btn btn-primary btn-sm" type="submit">회원 가입</button>
  </div>
</form>
</div><!-- .container -->

<!-- <label for='f-name'>이름</label> <input id='f-name' type='text' name='name'><br>
<label for='f-email'>이메일</label> <input id='f-email' type='email' name='email'><br>
<label for='f-password'>암호</label> <input id='f-password' type='password' name='password'><br>
<label for='f-photo'>사진</label> <input id='f-photo' type='text' name='photo'><br>
<label for='f-tel'>전화</label> <input id='f-tel' type='tel' name='tel'><br>
<button>등록</button><br> -->
</form>
</body>
</html>









