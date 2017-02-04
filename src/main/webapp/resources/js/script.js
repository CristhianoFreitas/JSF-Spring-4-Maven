fixSticky = function() {
//    var menuFixo=PF('menuFixo');
//    menuFixo.restore();
//    menuFixo.target.attr("style","");
//    menuFixo.init(menuFixo.cfg);
//    if($(window).scrollTop() > menuFixo.initialState.top) {menuFixo.fix();} else {menuFixo.restore();}
};

scrollTopo = function(){
	$('html, body').animate({scrollTop:0}, 'slow');
    return false;
};