<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <title>메인 화면</title>
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i&display=swap" rel="stylesheet">
  
  <link rel="stylesheet" href="${contextPath}/css/main/owl.carousel.min.css">
  <link rel="stylesheet" href="${contextPath}/css/main/owl.theme.default.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/4.5.6/css/ionicons.min.css">
  <link rel="stylesheet" href="${contextPath}/css/main/style.css">
  
  
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
 
  
  
  
  
  
  <style>
  body {
    background-image: linear-gradient( rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5) ),url(${contextPath}/img/header/main_bg1.png);
    /* background-image: url('/apus/img/header/opacity.svg'), url('/apus/img/header/main_bg.png'); */
    background-repeat: no-repeat;
    background-size : cover;

  }
  
  p {
  color: white;
  margin-top: 40px;
  }
  
  .banner {
    position: relative;
    height: 180px;
    padding: 11px 0 16px;
    margin: 0 auto;
    text-align: center;
}
.dg-container {
    position: relative;
    width: 100%;
    height: 350px;
}
.dg-wrapper {
    width: 320px;
    height: 250px;
    margin: 0 auto;
    position: relative;
    transform-style: preserve-3d;
    perspective: 1000px;
}
.dg-wrapper a {
    width: 100%;
    height: 250px;
    display: block;
    position: absolute;
    left: 0;
    top: 0;
}
.dg-wrapper a:first-child {
    z-index: 2;
}
.dg-wrapper a img {
    display: block;
    box-shadow: 0px 1px 3px 0px rgba(0, 0, 0, 0.20);
    border-radius: 4px;
    width: 100%;
    height:350px;
    background: #fff;
}
.dg-wrapper a.dg-transition {
    transition: all 0.5s ease-in-out;
}
.dg-wrapper a.dg-transition-fast {
    transition: all 0.2s ease-in-out;
}
.dg-container nav {
    display: none;
}
.dg-container nav span:hover {
    opacity: 1;
}
.dg-container nav span.dg-next {
    background-position: top right;
    margin-left: 10px;
}
.dg-container #lightButton2 {
    bottom: 20px;
}
.dg-container .button {
     position: relative;
     z-index: 5;
 }
.dg-container .button li {
    cursor: pointer;
    display: inline-block;
    width: 6px;
    height: 6px;
    border-radius: 50%;
    margin-right: 5px;
    background: rgba(255, 255, 255, 0.30);
    border: 1px solid rgba(0, 0, 0, 0.20);
}
.dg-container .button .light {
    background: #01BDFF;
}
  </style>
  

<div class="text" style="margin-bottom:500px;">
      <p class="text-center fs-1">We are here for friends in need.</p>
      <p class="text-center fs-2">We are here for friends in need.</p>
      <p class="text-center fs-3">We are here for friends in need.</p>
      <p class="text-center fs-4">We are here for friends in need.</p>
      <p class="text-center fs-5">We are here for friends in need.</p>
</div>
         
         
  <!-- <div style="width: 85%; height:500px; margin-top: 100px; background-color: #2C473E; position:absolute;">
  </div> -->
         
         
<!-- carausel -->    
<section class="ftco-section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <!-- <h2 class="heading-section mb-5">Carousel #03</h2> -->
          </div>
          <div class="col-md-12">
            <div class="featured-carousel owl-carousel">
              <div class="item">
                <div class="work">
                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_1.jpg);">
                    <div class="text w-100">
                      <span class="cat">김사사 상담사</span>
                      <h3><a href="#">안녕하세요 APUJIMA입니다.</a></h3>
                    </div>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="work">
                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_4.jpg);">
                    <div class="text w-100">
                      <span class="cat">조주리 상담사</span>
                      <h3><a href="#">정신건강에 도움을..</a></h3>
                    </div>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="work">
                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_3.jpg);">
                    <div class="text w-100">
                      <span class="cat">김태호 상담사</span>
                      <h3><a href="#">오늘날 4명중에 1명이 마음병이 생깁니다.</a></h3>
                    </div>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="work">
                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_2.jpg);">
                    <div class="text w-100">
                      <span class="cat">김진현 상담사</span>
                      <h3><a href="#">APUJIMA가 도와드리겠습니다.</a></h3>
                    </div>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="work">
                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_4.jpg);">
                    <div class="text w-100">
                      <span class="cat">신현지 상담사</span>
                      <h3><a href="#">감사합니다.</a></h3>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
