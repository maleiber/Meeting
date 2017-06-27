<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册员工</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="../css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<br><br>
<div class="demo form-bg">
    <div class="demo form-bg" style="padding: 20px 0;">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal">
                        <span class="heading">员工注册</span>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">姓名：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputUserName" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">账户名：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputUserZhangName" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">密码：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputPassword" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">确认密码：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputSamePassword" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">联系电话：</label>
                            <div class="col-sm-8">
                                <input type="tel" class="form-control" id="inputTele" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">电子邮件：</label>
                            <div class="col-sm-8">
                                <input type="email" class="form-control" id="inputEmail" placeholder="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">所在部门：</label>
                            <div class="col-sm-8">
                                <select class="form-control">
                                    <option value="0">技术部</option>
                                    <option value="1">财务部</option>
                                    <option value="2">人事部</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-offset-3 col-md-7">
                            <button type="submit" class="btn col-md-offset3 ">提交</button>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label"></label>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="related">
        <br>
    </div>
</div>

</body>
</html>