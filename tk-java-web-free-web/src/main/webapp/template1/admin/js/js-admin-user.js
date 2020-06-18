$(document).ready(function(){
    var table = $('#example').DataTable( {
        "lengthChange": false,
        "pageLength": 12,
        "autoWidth" : false,
        "info": false,
        "paging": false,
        scrollY: '60vh',
        scrollCollapse: true,
    });

    $('.dataTables_filter input').prop("placeholder", "Search");

    $('#pagination-user').twbsPagination({
        totalPages: 10,
        visiblePages: 5,
        onPageClick: function (event, page) {
        }
    });
});

$('#form-avatar').change(function(){
    openImage(this, $("#avater-review"));
});

function openImage(input, imageView) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $(imageView).attr('src', reader.result);
        }
        reader.readAsDataURL(input.files[0]);
    } else {
        $(imageView).attr('src', "");
    }
}