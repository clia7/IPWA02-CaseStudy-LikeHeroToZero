'use strict';

//NAVBAR
document.addEventListener('click', function (event) {
  const navbarToggler = document.querySelector('.navbar-toggler');
  const navbarCollapse = document.querySelector('.navbar-collapse');

  const isClickInsideNavbar = navbarCollapse.contains(event.target) || navbarToggler.contains(event.target);

  if (!isClickInsideNavbar && navbarCollapse.classList.contains('show')) {
    navbarToggler.click();
  }
});


//BACK TO TOP BUTTON
let back_btn = document.getElementById("back_btn");

window.onscroll = function () { scrollFunction() };

function scrollFunction() {
  if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
    back_btn.style.display = "block";
  } else {
    back_btn.style.display = "none";
  }
}

function topFunction() {
  window.scrollTo({
    top: 0,
    behavior: "smooth"
  });
}
