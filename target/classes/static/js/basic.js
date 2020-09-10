/**
 * 
 */

$(document).bind("contextmenu",function(e) { 
	e.preventDefault();
});

//function animate(){
//	
//	var tl = gsap.timeline();
//	
//	tl.set('.loading-screen', { transformOrigin: "bottom left"});
//	tl.to('.loading-screen', { duration: .5, scaleY: 1});
//	tl.to('.loading-screen', { duration: .5, scaleY: 0, skewX: 0, transformOrigin: "top left", ease: "power1.out", delay: 1 });
//	
//}
//
//function animOnce() {
//	
//	gsap.from("main", {duration: 1, delay: 0.6, opacity: 1, translateY: 30});
//	gsap.to("main", {duration: 1, delay: 0.6, opacity: 1, translateY: 0});
//	
////	gsap.from(".nav-anim", {duration: 1, delay: 0.1, opacity: 0, y: -2});
////	gsap.to(".nav-anim", {duration: 1, delay: 0.1, opacity: 1, y: 0});
//	
//}
//
//$(document).ready(function() {
//	animOnce();
//	/*setTimeout(() => {
//		$('main').show();
//		
//	}, 200);*/
//	
//});