<!--Carousel Wrapper-->

<div class="noticeBoardTab" style="margin-top: 300px;">
<h4 style="margin-left:18%; color: #2C473E;">공지사항</h4>
<table class="table table-sm" style="margin-left: 18%; width: 850px; color: #2C473E">
  <thead>
  </thead>
  <tbody>
  <c:forEach items="${boardList}" var="board">
    <tr>
      <td style= "width: 10%" class="text-center">${board.no}</td>
     <c:if test='${board.whichBoard == 1}'>
    <td style= "width: 10%" class="text-center">자유게시판</td> 
    </c:if>
    <c:if test='${board.whichBoard == 2}'>
     <td style= "width: 10%" class="text-center">Healer지식in</td> 
    </c:if>
    <c:if test='${board.whichBoard == 3}'>
     <td style= "width: 10%" class="text-center">공지사항</td> 
    </c:if>
     <td style= "width: 10%" class="text-center"><a href='detail?no=${board.no}'>${board.title}</a></td> 
     <td style= "width: 10%" class="text-center">${board.content}</td> 
     <td style= "width: 10%" class="text-center">${board.writer.nickname}</td> 
     <td style= "width: 30%" class="text-center">${board.registeredDate}</td> 
     <td style= "width: 10%" class="text-center">${board.viewCount}</td>
    </tr>
    </c:forEach>
  </tbody>
</table>
</div>


<div class="homebottombtn" style="margin-left: 45%; border-color: #2C473E">

<a class="btn btn-light" href="/apus/board/list" role="button">자세히 보기&nbsp;&nbsp;<i class="bi bi-arrow-bar-right"></i></a>
</div>






