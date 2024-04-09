// $("[data-toggle=tooltip]").tooltip();
// Kiểm tra nếu đường dẫn là '/login'
document.addEventListener('DOMContentLoaded', (event) => {
    if (window.location.pathname === '/login') {
        var alertBox = document.querySelector('.alert-danger');
        if(alertBox) {
            alertBox.classList.add('alert-fade-in');
            setTimeout(function() {
                alertBox.classList.remove('alert-fade-in');
                alertBox.classList.add('alert-fade-out');
            }, 1000);
        }
    }
});
