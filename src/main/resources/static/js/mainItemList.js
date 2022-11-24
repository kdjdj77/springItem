var swiper = new Swiper(".mySwiper", {
	slidesPerView: 1,
	centeredSlides: false,
	slidesPerGroupSkip: 1,
	grabCursor: false,
	keyboard: {
		enabled: true,
	},
	allowTouchMove : false,
	breakpoints: {
		769: {
			slidesPerView: 2,
			slidesPerGroup: 2,
		},
	},
	autoplay: {   
          delay: 2500,
          disableOnInteraction: false,
        },
	loop : true, 
	loopAdditionalSlides : 1,  
	navigation: {
		nextEl: ".swiper-button-next",
		prevEl: ".swiper-button-prev",
	},
	pagination: {
		el: ".swiper-pagination",
		clickable: true,
	},
});