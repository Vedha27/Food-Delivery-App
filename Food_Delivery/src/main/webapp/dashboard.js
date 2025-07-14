function toggleMenu() {
   const nav = document.getElementById("navLinks");
   nav.classList.toggle("active");
 }

 // Close button logic
 document.addEventListener("DOMContentLoaded", () => {
   document.querySelector(".close-btn").addEventListener("click", () => {
     document.getElementById("navLinks").classList.remove("active");
   });
 });