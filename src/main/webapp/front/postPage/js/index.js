// Scroll to top
jQuery(document).ready(function() {
  var b = "#move-to-top";
  var c = "move-to-top";
  var a = "<i class='fa fa-angle-up'></i>";
jQuery("body").prepend("<a href='#' id='"+ c +"'>" + a + "</a>");
	var scrollDes = 'html,body';  
	//Opera d
	if(navigator.userAgent.match(/opera/i)){scrollDes = 'html';}
	//show ,hide
	jQuery(window).scroll(function(){
		if(jQuery(this).scrollTop() > 160){
			jQuery(b).addClass('filling').removeClass('hiding');
		} else {
			jQuery(b).removeClass('filling').addClass('hiding');
		}
	});
	// scroll to top when click 
	jQuery(b).click(function(e) {
        e.preventDefault();
		jQuery(scrollDes).animate({scrollTop: 0},{duration :500});
	});
});

//reload post 10+
/*//$(function() {
  var i = 2, a =".feed-box:nth-child(-n+"+i+")";
  $(".feed-box").hide();$(a).show();
  $("#k").click(function(){
    i+=2;
    var a=".feed-box:nth-child(-n+"+i+")";$(a).show();
    i>$(".feed-box").length&&$(".more-page").fadeIn(800).fadeOut(800)
  })
});
//**/

//submenu acoordion
$(function() {
var Accordion = function(el, multiple) {
this.el = el || {};
this.multiple = multiple || false;
// Variables privadas
var links = this.el.find('.link');
// Evento
links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
}
Accordion.prototype.dropdown = function(e) {
var $el = e.data.el;
$this = $(this),
$next = $this.next();
$next.slideToggle();
$this.parent().toggleClass('open');
if (!e.data.multiple) {
$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
};
}
var accordion = new Accordion($('#mobile'), false);
});

jQuery(function() {//mobile menu and desktop menu
 Hl = $(document).width();
 $("#mobil").css({right: Hl});
 $("#mobil").hide();
 $(".main").click(function(){
  if($('#mobil').is(':visible')) {
   $("#mobile").removeClass("s45");
   Hl = $(document).width();
   $("#mobil").animate({right: Hl},500,function(){$("#mobil").hide();});
  }else{
   $("#mobile").addClass("s45");
   $("#mobil").show();
   Sl = $(document).width() - 250 ;
   $("#mobil").animate({right: Sl},500);
  }
 return false;
});
jQuery.fn.anchorAnimate = function(settings) {
 settings = jQuery.extend({speed : 1000}, settings);	
 return this.each(function(){
 var caller = this
 $(caller).click(function (event){
  event.preventDefault();
  var locationHref = window.location.href;
  var elementClick = $(caller).attr("href");
  var destination = $(elementClick).offset().top ;
  //hide the mobile menu
  $("#mobile").removeClass("s45");
  Hl = $(document).width();
  $("#mobil").animate({right: Hl},500,function(){$("#mobil").hide();});
$("html,body").stop().animate({ scrollTop: destination}, settings.speed, function(){
 // Detect if pushState is available
   if(history.pushState) {history.pushState(null, null, elementClick);}
  });
 return false;
 });
});
}
//hide sidebar click left icon
$("#hide").click(function(){
  $("#mobile").removeClass("s45");
  Hl = $(document).width();
  $("#mobil").animate({right: Hl},500,function(){$("#mobil").hide();});
});
//animate scroll function calll
$("#mobil a").anchorAnimate();
//main menu auto select and animate scroll
//$(".header-menu ul").singlePageNav({offset: jQuery(".header-menu").outerHeight()});
//define main menu position
$(window).on("resize scroll load",function(){
 top_banner_and_slider_height = $("#header").outerHeight(); //+ $("#mobile").outerHeight() + $("#intro").outerHeight();
        if($(document).scrollTop() < (top_banner_and_slider_height - $(window).height() + 105) ){
            menu_top = $(document).scrollTop() + $(window).height() - 105 ;
            $(".header-menu").css({"position":"absolute","z-index":"0","top":menu_top});
            $(".header-menu li").css({"background":"transparent","opacity":"0"});
        }else if( $(document).scrollTop() < top_banner_and_slider_height ){
            menu_top = 0;
            $(".header-menu").css({"position":"relative","left":"0","right":"0","z-index":"1","top":menu_top});
            $(".header-menu a").css({"font-size":"20px"});
            $(".header-menu li").css({"background":"transparent","opacity":"1"});
        }else{
            menu_top = 0;
            $(".header-menu").css({
              "position":"fixed",
              "z-index":"9",
              "width":"initial",
              "left":"0",
              "right":"0",
              "top":menu_top
            });
           $(".header-menu li").css({"background":"transparent","opacity":"1"});
           $(".header-menu a").css({
             "font-size":"20px",
             "font-weight":"700",
           });
        }
    });
});


