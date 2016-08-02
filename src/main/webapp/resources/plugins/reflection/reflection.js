/**
 * Created by hoshen.mahmud on 17-Mar-16.
 */
/*======================================================================================================================================================*/

$(document).ready(function () {

    $(".collapse-btn").click(function () {
        $(this).closest("legend").next(".collapse-block").slideToggle("slow", function () {
            $(this).find("i").toggleClass("fa fa-minus fa fa-plus");
        });
    });

    $("#btnExport").click(function (e) {
        window.open('data:application/vnd.ms-excel,' + $('#example').jsp());
        //Response.AddHeader("Content-Disposition", "attachment;filename=download.xlsx");
        //x-fast
        //e.preventDefault();
    });
});

/*document.onscroll = function () {
 if ($(window).scrollTop() >= headerHeight + 75) {
 $('.navbar').removeClass('navbar-static-top').addClass('navbar-fixed-top');
 }
 else {
 $('.navbar').removeClass('navbar-fixed-top').addClass('navbar-static-top');
 }
 }
 document.onscroll = function () {
 var header = $('header.banner')
 if ($(window).scrollTop() > headerHeight + 75) {
 header.animate({
 // place your own css styles here
 opacity: 0.5,
 }, 5000, function () {
 header.removeClass('navbar-static-top').addClass('navbar-fixed-top');
 })
 }
 else {
 header.removeClass('navbar-fixed-top').addClass('navbar-static-top');
 }
 }*/
/*======================================================================================================================================================*/