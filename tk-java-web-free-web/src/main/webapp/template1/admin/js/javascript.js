$(document).ready(function(){
    
});
function getVal(selector) {
  return $(selector).val();
}
$('#avatar-img').click(function(){
    $('.personal-info-header').slideToggle(200);
});

$('.icon-menu').click(function(){
	if ($('.logo-header').hasClass("hidden-menu")) {
		$('.logo-header').removeClass("hidden-menu");
//		$('.logo-header').addClass("col-sm-2");
//		$('.menu-header').removeClass("col-sm-11");
//		$('.menu-header').addClass("col-sm-9");
		$('.main-menu').removeClass("hidden-menu");
	} else {
		$('.logo-header').addClass("hidden-menu");
		$('.main-menu').addClass("hidden-menu");
//		$('.logo-header').removeClass("col-sm-2");
//		$('.menu-header').removeClass("col-sm-9");
//		$('.menu-header').addClass("col-sm-11");
	}
});

$('.main-auth-icon').click(function(){
    if ($('.main-auth-icon').hasClass("fa-plus")) {
        removeIconPlusAndAdIconMinus(this, '.main-auth-list');
    } else {
        removeIconMinusAndAdIconPlus(this, '.main-auth-list');
    }
});

$('.menu-manager-icon').click(function(){
    if ($('.menu-manager-icon').hasClass("fa-plus")) {
        removeIconPlusAndAdIconMinus(this, '.main-menu-manager-list');
    } else {
        removeIconMinusAndAdIconPlus(this, '.main-menu-manager-list');
    }
});

function removeIconMinusAndAdIconPlus(icon, ul){
    $(ul).animate({"opacity": "1"}, 200 )
    $(ul).show(1000);
    $(icon).removeClass("fa-minus");
    $(icon).addClass("fa-plus");
};

function removeIconPlusAndAdIconMinus(icon, ul){
    $(ul).animate({ "opacity": "0.2" }, 200 );
    $(ul).hide(1000);
    $(icon).removeClass("fa-plus");
    $(icon).addClass("fa-minus");
};
function openCreate(selector) {
  $(selector)
    .addClass("disabled-button")
    .prop("disabled", true);
};
function closeCreate(selector) {
  $(selector)
    .removeClass("disabled-button")
  	.prop("disabled", false);
};