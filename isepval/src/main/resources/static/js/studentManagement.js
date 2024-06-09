
document.addEventListener('DOMContentLoaded', (event) => {
    loadStudentList();
    loadGroupList();
});

function loadGroupList() {
    $.ajax({
        url: '/admin/studentManagement/loadAllGroup',
        method: 'GET',
        success: function(data) {
            $('#groupList').html(data);
            $('#studentSkill').html("");
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function addGroup() {
    var data = $("#createGroupForm").serialize();
    closeModal();
    $.ajax({
       url: '/admin/studentManagement/createGroup',
        method: 'POST',
        data: data,
        success: function(response) {
            $('#groupList').html(response);
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function deleteGroup(groupId) {
    $.ajax({
        url: '/admin/studentManagement/deleteGroup/' + groupId,
        method: 'GET',
        success: function(response) {
            $('#groupList').html(response);
            loadStudentList();
            $('#skillList').html("");
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function loadStudentList() {
    $.ajax({
        url: '/admin/studentManagement/loadAllStudent',
        method: 'GET',
        success: function(data) {
            $('#studentList').html(data);
            $('#studentSkill').html("");
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function loadGroupStudentList(groupId) {
    $.ajax({
        url: '/admin/studentManagement/loadAllGroupStudent/' + groupId,
        method: 'GET',
        success: function(data) {
            $('#studentList').html(data);
            $('#studentSkill').html("");
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function addStudent() {
    var data = $("#createStudentForm").serialize();
    closeModal();
    $.ajax({
       url: '/admin/studentManagement/createStudent',
        method: 'POST',
        data: data,
        success: function(response) {
            $('#studentList').html(response);
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function deleteStudent(studentId) {
    $.ajax({
        url: '/admin/studentManagement/deleteStudent/' + studentId,
        method: 'GET',
        success: function(response) {
            $('#studentList').html(response);
            $('#skillList').html("");
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function loadStudentSkill(studentId) {
    $.ajax({
        url: '/admin/studentManagement/loadStudentSkill/' + studentId,
        method: 'GET',
        success: function(data) {
            $('#studentSkill').html(data);
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function loadStudentSkillForSubCategory(subCategoryId) {
    var studentId = $('#currentStudent').val();

    var formData = new FormData();
    formData.append("studentId", studentId);
    formData.append("subCategoryId", subCategoryId);

    $.ajax({
        url: '/admin/studentManagement/loadStudentSkill',
        method: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: function(data) {
            var divId = "#studentSkillForSubCategory" + subCategoryId
            $(divId).html(data);
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function updateStudentSkillForSubCategory(markSelect, studentSkillId) {
    var formData = new FormData();
    formData.append("mark", markSelect.value);
    formData.append("studentSkillId", studentSkillId);
        $.ajax({
            url: "/admin/studentManagement/updateStudentSkill",
            method: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function(data) {},
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




