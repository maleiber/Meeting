<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/bootstrap-table.min.css">
    <link rel="stylesheet" href="../css/button_style.css">

</head>
<body>
<div>
    <br><br><br>
    <p style="text-align:center;font-size: larger" class=" col-xs-2 ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;会议预定->搜索会议</p>
    <br>
    <div class="container">
        <div class="panel-body" style="padding-bottom:0px;padding-left: 0px;padding-right: 0px">
            <div class="panel panel-default">
                <div class="panel-heading">搜索会议</div>
                <div class="panel-body">
                    <form id="formSearch" class="form-horizontal">
                        <div class="form-group" style="margin-top:15px">
                            <label style="width: 45px" class="control-label " for="txt_search_departmentname">姓名</label>
                            <div class="col-xs-3">
                                <input type="text" class="form-control" id="txt_search_departmentname">
                            </div>
                            <label style="width: 100px" class="control-label col-xs-1"
                                   for="txt_search_statu">账号名</label>
                            <div class="col-xs-3">
                                <input type="text" class="form-control" id="txt_search_statu">
                            </div>
                            <label style="width: 100px" class="control-label col-xs-1">状态</label>


                            <div class="col-xs-3" style="margin-top:5px">
                                <label>
                                    <input style="margin:0 2px 0 10px" type="radio" name="gender" value="pass"/> 已批准
                                </label>
                                <label>
                                    <input style="margin:0 2px 0 20px" type="radio" name="gender" value="wait"/> 待审批
                                </label>
                                <label>
                                    <input style="margin:0 2px 0 20px" type="radio" name="gender" value="close"/> 已关闭
                                </label>
                            </div>
                        </div>
                        <div>
                            <div class="col-xs-offset-5 col-xs-3" style="text-align:left;">

                                <button  type="button" class="col-xs-4 nooutline btn btn-default  btn-sm" >查询</button>
                                <button  type="button" class="col-xs-4 col-xs-offset-1 otherstyle nooutline btn btn-default  btn-sm" >重置</button>

                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <p style="text-align:center;font-weight:bold;font-size: larger" class="col-xs-12 ">我预定的会议</p>
    <br>
</div>

<div class="container">
    <table id="table"
           class="table table-striped"
           data-toggle="table"
           data-url="data/data1.json"
           data-show-columns="true"
           data-search="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-pagination="true"
           data-cache="false"
           data-height="517"
           col
    >
        <thead>
        <tr>
            <th data-field="meetingname" data-formatter="idFormatter">会议名称</th>
            <th data-field="roomname">会议室名称</th>
            <th data-field="begintime">会议开始时间</th>
            <th data-field="endtime">会议结束时间</th>
            <th data-field="resvertime">会议预定时间</th>
            <th data-field="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<!--<script src="assets/bootstrap2.3/js/bootstrap.min.js"></script>-->
<script src="../js/jquery.base64.js"></script>
<script src="../js/bootstrap-table.js"></script>
<script src="../js/bootstrap-table-export.js"></script>


</body>
</html>
