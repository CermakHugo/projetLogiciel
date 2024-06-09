
document.addEventListener('DOMContentLoaded', (event) => {
    loadStudentList();
    loadGroupList();
});

function loadGroupList() {
    $.ajax({
        url: '/teacher/studentManagement/loadAllGroup',
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

function findGroup() {
    var data = $("#findGroupForm").serialize();
    closeModal();
    $.ajax({
        url: '/teacher/studentManagement/findGroup',
        method: 'POST',
        success: function(data) {
            $('#groupList').html(data);
            $('#studentSkill').html("");
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function findStudent() {
    var data = $("#findStudentForm").serialize();
    closeModal();
    $.ajax({
        url: '/teacher/studentManagement/findStudent',
        method: 'POST',
        success: function(data) {
            $('#studentList').html(data);
            $('#studentSkill').html("");
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function loadStudentList() {
    $.ajax({
        url: '/teacher/studentManagement/loadAllStudent',
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
        url: '/teacher/studentManagement/loadAllGroupStudent/' + groupId,
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

function loadStudentSkill(studentId) {
    $.ajax({
        url: '/teacher/studentManagement/loadStudentSkill/' + studentId,
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
        url: '/teacher/studentManagement/loadStudentSkill',
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
            url: "/teacher/studentManagement/updateStudentSkill",
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