(function() { // canvas header

    var width, height, largeHeader, canvas, ctx, circles, target, animateHeader = true;

    // Main
    initHeader();
    addListeners();

    function initHeader() {
        width = window.innerWidth;
        height = window.innerHeight;
        target = {x: 0, y: height};

        largeHeader = document.getElementById('large-header');
        largeHeader.style.height = height+'px';

        canvas = document.getElementById('header-canvas');
        canvas.width = width;
        canvas.height = height;
        ctx = canvas.getContext('2d');

        // create particles
        circles = [];
        for(var x = 0; x < width*0.5; x++) {
            var c = new Circle();
            circles.push(c);
        }
        animate();
    }

    // Event handling
    function addListeners() {
        window.addEventListener('scroll', scrollCheck);
        window.addEventListener('resize', resize);
    }

    function scrollCheck() {
        if(document.body.scrollTop > height) animateHeader = false;
        else animateHeader = true;
    }

    function resize() {
        width = window.innerWidth;
        height = window.innerHeight;
        largeHeader.style.height = height+'px';
        canvas.width = width;
        canvas.height = height;
    }

    function animate() {
        if(animateHeader) {
            ctx.clearRect(0,0,width,height);
            for(var i in circles) {
                circles[i].draw();
            }
        }
        requestAnimationFrame(animate);
    }

    // Canvas manipulation
    function Circle() {
        var _this = this;

        // constructor
        (function() {
            _this.pos = {};
            init();
            console.log(_this);
        })();

        function init() {
            _this.pos.x = Math.random()*width;
            _this.pos.y = height+Math.random()*100;
            _this.alpha = 0.1+Math.random()*0.3;
            _this.scale = 0.1+Math.random()*0.3;
            _this.velocity = Math.random();
        }

        this.draw = function() {
            if(_this.alpha <= 0) {
                init();
            }
            _this.pos.y -= _this.velocity;
            _this.alpha -= 0.0005;
            ctx.beginPath();
            ctx.arc(_this.pos.x, _this.pos.y, _this.scale*10, 0, 2 * Math.PI, false);
            ctx.fillStyle = 'rgba(255,255,255,'+ _this.alpha+')';
            ctx.fill();
        };
    }

})();

