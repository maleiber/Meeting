function myreset() {
    $("#defaultForm").data('bootstrapValidator').resetForm();
}

function mysubmit() {
    var bootstrapValidator = $('#defaultForm').data('bootstrapValidator');
    bootstrapValidator.validate();
    if (bootstrapValidator.isValid()) {
        //表单提交的方法、比如ajax提交
        $.ajax(
            {
                type: "post",
                url: "jsp/Regist.jsp",
                data: {
                    username: $("#username").val(),
                    password: hex_md5($("#password").val()),
                    name: $("#name").val(),
                    telephone: $("#telephone").val(),
                    email: $("#email").val(),
                    departmentid: $("#departmentid").val(),
                    position: $("#position").val()
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("error!");
                    alert(textStatus);
                    alert(errorThrown);

                },
                success: function () {
                    alert("注册成功");
                    location.reload(true);
                }
            }
        );
    }
    return false;

}


var getdata;
$(document).on("change", 'select#position', function () {
    var thischoice = $(this).val();
    $.each(getdata, function (i) {
        if (thischoice == getdata[i].departmentName) {
            document.getElementById('departmentid').value = getdata[i].departmentId;
        }
    })
});
$(function () {
    $.ajax({
        type: "POST",
        url: "jsp/ShowAllDepartment.jsp",
        cache: false,
        dataType: 'json',
        success: function (data) {
            getdata = data;
            $.each(data, function (i) {
                $('#position').append("<option>" + data[i].departmentName + "</option>");
            });
            document.getElementById('departmentid').value = data[0].departmentId;
            $('#position').selectpicker('refresh');
        },
        error: function () {
            alert("error");

        }

    })
});


$(document).ready(function () {
    $('#defaultForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    },
                    regexp: {
                        regexp: /^[\u4e00-\u9fa5a-zA-Z0-9\.]+$/,
                        message: '请勿使用特殊字符'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: '请输入2-10个字符'
                    }

                }
            },
            username: {
                threshold:6,
                trigger:'blur',
                message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: '账户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '账号名长度在6-20之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '请勿使用特殊字符'
                    },

                    remote: {
                        url: 'jsp/checkUsername.jsp',
                        message: '账户名已存在',
                        data:function (validator) {
                            return{
                                username:$('#username').val()
                            };
                        },
                    },
                }
            },
            email: {
                validators: {
                    emailAddress: {
                        message: '请输入有效的邮箱地址'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能与用户名相同'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '请勿使用特殊字符'
                    },
                    stringLength: {
                        min: 6,
                        max: 32,
                        message: '请输入6-32个字符'
                    },
                    callback: {
                        message: '',
                        callback: function (value, validator, $field) {
                            if (value === '') {
                                return true;
                            }

                            // The password doesn't contain any uppercase character
                            if (value === value.toLowerCase()) {
                                return {
                                    valid: false,
                                    message: '必须包含大写字母'
                                }
                            }

                            // The password doesn't contain any uppercase character
                            if (value === value.toUpperCase()) {
                                return {
                                    valid: false,
                                    message: '必须包含小写字母'
                                }
                            }

                            // The password doesn't contain any digit
                            if (value.search(/[0-9]/) < 0) {
                                return {
                                    valid: false,
                                    message: '必须包含数字'
                                }
                            }

                            return true;
                        }
                    }

                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '密码不相同'
                    },
                }
            },
            age: {
                validators: {
                    notEmpty: {},
                    digits: {},
                    greaterThan: {
                        value: 18
                    },
                    lessThan: {
                        value: 100
                    }
                }
            },
            website: {
                validators: {
                    notEmpty: {},
                    uri: {}
                }
            },
            telephone: {
                validators: {
                    digits: {
                        message: "请输入11位整数"
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: "请输入11位整数"

                    },
                }
            },
            gender: {
                validators: {
                    notEmpty: {}
                }
            },
            'languages[]': {
                validators: {
                    notEmpty: {}
                }
            },
            'programs[]': {
                validators: {
                    choice: {
                        min: 2,
                        max: 4
                    }
                }
            }
        }
    });
});

