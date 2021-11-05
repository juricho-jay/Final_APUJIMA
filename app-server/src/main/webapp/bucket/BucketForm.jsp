<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>버킷리스트 등록</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
  
  <style>
body {
    margin: 0;
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
    font-size: 0.88rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    text-align: left;
    background-color: skyblue;
}

i {
    font-style: italic
}

.container {
    margin-top: 100px
}

.card {
    box-shadow: 0 0.46875rem 2.1875rem rgba(4, 9, 20, 0.03), 0 0.9375rem 1.40625rem rgba(4, 9, 20, 0.03), 0 0.25rem 0.53125rem rgba(4, 9, 20, 0.05), 0 0.125rem 0.1875rem rgba(4, 9, 20, 0.03);
    border-width: 0;
    transition: all .2s
}

.card-header:first-child {
    border-radius: calc(0.25rem - 1px) calc(0.25rem - 1px) 0 0
}

.card-header {
    display: flex;
    align-items: center;
    border-bottom-width: 1px;
    padding-top: 0;
    padding-bottom: 0;
    padding-right: 0.625rem;
    height: 3.5rem;
    background-color: #fff
}

.widget-subheading {
    color: #858a8e;
    font-size: 10px
}

.card-header.card-header-tab .card-header-title {
    display: flex;
    align-items: center;
    white-space: nowrap
}

.card-header .header-icon {
    font-size: 1.65rem;
    margin-right: 0.625rem
}

.card-header.card-header-tab .card-header-title {
    display: flex;
    align-items: center;
    white-space: nowrap
}

.btn-actions-pane-right {
    margin-left: auto;
    white-space: nowrap
}

.text-capitalize {
    text-transform: capitalize !important
}

.scroll-area-sm {
    height: 288px;
    overflow-x: hidden
}

.list-group-item {
    position: relative;
    display: block;
    padding: 0.75rem 1.25rem;
    margin-bottom: -1px;
    background-color: #fff;
    border: 1px solid rgba(0, 0, 0, 0.125)
}

.list-group {
    display: flex;
    flex-direction: column;
    padding-left: 0;
    margin-bottom: 0
}

.todo-indicator {
    position: absolute;
    width: 4px;
    height: 60%;
    border-radius: 0.3rem;
    left: 0.625rem;
    top: 20%;
    opacity: .6;
    transition: opacity .2s
}

.bg-warning {
    background-color: #f7b924 !important
}

.widget-content {
    padding: 1rem;
    flex-direction: row;
    align-items: center
}

.widget-content .widget-content-wrapper {
    display: flex;
    flex: 1;
    position: relative;
    align-items: center
}

.widget-content .widget-content-right.widget-content-actions {
    visibility: hidden;
    opacity: 0;
    transition: opacity .2s
}

.widget-content .widget-content-right {
    margin-left: auto
}

.btn:not(:disabled):not(.disabled) {
    cursor: pointer
}

.btn {
    position: relative;
    transition: color 0.15s, background-color 0.15s, border-color 0.15s, box-shadow 0.15s
}

.btn-outline-success {
    color: #3ac47d;
    border-color: #3ac47d
}

.btn-outline-success:hover {
    color: #fff;
    background-color: #3ac47d;
    border-color: #3ac47d
}

.btn-outline-success:hover {
    color: #fff;
    background-color: #3ac47d;
    border-color: #3ac47d
}

.btn-primary {
    color: #fff;
    background-color: #3f6ad8;
    border-color: #3f6ad8;
    margin-left: 2%;
}

.btn {
    position: relative;
    transition: color 0.15s, background-color 0.15s, border-color 0.15s, box-shadow 0.15s;
    outline: none !important
}

.card-footer {
    background-color: #fff
}
  </style>
</head>
<body>

