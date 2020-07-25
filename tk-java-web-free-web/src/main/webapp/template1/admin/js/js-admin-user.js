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