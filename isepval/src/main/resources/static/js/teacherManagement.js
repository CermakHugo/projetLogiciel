
document.addEventListener('DOMContentLoaded', (event) => {
    loadTeacherList();
});

function loadTeacherList() {
    $.ajax({
        url: '/admin/teacherManagement/loadAllTeacher',
        method: 'GET',
        success: function(data) {
            $('#teacherList').html(data);
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function addTeacher() {
    var data = $("#createTeacherForm").serialize();
    closeModal();
    $.ajax({
       url: '/admin/teacherManagement/createTeacher',
        method: 'POST',
        data: data,
        success: function(response) {
            $('#teacherList').html(response);
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function deleteTeacher(teacherId) {
    $.ajax({
        url: '/admin/teacherManagement/deleteTeacher/' + teacherId,
        method: 'GET',
        success: function(response) {
            $('#teacherList').html(response);
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}


function closeModal(){
    $(".modal").on('hide.bs.modal', function (e) {
        $(document.body).removeClass('modal-open');
        $('.modal-backdrop').remove();
    });
    $(".modal").modal("hide");
}