<script>
$(function () {
    $('#dg-container').carrousel({
        current: 0,
        autoplay: true,
        interval: 2000
    });
});
!function(e,n,t){function r(e,n){return typeof e===n}function o(){var e,n,t,o,s,i,f;for(var a in y){if(e=[],n=y[a],n.name&&(e.push(n.name.toLowerCase()),n.options&&n.options.aliases&&n.options.aliases.length))for(t=0;t<n.options.aliases.length;t++)e.push(n.options.aliases[t].toLowerCase());for(o=r(n.fn,"function")?n.fn():n.fn,s=0;s<e.length;s++)i=e[s],f=i.split("."),1===f.length?Modernizr[f[0]]=o:(!Modernizr[f[0]]||Modernizr[f[0]]instanceof Boolean||(Modernizr[f[0]]=new Boolean(Modernizr[f[0]])),Modernizr[f[0]][f[1]]=o),C.push((o?"":"no-")+f.join("-"))}}function s(){return"function"!=typeof n.createElement?n.createElement(arguments[0]):_?n.createElementNS.call(n,"http://www.w3.org/2000/svg",arguments[0]):n.createElement.apply(n,arguments)}function i(){var e=n.body;return e||(e=s(_?"svg":"body"),e.fake=!0),e}function f(e,t,r,o){var f,a,u,l,p="modernizr",d=s("div"),c=i();if(parseInt(r,10))for(;r--;)u=s("div"),u.id=o?o[r]:p+(r+1),d.appendChild(u);return f=s("style"),f.type="text/css",f.id="s"+p,(c.fake?c:d).appendChild(f),c.appendChild(d),f.styleSheet?f.styleSheet.cssText=e:f.appendChild(n.createTextNode(e)),d.id=p,c.fake&&(c.style.background="",c.style.overflow="hidden",l=S.style.overflow,S.style.overflow="hidden",S.appendChild(c)),a=t(d,e),c.fake?(c.parentNode.removeChild(c),S.style.overflow=l,S.offsetHeight):d.parentNode.removeChild(d),!!a}function a(e,n){return!!~(""+e).indexOf(n)}function u(e){return e.replace(/([a-z])-([a-z])/g,function(e,n,t){return n+t.toUpperCase()}).replace(/^-/,"")}function l(e){return e.replace(/([A-Z])/g,function(e,n){return"-"+n.toLowerCase()}).replace(/^ms-/,"-ms-")}function p(n,r){var o=n.length;if("CSS"in e&&"supports"in e.CSS){for(;o--;)if(e.CSS.supports(l(n[o]),r))return!0;return!1}if("CSSSupportsRule"in e){for(var s=[];o--;)s.push("("+l(n[o])+":"+r+")");return s=s.join(" or "),f("@supports ("+s+") { #modernizr { position: absolute; } }",function(e){return"absolute"==getComputedStyle(e,null).position})}return t}function d(e,n,o,i){function f(){d&&(delete k.style,delete k.modElem)}if(i=r(i,"undefined")?!1:i,!r(o,"undefined")){var l=p(e,o);if(!r(l,"undefined"))return l}for(var d,c,m,v,h,y=["modernizr","tspan"];!k.style;)d=!0,k.modElem=s(y.shift()),k.style=k.modElem.style;for(m=e.length,c=0;m>c;c++)if(v=e[c],h=k.style[v],a(v,"-")&&(v=u(v)),k.style[v]!==t){if(i||r(o,"undefined"))return f(),"pfx"==n?v:!0;try{k.style[v]=o}catch(g){}if(k.style[v]!=h)return f(),"pfx"==n?v:!0}return f(),!1}function c(e,n){return function(){return e.apply(n,arguments)}}function m(e,n,t){var o;for(var s in e)if(e[s]in n)return t===!1?e[s]:(o=n[e[s]],r(o,"function")?c(o,t||n):o);return!1}function v(e,n,t,o,s){var i=e.charAt(0).toUpperCase()+e.slice(1),f=(e+" "+P.join(i+" ")+i).split(" ");return r(n,"string")||r(n,"undefined")?d(f,n,o,s):(f=(e+" "+T.join(i+" ")+i).split(" "),m(f,n,t))}function h(e,n,r){return v(e,t,t,n,r)}var y=[],g={_version:"3.0.0",_config:{classPrefix:"",enableClasses:!0,enableJSClass:!0,usePrefixes:!0},_q:[],on:function(e,n){var t=this;setTimeout(function(){n(t[e])},0)},addTest:function(e,n,t){y.push({name:e,fn:n,options:t})},addAsyncTest:function(e){y.push({name:null,fn:e})}},Modernizr=function(){};Modernizr.prototype=g,Modernizr=new Modernizr;var C=[],S=n.documentElement,w="CSS"in e&&"supports"in e.CSS,x="supportsCSS"in e;Modernizr.addTest("supports",w||x);var _="svg"===S.nodeName.toLowerCase(),b=g.testStyles=f,z="Moz O ms Webkit",P=g._config.usePrefixes?z.split(" "):[];g._cssomPrefixes=P;var T=g._config.usePrefixes?z.toLowerCase().split(" "):[];g._domPrefixes=T;var E={elem:s("modernizr")};Modernizr._q.push(function(){delete E.elem});var k={style:E.elem.style};Modernizr._q.unshift(function(){delete k.style}),g.testAllProps=v,g.testAllProps=h,Modernizr.addTest("csstransforms3d",function(){var e=!!h("perspective","1px",!0),n=Modernizr._config.usePrefixes;if(e&&(!n||"webkitPerspective"in S.style)){var t;Modernizr.supports?t="@supports (perspective: 1px)":(t="@media (transform-3d)",n&&(t+=",(-webkit-transform-3d)")),t+="{#modernizr{left:9px;position:absolute;height:5px;margin:0;padding:0;border:0}}",b(t,function(n){e=9===n.offsetLeft&&5===n.offsetHeight})}return e}),Modernizr.addTest("csstransforms",function(){return-1===navigator.userAgent.indexOf("Android 2.")&&h("transform","scale(1)",!0)}),o(),delete g.addTest,delete g.addAsyncTest;for(var A=0;A<Modernizr._q.length;A++)Modernizr._q[A]();e.Modernizr=Modernizr}(window,document);
(function ($) {
$.carrousel = function (options, element) {
this.$el = $(element);
this._init(options);
};
$.carrousel.defaults = {
current: 0, // index of current item
autoplay: true,// slideshow on / off
interval: 2000  // time between transitions
};
$.carrousel.prototype = {
// 初始化
_init: function (options) {
    this.options = $.extend(true, {}, $.carrousel.defaults, options);
    this.support3d = Modernizr.csstransforms3d;
    this.support2d = Modernizr.csstransforms;
    this.$wrapper = this.$el.find('.dg-wrapper');
    this.$items = this.$wrapper.children();
    this.itemsCount = this.$items.length;
    this.$nav = this.$el.find('nav');
    this.$navPrev = this.$nav.find('.dg-prev');
    this.$navNext = this.$nav.find('.dg-next');
    this.button = $('#lightButton li');
    this.box = $('.banner');
    this.imgWidth = $('.banner .dg-wrapper img').width();
    this.indexB = 0;
    this.CSSX = 0;
    this.CSSXout = 0;
    this.button[0].classList.add('light');
    this.current = this.options.current;
    this.isAnim = false;
    this.$items.css({
        'opacity': 1
    });
    this._updateWidth();
    this._layout();
    // load the events
    this._loadEvents();
    // slideshow
    if (this.options.autoplay) {
        this._startSlideshow();
    }
    var _self = this;
    for (var i = 0, len = this.button.length; i < len; i++) {     // 点击小圆点
        this.button[i].addEventListener('click', function() {
            var toIndex = parseInt(this.getAttribute('index'));
            var toMove = toIndex - _self.indexB;
            switch (toMove) {
                case 0:
                    break;
                case 1:
                    _self._navigate('next', 'dg-transition');
                    break;
                case -1:
                    _self._navigate('prev', 'dg-transition');
                    break;
                default:
                    _self._stopSlideshow();
                    var bTime = setInterval(function () {
                        if (!_self.isAnim) {
                            if (!toMove) {
                                clearInterval(bTime);
                                if (_self.options.autoplay) {
                                    _self._startSlideshow();
                                }
                            }
                            else if (toMove > 0) {
                                _self._navigate('next', 'dg-transition-fast');
                                toMove--;
                            }
                            else if (toMove < 0) {
                                _self._navigate('prev', 'dg-transition-fast');
                                toMove++;
                            }
                        }
                    }, 0);
                    break;
            }
        });
    }
},
// 自适应宽度
_updateWidth: function () {
    if (this.support3d) {
        if (document.body.clientWidth < 1000) {
            this.CSSX = ($(this.box).width()  - 10 - this.imgWidth * 0.7) / 2;
            this.CSSXout = 0;
        }
        else if (document.body.clientWidth >= 1000) {
            this.CSSX = ($(this.box).width() * 1.2 + 480 - 10 - this.imgWidth * 0.5) / 2 * 0.5;
            this.CSSXout = ($(this.box).width() * 1.2 + 480 - 10 - this.imgWidth * 0.5) / 2;
        }
    }
    else if (this.support2d) {
        if (document.body.clientWidth < 1464) {
            this.CSSX = ($(this.box).width() - 10 - this.imgWidth * 0.9) / 2;
            this.CSSXout = 0;
        }
        else if (document.body.clientWidth >= 1464) {
            this.CSSX = (($(this.box).width() - 10 - this.imgWidth * 0.8) / 2) * 0.7;
            this.CSSXout = ($(this.box).width() - 10 - this.imgWidth * 0.8) / 2;
        }
    }
},
// 显示小圆点
_showButton: function () {
    var _self = this;
    for (var i = 0, len = _self.button.length; i < len; i++) {
        if (_self.button[i].classList.contains('light')) {
            _self.button[i].classList.remove('light');
            break;
        }
    }
    _self.button[_self.indexB].classList.add('light');
},
// 用来绑定点击事件
_click: function (element, move) {
    var _self = this;
    element.off('click.gallery');
    element.on('click.gallery', function () {
        if (!this.isAnim) {
            _self._navigate(move);
            if (_self.options.autoplay) {
                _self._startSlideshow();
            }
        }
    });
},
// 初始样式
_layout: function () {
    this._setItems();
    this.$leftItm.css(this._getCoordinates('left'));
    this.$rightItm.css(this._getCoordinates('right'));
    this.$currentItm.css(this._getCoordinates('center')).addClass('dg-center');
    this._click(this.$leftItm, 'prev');
    this._click(this.$prevItm, 'prev');
    this.$currentItm.off('click.carrousel');
    this._click(this.$rightItm, 'next');
    this._click(this.$nextItm, 'next');
    this.$nextItm.css(this._getCoordinates('outright'));
    this.$prevItm.css(this._getCoordinates('outleft'));
    this.$currentItm[0].href = this.$currentItm[0].getAttribute('link');
},
// 更新图片位置
_setItems: function () {
    this.$items.removeClass('dg-center');
    this.$currentItm = this.$items.eq(this.current);
    this.$leftItm = ( this.current === 0 ) ? this.$items.eq(this.itemsCount - 1) : this.$items.eq(this.current - 1);
    this.$rightItm = ( this.current === this.itemsCount - 1 ) ? this.$items.eq(0) : this.$items.eq(this.current + 1);
    this.$nextItm = ( this.$rightItm.index() === this.itemsCount - 1 ) ? this.$items.eq(0) : this.$rightItm.next();
    this.$prevItm = ( this.$leftItm.index() === 0 ) ? this.$items.eq(this.itemsCount - 1) : this.$leftItm.prev();
},
_loadEvents: function () {
    var _self = this;
    this.$navPrev.on('click.carrousel', function () {
        _self._navigate('prev');
        return false;
    });
    this.$navNext.on('click.carrousel', function () {
        _self._navigate('next');
        return false;
    });
    this.$wrapper.on('webkitTransitionEnd.carrousel transitionend.carrousel OTransitionEnd.carrousel', function () {
        _self.$currentItm.addClass('dg-center');
        _self.$items.removeClass('dg-transition');
        _self.$items.removeClass('dg-transition-fast');
        _self.isAnim = false;
        // 处理中间元素的href
        _self.$currentItm[0].href = _self.$currentItm[0].getAttribute('link');
        _self.$leftItm[0].href = '#';
        _self.$rightItm[0].href = '#';
        // 处理左右元素的点击事件
        _self._click(_self.$leftItm, 'prev');
        _self._click(_self.$prevItm, 'prev');
        _self.$currentItm.off('click.gallery');
        _self._click(_self.$rightItm, 'next');
        _self._click(_self.$nextItm, 'next');
    });
},
// 定义样式
_getCoordinates: function (position) {
    if (this.support3d) {
        switch (position) {
            case 'outleft':
                return {
                     'opacity': 0,
      'visibility': 'hidden'
                };
                break;
            case 'outright':
                return {
                     'opacity': 0,
      'visibility': 'hidden'
                };
                break;
            case 'left':
                return {
                    '-webkit-transform': 'translateX(-' + this.CSSX + 'px) translateZ(-300px) rotateY(25deg)',
                    '-moz-transform': 'translateX(-' + this.CSSX + 'px) translateZ(-300px) rotateY(25deg)',
                    '-o-transform': 'translateX(-' + this.CSSX + 'px) translateZ(-300px) rotateY(25deg)',
                    '-ms-transform': 'translateX(-' + this.CSSX + 'px) translateZ(-300px) rotateY(25deg)',
                    'transform': 'translateX(-' + this.CSSX + 'px) translateZ(-300px) rotateY(25deg)',
                    'opacity': 1,
                    'visibility': 'visible'
                };
                break;
            case 'right':
                return {
                    '-webkit-transform': 'translateX(' + this.CSSX + 'px) translateZ(-300px) rotateY(-25deg)',
                    '-moz-transform': 'translateX(' + this.CSSX + 'px) translateZ(-300px) rotateY(-25deg)',
                    '-o-transform': 'translateX(' + this.CSSX + 'px) translateZ(-300px) rotateY(-25deg)',
                    '-ms-transform': 'translateX(' + this.CSSX + 'px) translateZ(-300px) rotateY(-25deg)',
                    'transform': 'translateX(' + this.CSSX + 'px) translateZ(-300px) rotateY(-25deg)',
                    'opacity': 1,
                    'visibility': 'visible'
                };
                break;
            case 'center':
                return {
                    '-webkit-transform': 'translateX(0px) translateZ(0px) rotateY(0deg)',
                    '-moz-transform': 'translateX(0px) translateZ(0px) rotateY(0deg)',
                    '-o-transform': 'translateX(0px) translateZ(0px) rotateY(0deg)',
                    '-ms-transform': 'translateX(0px) translateZ(0px) rotateY(0deg)',
                    'transform': 'translateX(0px) translateZ(0px) rotateY(0deg)',
                    'opacity': 1,
                    'visibility': 'visible'
                };
                break;
            case 'hide':
                return {
                    '-webkit-transform': 'translate(0px) scale(0.7)',
                    'opacity': 1,
                    'visibility': 'visible',
                    'z-index': 1
                };
                break;
        }
    }
    else if (this.support2d) {
        switch (position) {
            case 'outleft':
                return {
                    '-webkit-transform': 'translate(-' + this.CSSXout + 'px) scale(0.8)',
                    '-moz-transform': 'translate(-' + this.CSSXout + 'px) scale(0.8)',
                    '-o-transform': 'translate(-' + this.CSSXout + 'px) scale(0.8)',
                    '-ms-transform': 'translate(-' + this.CSSXout + 'px) scale(0.8)',
                    'transform': 'translate(-' + this.CSSXout + 'px) scale(0.8)',
                    'opacity': 1,
                    'z-index': 2
                };
                break;
            case 'outright':
                return {
                    '-webkit-transform': 'translate(' + this.CSSXout + 'px) scale(0.8)',
                    '-moz-transform': 'translate(' + this.CSSXout + 'px) scale(0.8)',
                    '-o-transform': 'translate(' + this.CSSXout + 'px) scale(0.8)',
                    '-ms-transform': 'translate(' + this.CSSXout + 'px) scale(0.8)',
                    'transform': 'translate(' + this.CSSXout + 'px) scale(0.8)',
                    'opacity': 1,
                    'z-index': 2
                };
                break;
            case 'left':
                return {
                    '-webkit-transform': 'translate(-' + this.CSSX + 'px) scale(0.9)',
                    '-moz-transform': 'translate(-' + this.CSSX + 'px) scale(0.9)',
                    '-o-transform': 'translate(-' + this.CSSX + 'px) scale(0.9)',
                    '-ms-transform': 'translate(-' + this.CSSX + 'px) scale(0.9)',
                    'transform': 'translate(-' + this.CSSX + 'px) scale(0.9)',
                    'opacity': 1,
                    'visibility': 'visible',
                    'z-index': 3
                };
                break;
            case 'right':
                return {
                    '-webkit-transform': 'translate(' + this.CSSX + 'px) scale(0.9)',
                    '-moz-transform': 'translate(' + this.CSSX + 'px) scale(0.9)',
                    '-o-transform': 'translate(' + this.CSSX + 'px) scale(0.9)',
                    '-ms-transform': 'translate(' + this.CSSX + 'px) scale(0.9)',
                    'transform': 'translate(' + this.CSSX + 'px) scale(0.9)',
                    'opacity': 1,
                    'visibility': 'visible',
                    'z-index': 3
                };
                break;
            case 'center':
                return {
                    '-webkit-transform': 'translate(0px) scale(1)',
                    '-moz-transform': 'translate(0px) scale(1)',
                    '-o-transform': 'translate(0px) scale(1)',
                    '-ms-transform': 'translate(0px) scale(1)',
                    'transform': 'translate(0px) scale(1)',
                    'opacity': 1,
                    'visibility': 'visible',
                    'z-index': 4
                };
            case 'hide':
                return {
                    '-webkit-transform': 'translate(0px) scale(0.7)',
                    '-moz-transform': 'translate(0px) scale(0.7)',
                    '-o-transform': 'translate(0px) scale(0.7)',
                    '-ms-transform': 'translate(0px) scale(0.7)',
                    'transform': 'translate(0px) scale(0.7)',
                    'opacity': 1,
                    'visibility': 'visible',
                    'z-index': 1
                }
                break;
        }
    }
},
// navigate
_navigate: function (dir, speedClass) {
    speedClass = speedClass || 'dg-transition';
    if (!this.isAnim) {
        this._updateWidth();
        this.isAnim = true;
        var _self = this;
        switch (dir) {
            case 'next' :
                this.indexB++;
                if (this.indexB === this.itemsCount) {
                    this.indexB = 0;
                }
                this._showButton();
                this.current = this.$rightItm.index();
                // current item moves left
                this.$currentItm.addClass(speedClass).css(this._getCoordinates('left'));
                // right item moves to the center
                this.$rightItm.addClass(speedClass).css(this._getCoordinates('center'));
                // left item moves out
                this.$leftItm.addClass(speedClass).css(this._getCoordinates('outleft'));
                this.$nextItm.addClass(speedClass).css(this._getCoordinates('right'));
                if (this.itemsCount > 5) {
                    this.$prevItm.addClass(speedClass).css(this._getCoordinates('hide'));
                    this.$prevItm.off('click.carrousel');
                }
                var nextEle = ( this.$nextItm.index() === this.itemsCount - 1 ) ? this.$items.eq(0) : this.$nextItm.next();
                $(nextEle).addClass(speedClass).css(this._getCoordinates('outright'));
                $(nextEle).off('click.carrousel');
                break;
            case 'prev' :
                this.indexB--;
                if (this.indexB === -1) {
                    this.indexB = this.itemsCount - 1;
                }
                this._showButton();
                this.current = this.$leftItm.index();
                // current item moves right
                this.$currentItm.addClass(speedClass).css(this._getCoordinates('right'));
                // left item moves to the center
                this.$leftItm.addClass(speedClass).css(this._getCoordinates('center'));
                // right item moves out
                this.$rightItm.addClass(speedClass).css(this._getCoordinates('outright'));
                this.$prevItm.addClass(speedClass).css(this._getCoordinates('left'));
                if (this.itemsCount > 5) {
                    this.$nextItm.addClass(speedClass).css(this._getCoordinates('hide'));
                    this.$nextItm.off('click.carrousel');
                }
                var prevEle = ( this.$prevItm.index() === 0 ) ? this.$items.eq(this.itemsCount - 1) : this.$prevItm.prev();
                $(prevEle).addClass(speedClass).css(this._getCoordinates('outleft'));
                $(prevEle).off('click.carrousel');
                break;
        }
        ;
        this._setItems();
    }
},
// auto slide
_startSlideshow: function () {
    if (this.slideshow) {
        clearInterval(this.slideshow);
    }
    var _self = this;
    this.slideshow = setInterval(function () {
        if ($('.dg-center')[0] && !_self.isAnim) {
            _self._navigate('next');
        }
    }, this.options.interval);
},
_stopSlideshow: function () {
    clearTimeout(this.slideshow);
}
};
$.fn.carrousel = function (options) {
if (typeof options === 'object') {
    this.each(function () {
        var instance = $.data(this, 'carrousel');
        if (!instance) {
            $.data(this, 'carrousel', new $.carrousel(options, this));
        }
    });
}
else if (typeof options === 'string') {
    this.each(function () {
        var instance = $.data(this, 'carrousel');
        if (instance) {
            switch (options) {
                case 'play':
                    instance._startSlideshow();
                    instance.options.autoplay = true;
                    break;
                case 'stop':
                    instance._stopSlideshow();
                    instance.options.autoplay = false;
                    break;
                case 'next':
                    instance._navigate('next');
                    break;
                case 'prev':
                    instance._navigate('prev');
                    break;
            }
        }
    });
}
else if (typeof options === 'number') {
    this.each(function () {
        var instance = $.data(this, 'carrousel');
        instance.button[options].click();
    });
}
return this;
};
})(jQuery);
</script>

    <script src="${contextPath}/js/jquery.min.js"></script>
    <script src="${contextPath}/js/popper.js"></script>
    <script src="${contextPath}/js/bootstrap.min.js"></script>
    <script src="${contextPath}/js/owl.carousel.min.js"></script>
    <script src="${contextPath}/js/main.js"></script>

