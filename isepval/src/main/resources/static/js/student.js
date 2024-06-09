
document.addEventListener('DOMContentLoaded', (event) => {
    loadStudentSkill();
});


function loadStudentSkill() {
    $.ajax({
        url: '/student/loadStudentSkill',
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
    var formData = new FormData();
    formData.append("subCategoryId", subCategoryId);

    $.ajax({
        url: '/student/loadStudentSkill',
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





