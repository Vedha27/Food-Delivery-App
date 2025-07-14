// ✅ Toggle Menu (opens sidebar)
function toggleMenu() {
  document.getElementById("navLinks").classList.add("active");
}

// ✅ Wait until the page is fully loaded
document.addEventListener("DOMContentLoaded", function () {
  // ✅ Close sidebar when clicking close button
  const closeBtn = document.querySelector(".close-btn");
  if (closeBtn) {
    closeBtn.addEventListener("click", function () {
      document.getElementById("navLinks").classList.remove("active");
    });
  }

  // ✅ Handle cart button actions (+, −, Remove)
  document.querySelectorAll(".cart-btn").forEach(btn => {
    btn.addEventListener("click", function () {
      const action = this.getAttribute("data-action");
      const container = this.closest(".cart-actions");
      const itemId = container.getAttribute("data-item-id");
      const restaurantId = container.getAttribute("data-restaurant-id");

      // ✅ AJAX request to cart servlet
      fetch("cart", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `action=${action}&itemId=${itemId}&restaurantId=${restaurantId}`
      })
        .then(response => {
          if (!response.ok) {
            throw new Error("Cart update failed.");
          }
          return response.text();
        })
        .then(() => {
          // ✅ Reload the page or just refresh cart part later
          location.reload();
        })
        .catch(err => {
          console.error("Error:", err);
          alert("Something went wrong updating the cart.");
        });
    });
  });
});
