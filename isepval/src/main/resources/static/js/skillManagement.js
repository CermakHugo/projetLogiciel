
document.addEventListener('DOMContentLoaded', (event) => {
    loadCategoryList();
});

function loadCategoryList() {
    $.ajax({
        url: '/admin/skillManagement/loadAllCategory',
        method: 'GET',
        success: function(data) {
            $('#categoryList').html(data);
            $('#subCategoryList').html("");
            $('#skillList').html("");
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function loadSubCategoryList(categoryId) {
    $.ajax({
        url: '/admin/skillManagement/loadAllSubCategory/' + categoryId,
        method: 'GET',
        success: function(data) {
            $('#subCategoryList').html(data);
            $('#skillList').html("");
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function loadSkillList(subCategoryId) {
    $.ajax({
        url: '/admin/skillManagement/loadAllSkill/' + subCategoryId,
        method: 'GET',
        success: function(data) {
            $('#skillList').html(data);
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function deleteSkill(skillId) {
    $.ajax({
        url: '/admin/skillManagement/deleteSkill/' + skillId,
        method: 'GET',
        success: function(data) {
            $('#skillList').html(data);
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function deleteSubCategory(subCategoryId) {
    $.ajax({
        url: '/admin/skillManagement/deleteSubCategory/' +subCategoryId,
        method: 'GET',
        success: function(data) {
            $('#subCategoryList').html(data);
            $('#skillList').html("");
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function deleteCategory(categoryId) {
    $.ajax({
        url: '/admin/skillManagement/deleteCategory/' + categoryId,
        method: 'GET',
        success: function(data) {
            $('#categoryList').html(data);
            $('#subCategoryList').html("");
            $('#skillList').html("");
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function addSkill(subCategoryId) {
    var data = $("#createSkillForm").serialize();
    closeModal();
    $.ajax({
        url: '/admin/skillManagement/createSkill',
        method: 'GET',
        data: data,
        success: function(data) {
            $('#skillList').html(data);
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function addSubCategory() {
    var data = $("#createSubCategoryForm").serialize();
    closeModal();
    $.ajax({
       url: '/admin/skillManagement/createSubCategory',
        method: 'GET',
        data: data,
        success: function(data) {
            $('#subCategoryList').html(data);
        },
        error: function() {
            alert('Error loading the form');
        }
    });
}

function addCategory() {
    var data = $("#createCategoryForm").serialize();
    closeModal();
    $.ajax({
        url: '/admin/skillManagement/createCategory',
        method: 'GET',
        data: data,
        success: function(response) {

            $('#categoryList').html(response);
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




