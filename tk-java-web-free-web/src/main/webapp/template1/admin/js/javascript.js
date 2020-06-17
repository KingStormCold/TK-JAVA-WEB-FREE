$(document).ready(function(){
    
});
$('#avatar-img').click(function(){
    $('.personal-info-header').slideToggle(200);
});

$('.icon-menu').click(function(){
    alert("CLick Click");
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