<div class="row d-flex justify-content-center container">
    <div class="col-md-8" style="margin-left: 20%; margin-top: 5%">
        <div class="card-hover-shadow-2x mb-3 card">
            <div class="card-header-tab card-header">
                <div class="card-header-title font-size-lg text-capitalize font-weight-normal">
                <i class="fa fa-tasks"></i>버킷리스트</div>
            </div>
            <div class="scroll-area-sm">
                <perfect-scrollbar class="ps-show-limits">
                    <div style="position: static;" class="ps ps--active-y">
                        <div class="ps-content">
                            <ul class="list-group list-group-flush">
                                <!-- <li class="list-group-item">
                                    <div class="todo-indicator bg-warning"></div>
                                    <div class="widget-content p-0">
                                        <div class="widget-content-wrapper">
                                            <div class="widget-content-left mr-2">
                                                <div class="custom-checkbox custom-control"> 
                                                <input class="custom-control-input" id="exampleCustomCheckbox12" 
                                                type="checkbox"><label class="custom-control-label"
                                                 for="exampleCustomCheckbox12">&nbsp;</label> </div>
                                            </div>
                                            <div class="widget-content-left">
                                                <div class="widget-heading">태호한테 점심 뜯어내기 
                                                <div class="badge badge-danger ml-2">Rejected</div>
                                                </div>
                                                <div class="widget-subheading"><i>By 주리</i></div>
                                            </div>
                                            <div class="widget-content-right"> 
                                            <button class="border-0 btn-transition btn btn-outline-success"> 
                                            <i class="fa fa-check"></i></button> 
                                            <button class="border-0 btn-transition btn btn-outline-danger"> 
                                            <i class="fa fa-trash"></i> </button> </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="todo-indicator bg-primary"></div>
                                    <div class="widget-content p-0">
                                        <div class="widget-content-wrapper">
                                            <div class="widget-content-left mr-2">
                                                <div class="custom-checkbox custom-control">
                                                <input class="custom-control-input" id="exampleCustomCheckbox4" 
                                                type="checkbox"><label class="custom-control-label" for="exampleCustomCheckbox4">&nbsp;</label></div>
                                            </div>
                                            <div class="widget-content-left flex2">
                                                <div class="widget-heading">현지한테 찡찡거리기</div>
                                                <div class="widget-subheading">By 주리!!</div>
                                            </div>
                                            <div class="widget-content-right"> 
                                            <button class="border-0 btn-transition btn btn-outline-success">
                                             <i class="fa fa-check"></i></button> 
                                             <button class="border-0 btn-transition btn btn-outline-danger">
                                              <i class="fa fa-trash"></i> </button> </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="todo-indicator bg-info"></div>
                                    <div class="widget-content p-0">
                                        <div class="widget-content-wrapper">
                                            <div class="widget-content-left mr-2">
                                                <div class="custom-checkbox custom-control">
                                                <input class="custom-control-input" id="exampleCustomCheckbox2"
                                                 type="checkbox"><label class="custom-control-label" for="exampleCustomCheckbox2">&nbsp;</label></div>
                                            </div>
                                            <div class="widget-content-left">
                                                <div class="widget-heading">자바스크립트 공부하기</div>
                                                <div class="widget-subheading">By 주리,태호,현지,진현</div>
                                            </div>
                                            <div class="widget-content-right">
                                             <button class="border-0 btn-transition btn btn-outline-success">
                                              <i class="fa fa-check"></i></button> 
                                              <button class="border-0 btn-transition btn btn-outline-danger"> 
                                              <i class="fa fa-trash"></i> </button> </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="todo-indicator bg-success"></div>
                                    <div class="widget-content p-0">
                                        <div class="widget-content-wrapper">
                                            <div class="widget-content-left mr-2">
                                                <div class="custom-checkbox custom-control">
                                                <input class="custom-control-input" id="exampleCustomCheckbox3" 
                                                type="checkbox"><label class="custom-control-label" for="exampleCustomCheckbox3">&nbsp;</label></div>
                                            </div>
                                            <div class="widget-content-left flex2">
                                                <div class="widget-heading">점심 메뉴 정하기</div>
                                                <div class="widget-subheading">By 김태호 대리</div>
                                            </div>
                                            <div class="widget-content-right"> 
                                            <button class="border-0 btn-transition btn btn-outline-success"> 
                                            <i class="fa fa-check"></i></button> <button class="border-0 btn-transition btn btn-outline-danger">
                                             <i class="fa fa-trash"></i> </button> </div>
                                        </div>
                                    </div>
                                </li> -->
                            </ul>
                        </div>
                    </div>
                </perfect-scrollbar>
            </div>
            <div class="d-block text-right card-footer">
            <button class="mr-2 btn btn-link btn-sm">취소</button><button class="btn btn-primary" onclick="addButton();">버킷리스트 추가</button></div>
        </div>
    </div>
    
  <!--   <script type="text/javascript">
    function addList() {
    	
    	const addValue = document.getElementById('addValue').value;
    	
    	const li = document.createElement("li");
    	
    	li.setAttributre('id', addValue);
    	
    	const textNode = document.createTextNode(addValue);
    	li.appendChild(textNode);
    	
    	
    	document.getElementById('list-group list-group-flush').appendChild(li);
    	
    };
    </script> -->
    
    
  <script type="text/javascript">
   async function addButton() {
	   const { value : text } = await Swal.fire({
		   input: 'textarea',
		   /* inputLabel: 'Message', */
		   inputPlaceholder: '버킷리스트를 입력하세요...',
		   inputAttributes: {
		     'aria-label': 'Type your message here'
		   },
		   showCancelButton: true
		 })
		  
		 if (text) {
			      
	          
	          const li = document.createElement("li");
	          
	          /* li.setAttribute('id', text); */
	          
	          const textNode = document.createTextNode(text);
	          li.appendChild(textNode);
	          
	          console.log(li);
	          const className = document.getElementsByClassName('list-group list-group-flush')[0];
	          className.appendChild(li); 
	          
		 }
		 
      };
    </script>
    
    
</div>
</body>
</html>









