function toggleMenu() {
	  const nav = document.getElementById("navLinks");
	  nav.classList.toggle("active");
	}

	document.addEventListener("DOMContentLoaded", () => {
	  const closeBtn = document.querySelector(".close-btn");
	  if (closeBtn) {
	    closeBtn.addEventListener("click", () => {
	      document.getElementById("navLinks").classList.remove("active");
	    });
	  }
	});