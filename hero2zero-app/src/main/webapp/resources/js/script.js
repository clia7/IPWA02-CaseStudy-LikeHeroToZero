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

window.onscroll = function() {scrollFunction()};

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

//SWITCH DIRECTION
const rtlLangs = ['ar', 'he', 'fa', 'ur'];
const userLang = navigator.language || navigator.userLanguage;

if (rtlLangs.some(code => userLang.startsWith(code))) {
  document.documentElement.setAttribute("dir", "rtl");
  document.documentElement.setAttribute("lang", userLang);
} else {
  document.documentElement.setAttribute("dir", "ltr");
  document.documentElement.setAttribute("lang", userLang);
}

  //SEARCH TABLE
  function filterfunction() {
    var input, filter, table, tr, td, i, j, txtValue, rowMatches;
    input = document.getElementById("filterInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("emissionTable");
    tr = table.getElementsByTagName("tr");
  
    for (i = 0; i < tr.length; i++) {
      td = tr[i].getElementsByTagName("td");
      rowMatches = false;
  
      for (j = 0; j < td.length; j++) {
        txtValue = td[j].textContent || td[j].innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
          rowMatches = true;
          break;
        }
      }
  
      tr[i].style.display = rowMatches ? "" : "none";
    }
  }

    // FILTER TABLE
let sortDirection = {};

function sortFunction(columnIndex) {
  const table = document.getElementById("emissionTable");
  const tbody = table.tBodies[0];
  const rows = Array.from(tbody.rows);

  sortDirection[columnIndex] = !sortDirection[columnIndex];

  rows.sort((a, b) => {
    let valA = a.cells[columnIndex].innerText.trim();
    let valB = b.cells[columnIndex].innerText.trim();


    let numA = parseFloat(valA.replace(/,/g, ""));
    let numB = parseFloat(valB.replace(/,/g, ""));
    let isNumeric = !isNaN(numA) && !isNaN(numB);

    if (isNumeric) {
      return sortDirection[columnIndex] ? numA - numB : numB - numA;
    } else {
      return sortDirection[columnIndex]
        ? valA.localeCompare(valB)
        : valB.localeCompare(valA);
    }
  });

  rows.forEach(row => tbody.appendChild(row));
}

//FORM VALIDATIOM FOR MAIL
const regMail = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;

const inputMail = document.querySelector('#email');
const errorDivMail = inputMail.nextElementSibling;

let errorMessageMail = [];

inputMail.addEventListener("input", checkInputMail);
document.forms[0].addEventListener("submit", function (e) {
    e.preventDefault();
    checkInputMail();
});


function checkInputMail() {
    let errorMail = [];
    errorMessageMail.length = 0; 

    errorMail.push(checkEmpty(inputMail, errorMessageMail));
    errorMail.push(checkMinMax(inputMail, 3, 100, errorMessageMail));
    errorMail.push(checkMail(inputMail, errorMessageMail));

    if (errorMail.includes(false)) {
        inputMail.classList.add("error");
        errorDivMail.textContent = errorMessageMail.join("");
        inputMail.focus(); 
    } else {
        inputMail.classList.remove("error");
        errorDivMail.textContent = "";

        const safeEmail = escapeHTML(inputMail.value);


        console.log("filtered input", safeEmail);
    }
}

function checkEmpty(inp, arrayMessage) {
    if (inp.value.trim() === "") {
        arrayMessage[0] = "This is a required field\n";
        return false;
    } else {
        arrayMessage[0] = "";
        return true;
    }
}

function checkMinMax(inp, min, max, arrayMessage) {
    if (inp.value.length < min) {
        arrayMessage[1] = `At least ${min} characters\n`;
        return false;
    } else if (inp.value.length > max) {
        arrayMessage[2] = `Maximum ${max} characters\n`;
        return false;
    } else {
        arrayMessage[1] = "";
        arrayMessage[2] = "";
        return true;
    }
}

function checkMail(inp, arrayMessage) {
    if (!regMail.test(inp.value)) {
        arrayMessage[3] = "Format invalid\n";
        return false;
    } else {
        arrayMessage[3] = "";
        return true;
    }
}

//ESCAPE FUNCTION
function escapeHTML(input) {
  return input
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&#039;");
}