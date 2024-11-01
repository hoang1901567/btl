// Chế độ tối
function toggleDarkMode() {
    document.body.classList.toggle('dark-mode');
  }
  
  }
  
  // Thông báo chào mừng
  function welcomeMessage() {
    alert("Chào mừng bạn đến với trang cá nhân của tôi!");
  }
  
  // Phóng to/thu nhỏ ảnh
  function toggleImageSize(image) {
    image.classList.toggle('large-image');
  }
  
  // Gọi các hàm khi trang được tải
  window.onload = function() {
    showDate();
    welcomeMessage();
  };
  