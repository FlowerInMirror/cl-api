; $(function ($, window, document, undefined) {
    
    Slider = function (container, options) {
        /*
        options = {
            auto: true,
            time: 3000,
            event: 'hover' | 'click',
            mode: 'slide | fade',
            controller: $(),
            activeControllerCls: 'className',
            exchangeEnd: $.noop
        }
        */

        "use strict"; //stirct mode not support by IE9-

        if (!container) return;

        var options = options || {},
            currentIndex = 0,
            cls = options.activeControllerCls,
            delay = options.delay,
            isAuto = options.auto,
            controller = options.controller,
            event = options.event,
            interval,
            slidesWrapper = container.children().first(),
            slides = slidesWrapper.children(),
            length = slides.length,
            childWidth = container.width(),
            totalWidth = childWidth * slides.length;

        function init() {
            var controlItem = controller.children();

            mode();

            event == 'hover' ? controlItem.mouseover(function () {
                stop();
                var index = $(this).index();

                play(index, options.mode);
            }).mouseout(function () {
                isAuto && autoPlay();
            }) : controlItem.click(function () {
                stop();
                var index = $(this).index();

                play(index, options.mode);
                isAuto && autoPlay();
            });

            isAuto && autoPlay();
        }

        //animate mode
        function mode() {
            var wrapper = container.children().first();

            options.mode == 'slide' ? wrapper.width(totalWidth) : wrapper.children().css({
                'position': 'absolute',
                'left': 0,
                'top': 0
            })
                .first().siblings().hide();
        }

        //auto play
        function autoPlay() {
            interval = setInterval(function () {
                triggerPlay(currentIndex);
            }, options.time);
        }

        //trigger play
        function triggerPlay(cIndex) {
            var index;

            (cIndex == length - 1) ? index = 0 : index = cIndex + 1;
            play(index, options.mode);
        }

        //play
        function play(index, mode) {
            slidesWrapper.stop(true, true);
            slides.stop(true, true);

            mode == 'slide' ? (function () {
                if (index > currentIndex) {
                    slidesWrapper.animate({
                        left: '-=' + Math.abs(index - currentIndex) * childWidth + 'px'
                    }, delay);
                } else if (index < currentIndex) {
                    slidesWrapper.animate({
                        left: '+=' + Math.abs(index - currentIndex) * childWidth + 'px'
                    }, delay);
                } else {
                    return;
                }
            })() : (function () {
                if (slidesWrapper.children(':visible').index() == index) return;
                slidesWrapper.children().fadeOut(delay).eq(index).fadeIn(delay);
            })();

            try {
                controller.children('.' + cls).removeClass(cls);
                controller.children().eq(index).addClass(cls);
            } catch (e) { }

            currentIndex = index;

            options.exchangeEnd && typeof options.exchangeEnd == 'function' && options.exchangeEnd.call(this, currentIndex);
        }

        //stop
        function stop() {
            clearInterval(interval);
        }

        //prev frame
        function prev() {
            stop();

            currentIndex == 0 ? triggerPlay(length - 2) : triggerPlay(currentIndex - 2);

            isAuto && autoPlay();
        }

        //next frame
        function next() {
            stop();

            currentIndex == length - 1 ? triggerPlay(-1) : triggerPlay(currentIndex);

            isAuto && autoPlay();
        }

        //init
        init();

        //expose the Slider API
        return {
            prev: function () {
                prev();
            },
            next: function () {
                next();
            }
        }
    };
/*閫夐」鍗�*/
    tab=function(options){
        var defaults={
            liCur:"liCur",
            liClass:"li",
            boxClass:"select"
        };
        var opts=$.extend(defaults,options);
        $(opts.liClass).each(function(index){
            var _this=this;
            $(_this).click(function(){
                $(_this).addClass(opts.liCur).siblings().removeClass(opts.liCur);
                $(opts.boxClass).eq(index).show();
                $(opts.boxClass).eq(index).siblings().hide();
            })
        });
    };
    /*鍐呭缈婚〉*/
    turnPage= function (container,options) {
        var defaults={
            //nextPage:"nextPage",
            //lastPage:"lastPage",
            //ulBox:"listInfo",//楂樺害涓�288鐨刣iv
            //ulH:"listBox"//鍖呭惈li锛岀敤margin-top鏉ユ帶鍒舵樉绀虹殑div
        };
        if (!container) return;
        var options = options || {},
            step=0,
            event = options.event,
            ulBox=container.children().first(),
            ulH=ulBox.children(),
            boxH=ulBox.height(),//澶栭潰鏄剧ず鍖哄煙鐨勯珮搴�
            contentH=ulH.height();//鍐呭鐨勬€婚珮搴�
        //console.log(contentH);
        function next(){
            var _that=container;
            if(contentH>boxH){
                step++;
                _that.find(ulH).stop().animate({"margin-top":-boxH*step},300);
                var lastH=contentH-boxH;//鏈€鍚庝竴姝ヤ笂绉荤殑楂樺害
                var olastH="-"+lastH;
                //console.log(olastH);
                if(contentH<boxH*(step+1)){
                    _that.find(ulH).stop().animate({"margin-top":olastH},0);
                }
            }else{
                _that.find(ulH).css({"margin-top":0});
            }
        }
        var i=0;
            function last(){
                var _that=container;
                step--;
                i++;
                _that.find(ulH).stop().animate({"margin-top":-contentH + (i + 1) * boxH},300);
                if (step <= 0) {
                    step = 0;
                    i = 0;
                    _that.find(ulH).stop().animate({ "margin-top": 0 }, 300);
                }
            }
        return {
            next:function(){
                next();
            },
            last:function(){
                last();
            }
        }
    };

}(jQuery, window, document));














