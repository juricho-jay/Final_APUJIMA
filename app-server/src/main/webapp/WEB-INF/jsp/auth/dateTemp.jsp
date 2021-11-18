<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"
 trimDirectiveWhitespaces="true"%> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title>dateCheckForm.jsp</title>

  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>  
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.5.1/moment.min.js"></script>
    
  <style>
  /* document.getElementById('currentDate').value = new Date().toISOString().substring(0, 10); */
  body {
  @import url('https://fonts.googleapis.com/css?family=Fjalla+One');
  font-family: 'Fjalla One', sans-serif;
}

$black: #222222;

.box {
  width: 500px;
  margin: 2% auto;
  position: relative;
  float: o
  
  
}
.calendar {
  width: 40%;
  height:20%;
  text-align: center;
  overflow: hidden;
  color: white;
  background-color: #9FBBBF!important;
  }
  

.year, .month, .day {
    width: 33.333%;
    float: left;
    
    }
    .previous, .next, .current > p {
        font-size: 40px;
        margin: 0;
    }
    .previous, .next, .current > span {
        font-size: 14px;
        text-transform: uppercase;
    }
    .previous, .next {
      background-color: rgba(0,0,0,0.5);
      padding: 2.6em 0;
      p {
        opacity: 0.3;
      }
    }
    .current {
      padding: 2em 0;
    }
  }
  .year {
    background-color: #D1EAEE;
  }
  .month {
    background-color: #D1EAEE;
  }
  .day {
    background-color: #4B6D90;
  }
}
  </style>  
    

 </head> 
 <body> 
 <div style="text-align: center">

  <h3 style="margin-top: 15%;">출석 체크!</h3>
   <!-- <input type='date' id='currentDate'/> -->
   <form method="post" action="dateCheckFinder">
   <button class="btn btn-primary" type="submit">출석체크</button>
   </form>
    </div> 
  <div class="box">
    <div class="calendar">
      <div class="year">
        <div class="previous"><p id="prevYear"></p></div>
        <div class="current"><p id="currentYear"></p><span>Year</span></div>
        <div class="next"><p id="nextYear"></p></div>
      </div>
      <div class="month">
        <div class="previous"><p id="prevMonth"></p></div>
        <div class="current"><p id="currentMonth"></p><span>Month</span></div>
        <div class="next"><p id="nextMonth"></p></div>
      </div>
      <div class="day">
        <div class="previous"><p id="prevDay"></p></div>
        <div class="current"><p id="currentDay"></p><span>Day</span></div>
        <div class="next"><p id="nextDay"></p></div>
      </div>
    </div>
  </div>
    
  </body>
  
  <script>
//Using Moment

  function calendar() {
    //for current
    var nMoment = moment();
    
    //get the current year
    var currentYear = document.getElementById('currentYear');
    currentYear.innerHTML = nMoment.format('YYYY');
    //get the previous year
    var prevYear = document.getElementById('prevYear');
    prevYear.innerHTML = moment().subtract(1, 'years').format('YYYY');
    //get the next year
    var nextYear = document.getElementById('nextYear');
    nextYear.innerHTML = moment().add(1, 'years').format('YYYY');
    
    //get the current month
    var currentMonth = document.getElementById('currentMonth');
    currentMonth.innerHTML = nMoment.format('MM');
    //get the previous month
    var prevMonth = document.getElementById('prevMonth');
    prevMonth.innerHTML = moment().subtract(1, 'months').format('MM');
    //get the next month
    var nextMonth = document.getElementById('nextMonth');
    nextMonth.innerHTML = moment().add(1, 'months').format('MM');
    
    //get the current day
    var currentDay = document.getElementById('currentDay');
    currentDay.innerHTML = nMoment.format('DD');
    //get the previous day
    var prevDay = document.getElementById('prevDay');
    prevDay.innerHTML = moment().subtract(1, 'days').format('DD');
    //get the next day
    var nextDay = document.getElementById('nextDay');
    nextDay.innerHTML = moment().add(1, 'days').format('DD');
    
  }

  calendar();
  </script>
</html>