//Canvas feed-image header
$(function canvas() { 
  $.fn.thumbsAnimate = function(options) {
    if (!this.length) { return this; }
    var opts = $.extend(true, {}, $.fn.thumbsAnimate.defaults, options);
    this.each(function(index) {
    var $this = $(this);
    if ($this.data('thumbsAnim')) return true;
        $this.data('thumbsAnim', '1');            
         var w, h, lH, cvs, cx, cles, tget, aNow = true;
         var W = $('.feed-image').width();
         var H = $('.feed-image').height();
         var $wrapper = $this.wrap('<div class="anim-wrapper"></div>').parent();
         var $canvas = $('<canvas class="anim" width="'+W+'" height="'+H+'"></canvas>').insertAfter($this).attr('width', $wrapper.width()).attr('height', $wrapper.height());

         w = $canvas.innerWidth();
         h = $canvas.innerHeight();
         tget = {x: 0, y: h};
         cx = $canvas[0].getContext('2d');

         var animate = function() {
         if(aNow) {
            cx.clearRect(0,0,w,h);
             for(var i in cles) {
                cles[i].draw();
             }
           }
          requestAnimationFrame(animate);
         };

            var Circle = function() {
                var _this = this;
                var dir = opts.direction;
                if (dir == 'both' && Math.random() > 0.5) dir = 'up';
                // constructor
                (function() {
                    _this.pos = {};
                    init();
                    //console.log(_this);
                })();

                function init() {
                    _this.pos.x = Math.random()*w;
                    if (dir == 'up')
                        _this.pos.y = h+Math.random()*100;
                    else
                        _this.pos.y = -Math.random()*100;
                    _this.alpha = 0.1+Math.random()*parseFloat(opts.alpha);
                    _this.scale = 0.1+Math.random()*parseFloat(opts.size);
                    _this.velocity = Math.random()*parseFloat(opts.speed);
                }

                this.draw = function() {

                    if(_this.alpha <= 0 || _this.scale == 0 || (dir == 'up' && _this.pos.y < 0) || (dir == 'down' && _this.pos.y > h)) {
                        init();
                    }
                    if (dir == 'up')
                        _this.pos.y -= _this.velocity;
                    else
                        _this.pos.y += _this.velocity;

                    _this.alpha -= parseFloat(opts.fading);
                    _this.scale = Math.max(0, _this.scale - parseFloat(opts.scaling));
                    cx.beginPath();
                    cx.arc(_this.pos.x, _this.pos.y, _this.scale*10, 0, 2 * Math.PI, false);
                    cx.fillStyle = 'rgba('+opts.color+','+ _this.alpha+')';
                    cx.fill();
                };
            };

            // create particles
            cles = [];
            for(var x = 0; x < w*parseFloat(opts.density); x++) {
                var c = new Circle();
                cles.push(c);
            }
            animate();

            // scroll & resize listeners
            $(window).scroll(function(event) {
                if ($this.isOnScreen()) {
                    //if (!animateNow) console.log('#'+index+' started');
                    aNow = true;
                } else {
                    //if (animateNow) console.log('#'+index+' stopped');
                    aNow = false;
                }
            }).resize(function(event) {                
                w = $canvas.innerWidth();
                h = $canvas.innerHeight();
            });

        });

        return this;
    };

    // default options
    $.fn.thumbsAnimate.defaults = {
      color: '255,255,255', // stick to this format
      alpha: '0.6',size: '0.6', speed: '1',           
      fading: '0.0005',     // fade out per frame
      scaling: '0',         // scale down per frame
      density: '0.5',       // number of circles = width * density
      direction: 'up'
    };

    if (typeof $.fn.isOnScreen === 'undefined') {
        $.fn.isOnScreen = function(){
        
            var win = $(window);
            
            var viewport = {
                top : win.scrollTop(),
                left : win.scrollLeft()
            };
            viewport.right = viewport.left + win.width();
            viewport.bottom = viewport.top + win.height();
            
            var bounds = this.offset();
            bounds.right = bounds.left + this.outerWidth();
            bounds.bottom = bounds.top + this.outerHeight();
            
            return (!(viewport.right < bounds.left || viewport.left > bounds.right || viewport.bottom < bounds.top || viewport.top > bounds.bottom));
            
        };
    }

    // Init plugin
    var opts = {
        speed: 1,density: 0.4,alpha: .6,size: .6,
        fading: 0.001,  // fade out per frame
        direction: 'up' // up/down/both
    };
    $(window).load(function() {$('.feed-post-image').thumbsAnimate(opts);});

    // Apply to elements loaded via AJAX
    $( document ).ajaxComplete(function(event, xhr, settings) {
        $('.more-page').imagesLoaded(function() { $('.feed-post-image').thumbsAnimate(opts); });
    });

})(jQuery);