document.addEventListener("DOMContentLoaded", function () {
  if (localStorage.getItem("darkMode") === "enabled") {
    document.body.classList.add("dark-mode");
  }
});

function toggleDarkMode() {
  document.body.classList.toggle("dark-mode");

  if (document.body.classList.contains("dark-mode")) {
    localStorage.setItem("darkMode", "enabled");
  } else {
    localStorage.setItem("darkMode", "disabled");
  }
}


document.addEventListener("DOMContentLoaded", function () {
  const sections = document.querySelectorAll("section");
  sections.forEach((section) => {
    section.querySelector("h2").addEventListener("click", function () {
      const content = section.querySelector("ul") || section.querySelector("p") || section.querySelector("img");

      if (content) {
        content.style.display = content.style.display === "none" ? "block" : "none";
      }
    });
  });
});