/**
 * 
 */
$(document).ready(function() {
	
var t1 = gsap.timeline();
	
	t1.from("#imgApp", {opacity: 0.5, duration: 0.4, ease: Expo.easeOut, x: -400});
	t1.from(".stag", {opacity: 0.5, duration: 0.1, ease: Expo.easeOut, y: 300});
	t1.to(".stag", {opacity: 1, ease: Expo.easeOut, y: 0});
	t1.to("#imgApp", {opacity: 1, ease: Expo.easeOut, x: 0});
	
	const controller = new ScrollMagic.Controller();
	
	const scene = new ScrollMagic.Scene({
		triggerElement: '#appPromo',
		duration: 1500,
		triggerHook: 0.8
	})
	.setTween(t1)
	.addTo(controller);
	
	var t2 = new TimelineMax();
	
	t2.from('.outer', 0.25, { scaleX: 0 });
	t2.from(".stagger", {opacity: 0.5, duration: 0.3, ease: Expo.easeOut, y: 300});
	t2.to(".stagger", {opacity: 1, ease: Expo.easeOut, y: 0});
	t2.from('.inner', 0.65, { yPercent: 50, ease: Back.easeOut });

	const controller2 = new ScrollMagic.Controller();
	
	const scene2 = new ScrollMagic.Scene({
		triggerElement: '#aboutus',
		duration: 1500,
		triggerHook: 0.5
	})
	.setTween(t2)
	.addTo(controller2);
	
});