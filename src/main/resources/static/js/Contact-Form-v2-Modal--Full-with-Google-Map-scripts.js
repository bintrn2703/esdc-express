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
        var modal = document.querySelector('#cancel-order-modal');
        var bootstrapModal = bootstrap.Modal.getInstance(modal);
        bootstrapModal.hide();
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

$(document).ready(function() {
    // Khi click vào nút edit
    $('td .btn-primary').click(function() {


        // Đặt ID vào trường ẩn trong form

        // Lấy dữ liệu từ hàng chứa nút được click
        var id = $(this).data('id');
        var row = $(this).closest('tr');
        var contactName = row.find('td:eq(1)').text();
        var phoneNumber = row.find('td:eq(2)').text();
        var address = row.find('td:eq(3)').text();
        var deliveryInstruction = row.find('td:eq(4)').text();
        var postalCode = row.find('td:eq(5)').text();

        // Đặt giá trị cho các trường input trong modal
        $('#modal-edit-address #edit-id').val(id);
        $('#modal-edit-address #edit-contact-name').val(contactName);
        $('#modal-edit-address #edit-phone-number').val(phoneNumber);
        $('#modal-edit-address #edit-address').val(address);
        $('#modal-edit-address #edit-delivery-instruction').val(deliveryInstruction);
        $('#modal-edit-address #edit-postal-code').val(postalCode);

    });
});

$(document).ready(function() {
    // Khi click vào nút delete
    $('.btn-delete').click(function() {
        // Hiển thị modal xác nhận xóa
        $('#deleteModal').modal('show');
    });
    $('#cancelDelete').click(function() {
        $('#deleteModal').modal('hide');
    });
    // Khi click vào nút "Yes" trong modal
    $('#confirmDelete').click(function() {
        // Lấy số điện thoại từ các hàng được chọn
        var selectedPhones = [];
        $('input[name="checkbox"]:checked').each(function() {
            var row = $(this).closest('tr');
            var phoneNumber = row.find('td:eq(2)').text();
            selectedPhones.push(phoneNumber);
        });
        if(selectedPhones.length === 0) {
            alert('Vui lòng chọn ít nhất một địa chỉ.');
            return;
        }
        // if(selectedPhones.length === 2) {
        //     alert('Không thể xóa nhiều hơn 1 địa chỉ cùng lúc.');
        //     return;
        // }

        // Gửi yêu cầu xóa đến controller
        $.ajax({
            url: '/delete-addresses',
            type: 'POST',
            data: JSON.stringify(selectedPhones),
            contentType: 'application/json; charset=utf-8',
            success: function(response) {
                // Xử lý khi xóa thành công
                $('#deleteModal').modal('hide');
                location.reload();
            },
            error: function(error) {
                // Xử lý khi có lỗi xảy ra
                console.error(error);
            }
        });
    });
});
