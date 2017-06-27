<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>增加会议室</title>
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
                        <span class="heading">会议室信息</span>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">门牌号：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputUserName" placeholder="例如：201">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">会议室名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputUserZhangName" placeholder="例如：第一会议室">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">容纳人数：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputPassword" placeholder="填写一个整数">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">当前状态：</label>
                            <div class="col-sm-8">
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1">启用
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                    <label><input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">停用
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                    <label>  <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">删除
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">备注：</label>
                            <div class="col-sm-8">
                            <textarea class="form-control" rows="3"></textarea>
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