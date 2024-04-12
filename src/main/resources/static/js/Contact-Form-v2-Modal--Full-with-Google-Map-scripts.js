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

document.addEventListener('DOMContentLoaded', function() {
    // Handle No button click
    document.querySelector('#cancel-order-modal .btn-primary').addEventListener('click', function() {
        document.querySelector('#cancel-order-modal').style.display = 'none';
    });

    // Handle Yes button click
    document.querySelector('#cancel-order-modal .btn-light').addEventListener('click', function() {
        var selectedOrders = [];
        document.querySelectorAll('input[name="checkbox"]:checked').forEach(function(checkbox) {
            selectedOrders.push(checkbox.closest('tr').querySelector('td:nth-child(2) a').textContent);
        });

        if (selectedOrders.length === 0) {
            alert('Vui lòng chọn đơn hàng.');
            return;
        }

        fetch('/order/cancel', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(selectedOrders)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                window.location.href = '/order';
            })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
            });
    });
});
