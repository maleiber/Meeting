<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>预定房间</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href="../css/resver.css" rel="stylesheet" type="text/css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/i18n/defaults-*.min.js"></script>

    <script type="text/javascript" src="../js/resver.js"></script>
</head>
<body onload="body_load()">
<br><br>
<div class="demo form-bg">
    <div class="demo form-bg" style="padding: 20px 0;">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-2 col-md-8">
                    <form class="form-horizontal">
                        <span class="heading">会议信息</span>
                        <div class="form-group">
                            <label class="col-xs-2 control-label">会议名称：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputUserName" placeholder="例如：201">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">预参加人数：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="inputUserZhangName" placeholder="例如：第一会议室">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">预开始时间：</label>
                            <div class="col-sm-8">
                                <input type="date" class="form-control" id="inputStartDate">
                                <br><br>
                                <input type="time" class="form-control" id="inputStartTime">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">预结束时间：</label>
                            <div class="col-sm-8">
                                <input type="date" class="form-control" id="inputEndDate">
                                <br><br>
                                <input type="time" class="form-control" id="inputEndTime">
                            </div>
                        </div>
                        <div class="form-group">
                        <label class="col-xs-3 control-label">会议室名称：</label>
                        <div class="col-sm-8">
                            <select class="form-control">
                                <option value="0">第一会议室</option>
                                <option value="1">第二会议室</option>
                                <option value="2">第三会议室</option>
                            </select>
                        </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 control-label">会议说明：</label>
                            <div class="col-sm-8">
                                <textarea class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <table class="formtable">
                                <tr>
                                    <label class="col-xs-3 control-label">参会人员：</label>
                                    <td>
                                        <div class="col-xs-6 col-md-2" id="divfrom">
                                            <select class="" id="selDepartments" onchange="fillEmployees()">
                                            </select>
                                            <select  id="selEmployees" multiple="true">
                                            </select>
                                        </div>
                                        <div class="col-xs-7 col-md-1" id="divoperator">
                                            <input type="button" class="btn" value="&gt;" onclick="selectEmployees()"/>
                                            <input type="button" class="btn" value="&lt;" onclick="deSelectEmployees()"/>
                                        </div>
                                        <div class="col-xs-9 col-md-2" id="divto">
                                            <select id="selSelectedEmployees" multiple="true">
                                            </select>
                                        </div>
                                    </td>
                                </tr>
                            </table>
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